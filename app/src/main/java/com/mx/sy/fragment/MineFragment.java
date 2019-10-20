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
import com.mx.sy.activity.StatisticalAnalysisActivity;
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
                            //统计分析
                            intent.setClass(getActivity(), StatisticalAnalysisActivity.class);
                            intent.putExtra("pageType","1");
                            startActivity(intent);
                            break;
                        case 2:
                            //系统设置
                            intent.setClass(getActivity(), StatisticalAnalysisActivity.class);
                            intent.putExtra("pageType","2");
                            startActivity(intent);
                            break;
                        case 3:
                            //推送消息
                            intent.setClass(getActivity(), SettingActivity.class);
                            startActivity(intent);
                            break;
                        case 4:
                            //修改密码
                            intent.setClass(getActivity(), UserInfoActivity.class);
                            startActivity(intent);
                            break;
                        case 5:
                            // 意见反馈
                            intent.setClass(getActivity(), FeedBackActivity.class);
                            startActivity(intent);
                            break;
                        case 6:
                            // 关于我们
                            intent.setClass(getActivity(), AboutUsActivity.class);
                            startActivity(intent);
                            break;

                        case 7:
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
            userInfoMap3.put("mytypeImg", R.drawable.ic_new_16);
            userInfoMap3.put("isnbsp", "0");
            dateList.add(userInfoMap3);

            HashMap<String, Object> userInfoMap4 = new HashMap<String, Object>();
            userInfoMap4.put("content", "密码修改");
            userInfoMap4.put("contentImg", R.drawable.icon_tip);
            userInfoMap4.put("mytypeImg", R.drawable.ic_new_13);
            userInfoMap4.put("isnbsp", "0");
            dateList.add(userInfoMap4);


            HashMap<String, Object> userInfoMap7 = new HashMap<String, Object>();
            userInfoMap7.put("content", "意见反馈");
            userInfoMap7.put("contentImg", R.drawable.icon_tip);
            userInfoMap7.put("mytypeImg", R.drawable.ic_new_17);
            userInfoMap7.put("isnbsp", "0");
            dateList.add(userInfoMap7);

            HashMap<String, Object> userInfoMap8 = new HashMap<String, Object>();
            userInfoMap8.put("content", "关于我们");
            userInfoMap8.put("contentImg", R.drawable.icon_tip);
            userInfoMap8.put("mytypeImg", R.drawable.ic_new_10);
            userInfoMap8.put("isnbsp", "0");
            dateList.add(userInfoMap8);

            return dateList;
        } else {
            List<HashMap<String, Object>> dateList = new ArrayList<HashMap<String, Object>>();
            HashMap<String, Object> userInfoMap1 = new HashMap<String, Object>();
            userInfoMap1.put("content", "统计分析");
            userInfoMap1.put("contentImg", R.drawable.icon_tip);
            userInfoMap1.put("mytypeImg", R.drawable.ic_new_1);
            userInfoMap1.put("isnbsp", "0");
            dateList.add(userInfoMap1);


            HashMap<String, Object> userInfoMapnew1 = new HashMap<String, Object>();
            userInfoMapnew1.put("content", "系统设置");
            userInfoMapnew1.put("contentImg", R.drawable.icon_tip);
            userInfoMapnew1.put("mytypeImg", R.drawable.ic_new_15);
            userInfoMapnew1.put("isnbsp", "0");
            dateList.add(userInfoMapnew1);


            HashMap<String, Object> userInfoMap3 = new HashMap<String, Object>();
            userInfoMap3.put("content", "推送消息");
            userInfoMap3.put("contentImg", R.drawable.icon_tip);
            userInfoMap3.put("mytypeImg", R.drawable.ic_new_16);
            userInfoMap3.put("isnbsp", "0");
            dateList.add(userInfoMap3);

            HashMap<String, Object> userInfoMap4 = new HashMap<String, Object>();
            userInfoMap4.put("content", "密码修改");
            userInfoMap4.put("contentImg", R.drawable.icon_tip);
            userInfoMap4.put("mytypeImg", R.drawable.ic_new_13);
            userInfoMap4.put("isnbsp", "1");
            dateList.add(userInfoMap4);

            HashMap<String, Object> userInfoMap7 = new HashMap<String, Object>();
            userInfoMap7.put("content", "意见反馈");
            userInfoMap7.put("contentImg", R.drawable.icon_tip);
            userInfoMap7.put("mytypeImg", R.drawable.ic_new_17);
            userInfoMap7.put("isnbsp", "0");
            dateList.add(userInfoMap7);

            HashMap<String, Object> userInfoMap8 = new HashMap<String, Object>();
            userInfoMap8.put("content", "关于我们");
            userInfoMap8.put("contentImg", R.drawable.icon_tip);
            userInfoMap8.put("mytypeImg", R.drawable.ic_new_10);
            userInfoMap8.put("isnbsp", "0");
            dateList.add(userInfoMap8);


            HashMap<String, Object> userInfoMap15 = new HashMap<String, Object>();
            userInfoMap15.put("content", "会员查询");
            userInfoMap15.put("contentImg", R.drawable.icon_tip);
            userInfoMap15.put("mytypeImg", R.drawable.ic_new_11);
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
        String url = ApiConfig.API_URL_MEMBER + ApiConfig.FINDONEBYPHONE;
        RequestParams params = new RequestParams();
        params.put("shopId", preferences.getString("menmbers_shop_id", ""));
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
                        String CODE = jsonObject.getString("code");
                        if (CODE.equals("0")) {
                            Toast.makeText(getActivity(),
                                    jsonObject.getString("msg"),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(),
                                    jsonObject.getString("msg"),
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
