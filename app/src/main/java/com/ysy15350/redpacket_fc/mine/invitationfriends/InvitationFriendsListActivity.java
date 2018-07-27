package com.ysy15350.redpacket_fc.mine.invitationfriends;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.ArrayMap;
import android.view.View;
import android.widget.BaseAdapter;

import com.google.gson.reflect.TypeToken;
import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_Follow;
import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_Invitation_Friends;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.api.model.ResponseHead;
import com.ysy15350.ysyutils.base.mvp.MVPBaseListViewActivity;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.common.string.JsonConvertor;
import com.ysy15350.ysyutils.model.SysUser;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import model.invitation.MailList;

import static com.ysy15350.ysyutils.common.cache.ACache.aCache;

/**
 * 邀请好友
 */
@ContentView(R.layout.activity_invitationfriends)
public class InvitationFriendsListActivity extends MVPBaseListViewActivity<InvitationFriendsListViewInterface, InvitationFriendsListPresenter>
        implements InvitationFriendsListViewInterface {

    //  缓存未做

    /**
     * adapter
     */
    ListViewAdapter_Invitation_Friends mAdapter;

    /**
     * 联系人
     */
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
    public void initData(int page, int pageSize) {

    }


    @Override
    public void loadData() {
        super.loadData();

        MessageBox.showWaitDialog(this, "正在加载...");

        // 获取手机联系人
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
    public void readCahce() {
        super.readCahce();

        List<MailList> mailLists = null;//从缓存中获取

        try {
            if (aCache != null) {
                String mailListsJson = aCache.getAsString("mailListsJson");
                if (CommFun.notNullOrEmpty(mailListsJson)) {
                    mailLists = JsonConvertor.fromJson(mailListsJson, new TypeToken<List<MailList>>() {
                    }.getType());
                    if (mailLists != null && mailLists.size() > 0) {
                        bindListView(mailLists);
                    }
                }
            }
        } catch (Exception e) {

        }

    }

    @Override
    public void getphonenemeCallback(final List<MailList> mailLists) {

        MessageBox.hideWaitDialog();

        String mobile = ""; // 所有联系人

        if (mailLists != null && mailLists.size() > 0) {
            for (int i = 0; i < mailLists.size(); i++) {
                String phone = mailLists.get(i).getPhone() + ",";
                mobile += phone;
            }

            mList = mailLists;

            // 获取已邀请过的联系人
            mPresenter.getInviteList(mobile);
        }


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

    private void bindListView(List<MailList> mailLists) {

        mList = mailLists;


        mAdapter = new ListViewAdapter_Invitation_Friends(InvitationFriendsListActivity.this, mList);

        bindListView(mAdapter);// 调用父类绑定数据方法
    }

    @Override
    public void inviteCallback(boolean isCache, Response response) {
        try {

            hideWaitDialog();

            if (response != null) {
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {

//                        Object responseBody = response.getBody();
//                        String body = JsonConvertor.toJson(responseBody);

//                        List<MailList> mailLists = JsonConvertor.fromJson(body,new TypeToken<List<MailList>>(){}.getType());
//                        bindListView(mailLists);
//                        mList = mailLists;
//                        if(aCache != null){
//                            String mailListsJson = JsonConvertor.toJson(mailLists);
//                            aCache.put("mailListsJson",mailListsJson);
//                        }
                    }
                    showMsg(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindInviteList(List<SysUser> sysUserList) {
        String userphone = "";
//        int i = mobile.indexOf(mailLists.get(4).getPhone());
//        if (i != -1) {
//
//        }

    }

    @Override
    public void getInviteListCallback(boolean isCache, Response response) {
        try {

            hideWaitDialog();

            if (response != null) {
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {

                        Object responseBody = response.getBody();
                        String body = JsonConvertor.toJson(responseBody);

                        List<SysUser> sysUserList = JsonConvertor.fromJson(body,new TypeToken<List<SysUser>>(){}.getType());
                        if (sysUserList != null && sysUserList.size()>0){
                            bindInviteList(sysUserList);
                        }
                    }
                    showMsg(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 全部选中
     *
     * @param view
     */
    @Event(value = R.id.ll_btn1)
    private void ll_btn1Click(View view) {
        for (MailList mailList : mList) {
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
        for (MailList mailList : mList) {
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
                for (MailList mailList : finalMail) {
                    if (mailList.getStatus() == 1) {
                        String name = mailList.getName();
                        String phone = mailList.getPhone();
                    }
                }
            }
        });
    }

    /**
     * 全部取消
     *
     * @param view
     */
    @Event(value = R.id.llbtn_Invitation)
    private void llbtn_InvitationClick(View view) {


        MessageBox.showWaitDialog(this, "数据提交中...");
        mPresenter.invite("345161321,34135136132");
    }


    @Override
    protected void bindListView(BaseAdapter mAdapter) {
        super.bindListView(mAdapter);
    }


}
