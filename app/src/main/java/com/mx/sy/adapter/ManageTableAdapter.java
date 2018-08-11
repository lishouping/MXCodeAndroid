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
public class ManageTableAdapter extends BaseQuickAdapter<HashMap<String, String>, BaseViewHolder> {
    public ManageTableAdapter(int layoutResId, @Nullable List<HashMap<String, String>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HashMap<String, String> item) {
        helper.setText(R.id.tv_tabel_name, "桌台名:"+item.get("table_name"));
        helper.setText(R.id.tv_table_partion, "分区名称:"+item.get("area_name"));
        if (item.get("table_status").equals("0")){
            helper.setText(R.id.tv_table_state, "桌台状态:未使用");
        }else if (item.get("table_status").equals("1")){
            helper.setText(R.id.tv_table_state, "桌台状态:使用中");
        }else if (item.get("table_status").equals("2")){
            helper.setText(R.id.tv_table_state, "桌台状态:预定");
        }else if (item.get("table_status").equals("3")){
            helper.setText(R.id.tv_table_state, "桌台状态:占用");
        }else if (item.get("table_status").equals("4")){
            helper.setText(R.id.tv_table_state, "桌台状态:其他");
        }
        helper.setText(R.id.tv_table_create_time, CommonUtils.getStrTime(item.get("create_time")));
    }
}
