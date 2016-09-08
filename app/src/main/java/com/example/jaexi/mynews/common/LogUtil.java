package com.example.jaexi.mynews.common;

import android.util.Log;

/**
 * 日志管理
 */
public class LogUtil {
    /*略*/
    public static final String TAG = "新闻随意看";
    //调试日志的开关
    public static boolean isDebug = true;


    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }
    public static void d(String msg) {
        if (isDebug)
            Log.d(LogUtil.TAG,msg);
    }
}

