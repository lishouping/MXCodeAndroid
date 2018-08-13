package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
import com.mx.sy.adapter.ManagePrintAdapter;
import com.mx.sy.api.ApiConfig;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.common.RecyclerViewDivider;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>Title: AboutUsActivity<／p>
 * <p>Description: 打印机设置<／p>
 * <p>Company: LTGames<／p>
 *
 * @author lishp
 * @date 2017年7月23日
 */
public class PrinterSeetingActivity extends BaseActivity {
    private LinearLayout ll_back;
    private TextView tv_title;
    private LinearLayout ll_right;
    private ImageView iv_icon;
    private RecyclerView rv_print;
    private ManagePrintAdapter managePrintAdapter;
    private List<HashMap<String, String>> mapList;
    private SharedPreferences preferences;
    private AlertDialog alertDialog;

    @Override
    public void widgetClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_right:
                Intent intent = new Intent(getApplicationContext(), PrintAddActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void initParms(Bundle parms) {
        // TODO Auto-generated method stub

    }

    @Override
    public View bindView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int bindLayout() {
        // TODO Auto-generated method stub
        return R.layout.activity_printerseeting;
    }

    @Override
    public void initView(View view) {
        // TODO Auto-generated method stub
        ll_back = $(R.id.ll_back);
        tv_title = $(R.id.tv_title);
        ll_right = $(R.id.ll_right);
        iv_icon = $(R.id.iv_icon);
        iv_icon.setImageResource(R.drawable.ic_add);
        ll_right.setVisibility(View.VISIBLE);
        rv_print = $(R.id.rv_print);
    }

    @Override
    protected void initdata() {
        // TODO Auto-generated method stub
        tv_title.setText("打印机管理");

        rv_print.setLayoutManager(new LinearLayoutManager(this));
        rv_print.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));
        mapList = new ArrayList<>();
        managePrintAdapter = new ManagePrintAdapter(R.layout.item_print, mapList);
        rv_print.setAdapter(managePrintAdapter);
        managePrintAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                final String[] items = new String[]{"修改", "删除"};
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(PrinterSeetingActivity.this);
                alertBuilder.setTitle("请选择操作");
                alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        alertDialog.dismiss();
                        if (index == 0) {


                            LayoutInflater factory = LayoutInflater
                                    .from(PrinterSeetingActivity.this);
                            final View textEntryView = factory
                                    .inflate(
                                            R.layout.jian_food_dialog,
                                            null);
                            final EditText textjianshao = textEntryView
                                    .findViewById(R.id.text_editjiannumbe);
                            final EditText textjianshao1 = textEntryView
                                    .findViewById(R.id.text_edit1);
                            textjianshao.setHint("请输入打印机名称");
                            textjianshao1.setVisibility(View.VISIBLE);
                            textjianshao1.setHint("请输入打印份数");
                            textjianshao.setText(mapList.get(position).get("printer_name"));
                            textjianshao1.setText(mapList.get(position).get("print_num"));
                            AlertDialog.Builder ad1 = new AlertDialog.Builder(
                                    PrinterSeetingActivity.this);
                            ad1.setTitle("编辑打印机");
                            ad1.setIcon(R.drawable.ic_launcher);
                            ad1.setView(textEntryView);
                            ad1.setPositiveButton(
                                    "保存",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int i) {
                                            String printer_id = mapList.get(position).get("printer_id");
                                            String priter_name = textjianshao.getText().toString();
                                            String printer_num = textjianshao1.getText().toString();
                                            if (priter_name.equals("")) {
                                                Toast.makeText(PrinterSeetingActivity.this, "请输入打印机名称", Toast.LENGTH_SHORT).show();
                                            } else if (printer_num.equals("")) {
                                                Toast.makeText(PrinterSeetingActivity.this, "请输入打印份数", Toast.LENGTH_SHORT).show();
                                            } else {
                                                updatePrint(printer_id, priter_name, printer_num);

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


                        } else if (index == 1) {
                            deletePrint(mapList.get(position).get("printer_id"));
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
        // TODO Auto-generated method stub
        ll_back.setOnClickListener(this);
        ll_right.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        // TODO Auto-generated method stub
        preferences = getSharedPreferences("userinfo",
                LoginActivity.MODE_PRIVATE);

    }

    @Override
    protected void onResume() {
        if (mapList.size() > 0) {
            mapList.clear();
            managePrintAdapter.notifyDataSetChanged();
        }
        printerlist();
        super.onResume();
    }

    // 查询打印机列表
    public void printerlist() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.PRINTLIST;
        RequestParams params = new RequestParams();
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
                            JSONArray jsonArray = new JSONArray(jsonObject
                                    .getString("DATA"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String type_print = object
                                        .getString("type_print");
                                String printer_no = object
                                        .getString("printer_no");
                                String printer_name = object.getString("printer_name");
                                HashMap<String, String> map = new HashMap<String, String>();
                                map.put("type_print", type_print);
                                map.put("printer_no", printer_no);
                                map.put("printer_name", printer_name);
                                map.put("print_num", object.getString("print_num"));
                                map.put("printer_id", object.getString("id"));
                                mapList.add(map);
                            }
                            managePrintAdapter.notifyDataSetChanged();
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
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    // 删除打印机
    public void deletePrint(String printer_id) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.PRINTDEL;
        RequestParams params = new RequestParams();
        params.put("shop_id", preferences.getString("shop_id", ""));
        params.put("printer_id", printer_id);
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
                            managePrintAdapter.notifyDataSetChanged();
                            printerlist();
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

    // 修改打印机
    public void updatePrint(String printer_id, String priter_name, String printer_num) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.PRINTUPDATE;
        RequestParams params = new RequestParams();
        params.put("printer_id", printer_id);
        params.put("priter_name", priter_name);
        params.put("printer_num", printer_num);
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
                            managePrintAdapter.notifyDataSetChanged();
                            printerlist();
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
