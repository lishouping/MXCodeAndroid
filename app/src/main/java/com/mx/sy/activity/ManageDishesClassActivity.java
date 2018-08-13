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
import com.mx.sy.adapter.ManageDishesClassAdapter;
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

public class ManageDishesClassActivity extends BaseActivity {

    private LinearLayout ll_back;
    private TextView tv_title;
    private LinearLayout ll_right;
    private ImageView iv_icon;
    private RecyclerView rv_ma_dishesclass;
    private List<HashMap<String, String>> mapList;
    private AlertDialog alertDialog;
    private ManageDishesClassAdapter manageDishesClassAdapter;

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
                final EditText textjianshao =  textEntryView
                        .findViewById(R.id.text_editjiannumbe);
                textjianshao.setHint("请输入分类名");
                AlertDialog.Builder ad1 = new AlertDialog.Builder(
                        this);
                ad1.setTitle("增加分类");
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
                                    Toast.makeText(ManageDishesClassActivity.this, "请填写分类名", Toast.LENGTH_SHORT)
                                            .show();
                                }else {
                                    addClass(number);
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
        return R.layout.activity_manage_dishesclass;
    }

    @Override
    public void initView(View view) {
        ll_back = $(R.id.ll_back);
        tv_title = $(R.id.tv_title);
        ll_right = $(R.id.ll_right);
        iv_icon = $(R.id.iv_icon);
        rv_ma_dishesclass = $(R.id.rv_ma_dishesclass);
    }

    @Override
    protected void initdata() {
        tv_title.setText("菜品分类管理");
        iv_icon.setImageResource(R.drawable.ic_add);

        rv_ma_dishesclass.setLayoutManager(new LinearLayoutManager(this));
        rv_ma_dishesclass.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));

        mapList = new ArrayList<>();
        manageDishesClassAdapter = new ManageDishesClassAdapter(R.layout.item_dishesclass, mapList);
        rv_ma_dishesclass.setAdapter(manageDishesClassAdapter);

        manageDishesClassAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                final String[] items;
                if (mapList.get(position).get("category_status").equals("1")){
                    items = new String[]{"下架", "修改", "删除"};
                }else {
                    items = new String[]{"上架", "修改", "删除"};
                }
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageDishesClassActivity.this);
                alertBuilder.setTitle("请选择操作");
                alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        alertDialog.dismiss();
                        if (index == 0) {
                            if (items[0].equals("下架")){
                                updateClass(mapList.get(position).get("category_name"),mapList.get(position).get("category_id"),"2");
                            }else {
                                updateClass(mapList.get(position).get("category_name"),mapList.get(position).get("category_id"),"1");
                            }
                        }else if (index==1){
                            LayoutInflater factory = LayoutInflater
                                    .from(ManageDishesClassActivity.this);
                            final View textEntryView = factory
                                    .inflate(
                                            R.layout.jian_food_dialog,
                                            null);
                            final EditText textjianshao = textEntryView
                                    .findViewById(R.id.text_editjiannumbe);
                            textjianshao.setText(mapList.get(position).get("category_name"));
                            textjianshao.setHint("请输入分类");
                            AlertDialog.Builder ad1 = new AlertDialog.Builder(
                                    ManageDishesClassActivity.this);
                            ad1.setTitle("修改分类");
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
                                                Toast.makeText(ManageDishesClassActivity.this, "请填写分类名", Toast.LENGTH_SHORT)
                                                        .show();
                                            }else {
                                                updateClass(number,mapList.get(position).get("category_id"),mapList.get(position).get("category_status"));
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
                        }else if (index==2){
                            deleteClass(mapList.get(position).get("category_id"));
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
        selectCategory();
    }

    // // 查询菜品分类(包含菜品)
    public void selectCategory() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.SELECTCATEGORY_URL + "/"
                + preferences.getString("shop_id", "");
        RequestParams params = new RequestParams();
        params.put("shop_id", preferences.getString("shop_id", ""));
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
                            dissmissDilog();
                            JSONArray jsonArray = new JSONArray(jsonObject
                                    .getString("DATA"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String category_id = object
                                        .getString("category_id");
                                String category_name = object
                                        .getString("category_name");
                                String category_status = object
                                        .getString("category_status");
                                String create_time = object.getString("create_time");
                                HashMap<String, String> map = new HashMap<String, String>();
                                map.put("category_id", category_id);
                                map.put("category_name", category_name);
                                map.put("category_status", category_status);
                                map.put("create_time",create_time);
                                mapList.add(map);
                            }
                            rv_ma_dishesclass.setAdapter(manageDishesClassAdapter);
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
                        dissmissDilog();
                    }
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "服务器异常",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 添加分类
    public void addClass(String category_name) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.ADDGOODSCATEGORY;
        RequestParams params = new RequestParams();
        params.put("category_name", category_name);
        params.put("category_status","1");
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
                            mapList.clear();
                            manageDishesClassAdapter.notifyDataSetChanged();
                            selectCategory();
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
    public void updateClass(String category_name,String category_id,String category_status) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.UPDATEGOODSCATEGORY;
        RequestParams params = new RequestParams();
        params.put("category_name", category_name);
        params.put("category_id",category_id);
        params.put("category_status",category_status);
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
                            mapList.clear();
                            manageDishesClassAdapter.notifyDataSetChanged();
                            selectCategory();
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
    public void deleteClass(String category_id) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.DELGOODSCATEGORY;
        RequestParams params = new RequestParams();
        params.put("category_id", category_id);
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
                            mapList.clear();
                            manageDishesClassAdapter.notifyDataSetChanged();
                            selectCategory();
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
