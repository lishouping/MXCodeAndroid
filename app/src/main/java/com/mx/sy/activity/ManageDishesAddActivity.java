package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.mx.sy.R;
import com.mx.sy.adapter.DishesSelectClassAdapter;
import com.mx.sy.adapter.DishesSpecifAdapter;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.common.RecyclerViewDivider;
import com.mx.sy.dialog.SweetAlertDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageDishesAddActivity extends BaseActivity {

    private LinearLayout ll_back;
    private TextView tv_title;
    private LinearLayout ll_right;
    private ImageView iv_icon;
    private Button btn_sumbit;
    private AlertDialog alertDialog;
    private EditText et_dis_name, et_dis_price, et_dis_discontent, et_dis_prief;
    private Button select_dis_class;
    ImageView img_dic_class;

    /**
     * 菜品分类
     */
    List<HashMap<String, String>> classList;
    View view;
    private DishesSelectClassAdapter dishesSelectClassAdapter;
    private RecyclerView mrv_dialog;

    private String pagetype;

    /**
     * 菜品规格
     */
    private RecyclerView rv_spcif;
    private List<HashMap<String,String>> msplist;
    private DishesSpecifAdapter dishesSpecifAdapter;

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
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageDishesAddActivity.this);
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
            case R.id.select_dis_class:

                view = getLayoutInflater().inflate(R.layout.dialog_rv, null);
                AlertDialog.Builder alertBuilder1 = new AlertDialog.Builder(ManageDishesAddActivity.this);
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
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();

                break;
            case R.id.img_dic_class:

                //		图片选择器
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, 1);


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
                final EditText textjianshao1 =  textEntryView
                        .findViewById(R.id.text_edit1);
                textjianshao.setHint("请输入规格名");
                textjianshao1.setVisibility(View.VISIBLE);
                textjianshao1.setHint("请输入价格");
                AlertDialog.Builder ad1 = new AlertDialog.Builder(
                        this);
                ad1.setTitle("编辑规格");
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
                break;
            default:
                break;
        }
    }

    @Override
    public void initParms(Bundle parms) {
        Intent intent = getIntent();
        //0 表示添加 1表示详情
        pagetype = intent.getStringExtra("pagetype");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_manage_dishesadd;
    }

    @Override
    public void initView(View view) {
        ll_back = $(R.id.ll_back);
        tv_title = $(R.id.tv_title);
        btn_sumbit = $(R.id.btn_sumbit);
        et_dis_name = $(R.id.et_dis_name);
        et_dis_price = $(R.id.et_dis_price);
        et_dis_discontent = $(R.id.et_dis_discontent);
        select_dis_class = $(R.id.select_dis_class);
        et_dis_prief = $(R.id.et_dis_prief);
        img_dic_class = $(R.id.img_dic_class);
        rv_spcif = $(R.id.rv_spcif);
        ll_right = $(R.id.ll_right);
        iv_icon = $(R.id.iv_icon);
        iv_icon.setImageResource(R.drawable.ic_add);
    }

    @Override
    protected void initdata() {
        if ("0".equals(pagetype)){
            tv_title.setText("添加菜品");
        }else {
            tv_title.setText("查看菜品");
            et_dis_name.setFocusable(false);
            et_dis_price.setFocusable(false);
            et_dis_discontent.setFocusable(false);
            et_dis_prief.setFocusable(false);
            select_dis_class.setEnabled(false);
            img_dic_class.setEnabled(false);
            btn_sumbit.setVisibility(View.GONE);
        }

        classList = new ArrayList<>();
        classList.add(new HashMap<String, String>());
        classList.add(new HashMap<String, String>());
        classList.add(new HashMap<String, String>());

        msplist = new ArrayList<>();
        msplist.add(new HashMap<String, String>());
        msplist.add(new HashMap<String, String>());
        msplist.add(new HashMap<String, String>());
        msplist.add(new HashMap<String, String>());
        rv_spcif.setLayoutManager(new LinearLayoutManager(this));
        rv_spcif.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));

        dishesSpecifAdapter = new DishesSpecifAdapter(R.layout.item_tablepartition,msplist);
        rv_spcif.setAdapter(dishesSpecifAdapter);
        dishesSpecifAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final String[] items = {"修改", "删除"};
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageDishesAddActivity.this);
                alertBuilder.setTitle("请选择操作");
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
            }
        });

    }

    @Override
    public void setListener() {
        ll_back.setOnClickListener(this);
        btn_sumbit.setOnClickListener(this);
        select_dis_class.setOnClickListener(this);
        img_dic_class.setOnClickListener(this);
        ll_right.setVisibility(View.VISIBLE);
        ll_right.setOnClickListener(this);
    }


    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 1) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker
                        .EXTRA_RESULT_ITEMS);
                Bitmap bitmap = BitmapFactory.decodeFile(images.get(0).path);
                img_dic_class.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
