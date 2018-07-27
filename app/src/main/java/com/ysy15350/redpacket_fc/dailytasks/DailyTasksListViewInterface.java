package com.ysy15350.redpacket_fc.dailytasks;

import com.ysy15350.ysyutils.api.model.Response;

/**
 * Created by yangshiyou on 2017/10/30.
 */

public interface DailyTasksListViewInterface {

    /**
     * 绑定每日任务列表
     * @param isCache
     * @param response
     */
    public void bindDailyTasksListCallback(boolean isCache, Response response);

}
