package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.mx.sy.adapter.ManageTableAdapter;
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

public class ManageTableActivity extends BaseActivity {

    private LinearLayout ll_back;
    private TextView tv_title;
    private LinearLayout ll_right;
    private ImageView iv_icon;

    private RecyclerView rv_ma_table;
    private List<HashMap<String, String>> mapList;
    private AlertDialog alertDialog;
    private ManageTableAdapter manageTableAdapter;

    private SharedPreferences preferences;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_right:
                Intent intent = new Intent(this,ManageTableAddActivity.class);
                intent.putExtra("pagetype","1");
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
        return R.layout.activity_manage_table;
    }

    @Override
    public void initView(View view) {
        ll_back = $(R.id.ll_back);
        tv_title = $(R.id.tv_title);
        ll_right = $(R.id.ll_right);
        iv_icon = $(R.id.iv_icon);
        rv_ma_table = $(R.id.rv_ma_table);
    }

    @Override
    protected void initdata() {
        tv_title.setText("桌台管理");
        iv_icon.setImageResource(R.drawable.ic_add);

        rv_ma_table.setLayoutManager(new LinearLayoutManager(this));
        rv_ma_table.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));

        mapList = new ArrayList<>();
        manageTableAdapter = new ManageTableAdapter(R.layout.item_table, mapList);
        rv_ma_table.setAdapter(manageTableAdapter);

        manageTableAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                final String[] items = {"修改","删除"};
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageTableActivity.this);
                alertBuilder.setTitle("请选择操作");
                alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        alertDialog.dismiss();
                        if (index == 0) {
                            Intent intent = new Intent(ManageTableActivity.this,ManageTableAddActivity.class);
                            intent.putExtra("pagetype","2");
                            intent.putExtra("table_id",mapList.get(position).get("table_id"));
                            startActivity(intent);
                        }else if (index==1){
                            delTable(mapList.get(position).get("table_id"));
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
        if (mapList.size()>0){
            mapList.clear();
            manageTableAdapter.notifyDataSetChanged();
        }
        getTableInfo();
        super.onResume();
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
                                JSONArray array = new JSONArray(object
                                        .getString("table_list"));
                                String area_name = object.getString("area_name");

                                for (int j = 0; j < array.length(); j++) {
                                    JSONObject object2 = array.getJSONObject(j);
                                    String table_name = object2
                                            .getString("table_name");// 餐桌名
                                    String table_status = object2
                                            .getString("table_status");// 餐桌状态
                                    String table_id = object2
                                            .getString("table_id");
                                    String create_time = object2.getString("create_time");

                                    String people_count = object2.getString("people_count");
                                    String area_id = object2.getString("area_id");
                                    HashMap<String, String> map4 = new HashMap<String, String>();
                                    map4.put("table_name", table_name);
                                    map4.put("table_status", table_status);
                                    map4.put("table_id", table_id);
                                    map4.put("create_time",create_time);
                                    map4.put("area_name",area_name);
                                    map4.put("people_count",people_count);
                                    map4.put("area_id",area_id);
                                    mapList.add(map4);
                                }
                            }
                            manageTableAdapter.notifyDataSetChanged();
                            dissmissDilog();
                        } else {
                            Toast.makeText(ManageTableActivity.this,
                                    jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Toast.makeText(ManageTableActivity.this, "服务器异常",
                                Toast.LENGTH_SHORT).show();
                        dissmissDilog();
                    }
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(ManageTableActivity.this, "服务器异常", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
    public void delTable(String table_id) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.DELTABLE;
        RequestParams params = new RequestParams();
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
                            mapList.clear();
                            manageTableAdapter.notifyDataSetChanged();
                            getTableInfo();
                        } else {
                            Toast.makeText(ManageTableActivity.this,
                                    jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Toast.makeText(ManageTableActivity.this, "服务器异常",
                                Toast.LENGTH_SHORT).show();
                        dissmissDilog();
                    }
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(ManageTableActivity.this, "服务器异常", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
