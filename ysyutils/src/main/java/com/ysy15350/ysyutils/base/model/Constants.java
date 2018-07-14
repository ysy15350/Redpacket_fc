package com.ysy15350.ysyutils.base.model;

/**
 * Created by yangshiyou on 2017/12/15.
 */

public class Constants {

    public static final boolean DEBUG = Boolean.parseBoolean("false");

    /**
     * 成功处理标识码
     */
    public static final int RET_SUCCESS = 100;

    /**
     * 表示系统内部错误，请通过联系运维支持，进行问题定位 1、后台逻辑错误 2、数据库访问错误 3、程序处理错误
     *
     */
    public static final int RET_CODE_ERROR = -100;

    /**
     * 用户未登录，需登录 1、用户访问需要登录权限的数据、接口
     */
    public static final int RET_CODE_NO_LOGIN = -101;

    /**
     * 数据格式有误 1、整个数据对象为空 2、对象中的数据字段为空 3、数据格式不符合，校验错误
     */
    public static final int RET_CODE_DATA_FORMAT_ERROR = -102;



}
