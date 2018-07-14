package com.ysy15350.ysyutils;

import android.app.Application;
import android.content.Context;

import com.ysy15350.ysyutils.api.model.Config;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.model.AppConfig;
import com.ysy15350.ysyutils.base.model.Constants;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.common.CrashHandler;

import org.xutils.x;

/**
 * Created by yangshiyou on 2017/11/29.
 */

public class YSYApplication extends Application {

    //Android 各种坑
    //http://blog.csdn.net/cjpx00008/article/details/52100755


    private static Context applicationContext;

    private static final String TAG = "MyApplication";

    public YSYApplication getInstance() {
        return (YSYApplication) getApplicationContext();
    }

    public static Context getContext() {

        return applicationContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        applicationContext = getApplicationContext();

        AppConfig.setDebug(Constants.DEBUG);
        Config.setDebug(Constants.DEBUG);

        initUtils();//初始化工具类
    }

    CommFunAndroid commFunAndroid = null;


    private void initUtils() {

        Context context = getApplicationContext();
        CommFunAndroid.mContext = context;
        commFunAndroid = CommFunAndroid.getInstance();
        //commFunAndroid.getMemoryInfo();

        BaseData.getInstance();

        //----------------------xUtils3----------------------
        // 网址：https://github.com/wyouflf/xUtils3
        // 参考博客：http://blog.csdn.net/tyk9999tyk/article/details/53306035

        //xUtils缓存目录:/data/user/0/com.ysy15350.startcarunion/databases/xUtils_http_cookie.db  (2)(3)

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.

        //----------------------xUtils3end----------------------


        //----------------------极光推送----------------------
        //考虑项目代码整洁性，根据实际需要添加，参考项目：EasyQuick 和EasyQuickCustom

        //----------------------极光推送end----------------------


        //----------------------闪退错误捕获----------------------

        //记录日志位置：

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

        //----------------------闪退错误捕获end----------------------

        String device_id = CommFunAndroid.getDeviceId(getApplicationContext());
        CommFunAndroid.setSharedPreferences("device_id", device_id);

    }

}