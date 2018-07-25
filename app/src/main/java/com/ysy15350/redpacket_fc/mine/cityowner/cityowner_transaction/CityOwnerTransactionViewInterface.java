package com.ysy15350.redpacket_fc.mine.cityowner.cityowner_transaction;

import com.ysy15350.ysyutils.api.model.Response;

/**
 * Created by yangshiyou on 2017/10/30.
 */

public interface CityOwnerTransactionViewInterface {

    /**
     * 用户协议
     * @param isCache
     * @param response
     */
    public void bindProtocolCallback(boolean isCache, Response response);

}
