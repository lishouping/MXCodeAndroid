package com.mx.sy.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.mx.sy.R;
import com.mx.sy.api.ApiConfig;
import com.mx.sy.app.MyApplication;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.dialog.SweetAlertDialog;

import org.apache.http.Header;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ManageShopActivity extends BaseActivity {

    private LinearLayout ll_back;
    private TextView tv_title;

    private EditText mEtShopname;
    private EditText mEtShoppersonname;
    private EditText mEtPhone;
    private Button mBtnStartTime;
    private Button mBtnEndTime;
    private Button btn_selelct_print;
    private EditText mEtAddress;
    private EditText mEtInfo;
    private EditText mEtNotice, et_shop_phone, et_service, et_recr_info;

    private AlertDialog alertDialog;
    private Button btn_sumbit;
    private Button btn_chef_work, btn_checkout_time;
    private ImageView img_shop, img_wx, img_alpay;

    private SharedPreferences preferences;
    private String shop_id;
    private String logoFile = "";
    private String wxFile = "";
    private String alpayFile = "";

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
            case R.id.btn_chef_work:
                createDateTime(2);
                break;
            case R.id.btn_checkout_time:
                createDateTime(3);
                break;
            case R.id.btn_selelct_print:

                final String[] items = {"一单一打", "一菜一打"};
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageShopActivity.this);
                alertBuilder.setTitle("请选择打印方式");
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
                                        updateShopInfo();
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
                break;
            case R.id.img_shop:

                //		图片选择器
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.img_wx:

                //		图片选择器
                Intent intent1 = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent1, 2);
                break;
            case R.id.img_alpay:

                //		图片选择器
                Intent intent2 = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent2, 3);
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
        mEtShopname = $(R.id.et_shopname);
        mEtShoppersonname = $(R.id.et_shoppersonname);
        mEtPhone = $(R.id.et_phone);
        mBtnStartTime = $(R.id.btn_start_time);
        mBtnEndTime = $(R.id.btn_end_time);
        mEtAddress = $(R.id.et_address);
        mEtInfo = $(R.id.et_info);
        mEtNotice = $(R.id.et_notice);
        btn_sumbit = $(R.id.btn_sumbit);
        et_shop_phone = $(R.id.et_shop_phone);
        btn_selelct_print = $(R.id.btn_selelct_print);
        btn_chef_work = $(R.id.btn_chef_work);
        btn_checkout_time = $(R.id.btn_checkout_time);
        et_service = $(R.id.et_service);
        et_recr_info = $(R.id.et_recr_info);
        img_shop = $(R.id.img_shop);
        img_wx = $(R.id.img_wx);
        img_alpay = $(R.id.img_alpay);

    }

    @Override
    protected void initdata() {
        tv_title.setText("店铺管理");
        preferences = getSharedPreferences("userinfo",
                LoginActivity.MODE_PRIVATE);
    }

    @Override
    public void setListener() {
        ll_back.setOnClickListener(this);
        mBtnStartTime.setOnClickListener(this);
        mBtnEndTime.setOnClickListener(this);
        btn_sumbit.setOnClickListener(this);
        btn_selelct_print.setOnClickListener(this);
        btn_chef_work.setOnClickListener(this);
        btn_checkout_time.setOnClickListener(this);
        img_shop.setOnClickListener(this);
        img_wx.setOnClickListener(this);
        img_alpay.setOnClickListener(this);
    }


    @Override
    public void doBusiness(Context mContext) {
        getShopTableInfo();
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
                if (id == 0) {
                    mBtnStartTime.setText(format);
                } else if (id == 1) {
                    mBtnEndTime.setText(format);
                } else if (id == 2) {
                    btn_chef_work.setText(format);
                } else {
                    btn_checkout_time.setText(format);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 1) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker
                        .EXTRA_RESULT_ITEMS);
                logoFile = images.get(0).path;
                Bitmap bitmap = BitmapFactory.decodeFile(images.get(0).path);
                img_shop.setImageBitmap(bitmap);
            } else if (data != null && requestCode == 2) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker
                        .EXTRA_RESULT_ITEMS);
                wxFile = images.get(0).path;
                Bitmap bitmap = BitmapFactory.decodeFile(images.get(0).path);
                img_wx.setImageBitmap(bitmap);
            } else if (data != null && requestCode == 3) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker
                        .EXTRA_RESULT_ITEMS);
                alpayFile = images.get(0).path;
                Bitmap bitmap = BitmapFactory.decodeFile(images.get(0).path);
                img_alpay.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 查询店铺信息
    public void getShopTableInfo() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.GETSHOPINFO + "/"
                + preferences.getString("shop_id", "");
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        JSONObject jsonObject = new JSONObject(response);
                        String CODE = jsonObject.getString("CODE");
                        if (CODE.equals("1000")) {
                            JSONObject jsonObject2 = new JSONObject(jsonObject.getString("DATA"));
                            shop_id = jsonObject2.getString("shop_id");
                            //店铺名称
                            String shop_name = jsonObject2.getString("shop_name");
                            mEtShopname.setText(shop_name);
                            //店铺地址
                            String address = jsonObject2.getString("address");
                            mEtAddress.setText(address);
                            //店铺老板
                            String shop_owner_name = jsonObject2.getString("shop_owner_name");
                            mEtShoppersonname.setText(shop_owner_name);
                            //老板电话
                            String shop_owner_phone = jsonObject2.getString("shop_owner_phone");
                            mEtPhone.setText(shop_owner_phone);
                            // 店铺简介
                            String introduction = jsonObject2.getString("introduction");
                            mEtInfo.setText(introduction);
                            // 商家公告
                            String notice = jsonObject2.getString("notice");
                            mEtNotice.setText(notice);
                            // 营业开始时间
                            String begin_time = jsonObject2.getString("begin_time");
                            mBtnStartTime.setText(begin_time);
                            // 营业结束时间
                            String end_time = jsonObject2.getString("end_time");
                            mBtnEndTime.setText(end_time);
                            //微信支付图片
                            String wechat_img = jsonObject2.getString("wechat_img");
                            MyApplication.mLoader.loadImage(ApiConfig.RESOURCE_URL + wechat_img, img_wx, true);
                            //支付宝支付图片
                            String alipay_img = jsonObject2.getString("alipay_img");
                            MyApplication.mLoader.loadImage(ApiConfig.RESOURCE_URL + alipay_img, img_alpay, true);
                            // 图标
                            String icon = jsonObject2.getString("icon");
                            MyApplication.mLoader.loadImage(ApiConfig.RESOURCE_URL + icon, img_shop, true);
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

    // 更新店铺
    public void updateShopInfo() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.UPDATESHOP;
        RequestParams params = new RequestParams();
        params.put("shop_id",shop_id);
        params.put("shop_name",mEtShopname.getText()+"");
        params.put("address",mEtAddress.getText()+"");
        params.put("shop_owner_name",mEtShoppersonname.getText()+"");
        params.put("shop_owner_phone",mEtPhone.getText()+"");
        params.put("introduction",mEtInfo.getText()+"");
        params.put("notice",mEtNotice.getText()+"");
        params.put("begin_time",mBtnStartTime.getText()+"");
        params.put("end_time",mBtnEndTime.getText()+"");
        if (!logoFile.equals("")){
            File file = new File(logoFile);
            try {
                params.put("file",file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (!wxFile.equals("")){
            File file = new File(wxFile);
            try {
                params.put("wechat_img_file",file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (!alpayFile.equals("")){
            File file = new File(alpayFile);
            try {
                params.put("alipay_img_file",file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        client.post(url,params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        JSONObject jsonObject = new JSONObject(response);
                        String CODE = jsonObject.getString("CODE");
                        if (CODE.equals("1000")){
                            Toast.makeText(ManageShopActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
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
