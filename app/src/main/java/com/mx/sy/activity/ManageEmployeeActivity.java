package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mx.sy.R;
import com.mx.sy.adapter.ManageEmployeeAdapter;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.common.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageEmployeeActivity extends BaseActivity {
    private LinearLayout ll_back;
    private TextView tv_title;
    private LinearLayout ll_right;
    private ImageView iv_icon;
    private RecyclerView rv_ma_employee;

    private ManageEmployeeAdapter manageEmployeeAdapter;
    private List<HashMap<String, String>> mapList;
    private AlertDialog alertDialog;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_right:
                Intent intent = new Intent(this,ManageEmployeeAddActivity.class);
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
        return R.layout.activity_manage_employee;
    }

    @Override
    public void initView(View view) {
        ll_back = $(R.id.ll_back);
        tv_title = $(R.id.tv_title);
        ll_right = $(R.id.ll_right);
        iv_icon = $(R.id.iv_icon);
        rv_ma_employee = $(R.id.rv_ma_employee);
    }

    @Override
    protected void initdata() {
        tv_title.setText("员工管理");
        iv_icon.setImageResource(R.drawable.ic_add);
        rv_ma_employee.setLayoutManager(new LinearLayoutManager(this));
        rv_ma_employee.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));

        mapList = new ArrayList<>();
        mapList.add(new HashMap<String, String>());
        mapList.add(new HashMap<String, String>());
        mapList.add(new HashMap<String, String>());
        mapList.add(new HashMap<String, String>());

        manageEmployeeAdapter = new ManageEmployeeAdapter(R.layout.item_employee, mapList);
        rv_ma_employee.setAdapter(manageEmployeeAdapter);

        manageEmployeeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final String[] items = {"冻结","删除","重置密码"};
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageEmployeeActivity.this);
                alertBuilder.setTitle("请选择操作");
                alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        alertDialog.dismiss();
                        if (index==0) {

                        }else if (index==1) {

                        }else if (index==2) {

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
