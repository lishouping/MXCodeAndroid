package com.mx.sy.app;

import cn.jpush.android.api.JPushInterface;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.mx.sy.utils.ImageLoader;
import com.mx.sy.utils.ImageLoader.Type;
import com.mx.sy.utils.PicassoImageLoader;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MyApplication extends Application {
	public static ImageLoader mLoader;
	@Override
	public void onCreate() {
		super.onCreate();
		mLoader = ImageLoader.getInstance(3, Type.LIFO);
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this); // 初始化 JPush
		ImagePicker imagePicker = ImagePicker.getInstance();
		imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
		imagePicker.setMultiMode(false); //单选false 多选true
		imagePicker.setCrop(false);        //允许裁剪（单选才有效）
	}

	@Override
	protected void attachBaseContext(Context base) {
		MultiDex.install(this);
		super.attachBaseContext(base);
	}
}
