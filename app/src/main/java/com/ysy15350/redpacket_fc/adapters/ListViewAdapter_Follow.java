package com.ysy15350.redpacket_fc.adapters;

import android.content.Context;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.adapters.base.CommonAdapter;
import com.ysy15350.ysyutils.common.ViewHolder;

import java.util.List;

import model.AdsCard;
import model.follow.FollowListInfo;

/**
 * 关注
 */
public class ListViewAdapter_Follow extends CommonAdapter<FollowListInfo> {


    public ListViewAdapter_Follow(Context context, List<FollowListInfo> list) {
        super(context, list, R.layout.list_item_follow);


    }


    @Override
    public void convert(ViewHolder holder, FollowListInfo followList) {
        try {
            if(followList!=null){
                holder.setImageURL(R.id.img1,"");
            }
        } catch (Exception ex) {

        }
    }
}
