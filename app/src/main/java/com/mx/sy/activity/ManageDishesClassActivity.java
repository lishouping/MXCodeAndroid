package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mx.sy.R;
import com.mx.sy.adapter.ManageDishesClassAdapter;
import com.mx.sy.adapter.ManageTablePartitionAdapter;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.common.RecyclerViewDivider;

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
        mapList.add(new HashMap<String, String>());
        mapList.add(new HashMap<String, String>());
        mapList.add(new HashMap<String, String>());
        mapList.add(new HashMap<String, String>());

        manageDishesClassAdapter = new ManageDishesClassAdapter(R.layout.item_dishesclass, mapList);
        rv_ma_dishesclass.setAdapter(manageDishesClassAdapter);

        manageDishesClassAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final String[] items = {"下架","上架","改名","删除"};
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageDishesClassActivity.this);
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
