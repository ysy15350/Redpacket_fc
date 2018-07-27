package com.ysy15350.redpacket_fc.mine.invitationfriends;

import com.ysy15350.ysyutils.api.model.Response;

import java.util.List;

import model.invitation.MailList;

/**
 * Created by yangshiyou on 2017/10/30.
 */

public interface InvitationFriendsListViewInterface {

    /**
     * 邀请回调
     * @param isCache
     * @param response
     */
    void inviteCallback(boolean isCache, Response response);
    /**
     * 已邀请过的手机号回调
     * @param isCache
     * @param response
     */
    void getInviteListCallback(boolean isCache, Response response);


    /**
     * 获取手机联系人回调
     * @param mailLists
     */
    void getphonenemeCallback(List<MailList> mailLists);

}
