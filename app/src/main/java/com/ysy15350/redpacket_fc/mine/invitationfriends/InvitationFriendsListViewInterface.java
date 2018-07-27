package com.ysy15350.redpacket_fc.mine.invitationfriends;

import com.ysy15350.ysyutils.api.model.Response;

import java.util.List;

import model.invitation.MailList;

/**
 * Created by yangshiyou on 2017/10/30.
 */

public interface InvitationFriendsListViewInterface {

//    /**
//     * 绑定关注列表
//     * @param isCache
//     * @param response
//     */
//    public void bindFollowListCallback(boolean isCache, Response response);

    public void getphonenemeCallback(List<MailList> mailLists);

}
