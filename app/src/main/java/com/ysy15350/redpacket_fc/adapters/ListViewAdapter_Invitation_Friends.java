package com.ysy15350.redpacket_fc.adapters;

import android.content.Context;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.adapters.base.CommonAdapter;
import com.ysy15350.ysyutils.common.ViewHolder;

import java.util.List;

import model.follow.FollowListInfo;
import model.invitation.MailList;

/**
 * 邀请好友
 */
public class ListViewAdapter_Invitation_Friends extends CommonAdapter<MailList> {


    public ListViewAdapter_Invitation_Friends(Context context, List<MailList> list) {
        super(context, list, R.layout.list_item_maillist);


    }


    @Override
    public void convert(ViewHolder holder, MailList mailList) {
        try {
            if(mailList !=null){
                holder.setText(R.id.tv_name,mailList.getName());
            }
        } catch (Exception ex) {

        }
    }
}
