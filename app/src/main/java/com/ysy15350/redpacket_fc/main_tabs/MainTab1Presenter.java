package com.ysy15350.redpacket_fc.main_tabs;

import android.content.Context;
import android.os.CountDownTimer;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.model.SysUser;

import api.CommonApi;
import api.RedpacketApi;
import api.UserApi;
import api.impl.CommonApiImpl;
import api.impl.RedpacketApiImpl;
import api.impl.UserApiImpl;


public class MainTab1Presenter extends BasePresenter<MainTab1ViewInterface> {

    private static final String TAG = "MainTab1Presenter";

    private Context context;

    public MainTab1Presenter() {
    }

    public MainTab1Presenter(Context context) {
        super(context);

        this.context = context;
    }

    private CommonApi commonApi = new CommonApiImpl();
    private RedpacketApi redpacketApi = new RedpacketApiImpl();

    /**
     * 获取系统时间
     */
    public void getSystemTime() {

        commonApi.getSystemTime(new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.getSystemTimeCallback(isCache,response);
            }
        });

    }

    /**
     *  整点红包
     */
    public void grabRedPacket(int type) {

        redpacketApi.grabRedPacket(type,new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.grabRedPacketCallback(isCache,response);
            }
        });
    }



}
