package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mx.sy.R;
import com.mx.sy.adapter.DishesSelectClassAdapter;
import com.mx.sy.api.ApiConfig;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.common.RecyclerViewDivider;
import com.mx.sy.dialog.SweetAlertDialog;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageTableAddActivity extends BaseActivity {

    private LinearLayout ll_back;
    private TextView tv_title;
    private Button select_table_partition;
    private Button btn_sumbit;
    private AlertDialog alertDialog;
    private EditText et_table_name, et_table_number, et_phone;

    private List<HashMap<String, String>> classList;
    View view;
    private DishesSelectClassAdapter dishesSelectClassAdapter;
    private RecyclerView mrv_dialog;

    private SharedPreferences preferences;

    private String airid = "";
    private String pagetype;
    private String table_name;
    private String people_count;
    private String area_name;
    private String table_id;


    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_sumbit:
                if (et_table_name.getText().toString().equals("")) {
                    Toast.makeText(ManageTableAddActivity.this, "请输入桌台名称", Toast.LENGTH_SHORT).show();
                } else if (et_table_number.getText().toString().equals("")) {
                    Toast.makeText(ManageTableAddActivity.this, "请输入桌台容纳人数", Toast.LENGTH_SHORT).show();
                } else if (airid.equals("")) {
                    Toast.makeText(ManageTableAddActivity.this, "请选择分区", Toast.LENGTH_SHORT).show();
                } else {
                    new SweetAlertDialog(this,
                            SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("确定要提交信息吗？")
                            .setCancelText("取消")
                            .setConfirmText("确定")
                            .showCancelButton(true)
                            .setConfirmClickListener(
                                    new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            if (pagetype.equals("1")) {
                                                addTable();
                                            } else {
                                                uploadTable();
                                            }
                                        }
                                    })
                            .setCancelClickListener(
                                    new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.cancel();
                                        }
                                    }).show();
                }
                break;
            case R.id.select_table_partition:
                view = getLayoutInflater().inflate(R.layout.dialog_rv, null);
                AlertDialog.Builder alertBuilder1 = new AlertDialog.Builder(ManageTableAddActivity.this);
                alertBuilder1.setTitle("请选择分类");
                alertBuilder1.setView(view);
                alertDialog = alertBuilder1.create();


                mrv_dialog = view.findViewById(R.id.rv_dialog);
                mrv_dialog.setLayoutManager(new LinearLayoutManager(this));
                mrv_dialog.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));

                dishesSelectClassAdapter = new DishesSelectClassAdapter(R.layout
                        .item_disclass, classList);
                mrv_dialog.setAdapter(dishesSelectClassAdapter);
                dishesSelectClassAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        select_table_partition.setText(classList.get(position).get("classname"));
                        airid = classList.get(position).get("area_id");
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void initParms(Bundle parms) {
        preferences = getSharedPreferences("userinfo",
                LoginActivity.MODE_PRIVATE);
        pagetype = getIntent().getStringExtra("pagetype");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_manage_tableadd;
    }

    @Override
    public void initView(View view) {
        ll_back = $(R.id.ll_back);
        tv_title = $(R.id.tv_title);
        select_table_partition = $(R.id.select_table_partition);
        btn_sumbit = $(R.id.btn_sumbit);
        et_table_name = $(R.id.et_table_name);
        et_table_number = $(R.id.et_table_number);

        if (pagetype.equals("1")) {
            //添加

        } else {
            //修改
            table_id = getIntent().getStringExtra("table_id");
            getOneTable();
        }
    }

    @Override
    protected void initdata() {
        tv_title.setText("新增桌台");
        classList = new ArrayList<>();
    }

    @Override
    public void setListener() {
        ll_back.setOnClickListener(this);
        select_table_partition.setOnClickListener(this);
        btn_sumbit.setOnClickListener(this);
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
                        com.orhanobut.logger.Logger.d(response);
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
                                map.put("classname", area_name);
                                map.put("create_time", create_time);
                                classList.add(map);
                            }
                            dishesSelectClassAdapter.notifyDataSetChanged();
                        }

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        dissmissDilog();
                    }
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
            }
        });
    }

    // 添加桌台
    public void addTable() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.ADDTABLE;
        RequestParams params = new RequestParams();
        params.put("table_name", et_table_name.getText().toString());
        params.put("people_count", et_table_number.getText().toString());
        params.put("area_id", airid);
        params.put("shop_id", preferences.getString("shop_id", ""));
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        com.orhanobut.logger.Logger.d(response);
                        JSONObject jsonObject = new JSONObject(response);
                        String CODE = jsonObject.getString("CODE");
                        if (CODE.equals("1000")) {
                            Toast.makeText(getApplicationContext(),
                                    jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                            finish();
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

    // 修改桌台
    public void uploadTable() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.UPDATETABLE;
        RequestParams params = new RequestParams();
        params.put("table_name", et_table_name.getText().toString());
        params.put("people_count", et_table_number.getText().toString());
        params.put("area_id", airid);
        params.put("shop_id", preferences.getString("shop_id", ""));
        params.put("table_id", table_id);
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        com.orhanobut.logger.Logger.d(response);
                        JSONObject jsonObject = new JSONObject(response);
                        String CODE = jsonObject.getString("CODE");
                        if (CODE.equals("1000")) {
                            Toast.makeText(getApplicationContext(),
                                    jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                            finish();
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

    // 查询桌台
    public void getOneTable() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.GETONETABLE + table_id;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        com.orhanobut.logger.Logger.d(response);
                        JSONObject jsonObject = new JSONObject(response);
                        String CODE = jsonObject.getString("CODE");
                        if (CODE.equals("1000")) {
                            JSONObject jsonObject1 = new JSONObject(jsonObject
                                    .getString("DATA"));

                            et_table_name.setText(jsonObject1.getString("table_name"));
                            et_table_number.setText(jsonObject1.getString("people_count"));
                            airid = jsonObject1.getString("area_id");
//            select_table_partition.setText(area_name);
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
