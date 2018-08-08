package com.mx.sy.common;

/**
 * CommonXml
 * (c)2018 AIR Times Inc. All rights reserved.
 *
 * @author Lishp
 * @version 1.0
 * @date 2018/8/8 19:04
 * @see
 */
public class CommonXml {

    activity_manage_dishes.xml

    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#fff"
    android:orientation="vertical">

    <include layout="@layout/common_title"/>

    <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@color/gray_btn_bg_color"
    android:orientation="horizontal">
        <LinearLayout
    android:id="@+id/lin_class"
    android:layout_width="40dp"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical">
            <ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@drawable/ic_todo"
            />
        </LinearLayout>
        <com.mx.sy.common.SearchView
    android:id="@+id/edit_search_food"
    android:layout_width="match_parent"
    android:layout_height="40dp"
    android:layout_marginBottom="5dp"
    android:layout_marginRight="15dp"
    android:layout_marginTop="5dp"
    android:background="@drawable/searchview_bg"
    android:imeOptions="actionSearch"
    android:maxLines="1"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textSize="12sp"
            />
    </LinearLayout>

    <android.support.v7.widget.RecyclerView
    android:id="@+id/rv_ma_dishes"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>


</LinearLayout>

    activity_manage_dishesadd.xml
    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/sweet_dialog_bg_color"
    android:orientation="vertical">

    <include layout="@layout/common_title"/>

    <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1"
    android:orientation="vertical">

        <android.support.v4.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1"
            >

            <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="菜品名称"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_dis_name"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入菜品名称"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
            />
                </LinearLayout>

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="菜品分类"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <Button
    android:id="@+id/select_dis_class"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:text="请选择菜品分类"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
            />
                </LinearLayout>

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="菜品单价"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_dis_price"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入菜品单价"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
            />
                </LinearLayout>

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="菜品折扣"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_dis_discontent"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入菜品折扣"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
            />
                </LinearLayout>

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="菜品简介"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_dis_prief"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入菜品简介"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
            />
                </LinearLayout>
                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="菜品图片"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <ImageView
    android:id="@+id/img_dic_class"
    android:layout_width="60dp"
    android:layout_height="60dp"
    android:layout_marginLeft="30dp"
    android:src="@drawable/ic_default_image"/>

                </LinearLayout>

            </LinearLayout>
        </android.support.v4.widget.NestedScrollView>
    </LinearLayout>

    <Button
    android:id="@+id/btn_sumbit"
    android:layout_width="fill_parent"
    android:layout_height="40dip"
    android:layout_marginBottom="10dp"
    android:layout_marginLeft="30dip"
    android:layout_marginRight="30dip"
    android:layout_marginTop="10dip"
    android:background="@drawable/shape_loginbtn_bg"
    android:text="提交"
    android:textColor="@color/button_text_color"
    android:textSize="15sp"/>
</LinearLayout>

    activity_manage_dishesclass.xml

    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <include layout="@layout/common_title" />


    <android.support.v7.widget.RecyclerView
    android:id="@+id/rv_ma_dishesclass"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />


</LinearLayout>


    activity_manage_employee.xml

    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <include layout="@layout/common_title"/>

    <android.support.v7.widget.RecyclerView
    android:id="@+id/rv_ma_employee"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>

</LinearLayout>


    activity_manage_employeeadd.xml
    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/sweet_dialog_bg_color"
    android:orientation="vertical">

    <include layout="@layout/common_title"/>

    <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1"
    android:orientation="vertical">

        <android.support.v4.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1"
            >

            <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="员工姓名"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_person_name"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入员工姓名"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
            />
                </LinearLayout>

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="登录账号"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_person_number"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入登录账号"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
            />
                </LinearLayout>

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="手机号码"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_person_phone"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入手机号码"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
            />
                </LinearLayout>

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="账号类型"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <Button
    android:id="@+id/select_persontype"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:text="请选择账号类型"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
            />
                </LinearLayout>
            </LinearLayout>
        </android.support.v4.widget.NestedScrollView>
    </LinearLayout>

    <Button
    android:id="@+id/btn_sumbit"
    android:layout_width="fill_parent"
    android:layout_height="40dip"
    android:layout_marginBottom="10dp"
    android:layout_marginLeft="30dip"
    android:layout_marginRight="30dip"
    android:layout_marginTop="10dip"
    android:background="@drawable/shape_loginbtn_bg"
    android:text="提交"
    android:textColor="@color/button_text_color"
    android:textSize="15sp"/>
</LinearLayout>

    activity_manage_shop.xml

    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/sweet_dialog_bg_color"
    android:orientation="vertical">

    <include layout="@layout/common_title"/>

    <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1"
    android:orientation="vertical">

        <android.support.v4.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
            >

            <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

                <RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
            >

                    <TextView
    android:id="@+id/tv1"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_centerVertical="true"
    android:layout_marginStart="15dp"
    android:text="店铺名称"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_shopname"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_alignParentRight="true"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:layout_toRightOf="@+id/tv1"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入店铺名称"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
            />
                </RelativeLayout>

                <RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
            >

                    <TextView
    android:id="@+id/tv2"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_centerVertical="true"
    android:layout_marginLeft="15dp"
    android:text="负责人员"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_shoppersonname"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:layout_toRightOf="@+id/tv2"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入负责人员名称"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
            />
                </RelativeLayout>

                <RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp">

                    <TextView
    android:id="@+id/tv_3"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="联系电话"
    android:layout_centerVertical="true"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_phone"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入负责人员电话"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
    android:layout_toRightOf="@+id/tv_3"
            />
                </RelativeLayout>

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="营业时间"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <LinearLayout
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:orientation="horizontal">

                        <LinearLayout
    android:layout_width="0dp"
    android:layout_height="35dp"
    android:layout_marginRight="5dp"
    android:layout_weight="1"
    android:background="@drawable/shape_edit_bg"
    android:gravity="center"
    android:orientation="horizontal">

                            <Button
    android:id="@+id/btn_start_time"
    android:layout_width="0dp"
    android:layout_height="fill_parent"
    android:layout_weight="1"
    android:background="@null"
    android:gravity="center"
    android:hint="上班时间"
    android:textSize="13sp"/>

                            <ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_margin="10dp"
    android:src="@drawable/calendar"/>
                        </LinearLayout>

                        <LinearLayout
    android:layout_width="0dp"
    android:layout_height="35dp"
    android:layout_marginLeft="5dp"
    android:layout_weight="1"
    android:background="@drawable/shape_edit_bg"
    android:gravity="center"
    android:orientation="horizontal">

                            <Button
    android:id="@+id/btn_end_time"
    android:layout_width="0dp"
    android:layout_height="fill_parent"
    android:layout_weight="1"
    android:background="@null"
    android:gravity="center"
    android:hint="下班时间"
    android:textSize="13sp"/>

                            <ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_margin="10dp"
    android:src="@drawable/calendar"/>
                        </LinearLayout>
                    </LinearLayout>
                </LinearLayout>

                <RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp">

                    <TextView
    android:id="@+id/tv4"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="店铺地址"
    android:textColor="#000"
    android:layout_centerVertical="true"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_address"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入店铺地址"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
    android:layout_toRightOf="@+id/tv4"
            />
                </RelativeLayout>

                <RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
            >

                    <TextView
    android:id="@+id/tv5"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="店铺简介"
    android:textColor="#000"
    android:layout_centerVertical="true"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_info"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入店铺简介"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
    android:layout_toRightOf="@+id/tv5"
            />
                </RelativeLayout>

                <RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
            >

                    <TextView
    android:id="@+id/tv6"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="店铺公告"
    android:textColor="#000"
    android:layout_centerVertical="true"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_notice"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入店铺公告"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
    android:layout_toRightOf="@+id/tv6"
            />
                </RelativeLayout>

                <RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:id="@+id/tv7"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="店铺电话"
    android:textColor="#000"
    android:layout_centerVertical="true"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_shop_phone"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入店铺电话"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
    android:layout_toRightOf="@+id/tv7"
            />
                </RelativeLayout>

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="打印方式"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <Button
    android:id="@+id/btn_selelct_print"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:text="请选择后厨打印方式"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
            />
                </LinearLayout>


                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="厨师下班"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <LinearLayout
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:orientation="horizontal">

                        <LinearLayout
    android:layout_width="0dp"
    android:layout_height="35dp"
    android:layout_marginRight="5dp"
    android:layout_weight="1"
    android:background="@drawable/shape_edit_bg"
    android:gravity="center"
    android:orientation="horizontal">

                            <Button
    android:id="@+id/btn_chef_work"
    android:layout_width="0dp"
    android:layout_height="fill_parent"
    android:layout_weight="1"
    android:background="@null"
    android:gravity="center"
    android:hint="厨师下班时间"
    android:textSize="13sp"/>

                            <ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_margin="10dp"
    android:src="@drawable/calendar"/>
                        </LinearLayout>
                    </LinearLayout>
                </LinearLayout>

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="结账时间"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <LinearLayout
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:orientation="horizontal">

                        <LinearLayout
    android:layout_width="0dp"
    android:layout_height="35dp"
    android:layout_marginRight="5dp"
    android:layout_weight="1"
    android:background="@drawable/shape_edit_bg"
    android:gravity="center"
    android:orientation="horizontal">

                            <Button
    android:id="@+id/btn_checkout_time"
    android:layout_width="0dp"
    android:layout_height="fill_parent"
    android:layout_weight="1"
    android:background="@null"
    android:gravity="center"
    android:hint="最晚结账时间"
    android:textSize="13sp"/>

                            <ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_margin="10dp"
    android:src="@drawable/calendar"/>
                        </LinearLayout>
                    </LinearLayout>
                </LinearLayout>
                <RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
            >

                    <TextView
    android:id="@+id/tv8"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="特色服务"
    android:layout_centerVertical="true"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_service"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入特色服务"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
    android:layout_toRightOf="@+id/tv8"
            />
                </RelativeLayout>
                <RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp">

                    <TextView
    android:id="@+id/tv9"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="招聘信息"
    android:textColor="#000"
    android:layout_centerVertical="true"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_recr_info"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入招聘信息"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
    android:layout_toRightOf="@+id/tv9"
            />
                </RelativeLayout>
                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="100dp"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="店铺图片"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <ImageView
    android:id="@+id/img_shop"
    android:layout_width="60dp"
    android:layout_height="60dp"
    android:layout_marginLeft="30dp"
    android:src="@drawable/ic_default_image"/>

                </LinearLayout>

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="100dp"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="微信支付图片"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <ImageView
    android:id="@+id/img_wx"
    android:layout_width="60dp"
    android:layout_height="60dp"
    android:layout_marginLeft="30dp"
    android:src="@drawable/ic_default_image"/>

                </LinearLayout>

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="100dp"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="支付宝支付图片"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <ImageView
    android:id="@+id/img_alpay"
    android:layout_width="60dp"
    android:layout_height="60dp"
    android:layout_marginLeft="30dp"
    android:src="@drawable/ic_default_image"/>

                </LinearLayout>


            </LinearLayout>
        </android.support.v4.widget.NestedScrollView>
    </LinearLayout>

    <Button
    android:id="@+id/btn_sumbit"
    android:layout_width="fill_parent"
    android:layout_height="40dip"
    android:layout_marginBottom="10dp"
    android:layout_marginLeft="30dip"
    android:layout_marginRight="30dip"
    android:layout_marginTop="10dip"
    android:background="@drawable/shape_loginbtn_bg"
    android:text="提交"
    android:textColor="@color/button_text_color"
    android:textSize="15sp"/>
</LinearLayout>


    activity_manage_table.xml

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <include layout="@layout/common_title" />


    <android.support.v7.widget.RecyclerView
    android:id="@+id/rv_ma_table"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />


</LinearLayout>

    activity_manage_tableadd.xml

    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/sweet_dialog_bg_color"
    android:orientation="vertical">

    <include layout="@layout/common_title"/>

    <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1"
    android:orientation="vertical">

        <android.support.v4.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1"
            >

            <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="桌台名称"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_table_name"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入桌台名称"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
            />
                </LinearLayout>

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="容纳人数"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <EditText
    android:id="@+id/et_table_number"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:hint="请输入桌台容纳人数"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
            />
                </LinearLayout>

                <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="分区名称"
    android:textColor="#000"
    android:textSize="14sp"/>

                    <Button
    android:id="@+id/select_table_partition"
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:layout_marginLeft="30dp"
    android:layout_marginRight="15dp"
    android:background="@drawable/shape_login_bg"
    android:paddingLeft="5dp"
    android:singleLine="true"
    android:text="请选择分区名称"
    android:textColor="@color/text_color"
    android:textColorHint="@color/text_color"
    android:textSize="12sp"
            />
                </LinearLayout>
            </LinearLayout>
        </android.support.v4.widget.NestedScrollView>
    </LinearLayout>

    <Button
    android:id="@+id/btn_sumbit"
    android:layout_width="fill_parent"
    android:layout_height="40dip"
    android:layout_marginBottom="10dp"
    android:layout_marginLeft="30dip"
    android:layout_marginRight="30dip"
    android:layout_marginTop="10dip"
    android:background="@drawable/shape_loginbtn_bg"
    android:text="提交"
    android:textColor="@color/button_text_color"
    android:textSize="15sp"/>
</LinearLayout>


    activity_manage_tablepartition.xml

    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <include layout="@layout/common_title"/>

    <android.support.v7.widget.RecyclerView
    android:id="@+id/rv_ma_tablepartition"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>


</LinearLayout>



    dialog_rv.xml
    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <android.support.v7.widget.RecyclerView
    android:id="@+id/rv_dialog"
    android:layout_width="match_parent"
    android:layout_height="200dp"/>
</LinearLayout>



    item_disclass.xml

    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="40dp"
    android:background="@color/sweet_dialog_bg_color"
    android:gravity="center|left">

    <TextView
    android:id="@+id/tv_content"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="25dp"
    android:textColor="#000"
    android:textSize="16sp"
            />
</LinearLayout>

    item_dishes.xml
    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="40dp"
    android:background="@color/sweet_dialog_bg_color"
    android:orientation="vertical">

    <LinearLayout
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:gravity="center_vertical"
    android:orientation="horizontal"
            >

        <LinearLayout
    android:layout_width="0dp"
    android:layout_height="fill_parent"
    android:layout_weight="1"
    android:gravity="center_vertical"
    android:orientation="vertical">

            <LinearLayout
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                <LinearLayout
    android:layout_width="20dp"
    android:layout_height="20dp"
    android:layout_marginLeft="15dp"
    android:background="@drawable/share_itemnumber"
    android:gravity="center"
    android:orientation="vertical">

                    <TextView
    android:id="@+id/tv_item_num"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="1"
    android:textColor="#fff"
    android:textSize="10sp"/>
                </LinearLayout>

                <TextView
    android:id="@+id/tv_class_name"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:layout_weight="1"
    android:text="菜品名"
    android:textColor="#000"
    android:textSize="12sp"/>

                <TextView
    android:id="@+id/tv_crate_time"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="分类"
    android:textColor="#000"
    android:textSize="12sp"/>

                <TextView
    android:id="@+id/tv_class_state"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:layout_marginRight="15dp"
    android:text="价格"
    android:textColor="@color/price_color"
    android:textSize="12sp"/>

                <LinearLayout
    android:id="@+id/ll_detail"
    android:layout_width="40dp"
    android:layout_height="40dp"
    android:gravity="center"
    android:orientation="vertical">
                    <ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@drawable/ic_arraw_right"
            />
                </LinearLayout>

            </LinearLayout>
        </LinearLayout>

    </LinearLayout>

</LinearLayout>




    item_dishesclass.xml
    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="40dp"
    android:orientation="vertical"
    android:background="@color/sweet_dialog_bg_color">

    <LinearLayout
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:orientation="horizontal"
    android:gravity="center_vertical"
            >

        <LinearLayout
    android:layout_width="0dp"
    android:layout_height="fill_parent"
    android:layout_weight="1"
    android:gravity="center_vertical"
    android:orientation="vertical">

            <LinearLayout
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:gravity="center_vertical"
    android:orientation="horizontal">
                <LinearLayout
    android:layout_width="20dp"
    android:layout_height="20dp"
    android:layout_marginLeft="15dp"
    android:orientation="vertical"
    android:background="@drawable/share_itemnumber"
    android:gravity="center">
                    <TextView
    android:id="@+id/tv_item_num"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textSize="10sp"
    android:textColor="#fff"
    android:text="1"/>
                </LinearLayout>
                <TextView
    android:id="@+id/tv_class_name"
    android:layout_width="0dp"
    android:layout_weight="1"
    android:layout_height="wrap_content"
    android:text="分类名称"
    android:textColor="#000"
    android:layout_marginLeft="15dp"
    android:textSize="12sp"/>
                <TextView
    android:id="@+id/tv_crate_time"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="创建时间"
    android:textColor="#000"
    android:layout_marginLeft="15dp"
    android:layout_marginRight="15dp"
    android:textSize="12sp"/>
                <TextView
    android:id="@+id/tv_class_state"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="状态"
    android:textColor="@color/suborder_color"
    android:layout_marginLeft="15dp"
    android:layout_marginRight="15dp"
    android:textSize="12sp"/>

            </LinearLayout>
        </LinearLayout>

    </LinearLayout>

</LinearLayout>



    item_employee.xml

    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="60dp"
    android:orientation="vertical"
    android:background="@color/sweet_dialog_bg_color">

    <TextView
    android:id="@+id/tv_user_name"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:layout_marginTop="10dp"
    android:text="姓名"
    android:textColor="#000"
    android:textSize="14sp"/>


    <LinearLayout
    android:layout_width="fill_parent"
    android:layout_height="30dp"
    android:orientation="horizontal"
    android:gravity="center_vertical"
            >

        <LinearLayout
    android:layout_width="0dp"
    android:layout_height="fill_parent"
    android:layout_weight="1"
    android:gravity="center_vertical"
    android:orientation="vertical">

            <LinearLayout
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:gravity="center_vertical"
    android:orientation="horizontal">
                <TextView
    android:id="@+id/tv_type"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="账号类型"
    android:textColor="#000"
    android:layout_marginLeft="15dp"
    android:textSize="12sp"/>

                <TextView
    android:id="@+id/tv_phone"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="手机号"
    android:textColor="#000"
    android:layout_marginLeft="15dp"
    android:textSize="12sp"/>

                <TextView
    android:id="@+id/tv_state"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="状态"
    android:textColor="@color/suborder_color"
    android:layout_marginLeft="15dp"
    android:textSize="12sp"/>

            </LinearLayout>
        </LinearLayout>

    </LinearLayout>

</LinearLayout>


    item_table.xml

    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="60dp"
    android:background="@color/sweet_dialog_bg_color"
    android:orientation="vertical">

    <TextView
    android:id="@+id/tv_tabel_name"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:layout_marginTop="10dp"
    android:text="桌台名"
    android:textColor="#000"
    android:textSize="14sp"/>


    <LinearLayout
    android:layout_width="fill_parent"
    android:layout_height="30dp"
    android:gravity="center_vertical"
    android:orientation="horizontal"
            >

        <LinearLayout
    android:layout_width="0dp"
    android:layout_height="fill_parent"
    android:layout_weight="1"
    android:gravity="center_vertical"
    android:orientation="vertical">

            <LinearLayout
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:gravity="center_vertical"
    android:orientation="horizontal">

                <TextView
    android:id="@+id/tv_table_partion"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="分区名"
    android:textColor="#000"
    android:textSize="12sp"/>


                <TextView
    android:id="@+id/tv_table_create_time"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="创建时间"
    android:textColor="#000"
    android:textSize="12sp"/>

                <TextView
    android:id="@+id/tv_table_state"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="桌台状态"
    android:textColor="@color/suborder_color"
    android:textSize="12sp"/>


            </LinearLayout>
        </LinearLayout>

    </LinearLayout>

</LinearLayout>



    item_tablepartition.xml

    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="40dp"
    android:orientation="vertical"
    android:background="@color/sweet_dialog_bg_color">

    <LinearLayout
    android:layout_width="fill_parent"
    android:layout_height="40dp"
    android:orientation="horizontal"
    android:gravity="center_vertical"
            >

        <LinearLayout
    android:layout_width="0dp"
    android:layout_height="fill_parent"
    android:layout_weight="1"
    android:gravity="center_vertical"
    android:orientation="vertical">

            <LinearLayout
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:gravity="center_vertical"
    android:orientation="horizontal">
                <LinearLayout
    android:layout_width="20dp"
    android:layout_height="20dp"
    android:layout_marginLeft="15dp"
    android:orientation="vertical"
    android:background="@drawable/share_itemnumber"
    android:gravity="center">
                    <TextView
    android:id="@+id/tv_item_num"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textSize="10sp"
    android:textColor="#fff"
    android:text="1"/>
                </LinearLayout>
                <TextView
    android:id="@+id/tv_partition_name"
    android:layout_width="0dp"
    android:layout_weight="1"
    android:layout_height="wrap_content"
    android:text="分区名称"
    android:textColor="#000"
    android:layout_marginLeft="15dp"
    android:textSize="12sp"/>

                <TextView
    android:id="@+id/tv_crate_time"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="创建时间"
    android:textColor="#000"
    android:layout_marginLeft="15dp"
    android:layout_marginRight="15dp"
    android:textSize="12sp"/>

            </LinearLayout>
        </LinearLayout>

    </LinearLayout>

</LinearLayout>



<activity
    android:name=".activity.ManageDishesActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"
            />
        <activity
    android:name=".activity.ManageEmployeeActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name=".activity.ManageShopActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name=".activity.ManageDishesClassActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name=".activity.ManageTableActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name=".activity.ManageTablePartitionActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name=".activity.ManageDishesActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name=".activity.ManageEmployeeAddActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name=".activity.ManageTableAddActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>
        <activity
    android:name=".activity.ManageDishesAddActivity"
    android:screenOrientation="portrait"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"></activity>

}
