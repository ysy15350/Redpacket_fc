package com.ysy15350.redpacket_fc.redpackage.open_treasurebox;

import com.ysy15350.ysyutils.api.model.Response;

/**
 * Created by yangshiyou on 2017/10/30.
 */

public interface OpenTreasureBoxViewInterface {


    /**
     * 整点红包
     * @param isCache
     * @param response
     */
    void grabRedPacketCallback(boolean isCache, Response response);
}
