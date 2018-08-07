package com.mx.sy.app;

import cn.jpush.android.api.JPushInterface;

import com.mx.sy.utils.ImageLoader;
import com.mx.sy.utils.ImageLoader.Type;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

public class MyApplication extends Application {
	public static ImageLoader mLoader;
	@Override
	public void onCreate() {
		super.onCreate();
		mLoader = ImageLoader.getInstance(3, Type.LIFO);
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this); // 初始化 JPush		
	}

	@Override
	protected void attachBaseContext(Context base) {
		MultiDex.install(this);
		super.attachBaseContext(base);
	}
}
