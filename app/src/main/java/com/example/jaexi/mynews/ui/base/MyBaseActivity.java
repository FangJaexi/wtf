package com.example.jaexi.mynews.ui.base;

/**
 * Created by Administrator on 2016/8/31 0031.
 */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jaexi.mynews.common.LogUtil;

/**
 * 父类 activity用来调试打印 activity生命周期和节目的进入和退出动画
 */
public class MyBaseActivity extends Activity {
    protected int screen_w, screen_h;

    /*略*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
  /*略*/
        super.onCreate(savedInstanceState);
        screen_w = getWindowManager().getDefaultDisplay().getWidth();
        screen_h = getWindowManager().getDefaultDisplay().getHeight();
  /*略*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(LogUtil.TAG, getClass().getSimpleName() + "onStart()");
    }

    //通过 class名字进行界面跳转并带 Bundle，Uri数据
    public void openActivity(Class<?> pClass, Bundle bundle, Uri uri) {
        Intent intent = new Intent(this, pClass);
        if (bundle != null)
            intent.putExtras(bundle);
        if (uri != null)
            intent.setData(uri);
        startActivity(intent);
    }

    //通过 class名字进行界面跳转只带 Bundle数据
    public void openActivity(Class<?> pClass, Bundle bundle) {
        openActivity(pClass, bundle, null);
    }

    //通过 class名字进行界面跳转
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null, null);
    }

    //通过 action字符串进行界面跳转
    public void openActivity(String action) {
        openActivity(action, null, null);
    }

    //通过 action字符串进行界面跳转，只带 Bundle数据
    public void openActivity(String action, Bundle bundle) {
        openActivity(action, bundle, null);
    }

    //通过 action字符串进行界面跳转，并带 Bundle和 Url数据
    public void openActivity(String action, Bundle bundle, Uri uri) {
        Intent intent = new Intent(action);
        if (bundle != null)
            intent.putExtras(bundle);
        if (uri != null)
            intent.setData(uri);
        startActivity(intent);
    }

    /**************************
     * 公共功能封装
     ****************************************/
    private Toast toast;

    public void showToast(int resId) {
        showToast(getString(resId));
    }

    public void showToast(String msg) {
        if (toast == null)
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    }
}
