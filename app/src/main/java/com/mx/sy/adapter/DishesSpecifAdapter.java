package com.mx.sy.adapter;


import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mx.sy.R;

import java.util.HashMap;
import java.util.List;

/**
 * @author lenovo
 */
public class DishesSpecifAdapter extends BaseQuickAdapter<HashMap<String,String>,BaseViewHolder>{

    public DishesSpecifAdapter(int layoutResId, @Nullable List<HashMap<String, String>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HashMap<String, String> item) {
        helper.setText(R.id.tv_partition_name,"正常量");
        helper.setText(R.id.tv_crate_time,"12").setTextColor(R.id.tv_crate_time, ContextCompat.getColor(mContext,R.color.red_btn_bg_color));
    }
}
