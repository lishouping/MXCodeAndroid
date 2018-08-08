package com.mx.sy.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mx.sy.R;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.dialog.SweetAlertDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ManageShopActivity extends BaseActivity{

    private LinearLayout ll_back;
    private TextView tv_title;
    private LinearLayout ll_right;
    private ImageView iv_icon;

    private EditText mEtShopname;
    private EditText mEtShoppersonname;
    private EditText mEtPhone;
    private Button mBtnStartTime;
    private Button mBtnEndTime;
    private EditText mEtAddress;
    private EditText mEtInfo;
    private EditText mEtNotice;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_start_time:
                createDateTime(0);
                break;
            case R.id.btn_end_time:
                createDateTime(1);
                break;
            case R.id.ll_right:
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
        return R.layout.activity_manage_shop;
    }

    @Override
    public void initView(View view) {
        ll_back = $(R.id.ll_back);
        tv_title = $(R.id.tv_title);
        ll_right = $(R.id.ll_right);
        iv_icon = $(R.id.iv_icon);
        mEtShopname = $(R.id.et_shopname);
        mEtShoppersonname = $(R.id.et_shoppersonname);
        mEtPhone = $(R.id.et_phone);
        mBtnStartTime = $(R.id.btn_start_time);
        mBtnEndTime = $(R.id.btn_end_time);
        mEtAddress = $(R.id.et_address);
        mEtInfo = $(R.id.et_info);
        mEtNotice = $(R.id.et_notice);
    }

    @Override
    protected void initdata() {
        tv_title.setText("店铺管理");
        iv_icon.setImageResource(R.drawable.ic_add);
    }

    @Override
    public void setListener() {
        ll_back.setOnClickListener(this);
        ll_right.setVisibility(View.VISIBLE);
        ll_right.setOnClickListener(this);
        mBtnStartTime.setOnClickListener(this);
        mBtnEndTime.setOnClickListener(this);
    }


    @Override
    public void doBusiness(Context mContext) {

    }
    /**
     * 创建日期及时间选择对话框
     */
    private void createDateTime(final int id) {
        boolean[] type = new boolean[]{false, false, false, true, true, false};//显示类型，默认显示： 年月日
        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                String format = simpleDateFormat.format(date);
                if (id==0){
                    mBtnStartTime.setText(format);
                }else {
                    mBtnEndTime.setText(format);
                }
            }
        }).setCancelText("取消")
                .setSubmitText("确定")
                .setTitleText("选择时间")
                .isDialog(true)
                .setType(type)
                .build();
        pvTime.show();
    }

}
