package com.mx.sy.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mx.sy.R;
import com.mx.sy.api.ApiConfig;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.utils.CommonUtils;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 会员统计
 */
public class MemberStatisticsActivity  extends BaseActivity {
    private SharedPreferences preferences;
    private String startDate = "2018-01-01 05:00";
    private String endDate = "2019-10-01 05:00";

    private LinearLayout ll_back;
    private ImageView iv_icon;
    private TextView tv_title;
    private LinearLayout ll_right;

    private Button btn_start_time, btn_end_time;

    private TextView tv_member_monery,tv_member_number;


    private RecyclerView rv_member_statics;
    private MyAdapter myAdapter;
    private List<HashMap<String,String>> myList = new ArrayList<>();

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_right:
                //统计
                if (btn_start_time.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "开始时间不能为空",
                            Toast.LENGTH_SHORT).show();
                } else if (btn_end_time.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "结束时间不能为空",
                            Toast.LENGTH_SHORT).show();
                } else {
                    getSumRechargeByDate();
                    getUserCountByDate();
                    getUserChartsByDate();
                }
                break;
            case R.id.btn_start_time:
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        startDate = CommonUtils.getTime(date);
                        btn_start_time.setText(startDate);
                    }
                }).setType(new boolean[]{true, true, true, true, true, false}).build();
                pvTime.show();
                break;
            case R.id.btn_end_time:
                //时间选择器
                TimePickerView pvTime1 = new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        endDate = CommonUtils.getTime(date);
                        btn_end_time.setText(endDate);
                    }
                }).setType(new boolean[]{true, true, true, true, true, false}).build();
                pvTime1.show();
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
        return R.layout.activity_member_statistics;
    }

    @Override
    public void initView(View view) {
        preferences = getSharedPreferences("userinfo",
                LoginActivity.MODE_PRIVATE);

        ll_back = $(R.id.ll_back);
        tv_title = $(R.id.tv_title);
        iv_icon = $(R.id.iv_icon);
        iv_icon.setImageResource(R.drawable.ic_search);
        tv_title.setText("会员统计");
        ll_right = $(R.id.ll_right);
        ll_right.setVisibility(View.VISIBLE);
        btn_start_time = $(R.id.btn_start_time);
        btn_end_time = $(R.id.btn_end_time);
        tv_member_monery = findViewById(R.id.tv_member_monery);
        tv_member_number = findViewById(R.id.tv_member_number);

        rv_member_statics = findViewById(R.id.rv_member_statics);
        rv_member_statics.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initdata() {
        myAdapter = new MyAdapter(R.layout.item_member_statics,myList);
        rv_member_statics.setAdapter(myAdapter);
    }

    @Override
    public void setListener() {
        ll_back.setOnClickListener(this);
        ll_right.setOnClickListener(this);

        btn_start_time.setOnClickListener(this);
        btn_end_time.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    // 根据日期段查询会员充值金额
    public void getSumRechargeByDate() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL_MEMBER + ApiConfig.SUMBYDATE;
        RequestParams params = new RequestParams();
        params.put("shopId", preferences.getString("menmbers_shop_id", ""));
        params.put("startDate", startDate);
        params.put("endDate",endDate);
        client.post(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        com.orhanobut.logger.Logger.d(response);
                        JSONObject jsonObject = new JSONObject(response);

                        String CODE = jsonObject.getString("code");
                        if (CODE.equals("0")) {
                            String money = jsonObject.getString("data");
                            tv_member_monery.setText(money);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    jsonObject.getString("msg"),
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
    //根据日期查询会员新开数量
    public void getUserCountByDate() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL_MEMBER + ApiConfig.GETUSERCOUNT;
        RequestParams params = new RequestParams();
        params.put("shopId", preferences.getString("menmbers_shop_id", ""));
        params.put("startDate", startDate);
        params.put("endDate",endDate);
        client.post(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        com.orhanobut.logger.Logger.d(response);
                        JSONObject jsonObject = new JSONObject(response);

                        String CODE = jsonObject.getString("code");
                        if (CODE.equals("0")) {
                            String number = jsonObject.getString("data");
                            tv_member_number.setText(number);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    jsonObject.getString("msg"),
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
    //根据日期查询会员新开数量图表
    public void getUserChartsByDate() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL_MEMBER + ApiConfig.GETUSERCHARTS;
        RequestParams params = new RequestParams();
        params.put("shopId", preferences.getString("menmbers_shop_id", ""));
        params.put("startDate", startDate);
        params.put("endDate",endDate);
        client.post(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        com.orhanobut.logger.Logger.d(response);
                        JSONObject jsonObject = new JSONObject(response);

                        String CODE = jsonObject.getString("code");
                        if (CODE.equals("0")) {

                            JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String user_count = object
                                        .getString("user_count");
                                String create_date = object.getString("create_date");

                                HashMap<String,String> map = new HashMap<>();
                                map.put("user_count", user_count);
                                map.put("create_date", create_date);// 销售数量
                                myList.add(map);
                            }

                            myAdapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(getApplicationContext(),
                                    jsonObject.getString("msg"),
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
    public class MyAdapter extends BaseQuickAdapter<HashMap<String,String>,BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<HashMap<String, String>> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HashMap<String, String> item) {
            helper.setText(R.id.tv_member_number,"会员数量："+ item.get("user_count"));
            helper.setText(R.id.tv_member_createdate,item.get("create_date"));
        }
    }


}

