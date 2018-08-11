package com.mx.sy.adapter;

import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mx.sy.R;

import java.util.HashMap;
import java.util.List;

/**
 * ManageEmployeeAdapter
 * (c)2018 AIR Times Inc. All rights reserved.
 *
 * @author Lishp
 * @version 1.0
 * @date 2018/8/8 11:20
 * @see
 */
public class ManageDishesAdapter extends BaseQuickAdapter<HashMap<String, String>, BaseViewHolder> {
    public ManageDishesAdapter(int layoutResId, @Nullable List<HashMap<String, String>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HashMap<String, String> item) {
        helper.setText(R.id.tv_item_num, helper.getLayoutPosition() + 1 + "");
        helper.setText(R.id.tv_class_name, "名称:" + item.get("goods_name"));
        helper.setText(R.id.tv_crate_time, "分类:" + item.get("category_name"));
        helper.setText(R.id.tv_class_state, "价格:" + item.get("pre_price"));
        helper.addOnClickListener(R.id.ll_detail);
    }
}
