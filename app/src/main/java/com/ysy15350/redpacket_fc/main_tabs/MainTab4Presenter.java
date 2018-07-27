package com.ysy15350.redpacket_fc.main_tabs;

import android.content.Context;

import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;

import api.UserApi;
import api.impl.UserApiImpl;


public class MainTab4Presenter extends BasePresenter<MainTab4ViewInterface> {

    public MainTab4Presenter() {
    }

    public MainTab4Presenter(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    private UserApi userApi = new UserApiImpl();

    /**
     * 获取用户信息
     */
    public void userInfo() {
        userApi.userInfo(new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.userInfoCallback(isCache, response);
            }
        });
    }


}
