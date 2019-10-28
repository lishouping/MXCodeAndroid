package com.mx.sy.activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mx.sy.R;
import com.mx.sy.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: ServiceDetailedActivity<／p>
 * <p>
 * Description: <／p> 统计分析
 * <p>
 * Company: LTGames<／p>
 *
 * @author lishp
 * @date 2017年9月14日
 */
public class StatisticalAnalysisActivity extends BaseActivity {
    private LinearLayout ll_back;
    private TextView tv_title;
    private RecyclerView rv_static_analysis;

    private List<HashMap<String,Object>> dateList = new ArrayList<>();
    private MyAdapter myAdapter;

    // 1统计分析 2系统设置
    private String pageType = "";

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void initParms(Bundle parms) {
        pageType = getIntent().getStringExtra("pageType");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_statiticalanalysis;
    }

    @Override
    public void initView(View view) {
        ll_back = $(R.id.ll_back);
        tv_title = $(R.id.tv_title);
        if (pageType.equals("1")){
            tv_title.setText("统计分析");
        }else {
            tv_title.setText("系统设置");
        }
        rv_static_analysis = findViewById(R.id.rv_static_analysis);

    }

    @Override
    protected void initdata() {
        myAdapter = new MyAdapter(R.layout.item_statictcal_analysis,dateList);
    }

    @Override
    public void setListener() {
        ll_back.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        setDate();
    }


    private void setDate(){
        if (pageType.equals("1")){
            HashMap<String, Object> userInfoMap1 = new HashMap<String, Object>();
            userInfoMap1.put("content", "销售统计");
            userInfoMap1.put("contentImg", R.drawable.icon_tip);
            userInfoMap1.put("mytypeImg", R.drawable.ic_new_1);
            userInfoMap1.put("isnbsp", "0");
            dateList.add(userInfoMap1);

            HashMap<String, Object> userInfoMapnew1 = new HashMap<String, Object>();
            userInfoMapnew1.put("content", "收款统计");
            userInfoMapnew1.put("contentImg", R.drawable.icon_tip);
            userInfoMapnew1.put("mytypeImg", R.drawable.ic_new_15);
            userInfoMapnew1.put("isnbsp", "0");
            dateList.add(userInfoMapnew1);

            HashMap<String, Object> userInfoMapnew2 = new HashMap<String, Object>();
            userInfoMapnew2.put("content", "桌台统计");
            userInfoMapnew2.put("contentImg", R.drawable.icon_tip);
            userInfoMapnew2.put("mytypeImg", R.drawable.ic_new_4);
            userInfoMapnew2.put("isnbsp", "0");
            dateList.add(userInfoMapnew2);

            HashMap<String, Object> userInfoMapnew3 = new HashMap<String, Object>();
            userInfoMapnew3.put("content", "会员统计");
            userInfoMapnew3.put("contentImg", R.drawable.icon_tip);
            userInfoMapnew3.put("mytypeImg", R.drawable.ic_new_14);
            userInfoMapnew3.put("isnbsp", "0");
            dateList.add(userInfoMapnew3);

            HashMap<String, Object> userInfoMap2 = new HashMap<String, Object>();
            userInfoMap2.put("content", "服务统计");
            userInfoMap2.put("contentImg", R.drawable.icon_tip);
            userInfoMap2.put("mytypeImg", R.drawable.ic_new_9);
            userInfoMap2.put("isnbsp", "0");
            dateList.add(userInfoMap2);
        }else {
            HashMap<String, Object> userInfoMap9 = new HashMap<String, Object>();
            userInfoMap9.put("content", "店铺管理");
            userInfoMap9.put("contentImg", R.drawable.icon_tip);
            userInfoMap9.put("mytypeImg", R.drawable.ic_new_8);
            userInfoMap9.put("isnbsp", "1");
            dateList.add(userInfoMap9);

            HashMap<String, Object> userInfoMap5 = new HashMap<String, Object>();
            userInfoMap5.put("content", "打印机设置");
            userInfoMap5.put("contentImg", R.drawable.icon_tip);
            userInfoMap5.put("mytypeImg", R.drawable.ic_new_7);
            userInfoMap5.put("isnbsp", "0");
            dateList.add(userInfoMap5);

            HashMap<String, Object> userInfoMap10 = new HashMap<String, Object>();
            userInfoMap10.put("content", "员工管理");
            userInfoMap10.put("contentImg", R.drawable.icon_tip);
            userInfoMap10.put("mytypeImg", R.drawable.ic_new_12);
            userInfoMap10.put("isnbsp", "0");
            dateList.add(userInfoMap10);

            HashMap<String, Object> userInfoMap11 = new HashMap<String, Object>();
            userInfoMap11.put("content", "桌台分区");
            userInfoMap11.put("contentImg", R.drawable.icon_tip);
            userInfoMap11.put("mytypeImg", R.drawable.ic_new_5);
            userInfoMap11.put("isnbsp", "0");
            dateList.add(userInfoMap11);

            HashMap<String, Object> userInfoMap12 = new HashMap<String, Object>();
            userInfoMap12.put("content", "桌台管理");
            userInfoMap12.put("contentImg", R.drawable.icon_tip);
            userInfoMap12.put("mytypeImg", R.drawable.ic_new_6);
            userInfoMap12.put("isnbsp", "0");
            dateList.add(userInfoMap12);

            HashMap<String, Object> userInfoMap13 = new HashMap<String, Object>();
            userInfoMap13.put("content", "菜品分类");
            userInfoMap13.put("contentImg", R.drawable.icon_tip);
            userInfoMap13.put("mytypeImg", R.drawable.ic_new_2);
            userInfoMap13.put("isnbsp", "0");
            dateList.add(userInfoMap13);

            HashMap<String, Object> userInfoMap14 = new HashMap<String, Object>();
            userInfoMap14.put("content", "菜品管理");
            userInfoMap14.put("contentImg", R.drawable.icon_tip);
            userInfoMap14.put("mytypeImg", R.drawable.ic_new_3);
            userInfoMap14.put("isnbsp", "0");
            dateList.add(userInfoMap14);
        }
        rv_static_analysis.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                if (pageType.equals("1")){
                    switch (position){
                        case 0:
                            intent.setClass(getApplicationContext(), SalesStatisticsActivity.class);
                            startActivity(intent);
                            break;
                        case 1:
                            intent.setClass(getApplicationContext(), CollectionStatisticsActivity.class);
                            startActivity(intent);
                            break;
                        case 2:
                            intent.setClass(getApplicationContext(), TableStatisticsActivity.class);
                            startActivity(intent);
                            break;
                        case 3:
                            intent.setClass(getApplicationContext(), MemberStatisticsActivity.class);
                            startActivity(intent);
                            break;
                        case 4:
                            intent.setClass(getApplicationContext(), ServiceStatisticsActivity.class);
                            startActivity(intent);
                            break;
                    }
                }else {
                    switch (position){
                        case 0:
                            // 店铺管理
                            intent.setClass(getApplicationContext(), ManageShopActivity.class);
                            startActivity(intent);
                            break;

                        case 1:
                            //打印机设置
                            intent.setClass(getApplicationContext(), PrinterSeetingActivity.class);
                            startActivity(intent);
                            break;
                        case 2:
                            // 员工管理
                            intent.setClass(getApplicationContext(), ManageEmployeeActivity.class);
                            startActivity(intent);
                            break;
                        case 3:
                            // 桌台分区
                            intent.setClass(getApplicationContext(), ManageTablePartitionActivity.class);
                            startActivity(intent);
                            break;
                        case 4:
                            // 桌台管理
                            intent.setClass(getApplicationContext(), ManageTableActivity.class);
                            startActivity(intent);
                            break;
                        case 5:
                            // 菜品分类
                            intent.setClass(getApplicationContext(), ManageDishesClassActivity.class);
                            startActivity(intent);
                            break;
                        case 6:
                            // 菜品管理
                            intent.setClass(getApplicationContext(), ManageDishesActivity.class);
                            startActivity(intent);
                            break;
                    }
                }

            }
        });
    }

    public class MyAdapter extends BaseQuickAdapter<HashMap<String,Object>,BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<HashMap<String, Object>> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HashMap<String, Object> item) {
            helper.setText(R.id.tv_conent,item.get("content")+"");
            ImageView imageView = helper.getView(R.id.icon);
            imageView.setImageResource(Integer.parseInt(item.get("mytypeImg")+""));
        }
    }
}
