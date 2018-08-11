package com.mx.sy.adapter;

import android.support.annotation.Nullable;

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
public class ManageEmployeeAdapter extends BaseQuickAdapter<HashMap<String, String>, BaseViewHolder> {
    public ManageEmployeeAdapter(int layoutResId, @Nullable List<HashMap<String, String>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HashMap<String, String> item) {
        helper.setText(R.id.tv_user_name, item.get("name") + "(" + item.get("username") + ")");
        helper.setText(R.id.tv_phone, "电话:" + item.get("phonenum"));
        if (item.get("user_status").equals("1")) {
            helper.setText(R.id.tv_state, "状态:" + "正常");
        } else {
            helper.setText(R.id.tv_state, "状态:" + "冻结");
        }
        if (item.get("type").equals("1")) {
            helper.setText(R.id.tv_type, "类型:店长");
        } else {
            helper.setText(R.id.tv_type, "类型:服务员");
        }
    }
}
