package com.mx.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mx.sy.R;
import com.mx.sy.adapter.DishesSelectClassAdapter;
import com.mx.sy.adapter.ManageDishesAdapter;
import com.mx.sy.api.ApiConfig;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.common.RecyclerViewDivider;
import com.mx.sy.common.SearchView;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
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

    private List<HashMap<String, String>> classList;
    View view;
    private DishesSelectClassAdapter dishesSelectClassAdapter;
    private RecyclerView mrv_dialog;

    private SharedPreferences preferences;


    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.lin_class:
                view = getLayoutInflater().inflate(R.layout.dialog_rv, null);
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageDishesActivity.this);
                alertBuilder.setTitle("请选择分类");
                alertBuilder.setView(view);
                alertDialog = alertBuilder.create();


                mrv_dialog = view.findViewById(R.id.rv_dialog);
                mrv_dialog.setLayoutManager(new LinearLayoutManager(this));
                mrv_dialog.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));

                dishesSelectClassAdapter = new DishesSelectClassAdapter(R.layout.item_disclass, classList);
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
                Intent intent = new Intent(this, ManageDishesAddActivity.class);
                intent.putExtra("pagetype", "0");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void initParms(Bundle parms) {
        preferences = getSharedPreferences("userinfo",
                LoginActivity.MODE_PRIVATE);
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
        classList = new ArrayList<>();

        iv_icon.setImageResource(R.drawable.ic_add);

        rv_ma_dishes.setLayoutManager(new LinearLayoutManager(this));
        rv_ma_dishes.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));

        mapList = new ArrayList<>();
        manageDishesAdapter = new ManageDishesAdapter(R.layout.item_dishes, mapList);
        rv_ma_dishes.setAdapter(manageDishesAdapter);

        manageDishesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                final String[] items = {"售罄", "修改", "删除"};
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ManageDishesActivity.this);
                alertBuilder.setTitle("请选择操作");
                alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        alertDialog.dismiss();
                        if (index == 0) {

                        }else if (index==1){
                            Intent intent = new Intent(ManageDishesActivity.this, ManageDishesAddActivity.class);
                            intent.putExtra("pagetype", "2");
                            intent.putExtra("goods_id",mapList.get(position).get("good_id"));
                            intent.putExtra("category_name",mapList.get(position).get("category_name"));
                            startActivity(intent);
                        }else if (index==2){
                            delFood(mapList.get(position).get("good_id"));
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
                Intent intent = new Intent(ManageDishesActivity.this, ManageDishesAddActivity.class);
                intent.putExtra("pagetype", "1");
                intent.putExtra("goods_id",mapList.get(position).get("good_id"));
                intent.putExtra("category_name",mapList.get(position).get("category_name"));
                startActivity(intent);
            }
        });
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
        selectCategory();
    }

    @Override
    protected void onResume() {
        if (mapList.size() > 0) {
            mapList.clear();
            manageDishesAdapter.notifyDataSetChanged();
        }
        selectFood();
        super.onResume();
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
            Log.d("------", money);
        }
    };

    private TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {//搜索按键action
                String money = edit_search_food.getText().toString();
                Log.d("------", money);
                return true;
            }
            return false;
        }
    };

    // // 查询菜品分类(包含菜品)
    public void selectCategory() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.SELECTCATEGORY_URL + "/"
                + preferences.getString("shop_id", "");
        RequestParams params = new RequestParams();
        params.put("shop_id", preferences.getString("shop_id", ""));
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        JSONObject jsonObject = new JSONObject(response);
                        String CODE = jsonObject.getString("CODE");
                        if (CODE.equals("1000")) {
                            dissmissDilog();
                            JSONArray jsonArray = new JSONArray(jsonObject
                                    .getString("DATA"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String category_id = object
                                        .getString("category_id");
                                String category_name = object
                                        .getString("category_name");
                                String category_status = object
                                        .getString("category_status");
                                String create_time = object.getString("create_time");
                                HashMap<String, String> map = new HashMap<String, String>();
                                map.put("category_id", category_id);
                                map.put("classname", category_name);
                                map.put("category_status", category_status);
                                map.put("create_time", create_time);
                                classList.add(map);
                            }
                            dishesSelectClassAdapter.notifyDataSetChanged();
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
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

    // // 查询菜品分类(包含菜品)
    public void selectFood() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.SELECTCATEGORY_URL + "/"
                + preferences.getString("shop_id", "");
        RequestParams params = new RequestParams();
        params.put("shop_id", preferences.getString("shop_id", ""));
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        JSONObject jsonObject = new JSONObject(response);
                        String CODE = jsonObject.getString("CODE");
                        if (CODE.equals("1000")) {
                            dissmissDilog();
                            JSONArray jsonArray = new JSONArray(jsonObject
                                    .getString("DATA"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String category_id = object
                                        .getString("category_id");
                                String category_name = object
                                        .getString("category_name");
                                String category_status = object
                                        .getString("category_status");

                                JSONArray array = new JSONArray(object
                                        .getString("goods_list"));

                                for (int j = 0; j < array.length(); j++) {
                                    JSONObject object2 = array.getJSONObject(j);
                                    String goods_name = object2.getString("goods_name");// 菜品名
                                    String pre_price = object2.getString("pre_price");// 单价
                                    String good_id = object2.getString("good_id");// 商品id

                                    HashMap<String, String> map = new HashMap<String, String>();
                                    map.put("goods_name", goods_name);
                                    map.put("pre_price", pre_price);
                                    map.put("good_id", good_id);
                                    map.put("category_name", category_name);
                                    mapList.add(map);
                                }

                            }
                            manageDishesAdapter.notifyDataSetChanged();
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

    //删除菜品
    public void delFood(String good_id) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.DELGOODS;
        RequestParams params = new RequestParams();
        params.put("good_id", good_id);
        client.post(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        JSONObject jsonObject = new JSONObject(response);
                        String CODE = jsonObject.getString("CODE");
                        if (CODE.equals("1000")) {
                            Toast.makeText(getApplicationContext(), jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                            mapList.clear();
                            manageDishesAdapter.notifyDataSetChanged();
                            selectFood();
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
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


}
