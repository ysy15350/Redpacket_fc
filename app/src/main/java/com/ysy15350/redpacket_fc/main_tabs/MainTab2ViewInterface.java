package com.ysy15350.redpacket_fc.main_tabs;

import com.ysy15350.ysyutils.api.model.Response;

public interface MainTab2ViewInterface {

    /**
     * 广告信息回调
     * @param isCache
     * @param response
     */
    public void getAdInfoListCallback(boolean isCache, Response response);

}
