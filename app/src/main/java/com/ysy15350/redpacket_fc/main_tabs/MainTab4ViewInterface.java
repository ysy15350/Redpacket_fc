package com.ysy15350.redpacket_fc.main_tabs;

import com.ysy15350.ysyutils.api.model.Response;

public interface MainTab4ViewInterface {

    /**
     * 用户信息回调
     * @param isCache
     * @param response
     */
    public void userInfoCallback(boolean isCache, Response response);

    public void activateCallback(boolean isCache, Response response);

}
