package com.ysy15350.redpacket_fc.main_tabs;

import com.ysy15350.ysyutils.api.model.Response;

public interface MainTab1ViewInterface {

    /**
     * 获取系统时间
     * @param isCache
     * @param response
     */
    void getSystemTimeCallback(boolean isCache, Response response);

    /**
     * 整点红包
     * @param isCache
     * @param response
     */
    void grabRedPacketCallback(boolean isCache, Response response);

    /**
     * 城主信息
     * @param isCache
     * @param response
     */
    void buyCityOwnerCallback(boolean isCache, Response response);

}
