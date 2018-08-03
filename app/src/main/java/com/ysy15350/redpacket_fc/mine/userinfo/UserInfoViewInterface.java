package com.ysy15350.redpacket_fc.mine.userinfo;

import com.ysy15350.ysyutils.api.model.Response;

/**
 * Created by yangshiyou on 2017/10/30.
 */

public interface UserInfoViewInterface {


    /**
     * 用户信息回调
     * @param isCache
     * @param response
     */
    public void userInfoCallback(boolean isCache, Response response);

    /**
     * 修改用户信息回调
     * @param isCache
     * @param response
     */
    public void saveUserInfoCallback(boolean isCache, Response response);

    /**
     * 上传头像回调
     * @param isCache
     * @param response
     */
    void imgUpCallback(boolean isCache, Response response);
}
