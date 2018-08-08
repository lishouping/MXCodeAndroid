package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mx.sy.R;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.dialog.SweetAlertDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ManageEmployeeAddActivity extends BaseActivity {

    private LinearLayout ll_back;
    private TextView tv_title;
    private Button select_persontype;
    private Button btn_sumbit;
    private AlertDialog alertDialog;
    private EditText et_person_name, et_person_number, et_phone;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_sumbit:
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


                                    }
                                })
                        .setCancelClickListener(
                                new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.cancel();
                                    }
                                }).show();
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

                        } else if (index == 1) {

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


}
