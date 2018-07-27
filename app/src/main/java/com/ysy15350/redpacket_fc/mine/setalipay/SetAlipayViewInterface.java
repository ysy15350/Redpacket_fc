package com.ysy15350.redpacket_fc.mine.setalipay;

import com.ysy15350.ysyutils.api.model.Response;

/**
 * Created by yangshiyou on 2017/10/30.
 */

public interface SetAlipayViewInterface {

    /**
     * 修改用户信息回调
     * @param isCache
     * @param response
     */
    public void saveUserInfoCallback(boolean isCache, Response response);

}
