package com.ysy15350.redpacket_fc.dailytasks;

import android.content.Context;

import com.ysy15350.ysyutils.base.mvp.BasePresenter;


public class DailyTasksListPresenter extends BasePresenter<DailyTasksListViewInterface> {

    public DailyTasksListPresenter(Context context) {
        super(context);

    }

    public void getDailyTasksList(int page,int pageSize){
        mView.bindDailyTasksListCallback(false,null);
    }

}
