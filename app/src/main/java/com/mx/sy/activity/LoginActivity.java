package com.mx.sy.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.mx.sy.R;
import com.mx.sy.api.ApiConfig;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.push.Logger;
import com.mx.sy.push.TagAliasOperatorHelper;
import com.mx.sy.service.PendingRemindedService;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;

import static com.mx.sy.push.TagAliasOperatorHelper.ACTION_SET;
import static com.mx.sy.push.TagAliasOperatorHelper.TagAliasBean;
import static com.mx.sy.push.TagAliasOperatorHelper.sequence;

/**
 * @author Administrator 登录页面
 */
public class LoginActivity extends BaseActivity {
	private EditText edit_user;
	private EditText edit_pass;
	private Button btn_login;
	private SharedPreferences preferences;

	@Override
	public void widgetClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
			if (isNull()) {
				// 调用登录方法
				userLogin();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void initParms(Bundle parms) {
		// TODO Auto-generated method stub
		preferences = getSharedPreferences("userinfo",
				LoginActivity.MODE_PRIVATE);
		// 动态申请权限
		permissions();
	}

	private void permissions(){
		RxPermissions rxPermissions = new RxPermissions(LoginActivity.this);
		rxPermissions.requestEach(Manifest.permission.INTERNET,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE)
				.subscribe(new Consumer<Permission>() {
					@Override
					public void accept(Permission permission) throws Exception {
						if (permission.granted) {
							Log.d("--------", "已同意");
						}
					}
				});
	}

	@Override
	public View bindView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int bindLayout() {
		// TODO Auto-generated method stub

		return R.layout.activity_login;
	}

	@Override
	public void initView(View view) {
		// TODO Auto-generated method stub
		edit_user = $(R.id.edit_user);
		edit_pass = $(R.id.edit_pass);
		btn_login = $(R.id.btn_login);
		
		String username = preferences.getString("username", "");
		String password = preferences.getString("password", "");
		
		edit_user.setText(username);
		edit_pass.setText(password);
	}

	@Override
	protected void initdata() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		btn_login.setOnClickListener(this);
	}

	@Override
	public void doBusiness(Context mContext) {
		// TODO Auto-generated method stub

	}

	private Boolean isNull() {
		if (edit_user.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(), "请输入用户名",
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (edit_pass.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT)
					.show();
			return false;
		} else {
			return true;
		}
	}

	private void userLogin() {
		showDilog("登录中");
		// 用户登录
		AsyncHttpClient client = new AsyncHttpClient();
		String url = ApiConfig.API_URL + ApiConfig.USERLOGIN_URL;
		RequestParams params = new RequestParams();
		// params.put("password",
		// CommonUtils.md5(edit_pass.getText().toString()));
		params.put("user_name", edit_user.getText().toString());
		params.put("password", edit_pass.getText().toString());
		params.put("type", "2");// Number 1:商户，2:管理员，3:个人用户，4:超级管理员
		client.post(url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				if (arg0 == 200) {
					try {
						String response = new String(arg2, "UTF-8");
						com.orhanobut.logger.Logger.d(response);
						JSONObject jsonObject = new JSONObject(response);
						String CODE = jsonObject.getString("CODE");
						if (CODE.equals("1000")) {

							JSONObject object = new JSONObject(jsonObject
									.getString("DATA"));

							JSONObject object2 = new JSONObject(object
									.getString("waiter"));


							String alias = object.getString("alias");
							String login_key = object.getString("login_key");
							String shop_id = object2.getString("shop_id");
							String name = object2.getString("name");
							String business_id = object.getString("business_id");
							String role_id = object.getString("role_id");

							preferences.edit().putString("userid", business_id)
									.commit();
							preferences.edit().putString("loginkey", login_key)
									.commit();
							preferences.edit().putString("shop_id", shop_id)
									.commit();
							preferences.edit().putString("name", name).commit();
							preferences.edit().putString("business_id", business_id).commit();
							
							preferences.edit().putString("username", edit_user.getText().toString()).commit();
							preferences.edit().putString("password", edit_pass.getText().toString()).commit();
							
							preferences.edit().putString("role_id", role_id).commit();

							/**
							 * 极光推送别名设置
							 * 用于7.0.1
							 */

							TagAliasBean tagAliasBean = new TagAliasBean();
							tagAliasBean.action = ACTION_SET;
							sequence++;
							tagAliasBean.alias = alias;
							tagAliasBean.isAliasAction = true;
							TagAliasOperatorHelper.getInstance().handleAction(getApplicationContext(),sequence,tagAliasBean);


							Intent intent1 = new Intent(LoginActivity.this, PendingRemindedService.class);
							startService(intent1);
							
							Intent intent = new Intent(getApplicationContext(),
									MainActivity.class);
							startActivity(intent);
							finish();

							dissmissDilog();
						} else {
							Toast.makeText(getApplicationContext(),
									jsonObject.getString("MESSAGE"),
									Toast.LENGTH_SHORT).show();
							dissmissDilog();
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						btn_login.setClickable(true);
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
				Log.i("出错了", arg3 + "");
				Toast.makeText(getApplicationContext(), "服务器异常",
						Toast.LENGTH_SHORT).show();
				dissmissDilog();
			}
		});
	}

}
