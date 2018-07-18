package com.ysy15350.redpacket_fc.mine.invitationfriends;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.ArrayMap;
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
        mPresenter.getFollowList(page, pageSize);
        //获取个人资料
        getphoneneme();
    }

    /**
     * 获取通信录信息
     */
    private void getphoneneme(){

        try{

            //得到ContentResolver对象
            ContentResolver cr = getContentResolver();
            //取得电话本中开始一项的光标
            Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            //向下移动光标
            while(cursor.moveToNext())
            {
                MailList mailList = new MailList();
                //取得联系人名字
                int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
                String contact = cursor.getString(nameFieldColumnIndex);
                mailList.setName(contact);
                //取得电话号码
                String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);

                while(phone.moveToNext())
                {
                    String PhoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    mailList.setPhone(PhoneNumber);
                    //格式化手机号
                    PhoneNumber = PhoneNumber.replace("-","");
                }

                mList.add(mailList);

                if (page == 1) {
                    mList.clear();
                } else {

                    if (mailList == null) {
                        showMsg("没有更多了");
                        xListView.stopLoadMore();
                    }
                }

                if (mailList != null)
                    mList.add(mailList);

                mAdapter = new ListViewAdapter_Invitation_Friends(this, mList);

                bindListView(mAdapter);// 调用父类绑定数据方法

                if (mailList != null ) {
                    page++;
                }

            }


        }catch (Exception e){
            e.getMessage();
        }



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
    protected void bindListView(BaseAdapter mAdapter) {
        super.bindListView(mAdapter);
    }



}
