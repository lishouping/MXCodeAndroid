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
public class ManageTableAdapter extends BaseQuickAdapter<HashMap<String, String>, BaseViewHolder> {
    public ManageTableAdapter(int layoutResId, @Nullable List<HashMap<String, String>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HashMap<String, String> item) {
        helper.setText(R.id.tv_tabel_name, "桌台名:23");
        helper.setText(R.id.tv_table_partion, "分区名称:一楼");
        helper.setText(R.id.tv_table_state, "桌台状态:使用中");
        helper.setText(R.id.tv_table_create_time, "创建时间:2018-05-12");
    }
}
