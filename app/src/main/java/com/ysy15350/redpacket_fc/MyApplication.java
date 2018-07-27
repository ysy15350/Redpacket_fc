package com.ysy15350.redpacket_fc;


import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.ysy15350.redpacket_fc.wxapi.WXEntryActivity;
import com.ysy15350.ysyutils.YSYApplication;
import com.ysy15350.ysyutils.wxAuth.Constants;

/**
 * Created by yangshiyou on 2018/3/6.
 */

public class MyApplication extends YSYApplication {

    public static IWXAPI iwxapi;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化微信组件
        initWeiXin();
    }

    private void initWeiXin() {
        iwxapi = WXEntryActivity.initWeiXin(this, Constants.WEIXIN_APP_ID);
    }
}
