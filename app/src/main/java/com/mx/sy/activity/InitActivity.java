package com.mx.sy.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mx.sy.BuildConfig;
import com.mx.sy.R;
import com.mx.sy.api.ApiConfig;
import com.mx.sy.base.BaseActivity;
import com.mx.sy.common.DownloadTask;
import com.mx.sy.dialog.SweetAlertDialog;

/**
 * <p>Title: InitActivity<／p>
 * <p>Description: <／p>
 * <p>Company: LTGames<／p>
 *
 * @author lishouping
 * @date 2017年4月2日
 */
public class InitActivity extends BaseActivity {

    public SweetAlertDialog sweetAlertDialog;

    private SharedPreferences preferences;

    String autoLogin;
    String telephone;

    @Override
    public void widgetClick(View v) {
        // TODO Auto-generated method stub

    }

    @Override
    public void initParms(Bundle parms) {
        // TODO Auto-generated method stub
    }

    @Override
    public View bindView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int bindLayout() {
        // TODO Auto-generated method stub
        return R.layout.activity_init;
    }

    @Override
    public void initView(View view) {
        // TODO Auto-generated method stub
        preferences = getSharedPreferences("userinfo", LoginActivity.MODE_PRIVATE);
        autoLogin = preferences.getString("autoLogin", "");// 记住密码
        telephone = preferences.getString("telephone", "");

    }

    @Override
    public void setListener() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doBusiness(Context mContext) {
        // TODO Auto-generated method stub
        versionUpdate();
    }

    @Override
    protected void initdata() {
        // TODO Auto-generated method stub
    }

    private void versionUpdate() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = ApiConfig.API_URL + ApiConfig.SERVICEUPDATE;
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                if (arg0 == 200) {
                    try {
                        String response = new String(arg2, "UTF-8");
                        com.orhanobut.logger.Logger.d(response);
                        JSONObject jsonObject = new JSONObject(response);
                        String code = jsonObject.getString("CODE");
                        final JSONObject object = new JSONObject(jsonObject.getString("DATA"));
                        if (code.equals("1000")) {
                            new SweetAlertDialog(InitActivity.this,
                                    SweetAlertDialog.NORMAL_TYPE)
                                    .setTitleText("有新的版本是否更新？")
                                    .setCancelText("取消")
                                    .setConfirmText("确定")
                                    .showCancelButton(true)
                                    .setConfirmClickListener(
                                            new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {

                                                    // 获取下载地址
                                                    String newApkUrl = null;
                                                    try {
                                                        newApkUrl = object.getString("url");
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    DownloadTask downloadTask = new DownloadTask(InitActivity.this);
                                                    downloadTask.execute(newApkUrl);

                                                    sDialog.cancel();
                                                }
                                            })
                                    .setCancelClickListener(
                                            new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {

                                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                                    startActivity(intent);
                                                    dissmissDilog();
                                                    finish();

                                                    sDialog.cancel();
                                                }
                                            }).show();



                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
                // TODO Auto-generated method stub

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //				Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        dissmissDilog();
                        finish();
                    }
                }, 3000);
            }
        });
    }


}
