package com.ysy15350.redpacket_fc;

import android.content.Context;

import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;

import api.UserApi;
import api.impl.UserApiImpl;


public class GuidePresenter extends BasePresenter<GuideViewInterface> {

    public GuidePresenter(Context context) {
        super(context);

    }

    private UserApi userApi = new UserApiImpl();

    //    public void login(){
//        userApi.login("test", "test", new ApiCallBack() {
//            @Override
//            public void onSuccess(boolean isCache, Response response) {
//                super.onSuccess(isCache, response);
//            }
//        });
//    }
//
    public void activate() {
        userApi.activate(new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.activateCallback(isCache, response);
            }
        });
    }

}
