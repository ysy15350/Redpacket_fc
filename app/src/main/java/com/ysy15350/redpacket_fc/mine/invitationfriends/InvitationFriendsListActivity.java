package com.ysy15350.redpacket_fc.mine.invitationfriends;

import android.widget.BaseAdapter;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_Follow;
import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_Invitation_Friends;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.MVPBaseListViewActivity;
import com.ysy15350.ysyutils.common.message.MessageBox;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;


import model.invitation.MailList;

/**
 * 邀请好友
 */
@ContentView(R.layout.activity_invitationfriends)
public class InvitationFriendsListActivity extends MVPBaseListViewActivity<InvitationFriendsListViewInterface, InvitationFriendsListPresenter>
        implements InvitationFriendsListViewInterface {


    /**
     * adapter
     */
    ListViewAdapter_Invitation_Friends mAdapter;

    List<MailList> mList = new ArrayList<>();

    @Override
    protected InvitationFriendsListPresenter createPresenter() {
        return new InvitationFriendsListPresenter(this);
    }

    @Override
    public void initView() {
        super.initView();
        setFormHead("邀请");

    }

    @Override
    protected void onResume() {
        super.onResume();

        page = 1;//从第一页开始
        initData(page, pageSize);


    }

    @Override
    public void initData() {
        super.initData();

        mHolder.setImageURL(R.id.img_head, "", true);
    }

    @Override
    public void initData(int page, int pageSize) {
        MessageBox.showWaitDialog(this, "正在加载");
        mPresenter.getFollowList(page, pageSize);
        //获取个人资料
    }


    private List<MailList> getMailListListFromResponse(Response response) {

        try {
            List<MailList> list = null;
            if (response != null) {
                Object body = response.getBody();
                if (body != null) {

                }
            }
            list = new ArrayList<>();
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());
            list.add(new MailList());


            return list;

        } catch (Exception ex) {
        }

        return null;
    }

    @Override
    public void bindFollowListCallback(boolean isCache, Response response) {

        MessageBox.hideWaitDialog();

        List<MailList> list = getMailListListFromResponse(response);
        if (page == 1) {
            mList.clear();
        } else {

            if (list == null) {
                showMsg("没有更多了");
                xListView.stopLoadMore();
            } else if (list.isEmpty()) {
                showMsg("没有更多了");
                xListView.stopLoadMore();
            }
        }

        if (list != null)
            mList.addAll(list);
        mAdapter = new ListViewAdapter_Invitation_Friends(this, mList);

        bindListView(mAdapter);// 调用父类绑定数据方法

        if (list != null && !list.isEmpty()) {
            page++;
        }
    }



    @Override
    protected void bindListView(BaseAdapter mAdapter) {
        super.bindListView(mAdapter);
    }



}
