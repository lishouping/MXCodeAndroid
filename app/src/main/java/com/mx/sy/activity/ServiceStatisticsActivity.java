package com.mx.sy.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * <p>
 * Title: AboutUsActivity<／p>
 * <p>
 * Description: 服务统计<／p>
 * <p>
 * Company: LTGames<／p>
 * 
 * @author lishp
 * @date 2017年7月23日
 */
public class ServiceStatisticsActivity extends BaseActivity {
	private LinearLayout ll_back;
	private TextView tv_title;
	private SharedPreferences preferences;
	private ImageView iv_icon;
	private LinearLayout ll_right;

	private TextView tv_service_num, tv_order_num, tv_jiezhangorder_num;

	private Button btn_month, btn_week, btn_day, btn_time, btn_start_time,
			btn_end_time;

	private String time_flag = "3";
	private String start_time = "";
	private String end_time = "";

	private final static int DATE_DIALOG = 0;
	private final static int TIME_DIALOG = 1;
	private Calendar c = null;
	int page = 1;
	int total_page;
	int totalnum;

	private RecyclerView rv_static;
	MyAdapter myAdapter;
	private List<HashMap<String,String>> myList;

	RefreshLayout mPullToRefreshView;

	@Override
	public void widgetClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ll_right:
			if (btn_start_time.getText().equals("")) {
				Toast.makeText(getApplicationContext(), "开始时间不能为空",
						Toast.LENGTH_SHORT).show();
			} else if (btn_end_time.getText().equals("")) {
				Toast.makeText(getApplicationContext(), "结束时间不能为空",
						Toast.LENGTH_SHORT).show();
			} else {
				if (myList.size()>0){
					myList.clear();
					myAdapter.notifyDataSetChanged();
					page = 1;
					getServiceStatics();
				}else {
					getServiceStatics();
				}
			}
			break;
            case R.id.ll_back:
                finish();
                break;
		case R.id.btn_start_time:
			//时间选择器
			TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
				@Override
				public void onTimeSelect(Date date, View v) {
					start_time = CommonUtils.getTime(date);
					btn_start_time.setText(start_time);
				}
			}).setType(new boolean[]{true, true, true, true, true, false}).build();
			pvTime.show();
			break;
		case R.id.btn_end_time:
			//时间选择器
			TimePickerView pvTime1 = new TimePickerBuilder(this, new OnTimeSelectListener() {
				@Override
				public void onTimeSelect(Date date, View v) {
					end_time = CommonUtils.getTime(date);
					btn_end_time.setText(start_time);
				}
			}).setType(new boolean[]{true, true, true, true, true, false}).build();
			pvTime1.show();
			break;
		}
	}

	@Override
	public void initParms(Bundle parms) {
		// TODO Auto-generated method stub
		preferences = getSharedPreferences("userinfo",
				LoginActivity.MODE_PRIVATE);
	}

	@Override
	public View bindView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int bindLayout() {
		// TODO Auto-generated method stub
		return R.layout.activity_servicestatistics;
	}

	@Override
	public void initView(View view) {
		// TODO Auto-generated method stub
		ll_back = $(R.id.ll_back);
		tv_title = $(R.id.tv_title);
		iv_icon = $(R.id.iv_icon);
		iv_icon.setImageResource(R.drawable.ic_search);
		ll_right = $(R.id.ll_right);
		ll_right.setVisibility(View.VISIBLE);

		btn_start_time = $(R.id.btn_start_time);
		btn_end_time = $(R.id.btn_end_time);

		rv_static = $(R.id.rv_static);
		mPullToRefreshView = findViewById(R.id.pullrefresh_table);
		rv_static.setLayoutManager(new LinearLayoutManager(this));
		myList = new ArrayList<>();
		myAdapter = new MyAdapter(R.layout.item_witger_statistics,myList);
		rv_static.setAdapter(myAdapter);
	}

	@Override
	protected void initdata() {
		// TODO Auto-generated method stub
		tv_title.setText("服务统计");


		mPullToRefreshView.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh(RefreshLayout refreshlayout) {
				refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
				myList.clear();
				myAdapter.notifyDataSetChanged();
				page = 1;
				getServiceStatics();
			}
		});
		mPullToRefreshView.setOnLoadMoreListener(new OnLoadMoreListener() {
			@Override
			public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
				refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示刷新失败
				if (myList.size() == totalnum) {
					Toast.makeText(ServiceStatisticsActivity.this, "没有更多数据了", Toast.LENGTH_LONG)
							.show();
				} else {
					page++;
					getServiceStatics();
				}
			}
		});
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		ll_back.setOnClickListener(this);
		ll_right.setOnClickListener(this);

		btn_start_time.setOnClickListener(this);
		btn_end_time.setOnClickListener(this);
        ll_back.setOnClickListener(this);
	}

	@Override
	public void doBusiness(Context mContext) {
		// TODO Auto-generated method stub
	}

	// 服务数量统计
	public void getServiceStatics() {
		AsyncHttpClient client = new AsyncHttpClient();
		client.addHeader("key", preferences.getString("loginkey", ""));
		client.addHeader("id", preferences.getString("userid", ""));
		String url = ApiConfig.API_URL + ApiConfig.GETWIRITESTATIS;
		RequestParams params = new RequestParams();
		params.put("shop_id", preferences.getString("shop_id", ""));
		params.put("start_date", start_time);
		params.put("end_date", end_time);
		params.put("page_no",page);
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
							JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));
							totalnum = Integer.parseInt(jsonObject1.getString("totalnum"));
							total_page = Integer.parseInt(jsonObject1.getString("total_page"));
							JSONArray jsonArray = new JSONArray(jsonObject1
									.getString("list"));
							if (jsonArray.length()==0){
								Toast.makeText(getApplicationContext(),
										"暂无数据",
										Toast.LENGTH_SHORT).show();
							}else {
								for (int i = 0; i < jsonArray.length(); i++) {

									JSONObject object = jsonArray.getJSONObject(i);

									HashMap<String,String> map = new HashMap<>();
									map.put("NAME",object.getString("NAME"));
									map.put("check_count",object.getString("check_count"));
									map.put("order_count",object.getString("order_count"));
									map.put("service_count",object.getString("service_count"));
									myList.add(map);
								}
								myAdapter.notifyDataSetChanged();
							}
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


	public class MyAdapter extends BaseQuickAdapter<HashMap<String,String>,BaseViewHolder> {

		public MyAdapter(int layoutResId, @Nullable List<HashMap<String, String>> data) {
			super(layoutResId, data);
		}

		@Override
		protected void convert(BaseViewHolder helper, HashMap<String, String> item) {
			helper.setText(R.id.tv_tabel_name,item.get("NAME"));
			helper.setText(R.id.tv_order_num,item.get("check_count"));
			helper.setText(R.id.tv_number,item.get("order_count"));
			helper.setText(R.id.tv_total_price,item.get("service_count"));
		}
	}

}
