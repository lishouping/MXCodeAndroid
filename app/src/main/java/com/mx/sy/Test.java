package com.mx.sy;

/**
 * Test
 * (c)2018 AIR Times Inc. All rights reserved.
 *
 * @author Lishp
 * @version 1.0
 * @date 2018/8/7 17:33
 * @see
 */
public class Test {
  /*  <manifest xmlns:android="http://schemas.android.com/apk/res/android"
            package="com.mx.sy">

    <!-- Required -->
    <permission
    android:name="com.mx.sy.permission.JPUSH_MESSAGE"
    android:protectionLevel="signature"/>

    <uses-permission android:name="android.permission.CAMERA"/>

    <!-- Required  一些系统要求的权限，如访问网络等-->
    <uses-permission android:name="com.mx.sy.permission.JPUSH_MESSAGE"/>
    <uses-permission android:name="android.permission.RECEIVE_USER_PRESENT"/>
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.WAKE_LOCK"/>
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_SETTINGS"/>
    <uses-permission android:name="android.permission.VIBRATE"/>
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>


    <!-- Optional for location -->
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/> <!-- 用于开启 debug 版本的应用在6.0 系统上 层叠窗口权限 -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
    <uses-permission android:name="android.permission.ACCESS_LOCATION_EXTRA_COMMANDS"/>
    <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.GET_TASKS"/>


    <application
    android:name="com.mx.sy.app.MyApplication"
    android:allowBackup="true"
    android:icon="@drawable/ic_launcher"
    android:label="@string/app_name"
    android:supportsRtl="true"
    android:theme="@style/AppTheme">
        <activity
    android:name="com.mx.sy.activity.InitActivity"
    android:label="@string/app_name"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>

                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>
        <activity
    android:name="com.mx.sy.activity.LoginActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.MainActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>

        <service android:name="com.mx.sy.service.PendingRemindedService"></service>

        <receiver android:name="com.mx.sy.service.AlarmReceiver"></receiver>

        <activity
    android:name="com.mx.sy.activity.QrCodeActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.ServiceDetailedActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.PayImagesActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.OrderUpdateActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.PrintActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.MipcaActivityCapture"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.SelectExtsActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.DeviceListActivity"
    android:label="@string/select_device"
    android:theme="@android:style/Theme.Dialog"
            ></activity>
        <activity
    android:name="com.mx.sy.dialog.TableChangeDialog"
    android:theme="@style/transcutestyle"
            ></activity>
        <activity
    android:name="com.mx.sy.dialog.ClassSelectDialog"
    android:theme="@style/transcutestyle"></activity>
        <activity
    android:name="com.mx.sy.dialog.ReserveDialog"
    android:theme="@style/transcutestyle"></activity>
        <activity
    android:name="com.mx.sy.dialog.PrintOrderDialog"
    android:theme="@style/transcutestyle"></activity>
        <activity
    android:name="com.mx.sy.activity.FoodCustomActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.OrderSubmitActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"
    android:windowSoftInputMode="adjustPan"></activity>
        <activity
    android:name="com.mx.sy.activity.OrderConductActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.OrderEndActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.OrderDetailedActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.push.SettingActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.SalesStatisticsActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.ServiceStatisticsActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.PushSeetingActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.UserInfoActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.PrinterSeetingActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.FeedBackActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name="com.mx.sy.activity.AboutUsActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>

        <!-- Rich push 核心功能 since 2.0.6 -->
        <activity
    android:name="cn.jpush.android.ui.PopWinActivity"
    android:exported="false"
    android:theme="@style/MyDialogStyle"></activity>
        <!-- For test only 测试状态通知栏，需要打开的Activity -->
        <activity
    android:name="com.mx.sy.push.TestActivity"
    android:exported="false">
            <intent-filter>
                <action android:name="jpush.testAction"/>

                <category android:name="jpush.testCategory"/>
            </intent-filter>
        </activity>
        <!-- Required SDK核心功能 -->
        <activity
    android:name="cn.jpush.android.ui.PushActivity"
    android:configChanges="orientation|keyboardHidden"
    android:exported="false"
    android:theme="@android:style/Theme.NoTitleBar">
            <intent-filter>
                <action android:name="cn.jpush.android.ui.PushActivity"/>

                <category android:name="android.intent.category.DEFAULT"/>
                <category android:name="com.mx.sy"/>
            </intent-filter>
        </activity>
        <!-- Required  SDK核心功能 -->
        <service
    android:name="cn.jpush.android.service.DownloadService"
    android:enabled="true"
    android:exported="false"></service>

        <!-- Required SDK 核心功能 -->
        <!-- 可配置android:process参数将PushService放在其他进程中 -->
        <service
    android:name="cn.jpush.android.service.PushService"
    android:process=":mult">
            <intent-filter>
                <action android:name="cn.jpush.android.intent.REGISTER"/>
                <action android:name="cn.jpush.android.intent.REPORT"/>
                <action android:name="cn.jpush.android.intent.PushService"/>
                <action android:name="cn.jpush.android.intent.PUSH_TIME"/>
            </intent-filter>
        </service>

        <provider
    android:name="cn.jpush.android.service.DataProvider"
    android:authorities="com.mx.sy.DataProvider"
    android:exported="false"
            />

        <!-- since 1.8.0 option 可选项。用于同一设备中不同应用的JPush服务相互拉起的功能。 -->
        <!-- 若不启用该功能可删除该组件，将不拉起其他应用也不能被其他应用拉起 -->
        <service
    android:name="cn.jpush.android.service.DaemonService"
    android:enabled="true"
    android:exported="true">
            <intent-filter>
                <action android:name="cn.jpush.android.intent.DaemonService"/>

                <category android:name="com.mx.sy"/>
            </intent-filter>
        </service>
        <!-- since 3.1.0 Required SDK 核心功能-->
        <provider
    android:name="cn.jpush.android.service.DownloadProvider"
    android:authorities="com.mx.sy.DownloadProvider"
    android:exported="true"
            />
        <!-- Required SDK核心功能 -->
        <receiver
    android:name="cn.jpush.android.service.PushReceiver"
    android:enabled="true">
            <intent-filter android:priority="1000">
                <action android:name="cn.jpush.android.intent.NOTIFICATION_RECEIVED_PROXY"/> <!-- Required  显示通知栏 -->
                <category android:name="com.mx.sy"/>
            </intent-filter>
            <intent-filter>
                <action android:name="android.intent.action.USER_PRESENT"/>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
            </intent-filter>
            <!-- Optional -->
            <intent-filter>
                <action android:name="android.intent.action.PACKAGE_ADDED"/>
                <action android:name="android.intent.action.PACKAGE_REMOVED"/>

                <data android:scheme="package"/>
            </intent-filter>
        </receiver>

        <!-- Required SDK核心功能 -->
        <receiver
    android:name="cn.jpush.android.service.AlarmReceiver"
    android:exported="false"/>

        <!-- User defined.  For test only  用户自定义的广播接收器 -->
        <receiver
    android:name="com.mx.sy.push.MyReceiver"
    android:enabled="true"
    android:exported="false">
            <intent-filter>
                <action android:name="cn.jpush.android.intent.REGISTRATION"/> <!-- Required  用户注册SDK的intent -->
                <action android:name="cn.jpush.android.intent.MESSAGE_RECEIVED"/> <!-- Required  用户接收SDK消息的intent -->
                <action android:name="cn.jpush.android.intent.NOTIFICATION_RECEIVED"/> <!-- Required  用户接收SDK通知栏信息的intent -->
                <action android:name="cn.jpush.android.intent.NOTIFICATION_OPENED"/> <!-- Required  用户打开自定义通知栏的intent -->
                <action android:name="cn.jpush.android.intent.CONNECTION"/> <!-- 接收网络变化 连接/断开 since 1.6.3 -->
                <category android:name="com.mx.sy"/>
            </intent-filter>
        </receiver>
        <!-- User defined.  For test only  用户自定义接收消息器,3.0.7开始支持,目前新tag/alias接口设置结果会在该广播接收器对应的方法中回调-->
        <receiver android:name="com.mx.sy.push.MyJPushMessageReceiver">
            <intent-filter>
                <action android:name="cn.jpush.android.intent.RECEIVE_MESSAGE"/>
                <category android:name="com.mx.sy"></category>
            </intent-filter>
        </receiver>
        <!-- Required  . Enable it you can get statistics data with channel -->
        <meta-data
    android:name="JPUSH_CHANNEL"
    android:value="developer-default"/>
        <meta-data
    android:name="JPUSH_APPKEY"
    android:value="3564c52a453a86670ded3ad1"/> <!-- </>值来自开发者平台取得的AppKey -->
    </application>
</manifest>
*/
}
