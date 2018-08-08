package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mx.sy.R;
import com.mx.sy.adapter.ManageTableAdapter;
import com.mx.sy.adapter.ManageTablePartitionAdapter;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.common.RecyclerViewDivider;

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

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_right:
                Intent intent = new Intent(this,ManageTableAddActivity.class);
                startActivity(intent);
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
        mapList.add(new HashMap<String, String>());
        mapList.add(new HashMap<String, String>());
        mapList.add(new HashMap<String, String>());
        mapList.add(new HashMap<String, String>());

        manageTableAdapter = new ManageTableAdapter(R.layout.item_table, mapList);
        rv_ma_table.setAdapter(manageTableAdapter);

        manageTableAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final String[] items = {"删除"};
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageTableActivity.this);
                alertBuilder.setTitle("请选择操作");
                alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        alertDialog.dismiss();
                        if (index == 0) {

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
}
