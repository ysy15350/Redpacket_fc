package com.ysy15350.redpacket_fc.mine.follow;

import com.ysy15350.ysyutils.api.model.Response;

/**
 * Created by yangshiyou on 2017/10/30.
 */

public interface FollowListViewInterface {

    /**
     * 绑定关注列表
     * @param isCache
     * @param response
     */
    public void bindFollowListCallback(boolean isCache, Response response);

}
