package com.ysy15350.redpacket_fc.adapters;

import android.content.Context;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.adapters.base.CommonAdapter;
import com.ysy15350.ysyutils.common.ViewHolder;

import java.util.List;

import model.dailytasks.DailyTasksListInfo;
import model.follow.FollowListInfo;

/**
 * 每日任务Adapter
 */
public class ListViewAdapter_DailyTasks extends CommonAdapter<DailyTasksListInfo> {


    public ListViewAdapter_DailyTasks(Context context, List<DailyTasksListInfo> list) {
        super(context, list, R.layout.list_item_dailytasks);

    }


    @Override
    public void convert(ViewHolder holder, DailyTasksListInfo dailyTasksListInfo) {
        try {
            if(dailyTasksListInfo!=null){
                holder.setImageURL(R.id.img1,"");
            }
        } catch (Exception ex) {

        }
    }
}
