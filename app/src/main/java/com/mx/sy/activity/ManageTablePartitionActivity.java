package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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
import com.mx.sy.adapter.ManageTablePartitionAdapter;
import com.mx.sy.api.ApiConfig;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.common.RecyclerViewDivider;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageTablePartitionActivity extends BaseActivity {

    private LinearLayout ll_back;
    private TextView tv_title;
    private LinearLayout ll_right;
    private ImageView iv_icon;
    private RecyclerView rv_ma_tablepartition;
    private List<HashMap<String, String>> mapList;
    private AlertDialog alertDialog;
    private ManageTablePartitionAdapter manageEmployeeAdapter;
    private SharedPreferences preferences;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_right:
                LayoutInflater factory = LayoutInflater
                        .from(this);
                final View textEntryView = factory
                        .inflate(
                                R.layout.jian_food_dialog,
                                null);
                final EditText textjianshao = textEntryView
                        .findViewById(R.id.text_editjiannumbe);
                textjianshao.setHint("请输入分区名");
                AlertDialog.Builder ad1 = new AlertDialog.Builder(
                        this);
                ad1.setTitle("增加分区");
                ad1.setIcon(R.drawable.ic_launcher);
                ad1.setView(textEntryView);
                ad1.setPositiveButton(
                        "保存",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(
                                    DialogInterface dialog,
                                    int i) {
                                String number = textjianshao
                                        .getText()
                                        .toString();
                                if (number.equals("")) {
                                    Toast.makeText(ManageTablePartitionActivity.this, "请填写分区名", Toast.LENGTH_SHORT)
                                            .show();
                                }else {
                                    addArea(number);
                                }
                            }
                        });
                ad1.setNegativeButton(
                        "关闭",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(
                                    DialogInterface dialog,
                                    int i) {

                            }
                        });
                ad1.show();// 显示对话框
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
        return R.layout.activity_manage_tablepartition;
    }

    @Override
    public void initView(View view) {
        ll_back = $(R.id.ll_back);
        tv_title = $(R.id.tv_title);
        ll_right = $(R.id.ll_right);
        iv_icon = $(R.id.iv_icon);
        rv_ma_tablepartition = $(R.id.rv_ma_tablepartition);
    }

    @Override
    protected void initdata() {
        tv_title.setText("桌台分区管理");
        iv_icon.setImageResource(R.drawable.ic_add);
        rv_ma_tablepartition.setLayoutManager(new LinearLayoutManager(this));
        rv_ma_tablepartition.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));

        mapList = new ArrayList<>();
        manageEmployeeAdapter = new ManageTablePartitionAdapter(R.layout.item_tablepartition, mapList);
        rv_ma_tablepartition.setAdapter(manageEmployeeAdapter);

        manageEmployeeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                final String[] items = {"修改", "删除"};
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageTablePartitionActivity.this);
                alertBuilder.setTitle("请选择操作");
                alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        alertDialog.dismiss();
                        if (index == 0) {
                            LayoutInflater factory = LayoutInflater
                                    .from(ManageTablePartitionActivity.this);
                            final View textEntryView = factory
                                    .inflate(
                                            R.layout.jian_food_dialog,
                                            null);
                            final EditText textjianshao = textEntryView
                                    .findViewById(R.id.text_editjiannumbe);
                            textjianshao.setText(mapList.get(position).get("area_name"));
                            AlertDialog.Builder ad1 = new AlertDialog.Builder(
                                    ManageTablePartitionActivity.this);
                            ad1.setTitle("增加分区");
                            ad1.setIcon(R.drawable.ic_launcher);
                            ad1.setView(textEntryView);
                            ad1.setPositiveButton(
                                    "保存",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int i) {
                                            String number = textjianshao
                                                    .getText()
                                                    .toString();
                                            if (number.equals("")) {
                                                Toast.makeText(ManageTablePartitionActivity.this, "请填写分区名", Toast.LENGTH_SHORT)
                                                        .show();
                                            }else {
                                                updateAreaArea(number,mapList.get(position).get("area_id"));
                                            }
                                        }
                                    });
                            ad1.setNegativeButton(
                                    "关闭",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int i) {

                                        }
                                    });
                            ad1.show();// 显示对话框
                        } else {
                            deleteAral(mapList.get(position).get("area_id"));
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
        getTableInfo();
    }

    // 查询分区(包含桌台) /tableservice/getTableInfo 从接口获得
    public void getTableInfo() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.GETTABLEINFO_URL;
        RequestParams params = new RequestParams();
        params.put("shopid", preferences.getString("shop_id", ""));
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
                            JSONArray jsonArray = new JSONArray(jsonObject
                                    .getString("DATA"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String area_id = object.getString("area_id");
                                String area_name = object
                                        .getString("area_name");
                                String create_time = object.getString("create_time");
                                HashMap<String, String> map = new HashMap<String, String>();
                                map.put("area_id", area_id);
                                map.put("area_name", area_name);
                                map.put("create_time", create_time);
                                mapList.add(map);
                            }
                            manageEmployeeAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(ManageTablePartitionActivity.this,
                                    jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Toast.makeText(ManageTablePartitionActivity.this, "服务器异常",
                                Toast.LENGTH_SHORT).show();
                        dissmissDilog();
                    }
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(ManageTablePartitionActivity.this, "服务器异常", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    // 添加分区
    public void addArea(String area_name) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.INSERTAREA;
        RequestParams params = new RequestParams();
        params.put("area_name", area_name);
        params.put("shop_id", preferences.getString("shop_id", ""));
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
                            getTableInfo();
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
    // 修改分区
    public void updateAreaArea(String area_name,String area_id) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.UNDATEAREA;
        RequestParams params = new RequestParams();
        params.put("area_name", area_name);
        params.put("area_id",area_id);
        params.put("shop_id", preferences.getString("shop_id", ""));
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
                            getTableInfo();
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
    // 删除
    public void deleteAral(String area_id) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.DELAREA;
        RequestParams params = new RequestParams();
        params.put("area_id", area_id);
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
                            getTableInfo();
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
