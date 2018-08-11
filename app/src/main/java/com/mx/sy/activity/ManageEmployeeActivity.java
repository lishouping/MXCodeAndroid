package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mx.sy.R;
import com.mx.sy.adapter.ManageEmployeeAdapter;
import com.mx.sy.api.ApiConfig;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.common.PullToRefreshView;
import com.mx.sy.common.RecyclerViewDivider;
import com.mx.sy.dialog.PrintOrderDialog;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageEmployeeActivity extends BaseActivity {
    private LinearLayout ll_back;
    private TextView tv_title;
    private LinearLayout ll_right;
    private ImageView iv_icon;
    private RecyclerView rv_ma_employee;

    private ManageEmployeeAdapter manageEmployeeAdapter;
    private List<HashMap<String, String>> mapList;
    private AlertDialog alertDialog;
    private SharedPreferences preferences;

    RefreshLayout mPullToRefreshView;
    int page = 1;
    int totalnum;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_right:
                Intent intent = new Intent(this, ManageEmployeeAddActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void initParms(Bundle parms) {
        preferences = getSharedPreferences("userinfo",
                LoginActivity.MODE_PRIVATE);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_manage_employee;
    }

    @Override
    public void initView(View view) {
        ll_back = $(R.id.ll_back);
        tv_title = $(R.id.tv_title);
        ll_right = $(R.id.ll_right);
        iv_icon = $(R.id.iv_icon);
        rv_ma_employee = $(R.id.rv_ma_employee);

        mPullToRefreshView = findViewById(
                R.id.pullrefresh_emp);
        mPullToRefreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                page = 1;
                mapList.clear();
                selectWaiters();
            }
        });
        mPullToRefreshView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                if (mapList.size() == totalnum) {
                    Toast.makeText(ManageEmployeeActivity.this, "没有更多数据了", Toast.LENGTH_LONG)
                            .show();
                } else {
                    page++;
                    selectWaiters();
                }
            }
        });
    }

    @Override
    protected void initdata() {
        tv_title.setText("员工管理");
        iv_icon.setImageResource(R.drawable.ic_add);
        rv_ma_employee.setLayoutManager(new LinearLayoutManager(this));
        rv_ma_employee.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));

        mapList = new ArrayList<>();
        manageEmployeeAdapter = new ManageEmployeeAdapter(R.layout.item_employee, mapList);
        rv_ma_employee.setAdapter(manageEmployeeAdapter);

        manageEmployeeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                final String[] items;
                final String userStatus;
                if (mapList.get(position).get("user_status").equals("1")) {
                    // 正常
                    items = new String[]{"冻结", "删除", "重置密码"};
                    userStatus = "2";
                } else {
                    // 冻结
                    items = new String[]{"取消冻结", "删除", "重置密码"};
                    userStatus = "1";
                }
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageEmployeeActivity.this);
                alertBuilder.setTitle("请选择操作");
                alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        alertDialog.dismiss();
                        if (index == 0) {
                            changWaiterStatus(mapList.get(position).get("waiter_id"), userStatus);
                        } else if (index == 1) {
                            deleteWaiter(mapList.get(position).get("waiter_id"));
                        } else if (index == 2) {

                        }
                    }
                });
                alertDialog = alertBuilder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public void setListener() {
        ll_back.setOnClickListener(this);
        ll_right.setVisibility(View.VISIBLE);
        ll_right.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    protected void onResume() {
        if (mapList.size() > 0) {
            mapList.clear();
            manageEmployeeAdapter.notifyDataSetChanged();
        }
        selectWaiters();
        super.onResume();
    }

    // 查询服务员列表
    public void selectWaiters() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.GETWAITER;
        RequestParams params = new RequestParams();
        params.put("shop_id", preferences.getString("shop_id", ""));
        params.put("pageNo", page);
        client.post(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        JSONObject jsonObject = new JSONObject(response);
                        String CODE = jsonObject.getString("CODE");
                        totalnum = Integer.parseInt(jsonObject.getString("totalnum"));
                        if (CODE.equals("1000")) {
                            JSONArray jsonArray = new JSONArray(jsonObject
                                    .getString("DATA"));

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String waiter_id = object
                                        .getString("waiter_id");
                                String name = object.getString("name");
                                String username = object.getString("username");
                                String phonenum = object.getString("phonenum");
                                String object1 = object.getString("user");
                                JSONObject jsonObject1 = new JSONObject(object1);

                                String user_status = jsonObject1.getString("user_status");
                                String type = jsonObject1.getString("type");


                                HashMap<String, String> map = new HashMap<String, String>();
                                map.put("name", name);
                                map.put("username", username);
                                map.put("phonenum", phonenum);
                                map.put("waiter_id", waiter_id);
                                map.put("user_status", user_status);
                                map.put("type", type);
                                mapList.add(map);
                            }
                            manageEmployeeAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "服务器异常",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "服务器异常",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    // 修改用户状态 1：正常，2：冻结
    public void changWaiterStatus(String waiter_id, String status) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.CHANGEWAITER;
        RequestParams params = new RequestParams();
        params.put("waiter_id", waiter_id);
        params.put("status", status);
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        JSONObject jsonObject = new JSONObject(response);
                        String CODE = jsonObject.getString("CODE");
                        if (CODE.equals("1000")) {
                            Toast.makeText(getApplicationContext(),
                                    jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                            mapList.clear();
                            manageEmployeeAdapter.notifyDataSetChanged();
                            selectWaiters();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "服务器异常",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "服务器异常",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    // 修改用户状态 1：正常，2：冻结
    public void deleteWaiter(String waiter_id) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.DELWAITER;
        RequestParams params = new RequestParams();
        params.put("waiter_id", waiter_id);
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        JSONObject jsonObject = new JSONObject(response);
                        String CODE = jsonObject.getString("CODE");
                        if (CODE.equals("1000")) {
                            Toast.makeText(getApplicationContext(),
                                    jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                            mapList.clear();
                            manageEmployeeAdapter.notifyDataSetChanged();
                            selectWaiters();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "服务器异常",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "服务器异常",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
