package com.i502tech.gitclub.app;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 创建时间:  2018/1/5
 * 创建人:Alex-Jerry
 * 功能描述: 全局静态常量值
 */

public class C {
    /**
     * SharePreferences常量保存类
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface SP {
        String FIRST_INSTALL = "first_install";//应用是否第一次被安装
        String AREA_CITY = "current_city";//当前城市
        String LANGUAGE = "language";//语言
        String CHINESE = "zh";//中文
        String ENGLISH = "en";//英文
    }

    //API静态
    @Retention(RetentionPolicy.SOURCE)
    public @interface API {
        String BASE_URL = "http://api.e-toys.cn/api/";//服务器路径
        String APP_UPLOAD_INSTALL   = "app/count";//上传安装信息的接口
        String APP_LAST_UPDATE     = "app/lastUpdate";//获取应用新版本
    }

    //设置  id
    @Retention(RetentionPolicy.SOURCE)
    public @interface SET {
        int ID_LANGUAGE = 0;
        int ID_ABOUT = 1;
    }

    //语言  id
    @Retention(RetentionPolicy.SOURCE)
    public @interface LANGUAGE {
        int ID_ZN = 0;
        int ID_EN = 1;
        int ID_PT = 2;
        int ID_FR = 3;
        int ID_DE = 4;
    }

    //主页接收eventbus常量
    @Retention(RetentionPolicy.SOURCE)
    public @interface EVENT {
        String READYPAYING = "ready_paying";//正在准备支付
        String DISH_FINISH = "dish_finish";//提现成功关闭提现部分界面
        String UPDATE_USERINFO = "update_user_info";//更新个人信息
        String SIGN_SUCCESS = "sign_success";//报名成功
    }

    //全局静态常量
    @Retention(RetentionPolicy.SOURCE)
    public @interface Constance {
        String APP_HOST = "http://502tech.com/geekdaily/";
    }

    public static String CURRENT_CITY_NAME = "全国站";

}
