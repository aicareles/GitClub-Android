package com.i502tech.gitclub.app;


import com.i502tech.gitclub.BuildConfig;

/**
 * 全局配置类
 */
public class AppConfig {
    public static boolean DEBUG = BuildConfig.DEBUG;
    public static final String PLATFORM = "Android";
    public static final String PACKAGENAME= "com.example.keli";


    // channel_id，用于退出APP账号传递的参数
    public static final String CHANNEL_ID = "channel_id";

    public static final String PERSONAL_DATA = "personal_data";

    public static final String FIRST_INSTALL = "first_install";

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CLOSE_LOGIN = "close_login";
    public static final String QUIT_LOGIN = "quit_login";
    //token过期
    public static final String TOKEN_OUT_TIME = "token_out_time";

    // Fields from build type: debug  发布改为false
    public static final boolean LOG_DEBUG = true;
    public static final String DEBUG_TAG = "logger";// LogCat的标记
    public static final String FLUSH_MY = "flush_my";// 修改个人资料成功。更新我的界面数据，
    public static final String FLUSH_SEX = "flush_sex";// 切换性别，更新数据，
}