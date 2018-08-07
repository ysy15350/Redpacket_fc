package com.ysy15350.redpacket_fc.main_tabs;

import android.content.Context;

import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;

import api.AdApi;
import api.impl.AdApiImpl;


public class MainTab2Presenter extends BasePresenter<MainTab2ViewInterface> {

    public MainTab2Presenter() {
    }

    public MainTab2Presenter(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    private AdApi adApi = new AdApiImpl();

    /**
     * 获取广告信息
     */
    public void getAdInfoList() {
        adApi.getAdInfoList(new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.getAdInfoListCallback(isCache,response);
            }
        });
    }

}
