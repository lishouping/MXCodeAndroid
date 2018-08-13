package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.mx.sy.R;
import com.mx.sy.adapter.DishesSelectClassAdapter;
import com.mx.sy.adapter.DishesSpecifAdapter;
import com.mx.sy.api.ApiConfig;
import com.mx.sy.app.MyApplication;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.common.RecyclerViewDivider;
import com.mx.sy.dialog.SweetAlertDialog;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
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
    private List<HashMap<String, String>> msplist;
    private DishesSpecifAdapter dishesSpecifAdapter;

    private SharedPreferences preferences;

    private String foodClassId = "";
    private String foodClassName = "";

    private String imageUrl = "";

    private String goods_id;
    private String category_name;
    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_sumbit:
                if (et_dis_name.getText().toString().equals("")) {
                    Toast.makeText(ManageDishesAddActivity.this, "请填写菜品名", Toast
                            .LENGTH_SHORT).show();
                } else if (foodClassId.equals("")) {
                    Toast.makeText(ManageDishesAddActivity.this, "请选择菜品分类", Toast
                            .LENGTH_SHORT).show();
                } else if (et_dis_price.getText().toString().equals("")) {
                    Toast.makeText(ManageDishesAddActivity.this, "请填写菜品单价", Toast
                            .LENGTH_SHORT).show();
                } else if (et_dis_discontent.getText().toString().equals("")) {
                    Toast.makeText(ManageDishesAddActivity.this, "请填写菜品折扣", Toast
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
                                            try {
                                                if (pagetype.equals("2")){
                                                    updateFood();
                                                }else if (pagetype.equals("0")){
                                                    addFood();
                                                }
                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            }
                                            sDialog.cancel();
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

                dishesSelectClassAdapter = new DishesSelectClassAdapter(R.layout
                        .item_disclass, classList);
                mrv_dialog.setAdapter(dishesSelectClassAdapter);
                dishesSelectClassAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        alertDialog.dismiss();
                        foodClassId = classList.get(position).get("category_id");
                        foodClassName = classList.get(position).get("classname");
                        select_dis_class.setText(foodClassName);
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
                final EditText textjianshao = textEntryView
                        .findViewById(R.id.text_editjiannumbe);
                final EditText textjianshao1 = textEntryView
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
        preferences = getSharedPreferences("userinfo",
                LoginActivity.MODE_PRIVATE);
        goods_id = intent.getStringExtra("goods_id");
        category_name = intent.getStringExtra("category_name");

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
        rv_spcif.setVisibility(View.GONE);
        ll_right = $(R.id.ll_right);
        iv_icon = $(R.id.iv_icon);
        iv_icon.setImageResource(R.drawable.ic_add);
    }

    @Override
    protected void initdata() {
        if ("0".equals(pagetype)) {
            tv_title.setText("添加菜品");
        } else if ("1".equals(pagetype)){
            tv_title.setText("查看菜品");
            et_dis_name.setFocusable(false);
            et_dis_price.setFocusable(false);
            et_dis_discontent.setFocusable(false);
            et_dis_prief.setFocusable(false);
            select_dis_class.setEnabled(false);
            img_dic_class.setEnabled(false);
            btn_sumbit.setVisibility(View.GONE);
        }else {
            tv_title.setText("修改菜品");
        }

        classList = new ArrayList<>();

        msplist = new ArrayList<>();
        msplist.add(new HashMap<String, String>());
        msplist.add(new HashMap<String, String>());
        msplist.add(new HashMap<String, String>());
        msplist.add(new HashMap<String, String>());
        rv_spcif.setLayoutManager(new LinearLayoutManager(this));
        rv_spcif.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));

        dishesSpecifAdapter = new DishesSpecifAdapter(R.layout.item_tablepartition, msplist);
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
        selectCategory();
        if (pagetype.equals("1")||pagetype.equals("2")){
            selectGoods();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 1) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker
                        .EXTRA_RESULT_ITEMS);
                imageUrl = images.get(0).path;
                Bitmap bitmap = BitmapFactory.decodeFile(images.get(0).path);
                img_dic_class.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // // 查询菜品分类(包含菜品)
    public void selectCategory() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.SELECTCATEGORY_URL + "/"
                + preferences.getString("shop_id", "");
        RequestParams params = new RequestParams();
        params.put("shop_id", preferences.getString("shop_id", ""));
        client.get(url, new AsyncHttpResponseHandler() {

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
                            dissmissDilog();
                            JSONArray jsonArray = new JSONArray(jsonObject
                                    .getString("DATA"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String category_id = object
                                        .getString("category_id");
                                String category_name = object
                                        .getString("category_name");
                                String category_status = object
                                        .getString("category_status");
                                String create_time = object.getString("create_time");
                                HashMap<String, String> map = new HashMap<String, String>();
                                map.put("category_id", category_id);
                                map.put("classname", category_name);
                                map.put("category_status", category_status);
                                map.put("create_time", create_time);
                                classList.add(map);
                            }
                            dishesSelectClassAdapter.notifyDataSetChanged();
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        dissmissDilog();
                    }
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "服务器异常",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    //添加菜品
    public void addFood() throws FileNotFoundException {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.ADDGOODS;
        RequestParams params = new RequestParams();
        params.put("shop_id", preferences.getString("shop_id", ""));
        params.put("category_id", foodClassId);
        params.put("goods_name", et_dis_name.getText().toString());
        params.put("pre_price", et_dis_price.getText().toString());
        params.put("discount", et_dis_discontent.getText().toString());
        params.put("introduction", et_dis_prief.getText().toString());
        if (!imageUrl.equals("")) {
            File file = new File(imageUrl);
            params.put("file", file);
        }
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
                            Toast.makeText(getApplicationContext(), jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        dissmissDilog();
                    }
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "服务器异常",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    //查询菜品
    public void selectGoods() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.SELECTGOODS;
        RequestParams params = new RequestParams();
        params.put("goods_id", goods_id);
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
                            JSONObject jsonObject1 = new JSONObject(jsonObject
                                    .getString("DATA"));

                            String category_id = jsonObject1.getString("category_id");
                            foodClassId = category_id;
                            String goods_name  = jsonObject1.getString("goods_name");
                            String pre_price  = jsonObject1.getString("pre_price");
                            String discount  = jsonObject1.getString("discount");
                            String img_url  = jsonObject1.getString("img_url");
                            String introduction = jsonObject1.getString("introduction");
                            et_dis_name.setText(goods_name);
                            et_dis_price.setText(pre_price);
                            et_dis_discontent.setText(discount);
                            et_dis_prief.setText(introduction);
                            MyApplication.mLoader.loadImage(ApiConfig.RESOURCE_URL + img_url, img_dic_class, true);

                            select_dis_class.setText(category_name);
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        dissmissDilog();
                    }
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "服务器异常",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    //修改菜品
    public void updateFood() throws FileNotFoundException {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.UPDETEGOODS;
        RequestParams params = new RequestParams();
        params.put("good_id",goods_id);
        params.put("shop_id", preferences.getString("shop_id", ""));
        params.put("category_id", foodClassId);
        params.put("goods_name", et_dis_name.getText().toString());
        params.put("pre_price", et_dis_price.getText().toString());
        params.put("discount", et_dis_discontent.getText().toString());
        params.put("Introduction", et_dis_prief.getText().toString());
        if (!imageUrl.equals("")) {
            File file = new File(imageUrl);
            params.put("file", file);
        }
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
                            Toast.makeText(getApplicationContext(), jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        dissmissDilog();
                    }
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "服务器异常",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }



}
