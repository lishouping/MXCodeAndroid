package com.mx.sy.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mx.sy.R;
import com.mx.sy.utils.CommonUtils;

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
public class ManageDishesClassAdapter extends BaseQuickAdapter<HashMap<String, String>, BaseViewHolder> {
    public ManageDishesClassAdapter(int layoutResId, @Nullable List<HashMap<String, String>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HashMap<String, String> item) {
        helper.setText(R.id.tv_item_num, helper.getLayoutPosition() + 1 + "");
        helper.setText(R.id.tv_class_name, item.get("category_name"));
        helper.setText(R.id.tv_crate_time, "创建时间:"+ CommonUtils.getStrTime(item.get("create_time")));
        if (item.get("category_status").equals("1")){
            helper.setText(R.id.tv_class_state, "状态:正常");
        }else {
            helper.setText(R.id.tv_class_state, "状态:下架");
        }

    }
}
