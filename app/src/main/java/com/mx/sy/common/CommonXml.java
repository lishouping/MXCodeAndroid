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

    /*


    item_print.xml
    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="60dp"
    android:background="@color/sweet_dialog_bg_color"
    android:orientation="vertical">

    <TextView
    android:id="@+id/tv_print_name"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:layout_marginTop="10dp"
    android:text="打印机名称"
    android:textColor="#000"
    android:textSize="14sp"/>


    <LinearLayout
    android:layout_width="fill_parent"
    android:layout_height="30dp"
    android:gravity="center_vertical"
    android:orientation="horizontal">

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
    android:id="@+id/tv_print_type"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:layout_weight="1"
    android:text="类型"
    android:textColor="#000"
    android:textSize="12sp"/>

                <TextView
    android:id="@+id/tv_print_number"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:layout_weight="1.3"
    android:text="手机号"
    android:textColor="#000"
    android:textSize="12sp"/>

                <TextView
    android:id="@+id/tv_print_status"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:layout_marginRight="15dp"
    android:layout_weight="1"
    android:text="状态"
    android:textColor="@color/suborder_color"
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
        android:orientation="horizontal">

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
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginLeft="15dp"
                    android:layout_weight="1"
                    android:text="分区名"
                    android:textColor="#000"
                    android:textSize="12sp"/>


                <TextView
                    android:id="@+id/tv_table_create_time"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginLeft="15dp"
                    android:layout_weight="1.3"
                    android:text="创建时间"
                    android:textColor="#000"
                    android:textSize="12sp"/>

                <TextView
                    android:id="@+id/tv_table_state"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginLeft="15dp"
                    android:layout_weight="1"
                    android:text="桌台状态"
                    android:textColor="@color/suborder_color"
                    android:textSize="12sp"/>


            </LinearLayout>
        </LinearLayout>

    </LinearLayout>

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
        android:orientation="horizontal">

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
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginLeft="15dp"
                    android:layout_weight="0.8"
                    android:text="分类"
                    android:textColor="#000"
                    android:textSize="12sp"/>

                <TextView
                    android:id="@+id/tv_class_state"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginLeft="15dp"
                    android:layout_marginRight="15dp"
                    android:layout_weight="0.6"
                    android:text="价格"
                    android:textColor="@color/price_color"
                    android:textSize="12sp"/>

                <LinearLayout
                    android:id="@+id/ll_detail"
                    android:layout_width="40dp"
                    android:layout_height="40dp"
                    android:gravity="center"
                    android:orientation="vertical"
                    android:visibility="gone">

                    <ImageView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:src="@drawable/ic_arraw_right"/>
                </LinearLayout>

            </LinearLayout>
        </LinearLayout>

    </LinearLayout>

</LinearLayout>


*/


}
