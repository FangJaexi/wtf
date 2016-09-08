package com.example.jaexi.mynews.common;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public class CommonUtil {
    /** 联网的 ip */
    public static String NETIP = "192.168.2.134";
    /** 联网的路径 */
    public static String NETPATH = "http://" + NETIP + ":8080/newsClient";
    /** SharedPreferences保存用户名键 */
    public static final String SHARE_USER_NAME = "fang";
    /** SharedPreferences保存用户名密码 */
    public static final String SHARE_USER_PWD = "1234";
    /** SharedPreferences保存是否第一次登陆 */
    public static final String SHARE_IS_FIRST_RUN = "isFirstRun";
    /** SharedPreferences存储路径 */
    public static final String SHAREPATH = "news_share";
}