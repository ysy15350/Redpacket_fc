package com.ysy15350.ysyutils.base.model;

/**
 * Created by yangshiyou on 2017/12/15.
 */

public class AppConfig {

    /**
     * 是否是调试模式
     */
    private static boolean isDebug = false;

    public static void setDebug(boolean debug) {
        AppConfig.isDebug = debug;
    }

    public static boolean isDebug() {
        return AppConfig.isDebug;
    }

}
