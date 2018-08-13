package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mx.sy.R;
import com.mx.sy.api.ApiConfig;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.dialog.SweetAlertDialog;

import org.apache.http.Header;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ManageEmployeeAddActivity extends BaseActivity {

    private LinearLayout ll_back;
    private TextView tv_title;
    private Button select_persontype;
    private Button btn_sumbit;
    private AlertDialog alertDialog;
    private EditText et_person_name, et_person_number, et_phone;

    private String userType = "";

    private SharedPreferences preferences;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_sumbit:
                if (et_person_name.getText().toString().equals("")) {
                    Toast.makeText(ManageEmployeeAddActivity.this, "请输入员工姓名", Toast
                            .LENGTH_SHORT).show();
                } else if (et_person_number.getText().toString().equals("")) {
                    Toast.makeText(ManageEmployeeAddActivity.this, "请输入登录账号", Toast
                            .LENGTH_SHORT).show();
                } else if (et_phone.getText().toString().equals("")) {
                    Toast.makeText(ManageEmployeeAddActivity.this, "请输入手机号码", Toast
                            .LENGTH_SHORT).show();
                } else if (userType.equals("")) {
                    Toast.makeText(ManageEmployeeAddActivity.this, "请选择账号类型", Toast
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
                                            addWrite();
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
            case R.id.select_persontype:
                final String[] items = {"服务员", "店长"};
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageEmployeeAddActivity.this);
                alertBuilder.setTitle("请选择类型");
                alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        alertDialog.dismiss();
                        if (index == 0) {
                            select_persontype.setText(items[index]);
                            userType = "1";
                        } else if (index == 1) {
                            select_persontype.setText(items[index]);
                            userType = "2";
                        }
                    }
                });
                alertDialog = alertBuilder.create();
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
        return R.layout.activity_manage_employeeadd;
    }

    @Override
    public void initView(View view) {
        ll_back = $(R.id.ll_back);
        tv_title = $(R.id.tv_title);
        select_persontype = $(R.id.select_persontype);
        btn_sumbit = $(R.id.btn_sumbit);
        et_person_name = $(R.id.et_person_name);
        et_person_number = $(R.id.et_person_number);
        et_phone = $(R.id.et_person_phone);
    }

    @Override
    protected void initdata() {
        tv_title.setText("新增员工");
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

    // 添加账号
    public void addWrite() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.ADDWAITER;
        RequestParams params = new RequestParams();
        params.put("name", et_person_name.getText().toString());
        params.put("username", et_person_number.getText().toString());
        params.put("shop_id", preferences.getString("shop_id", ""));
        params.put("user_status", "1");
        params.put("type", userType);
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
