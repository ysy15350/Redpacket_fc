package com.ysy15350.redpacket_fc.mine.userinfo;

import android.content.Context;

import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;
import com.ysy15350.ysyutils.model.SysUser;

import java.io.File;

import api.FileApi;
import api.UserApi;
import api.impl.FileApiImpl;
import api.impl.UserApiImpl;


public class UserInfoPresenter extends BasePresenter<UserInfoViewInterface> {

    public UserInfoPresenter(Context context) {
        super(context);

    }

    private UserApi userApi = new UserApiImpl();
    FileApi fileApi = new FileApiImpl();

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


    /**
     * 修改用户信息
     */
    public void saveUserInfo(SysUser sysUser) {

        userApi.saveUserInfo(sysUser, new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.saveUserInfoCallback(isCache,response);
            }
        });


    }

    /**
     * 上传头像
     * @param type
     * @param imgName
     * @param imgData
     */
    public void imgUp(int type, String imgName, File imgData) {
        fileApi.imgUp(type, imgName, imgData, new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.imgUpCallback(isCache, response);
            }
        });
    }

}
