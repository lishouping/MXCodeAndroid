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
public class ManagePrintAdapter extends BaseQuickAdapter<HashMap<String, String>, BaseViewHolder> {
    public ManagePrintAdapter(int layoutResId, @Nullable List<HashMap<String, String>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HashMap<String, String> item) {
        helper.setText(R.id.tv_print_name, "打印机名称:" + item.get("printer_name"));
        helper.setText(R.id.tv_print_number, "编号:" + item.get("printer_no"));

        if (item.get("type_print").equals("1")) {
            helper.setText(R.id.tv_print_type, "打印类型:" + "后厨");
        } else {
            helper.setText(R.id.tv_print_type, "打印类型:" + "结账");
        }
        helper.setText(R.id.tv_print_status, "打印份数:" + item.get("print_num"));
    }
}
