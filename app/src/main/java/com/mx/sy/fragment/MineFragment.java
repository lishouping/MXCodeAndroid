package com.mx.sy.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mx.sy.R;
import com.mx.sy.activity.AboutUsActivity;
import com.mx.sy.activity.CollectionStatisticsActivity;
import com.mx.sy.activity.FeedBackActivity;
import com.mx.sy.activity.ManageDishesActivity;
import com.mx.sy.activity.ManageDishesClassActivity;
import com.mx.sy.activity.ManageEmployeeActivity;
import com.mx.sy.activity.ManageShopActivity;
import com.mx.sy.activity.ManageTableActivity;
import com.mx.sy.activity.ManageTablePartitionActivity;
import com.mx.sy.activity.MemberStatisticsActivity;
import com.mx.sy.activity.PrinterSeetingActivity;
import com.mx.sy.activity.PushSeetingActivity;
import com.mx.sy.activity.SalesStatisticsActivity;
import com.mx.sy.activity.ServiceStatisticsActivity;
import com.mx.sy.activity.TableStatisticsActivity;
import com.mx.sy.activity.UserInfoActivity;
import com.mx.sy.adapter.MineUserAdapter;
import com.mx.sy.api.ApiConfig;
import com.mx.sy.base.BaseFragment;
import com.mx.sy.common.RoundedImageView;
import com.mx.sy.dialog.SweetAlertDialog;
import com.mx.sy.push.SettingActivity;

import org.apache.http.Header;
import org.json.JSONObject;

public class MineFragment extends BaseFragment {
    private ListView lv_mine_user;
    private MineUserAdapter mineUserAdapter;
    private List<HashMap<String, String>> dateList;
    private RoundedImageView user_round;
    private Button btn_signout;

    LayoutInflater inflater;
    View view;

    private TextView tv_user_name;

    private SharedPreferences preferences;

    public static int islogout = 0;

    private String role_id;

    @Override
    protected int setLayoutResouceId() {
        // TODO Auto-generated method stub
        return R.layout.fragment_mine;
    }

    @Override
    public void onResume() {
        if (islogout == 1) {
            preferences.edit().putString("username", "").commit();
            preferences.edit().putString("password", "").commit();
            getActivity().finish();
            islogout = 0;
        }
        super.onResume();
    }

    @Override
    protected void initData(Bundle arguments) {
        // TODO Auto-generated method stub
        super.initData(arguments);
    }

    @Override
    protected void initView() {
        // TODO Auto-generated method stub
        super.initView();
        lv_mine_user = findViewById(R.id.lv_mine_user);
        btn_signout = findViewById(R.id.btn_signout);
        btn_signout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                // 退出登录
                new SweetAlertDialog(getActivity(),
                        SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("确定要退出登录吗？")
                        // .setContentText("Won't be able to recover this file!")
                        .setCancelText("取消")
                        .setConfirmText("确定")
                        .showCancelButton(true)
                        .setConfirmClickListener(
                                new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(
                                            SweetAlertDialog sDialog) {

                                        //preferences.edit().putString("username", "").commit();
                                        preferences.edit().putString("password", "").commit();

                                        sDialog.cancel();
                                        getActivity().finish();
                                    }
                                })
                        .setCancelClickListener(
                                new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(
                                            SweetAlertDialog sDialog) {
                                        sDialog.cancel();
                                    }
                                }).show();
            }
        });
        inflater = (LayoutInflater) getActivity().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.headview_mine, null);

        tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
        user_round = (RoundedImageView) view.findViewById(R.id.user_round);
        user_round.setOval(true);
    }

    @Override
    protected void onLazyLoad() {
        // TODO Auto-generated method stub
        super.onLazyLoad();

        preferences = getActivity().getSharedPreferences("userinfo",
                getActivity().MODE_PRIVATE);
        role_id = preferences.getString("role_id", "");

        String name = preferences.getString("name", "");
        tv_user_name.setText(name);

        // dateList = new ArrayList<HashMap<String,String>>();
        mineUserAdapter = new MineUserAdapter(mActivity, makeDate(),
                R.layout.item_mineuser);
        lv_mine_user.addHeaderView(view);
        lv_mine_user.setAdapter(mineUserAdapter);
        lv_mine_user.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                if (role_id.equals("2")) {
                    Intent intent = new Intent();
                    switch (position) {
                        case 1:
                            //推送消息
                            intent.setClass(getActivity(), SettingActivity.class);
                            startActivity(intent);
                            break;
                        case 2:
                            //个人信息
                            intent.setClass(getActivity(), UserInfoActivity.class);
                            startActivity(intent);
                            break;
                        case 3:
                            // 意见反馈
                            intent.setClass(getActivity(), FeedBackActivity.class);
                            startActivity(intent);
                            break;
                        case 4:
                            // 关于我们
                            intent.setClass(getActivity(), AboutUsActivity.class);
                            startActivity(intent);
                            break;
                        default:
                            break;
                    }
                } else {
                    Intent intent = new Intent();
                    switch (position) {
                        case 1:
                            //销售统计
                            intent.setClass(getActivity(), SalesStatisticsActivity.class);
                            startActivity(intent);
                            break;
                        case 2:
                            //收款统计
                            intent.setClass(getActivity(), CollectionStatisticsActivity.class);
                            startActivity(intent);
                            break;
                        case 3:
                            //“桌台统计”
                            intent.setClass(getActivity(), TableStatisticsActivity.class);
                            startActivity(intent);
                            break;
                        case 4:
                            //“会员统计”
                            intent.setClass(getActivity(), MemberStatisticsActivity.class);
                            startActivity(intent);
                            break;
                        case 5:
                            //服务统计
                            intent.setClass(getActivity(), ServiceStatisticsActivity.class);
                            startActivity(intent);
                            break;
                        case 6:
                            //推送消息
                            intent.setClass(getActivity(), SettingActivity.class);
                            startActivity(intent);
                            break;
                        case 7:
                            //个人信息
                            intent.setClass(getActivity(), UserInfoActivity.class);
                            startActivity(intent);
                            break;
                        case 8:
                            // 意见反馈
                            intent.setClass(getActivity(), FeedBackActivity.class);
                            startActivity(intent);
                            break;
                        case 9:
                            // 关于我们
                            intent.setClass(getActivity(), AboutUsActivity.class);
                            startActivity(intent);
                            break;
                        case 10:
                            // 店铺管理
                            intent.setClass(getActivity(), ManageShopActivity.class);
                            startActivity(intent);
                            break;

                        case 11:
                            //打印机设置
                            intent.setClass(getActivity(), PrinterSeetingActivity.class);
                            startActivity(intent);
                            break;
                        case 12:
                            // 员工管理
                            intent.setClass(getActivity(), ManageEmployeeActivity.class);
                            startActivity(intent);
                            break;
                        case 13:
                            // 桌台分区
                            intent.setClass(getActivity(), ManageTablePartitionActivity.class);
                            startActivity(intent);
                            break;
                        case 14:
                            // 桌台管理
                            intent.setClass(getActivity(), ManageTableActivity.class);
                            startActivity(intent);
                            break;
                        case 15:
                            // 菜品分类
                            intent.setClass(getActivity(), ManageDishesClassActivity.class);
                            startActivity(intent);
                            break;
                        case 16:
                            // 菜品管理
                            intent.setClass(getActivity(), ManageDishesActivity.class);
                            startActivity(intent);
                            break;
                        case 17:
                            // 会员查询
                            LayoutInflater factory = LayoutInflater
                                    .from(getActivity());
                            final View textEntryView = factory
                                    .inflate(
                                            R.layout.jian_food_dialog,
                                            null);
                            final EditText textjianshao = textEntryView
                                    .findViewById(R.id.text_editjiannumbe);
                            textjianshao.setHint("请输入会员手机号");
                            AlertDialog.Builder ad1 = new AlertDialog.Builder(
                                    getActivity());
                            ad1.setTitle("会员查询");
                            ad1.setIcon(R.drawable.ic_launcher);
                            ad1.setView(textEntryView);
                            ad1.setPositiveButton(
                                    "查询",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int i) {
                                            String number = textjianshao
                                                    .getText()
                                                    .toString();
                                            if (number.equals("")) {
                                                Toast.makeText(getActivity(), "请填会员手机号", Toast.LENGTH_SHORT)
                                                        .show();
                                            }else {
                                                search(number);

                                            }
                                        }
                                    });
                            ad1.setNegativeButton(
                                    "关闭",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int i) {

                                        }
                                    });
                            ad1.show();// 显示对话框
                            break;
                        default:
                            break;
                    }
                }

            }
        });
    }

    private List<HashMap<String, Object>> makeDate() {
        if (role_id.equals("2")) {//服务员
            List<HashMap<String, Object>> dateList = new ArrayList<HashMap<String, Object>>();

            HashMap<String, Object> userInfoMap3 = new HashMap<String, Object>();
            userInfoMap3.put("content", "推送消息");
            userInfoMap3.put("contentImg", R.drawable.icon_tip);
            userInfoMap3.put("mytypeImg", R.drawable.icon_my3);
            userInfoMap3.put("isnbsp", "0");
            dateList.add(userInfoMap3);

            HashMap<String, Object> userInfoMap4 = new HashMap<String, Object>();
            userInfoMap4.put("content", "密码修改");
            userInfoMap4.put("contentImg", R.drawable.icon_tip);
            userInfoMap4.put("mytypeImg", R.drawable.icon_my4);
            userInfoMap4.put("isnbsp", "0");
            dateList.add(userInfoMap4);


            HashMap<String, Object> userInfoMap7 = new HashMap<String, Object>();
            userInfoMap7.put("content", "意见反馈");
            userInfoMap7.put("contentImg", R.drawable.icon_tip);
            userInfoMap7.put("mytypeImg", R.drawable.icon_my6);
            userInfoMap7.put("isnbsp", "0");
            dateList.add(userInfoMap7);

            HashMap<String, Object> userInfoMap8 = new HashMap<String, Object>();
            userInfoMap8.put("content", "关于我们");
            userInfoMap8.put("contentImg", R.drawable.icon_tip);
            userInfoMap8.put("mytypeImg", R.drawable.icon_my7);
            userInfoMap8.put("isnbsp", "0");
            dateList.add(userInfoMap8);

            return dateList;
        } else {
            List<HashMap<String, Object>> dateList = new ArrayList<HashMap<String, Object>>();
            HashMap<String, Object> userInfoMap1 = new HashMap<String, Object>();
            userInfoMap1.put("content", "销售统计");
            userInfoMap1.put("contentImg", R.drawable.icon_tip);
            userInfoMap1.put("mytypeImg", R.drawable.icon_my1);
            userInfoMap1.put("isnbsp", "0");
            dateList.add(userInfoMap1);


            HashMap<String, Object> userInfoMapnew1 = new HashMap<String, Object>();
            userInfoMapnew1.put("content", "收款统计");
            userInfoMapnew1.put("contentImg", R.drawable.icon_tip);
            userInfoMapnew1.put("mytypeImg", R.drawable.icon_my2);
            userInfoMapnew1.put("isnbsp", "0");
            dateList.add(userInfoMapnew1);

            HashMap<String, Object> userInfoMapnew2 = new HashMap<String, Object>();
            userInfoMapnew2.put("content", "桌台统计");
            userInfoMapnew2.put("contentImg", R.drawable.icon_tip);
            userInfoMapnew2.put("mytypeImg", R.drawable.icon_my2);
            userInfoMapnew2.put("isnbsp", "0");
            dateList.add(userInfoMapnew2);

            HashMap<String, Object> userInfoMapnew3 = new HashMap<String, Object>();
            userInfoMapnew3.put("content", "会员统计");
            userInfoMapnew3.put("contentImg", R.drawable.icon_tip);
            userInfoMapnew3.put("mytypeImg", R.drawable.icon_my2);
            userInfoMapnew3.put("isnbsp", "0");
            dateList.add(userInfoMapnew3);



            HashMap<String, Object> userInfoMap2 = new HashMap<String, Object>();
            userInfoMap2.put("content", "服务统计");
            userInfoMap2.put("contentImg", R.drawable.icon_tip);
            userInfoMap2.put("mytypeImg", R.drawable.icon_my2);
            userInfoMap2.put("isnbsp", "0");
            dateList.add(userInfoMap2);


            HashMap<String, Object> userInfoMap3 = new HashMap<String, Object>();
            userInfoMap3.put("content", "推送消息");
            userInfoMap3.put("contentImg", R.drawable.icon_tip);
            userInfoMap3.put("mytypeImg", R.drawable.icon_my3);
            userInfoMap3.put("isnbsp", "0");
            dateList.add(userInfoMap3);

            HashMap<String, Object> userInfoMap4 = new HashMap<String, Object>();
            userInfoMap4.put("content", "密码修改");
            userInfoMap4.put("contentImg", R.drawable.icon_tip);
            userInfoMap4.put("mytypeImg", R.drawable.icon_my4);
            userInfoMap4.put("isnbsp", "1");
            dateList.add(userInfoMap4);

            HashMap<String, Object> userInfoMap7 = new HashMap<String, Object>();
            userInfoMap7.put("content", "意见反馈");
            userInfoMap7.put("contentImg", R.drawable.icon_tip);
            userInfoMap7.put("mytypeImg", R.drawable.icon_my6);
            userInfoMap7.put("isnbsp", "0");
            dateList.add(userInfoMap7);

            HashMap<String, Object> userInfoMap8 = new HashMap<String, Object>();
            userInfoMap8.put("content", "关于我们");
            userInfoMap8.put("contentImg", R.drawable.icon_tip);
            userInfoMap8.put("mytypeImg", R.drawable.icon_my7);
            userInfoMap8.put("isnbsp", "0");
            dateList.add(userInfoMap8);

            HashMap<String, Object> userInfoMap9 = new HashMap<String, Object>();
            userInfoMap9.put("content", "店铺管理");
            userInfoMap9.put("contentImg", R.drawable.icon_tip);
            userInfoMap9.put("mytypeImg", R.drawable.icon_my7);
            userInfoMap9.put("isnbsp", "1");
            dateList.add(userInfoMap9);

            HashMap<String, Object> userInfoMap5 = new HashMap<String, Object>();
            userInfoMap5.put("content", "打印机设置");
            userInfoMap5.put("contentImg", R.drawable.icon_tip);
            userInfoMap5.put("mytypeImg", R.drawable.icon_my5);
            userInfoMap5.put("isnbsp", "0");
            dateList.add(userInfoMap5);

            HashMap<String, Object> userInfoMap10 = new HashMap<String, Object>();
            userInfoMap10.put("content", "员工管理");
            userInfoMap10.put("contentImg", R.drawable.icon_tip);
            userInfoMap10.put("mytypeImg", R.drawable.icon_my7);
            userInfoMap10.put("isnbsp", "0");
            dateList.add(userInfoMap10);

            HashMap<String, Object> userInfoMap11 = new HashMap<String, Object>();
            userInfoMap11.put("content", "桌台分区");
            userInfoMap11.put("contentImg", R.drawable.icon_tip);
            userInfoMap11.put("mytypeImg", R.drawable.icon_my7);
            userInfoMap11.put("isnbsp", "0");
            dateList.add(userInfoMap11);

            HashMap<String, Object> userInfoMap12 = new HashMap<String, Object>();
            userInfoMap12.put("content", "桌台管理");
            userInfoMap12.put("contentImg", R.drawable.icon_tip);
            userInfoMap12.put("mytypeImg", R.drawable.icon_my7);
            userInfoMap12.put("isnbsp", "0");
            dateList.add(userInfoMap12);

            HashMap<String, Object> userInfoMap13 = new HashMap<String, Object>();
            userInfoMap13.put("content", "菜品分类");
            userInfoMap13.put("contentImg", R.drawable.icon_tip);
            userInfoMap13.put("mytypeImg", R.drawable.icon_my7);
            userInfoMap13.put("isnbsp", "0");
            dateList.add(userInfoMap13);

            HashMap<String, Object> userInfoMap14 = new HashMap<String, Object>();
            userInfoMap14.put("content", "菜品管理");
            userInfoMap14.put("contentImg", R.drawable.icon_tip);
            userInfoMap14.put("mytypeImg", R.drawable.icon_my7);
            userInfoMap14.put("isnbsp", "0");
            dateList.add(userInfoMap14);

            HashMap<String, Object> userInfoMap15 = new HashMap<String, Object>();
            userInfoMap15.put("content", "会员查询");
            userInfoMap15.put("contentImg", R.drawable.icon_tip);
            userInfoMap15.put("mytypeImg", R.drawable.icon_my7);
            userInfoMap15.put("isnbsp", "0");
            dateList.add(userInfoMap15);


            return dateList;
        }

    }
    // 会员查询
    public void search(String phone) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("key", preferences.getString("loginkey", ""));
        client.addHeader("id", preferences.getString("userid", ""));
        String url = ApiConfig.API_URL + ApiConfig.FINDONEBYPHONE;
        RequestParams params = new RequestParams();
        params.put("shop_id", preferences.getString("shop_id", ""));
        params.put("userPhone", phone);
        client.post(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        com.orhanobut.logger.Logger.d(response);
                        JSONObject jsonObject = new JSONObject(response);
                        String CODE = jsonObject.getString("CODE");
                        if (CODE.equals("1000")) {
                            Toast.makeText(getActivity(),
                                    jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(),
                                    jsonObject.getString("MESSAGE"),
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "服务器异常",
                                Toast.LENGTH_SHORT).show();
                        dissmissDilog();
                    }
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "服务器异常",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
