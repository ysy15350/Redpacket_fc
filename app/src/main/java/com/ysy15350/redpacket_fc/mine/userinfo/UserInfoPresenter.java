package com.ysy15350.redpacket_fc.mine.userinfo;

import android.content.Context;

import com.ysy15350.ysyutils.base.mvp.BasePresenter;

import api.UserApi;
import api.impl.UserApiImpl;


public class UserInfoPresenter extends BasePresenter<UserInfoViewInterface> {

    public UserInfoPresenter(Context context) {
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


}
