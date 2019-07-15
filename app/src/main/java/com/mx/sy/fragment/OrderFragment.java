package com.mx.sy.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mx.sy.R;
import com.mx.sy.activity.OrderDetailedActivity;
import com.mx.sy.adapter.OrderAdapter;
import com.mx.sy.api.ApiConfig;
import com.mx.sy.base.BaseFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>Title: OrderFragment<／p>
 * <p>Description: <／p>
 * <p>Company: LTGames<／p>
 *
 * @author lishp
 * @date 2017年4月2日
 */
public class OrderFragment extends BaseFragment implements OnClickListener {
    private LinearLayout lin_order_nomanage, lin_order_manageing, lin_order_managend;
    private TextView tv_order_nomanage, tv_order_manageing, tv_order_managend;
    private View viw_order_nomanage, viw_order_manageing, viw_order_managend;
    private ListView lv_order;
    private List<HashMap<String, String>> dateList;
    private OrderAdapter orderAdapter;

    RefreshLayout mPullToRefreshView;

    private SharedPreferences preferences;

    private int selectBtnFlag = 0;

    int page = 1;

    int totalnum;


    @Override
    protected int setLayoutResouceId() {
        // TODO Auto-generated method stub
        return R.layout.fragment_order;
    }

    @Override
    protected void loadDate() {
        // TODO Auto-generated method stub
        super.loadDate();

        mPullToRefreshView = getActivity().findViewById(R.id.pullrefresh_order);
        mPullToRefreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                page = 1;
                dateList.clear();
                if (selectBtnFlag == 0) {
                    geOrderInfo(-1);
                } else if (selectBtnFlag == 1) {
                    geOrderInfo(0);
                } else if (selectBtnFlag == 2) {
                    geOrderInfo(1);
                }
            }
        });
        mPullToRefreshView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                if (dateList.size() == totalnum) {
                    Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_LONG)
                            .show();
                } else {
                    page++;
                    if (selectBtnFlag == 0) {
                        geOrderInfo(-1);
                    } else if (selectBtnFlag == 1) {
                        geOrderInfo(0);
                    } else if (selectBtnFlag == 2) {
                        geOrderInfo(1);
                    }
                }
            }
        });
    }

    @Override
    protected void initView() {
        // TODO Auto-generated method stub
        super.initView();
        lin_order_nomanage = findViewById(R.id.lin_order_nomanage);
        lin_order_nomanage.setOnClickListener(this);
        lin_order_manageing = findViewById(R.id.lin_order_manageing);
        lin_order_manageing.setOnClickListener(this);
        lin_order_managend = findViewById(R.id.lin_order_managend);
        lin_order_managend.setOnClickListener(this);
        lv_order = findViewById(R.id.lv_order);

        tv_order_nomanage = findViewById(R.id.tv_order_nomanage);
        tv_order_manageing = findViewById(R.id.tv_order_manageing);
        tv_order_managend = findViewById(R.id.tv_order_managend);

        viw_order_nomanage = findViewById(R.id.viw_order_nomanage);
        viw_order_manageing = findViewById(R.id.viw_order_manageing);
        viw_order_managend = findViewById(R.id.viw_order_managend);
    }

    @Override
    protected void initData(Bundle arguments) {
        // TODO Auto-generated method stub
        super.initData(arguments);
    }

    @Override
    protected void onLazyLoad() {
        // TODO Auto-generated method stub
        super.onLazyLoad();

        preferences = getActivity().getSharedPreferences("userinfo",
                getActivity().MODE_PRIVATE);

        dateList = new ArrayList<HashMap<String, String>>();
        orderAdapter = new OrderAdapter(getActivity(), dateList, R.layout.item_order_untreated);
        lv_order.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                if (selectBtnFlag == 0) {
                    intent.setClass(mActivity, OrderDetailedActivity.class);
                    intent.putExtra("detailedpage", "1");
                    intent.putExtra("order_num", dateList.get(arg2).get("order_num"));
                    intent.putExtra("jsonobj", dateList.get(arg2).get("object"));
                } else if (selectBtnFlag == 1) {
                    intent.setClass(mActivity, OrderDetailedActivity.class);
                    intent.putExtra("detailedpage", "2");
                    intent.putExtra("order_num", dateList.get(arg2).get("order_num"));
                    intent.putExtra("jsonobj", dateList.get(arg2).get("object"));
                } else if (selectBtnFlag == 2) {
                    intent.setClass(mActivity, OrderDetailedActivity.class);
                    intent.putExtra("detailedpage", "3");
                    intent.putExtra("order_num", dateList.get(arg2).get("order_num"));
                    intent.putExtra("jsonobj", dateList.get(arg2).get("object"));
                }
                startActivity(intent);
            }
        });

        // 0 未处理，1已结账，2取消 -1顾客提交订单服务员未确认
        //geOrderInfo(-1);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.lin_order_nomanage:
                // 未处理
                orderAdapter = new OrderAdapter(getActivity(), dateList, R.layout.item_order_untreated);
                selectBtnFlag = 0;
                changeBtnBg(selectBtnFlag);
                dateList.clear();
                page = 1;
                geOrderInfo(-1);
                break;
            case R.id.lin_order_manageing:
                orderAdapter = new OrderAdapter(getActivity(), dateList, R.layout.item_order_havingdinner);
                // 正在用餐
                selectBtnFlag = 1;
                changeBtnBg(selectBtnFlag);
                dateList.clear();
                page = 1;
                geOrderInfo(0);
                break;
            case R.id.lin_order_managend:
                orderAdapter = new OrderAdapter(getActivity(), dateList, R.layout.item_order_com);
                // 已完成
                selectBtnFlag = 2;
                changeBtnBg(selectBtnFlag);
                dateList.clear();
                page = 1;
                geOrderInfo(1);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        if (selectBtnFlag == 0) {
            dateList.clear();
            orderAdapter.notifyDataSetChanged();
            page = 1;
            geOrderInfo(-1);
        } else if (selectBtnFlag == 1) {
            dateList.clear();
            orderAdapter.notifyDataSetChanged();
            page = 1;
            geOrderInfo(0);
        } else if (selectBtnFlag == 2) {
            dateList.clear();
            orderAdapter.notifyDataSetChanged();
            page = 1;
            geOrderInfo(1);
        }
        super.onResume();
    }

    private void changeBtnBg(int selectTag) {
        switch (selectTag) {
            case 0:
                tv_order_nomanage.setTextColor(Color.rgb(79, 145, 244));
                viw_order_nomanage.setBackgroundResource(R.color.main_bg_color);

                tv_order_manageing.setTextColor(Color.rgb(0, 0, 0));
                viw_order_manageing.setBackgroundResource(R.color.sweet_dialog_bg_color);

                tv_order_managend.setTextColor(Color.rgb(0, 0, 0));
                viw_order_managend.setBackgroundResource(R.color.sweet_dialog_bg_color);

                break;
            case 1:
                tv_order_manageing.setTextColor(Color.rgb(79, 145, 244));
                viw_order_manageing.setBackgroundResource(R.color.main_bg_color);

                tv_order_nomanage.setTextColor(Color.rgb(0, 0, 0));
                viw_order_nomanage.setBackgroundResource(R.color.sweet_dialog_bg_color);

                tv_order_managend.setTextColor(Color.rgb(0, 0, 0));
                viw_order_managend.setBackgroundResource(R.color.sweet_dialog_bg_color);

                break;
            case 2:
                tv_order_managend.setTextColor(Color.rgb(79, 145, 244));
                viw_order_managend.setBackgroundResource(R.color.main_bg_color);

                tv_order_nomanage.setTextColor(Color.rgb(0, 0, 0));
                viw_order_nomanage.setBackgroundResource(R.color.sweet_dialog_bg_color);

                tv_order_manageing.setTextColor(Color.rgb(0, 0, 0));
                viw_order_manageing.setBackgroundResource(R.color.sweet_dialog_bg_color);

                break;
            default:
                break;
        }
    }

    public void geOrderInfo(final int orderstate) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.ORDERLISTFORWRITER;
        RequestParams params = new RequestParams();
        if (selectBtnFlag == 0) {
        } else {
            params.put("waiter_id", preferences.getString("business_id", ""));
        }
        params.put("status", orderstate);
        params.put("page_no", page);
        params.put("shop_id", preferences.getString("shop_id", ""));
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
                            JSONArray jsonArray = new JSONArray(jsonObject
                                    .getString("DATA"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String status = object.getString("status");
                                if (orderstate == Integer.parseInt(status)) {
                                    String order_id = object.getString("order_id");
                                    String order_num = object.getString("order_num");
                                    String table_id = object.getString("table_id");
                                    String order_time = null;
                                    if (selectBtnFlag == 0) {
                                        order_time = object.getString("create_time");
                                    } else {
                                        order_time = object.getString("order_time");
                                    }

                                    JSONObject tabobj = new JSONObject(object.getString("table"));
                                    JSONObject writerobj = null;
                                    String name = null;
                                    if (selectBtnFlag == 0) {

                                    } else {
                                        if (TextUtils.isEmpty(object.getString("waiter"))||object.getString("waiter").equals("null")){
                                            name = "";
                                        }else {
                                            writerobj = new JSONObject(object.getString("waiter"));
                                            name = writerobj.getString("name");
                                        }

                                    }

                                    //JSONObject cartobj = new JSONObject(object.getString("cart"));


                                    String table_name = tabobj.getString("table_name");
                                    String people_count = object.getString("people_count");


                                    HashMap<String, String> map = new HashMap<String, String>();
                                    map.put("table_id", table_id);
                                    map.put("order_id", order_id);
                                    map.put("order_num", order_num);
                                    map.put("order_time", order_time);
                                    map.put("status", status);
                                    map.put("table_name", table_name);
                                    map.put("people_count", people_count);
                                    if (selectBtnFlag == 0) {
                                        map.put("name", "");
                                    } else {
                                        map.put("name", name);
                                    }
                                    map.put("object", object + "");
                                    dateList.add(map);
                                }
                            }
                            if (page == 1) {
                                lv_order.setAdapter(orderAdapter);
                            } else {
                                orderAdapter.notifyDataSetChanged();
                            }
                            if (dateList.size() == 0) {
                                Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
                            }
                            dissmissDilog();
                        } else {
                            Toast.makeText(getActivity(),
                                    jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "服务器异常",
                                Toast.LENGTH_SHORT).show();
                        dissmissDilog();
                    }
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "服务器异常", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

}
