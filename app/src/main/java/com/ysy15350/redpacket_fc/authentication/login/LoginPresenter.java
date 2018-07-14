package com.ysy15350.redpacket_fc.authentication.login;

import android.content.Context;

import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;

import api.UserApi;
import api.impl.UserApiImpl;


public class LoginPresenter extends BasePresenter<LoginViewInterface> {

    public LoginPresenter(Context context) {
        super(context);

    }

    UserApi userApi = new UserApiImpl();

    /**
     * 获取手机验证码
     *
     * @param mobile
     */
    public void getDynCode(String mobile) {

        userApi.getDynCode(mobile, 3, new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.getDynCodeCallback(isCache, response);
            }
        });
    }

    public void loginByCode(String mobile, String code) {
        userApi.loginByCode(mobile, code, new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.loginByCodeCallback(isCache, response);
            }
        });
    }


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
