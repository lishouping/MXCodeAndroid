package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mx.sy.R;
import com.mx.sy.adapter.DishesSelectClassAdapter;
import com.mx.sy.adapter.ManageDishesAdapter;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.common.RecyclerViewDivider;
import com.mx.sy.common.SearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageDishesActivity extends BaseActivity {

    private LinearLayout ll_back;
    private TextView tv_title;
    private LinearLayout ll_right;
    private ImageView iv_icon;
    private SearchView edit_search_food;
    private RecyclerView rv_ma_dishes;
    private ManageDishesAdapter manageDishesAdapter;
    private List<HashMap<String, String>> mapList;
    private AlertDialog alertDialog;
    private LinearLayout lin_class;

    List<HashMap<String,String>> classList;
    View view;
    private DishesSelectClassAdapter dishesSelectClassAdapter;
    private RecyclerView mrv_dialog;


    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.lin_class:
                view = getLayoutInflater().inflate(R.layout.dialog_rv,null);
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageDishesActivity.this);
                alertBuilder.setTitle("请选择分类");
                alertBuilder.setView(view);
                alertDialog = alertBuilder.create();


                mrv_dialog = view.findViewById(R.id.rv_dialog);
                mrv_dialog.setLayoutManager(new LinearLayoutManager(this));
                mrv_dialog.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));

                DishesSelectClassAdapter dishesSelectClassAdapter = new DishesSelectClassAdapter(R.layout.item_disclass,classList);
                mrv_dialog.setAdapter(dishesSelectClassAdapter);
                dishesSelectClassAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();

                break;
            case R.id.ll_right:
                Intent intent = new Intent(this,ManageDishesAddActivity.class);
                intent.putExtra("pagetype","0");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_manage_dishes;
    }

    @Override
    public void initView(View view) {
        ll_back = $(R.id.ll_back);
        tv_title = $(R.id.tv_title);
        ll_right = $(R.id.ll_right);
        iv_icon = $(R.id.iv_icon);
        rv_ma_dishes = $(R.id.rv_ma_dishes);
        lin_class = $(R.id.lin_class);
    }

    @Override
    protected void initdata() {
        tv_title.setText("菜品管理");
        iv_icon.setImageResource(R.drawable.ic_add);

        rv_ma_dishes.setLayoutManager(new LinearLayoutManager(this));
        rv_ma_dishes.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));

        mapList = new ArrayList<>();
        mapList.add(new HashMap<String, String>());
        mapList.add(new HashMap<String, String>());
        mapList.add(new HashMap<String, String>());
        mapList.add(new HashMap<String, String>());
        manageDishesAdapter = new ManageDishesAdapter(R.layout.item_dishes,mapList);
        rv_ma_dishes.setAdapter(manageDishesAdapter);

        manageDishesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final String[] items = {"售罄","修改","删除"};
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageDishesActivity.this);
                alertBuilder.setTitle("请选择操作");
                alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        alertDialog.dismiss();
                        if (index == 0) {

                        }
                    }
                });
                alertDialog = alertBuilder.create();
                alertDialog.show();
            }
        });
        manageDishesAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ManageDishesActivity.this,ManageDishesAddActivity.class);
                intent.putExtra("pagetype","1");
                startActivity(intent);
            }
        });

        classList = new ArrayList<>();
        classList.add(new HashMap<String, String>());
        classList.add(new HashMap<String, String>());
        classList.add(new HashMap<String, String>());

    }

    @Override
    public void setListener() {
        ll_back.setOnClickListener(this);
        ll_right.setVisibility(View.VISIBLE);
        ll_right.setOnClickListener(this);
        edit_search_food = $(R.id.edit_search_food);
        edit_search_food.addTextChangedListener(editclick);
        edit_search_food.setImeOptions(EditorInfo.IME_ACTION_SEND);
        edit_search_food.setOnEditorActionListener(onEditorActionListener);
        lin_class.setOnClickListener(this);
    }


    @Override
    public void doBusiness(Context mContext) {

    }
    private TextWatcher editclick = new TextWatcher() {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }


        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


        }

        //一般我们都是在这个里面进行我们文本框的输入的判断，上面两个方法用到的很少
        @Override
        public void afterTextChanged(Editable s) {
            String money = edit_search_food.getText().toString();
            Log.d("------",money);
        }
    };

    private TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH){//搜索按键action
                String money = edit_search_food.getText().toString();
                Log.d("------",money);
                return true;
            }
            return false;
        }
    };

}
