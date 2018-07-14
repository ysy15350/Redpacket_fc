package com.ysy15350.redpacket_fc.authentication.login;


import com.ysy15350.ysyutils.api.model.Response;

public interface LoginViewInterface {

    /**
     * 获取手机验证码回调方法
     *
     * @param isCache
     * @param response
     */
    public void getDynCodeCallback(boolean isCache, Response response);

    /**
     * 验证码登录回调方法
     *
     * @param isCache
     * @param response
     */
    public void loginByCodeCallback(boolean isCache, Response response);

    //public void loginCallback(boolean isCache, Response response);

    public void activateCallback(boolean isCache, Response response);

}
