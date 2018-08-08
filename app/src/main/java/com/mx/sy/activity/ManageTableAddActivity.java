package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mx.sy.R;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.dialog.SweetAlertDialog;

public class ManageTableAddActivity extends BaseActivity {

    private LinearLayout ll_back;
    private TextView tv_title;
    private Button select_table_partition;
    private Button btn_sumbit;
    private AlertDialog alertDialog;
    private EditText et_table_name, et_table_number, et_phone;

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
            case R.id.select_table_partition:
                final String[] items = {"1楼", "2楼"};
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageTableAddActivity.this);
                alertBuilder.setTitle("请选择分区");
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
    }

    @Override
    protected void initdata() {
        tv_title.setText("新增员工");
    }

    @Override
    public void setListener() {
        ll_back.setOnClickListener(this);
        select_table_partition.setOnClickListener(this);
        btn_sumbit.setOnClickListener(this);
    }


    @Override
    public void doBusiness(Context mContext) {

    }


}
