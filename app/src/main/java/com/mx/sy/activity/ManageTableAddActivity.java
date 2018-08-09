package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mx.sy.R;
import com.mx.sy.adapter.DishesSelectClassAdapter;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.common.RecyclerViewDivider;
import com.mx.sy.dialog.SweetAlertDialog;

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

    List<HashMap<String, String>> classList;
    View view;
    private DishesSelectClassAdapter dishesSelectClassAdapter;
    private RecyclerView mrv_dialog;
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
                view = getLayoutInflater().inflate(R.layout.dialog_rv, null);
                AlertDialog.Builder alertBuilder1 = new AlertDialog.Builder(ManageTableAddActivity.this);
                alertBuilder1.setTitle("请选择分类");
                alertBuilder1.setView(view);
                alertDialog = alertBuilder1.create();


                mrv_dialog = view.findViewById(R.id.rv_dialog);
                mrv_dialog.setLayoutManager(new LinearLayoutManager(this));
                mrv_dialog.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));

                DishesSelectClassAdapter dishesSelectClassAdapter = new DishesSelectClassAdapter(R.layout
                        .item_disclass, classList);
                mrv_dialog.setAdapter(dishesSelectClassAdapter);
                dishesSelectClassAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        select_table_partition.setText(classList.get(position)+"");
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
        tv_title.setText("新增桌台");
        classList = new ArrayList<>();
        classList.add(new HashMap<String, String>());
        classList.add(new HashMap<String, String>());
        classList.add(new HashMap<String, String>());
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
