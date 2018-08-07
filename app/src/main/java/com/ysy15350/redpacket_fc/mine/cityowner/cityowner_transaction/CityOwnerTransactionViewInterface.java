package com.ysy15350.redpacket_fc.mine.cityowner.cityowner_transaction;

import com.ysy15350.ysyutils.api.model.Response;

/**
 * Created by yangshiyou on 2017/10/30.
 */

public interface CityOwnerTransactionViewInterface {

    /**
     * 城主协议
     * @param isCache
     * @param response
     */
    public void bindProtocolCallback(boolean isCache, Response response);

    /**
     * 购买城主
     * @param isCache
     * @param response
     */
    void buyCityOwnerCallback(boolean isCache, Response response);
    /**
     * 生成城主支付订单
     * @param response
     */
    void buildCityOwnerPayOrderCallback(boolean isCache, Response response);


    public void showAliPayResult(String msg);

}
