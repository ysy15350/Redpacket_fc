package com.ysy15350.redpacket_fc.mine.invitationfriends;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.ArrayMap;
import android.view.View;
import android.widget.BaseAdapter;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_Follow;
import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_Invitation_Friends;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.MVPBaseListViewActivity;
import com.ysy15350.ysyutils.common.message.MessageBox;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

    /**
     * 最终要邀请的联系人集合
     */
    List<MailList> finalMail = new ArrayList<>();


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
    public void loadData() {
        super.loadData();

        MessageBox.showWaitDialog(this, "正在加载...");

        mPresenter.getphoneneme();

//        new Thread() {
//
//            @Override
//            public void run() {
//                super.run();
//                mPresenter.getphoneneme();
//            }
//        }.start();
    }

    @Override
    public void bindData() {
        super.bindData();

//        List<MailList> mailLists=null;//从缓存中获取
//        bindListView(mList);
    }

    @Override
    public void getphonenemeCallback(final List<MailList> mailLists) {

        MessageBox.hideWaitDialog();

        bindListView(mailLists);


//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                MessageBox.hideWaitDialog();
//
//                bindListView(mailLists);
//            }
//        });


    }

    private void bindListView(List<MailList> mailLists){

        mList=mailLists;


        mAdapter = new ListViewAdapter_Invitation_Friends(InvitationFriendsListActivity.this, mList);

        bindListView(mAdapter);// 调用父类绑定数据方法
    }

    /**
     * 全部选中
     *
     * @param view
     */
    @Event(value = R.id.ll_btn1)
    private void ll_btn1Click(View view) {
        for (MailList mailList : mList){
            List<MailList> qmailLists = new ArrayList<>();
            mailList.setStatus(1);
            qmailLists.add(mailList);
        }

        mAdapter = new ListViewAdapter_Invitation_Friends(InvitationFriendsListActivity.this, mList);

        bindListView(mAdapter);// 调用父类绑定数据方法

        mAdapter.setMailListsListener(new ListViewAdapter_Invitation_Friends.MailListsListener() {
            @Override
            public void onFinalMailLists(List<MailList> mailLists) {

            }
        });
    }

    /**
     * 全部取消
     *
     * @param view
     */
    @Event(value = R.id.llbtn_cancel)
    private void llbtn_cancelClick(View view) {
        for (MailList mailList : mList){
            List<MailList> qmailLists = new ArrayList<>();
            mailList.setStatus(0);
            qmailLists.add(mailList);
        }

        mAdapter = new ListViewAdapter_Invitation_Friends(InvitationFriendsListActivity.this, mList);

        bindListView(mAdapter);// 调用父类绑定数据方法

        mAdapter.setMailListsListener(new ListViewAdapter_Invitation_Friends.MailListsListener() {
            @Override
            public void onFinalMailLists(List<MailList> mailLists) {
                finalMail = mailLists;
                for(MailList mailList : finalMail){
                    if(mailList.getStatus() == 1){
                        String name = mailList.getName();
                        String phone = mailList.getPhone();
                    }
                }
            }
        });
    }


    @Override
    public void initData(int page, int pageSize) {
        mPresenter.getFollowList(page, pageSize);
    }


    @Override
    protected void bindListView(BaseAdapter mAdapter) {
        super.bindListView(mAdapter);
    }


}
