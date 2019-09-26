package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mx.sy.R;
import com.mx.sy.api.ApiConfig;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.dialog.SweetAlertDialog;

import org.apache.http.Header;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class PrintAddActivity extends BaseActivity {

    private LinearLayout ll_back,ll_update;
    private TextView tv_title;
    private Button select_persontype,select_return_goods,select_cook_printtype;
    private Button btn_sumbit;
    private AlertDialog alertDialog;
    private EditText et_print_name, et_print_number, et_print_key, et_print_num;

    private String printType = "";
    private String back_good_if_print;
    private String print_way;

    private SharedPreferences preferences;

    private String printer_id = "";

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_sumbit:
                if (tv_title.getText().equals("新增打印机")){
                    if (et_print_name.getText().toString().equals("")) {
                        Toast.makeText(PrintAddActivity.this, "打印机名称", Toast
                                .LENGTH_SHORT).show();
                    } else if (et_print_number.getText().toString().equals("")) {
                        Toast.makeText(PrintAddActivity.this, "请输入打印机编号", Toast
                                .LENGTH_SHORT).show();
                    } else if (et_print_key.getText().toString().equals("")) {
                        Toast.makeText(PrintAddActivity.this, "请输入打印机KEY", Toast
                                .LENGTH_SHORT).show();
                    } else if (et_print_num.getText().toString().equals("")) {
                        Toast.makeText(PrintAddActivity.this, "请输入份数", Toast
                                .LENGTH_SHORT).show();
                    } else if (printType.equals("")) {
                        Toast.makeText(PrintAddActivity.this, "请选择打印类型", Toast
                                .LENGTH_SHORT).show();
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
                                                if (TextUtils.isEmpty(printer_id)){
                                                    addPrint();
                                                }else {
                                                    updatePrint();
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
                }else {
                    if (et_print_name.getText().toString().equals("")) {
                        Toast.makeText(PrintAddActivity.this, "打印机名称", Toast
                                .LENGTH_SHORT).show();
                    } else if (et_print_num.getText().toString().equals("")) {
                        Toast.makeText(PrintAddActivity.this, "请输入份数", Toast
                                .LENGTH_SHORT).show();
                    } else if (printType.equals("")) {
                        Toast.makeText(PrintAddActivity.this, "请选择打印类型", Toast
                                .LENGTH_SHORT).show();
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
                                                if (TextUtils.isEmpty(printer_id)){
                                                    addPrint();
                                                }else {
                                                    updatePrint();
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
                }

                break;
            case R.id.select_persontype:
                final String[] items = {"后厨", "结账"};
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(PrintAddActivity.this);
                alertBuilder.setTitle("请选择打印类型");
                alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        alertDialog.dismiss();
                        if (index == 0) {
                            select_persontype.setText(items[index]);
                            printType = "1";
                        } else if (index == 1) {
                            select_persontype.setText(items[index]);
                            printType = "2";
                        }
                    }
                });
                alertDialog = alertBuilder.create();
                alertDialog.show();
                break;
            case R.id.select_return_goods:
                final String[] items1 = {"是", "否"};
                AlertDialog.Builder alertBuilder1 = new AlertDialog.Builder(PrintAddActivity.this);
                alertBuilder1.setTitle("退菜是否打印");
                alertBuilder1.setItems(items1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        alertDialog.dismiss();
                        if (index == 0) {
                            select_return_goods.setText(items1[index]);
                            back_good_if_print = "1";
                        } else if (index == 1) {
                            select_return_goods.setText(items1[index]);
                            back_good_if_print = "2";
                        }
                    }
                });
                alertDialog = alertBuilder1.create();
                alertDialog.show();
                break;
            case R.id.select_cook_printtype:
                final String[] items2 = {"一菜一打", "一单一打"};
                AlertDialog.Builder alertBuilder2 = new AlertDialog.Builder(PrintAddActivity.this);
                alertBuilder2.setTitle("后厨打印方式");
                alertBuilder2.setItems(items2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        alertDialog.dismiss();
                        if (index == 0) {
                            select_cook_printtype.setText(items2[index]);
                            print_way = "1";
                        } else if (index == 1) {
                            select_cook_printtype.setText(items2[index]);
                            print_way = "2";
                        }
                    }
                });
                alertDialog = alertBuilder2.create();
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
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_print_add;
    }

    @Override
    public void initView(View view) {
        ll_back = $(R.id.ll_back);
        tv_title = $(R.id.tv_title);
        select_persontype = $(R.id.select_persontype);
        btn_sumbit = $(R.id.btn_sumbit);
        et_print_name = $(R.id.et_print_name);
        et_print_number = $(R.id.et_print_number);
        et_print_key = $(R.id.et_print_key);
        et_print_num = $(R.id.et_print_num);
        select_return_goods = findViewById(R.id.select_return_goods);
        select_return_goods.setOnClickListener(this);
        select_cook_printtype = findViewById(R.id.select_cook_printtype);
        select_cook_printtype.setOnClickListener(this);
        ll_update = findViewById(R.id.ll_update);
    }

    @Override
    protected void initdata() {
        if (TextUtils.isEmpty(getIntent().getStringExtra("printer_id"))){
            tv_title.setText("新增打印机");
        }else {
            ll_update.setVisibility(View.GONE);
            tv_title.setText("修改打印机");
            printer_id = getIntent().getStringExtra("printer_id");
            et_print_name.setText(getIntent().getStringExtra("printer_name"));
            et_print_number.setText(getIntent().getStringExtra("printer_no"));
            et_print_key.setText(getIntent().getStringExtra("key"));
            et_print_num.setText(getIntent().getStringExtra("print_num"));

            if (getIntent().getStringExtra("type_print").equals("1")){
                select_persontype.setText("后厨");
                printType = "1";
            }else {
                select_persontype.setText("结账");
                printType = "1";
            }

            if (getIntent().getStringExtra("back_good_if_print").equals("1")){
                select_return_goods.setText("是");
                back_good_if_print = "1";
            }else {
                select_return_goods.setText("否");
                back_good_if_print = "2";
            }

            if (getIntent().getStringExtra("print_way").equals("1")){
                select_cook_printtype.setText("一菜一打");
                print_way = "1";
            } else {
                select_cook_printtype.setText("一单一打");
                print_way = "2";
            }
        }
    }

    @Override
    public void setListener() {
        ll_back.setOnClickListener(this);
        select_persontype.setOnClickListener(this);
        btn_sumbit.setOnClickListener(this);
    }


    @Override
    public void doBusiness(Context mContext) {

    }

    // 添加打印机
    public void addPrint() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.PRINTADD;
        RequestParams params = new RequestParams();
        params.put("printer_name", et_print_name.getText().toString());
        params.put("printer_no", et_print_number.getText().toString());
        params.put("shop_id", preferences.getString("shop_id", ""));
        params.put("key", et_print_key.getText().toString());
        params.put("print_num", et_print_num.getText().toString());
        params.put("type_print", printType);

        params.put("printer_way","3");
        params.put("page_size","58");
        params.put("ip","");
        params.put("port","");
        params.put("back_good_if_print",back_good_if_print);
        params.put("print_way",print_way);


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

    // 修改打印机
    public void updatePrint() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.PRINTUPDATE;
        RequestParams params = new RequestParams();
        params.put("printer_id",printer_id);
        params.put("printer_name", et_print_name.getText().toString());
        params.put("printer_no", et_print_number.getText().toString());
        params.put("shop_id", preferences.getString("shop_id", ""));
        params.put("key", et_print_key.getText().toString());
        params.put("print_num", et_print_num.getText().toString());
        params.put("type_print", printType);

        params.put("printer_way","3");
        params.put("page_size","58");
        params.put("ip","");
        params.put("port","");
        params.put("back_good_if_print",back_good_if_print);
        params.put("print_way",print_way);


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

}
