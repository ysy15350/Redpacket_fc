package com.ysy15350.redpacket_fc.mine.invitationfriends;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_Invitation_Friends;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import api.AccountAPi;
import api.UserApi;
import api.impl.AccounApiImpl;
import api.impl.UserApiImpl;
import model.invitation.MailList;


public class InvitationFriendsListPresenter extends BasePresenter<InvitationFriendsListViewInterface> {

    public InvitationFriendsListPresenter(Context context) {
        super(context);

    }

    private AccountAPi accountAPi = new AccounApiImpl();


    /**
     * 邀请
     */
    public void invite(String mobile) {

        accountAPi.invite(mobile, new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.inviteCallback(isCache,response);
            }
        });
    }
    /**
     * 已邀请过的手机号
     */
    public void getInviteList(String mobile) {

        accountAPi.getInviteList(mobile,new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.getInviteListCallback(isCache,response);
            }
        });
    }



    /**
     * 获取通信录信息
     */
    public void getphoneneme() {

        try {

            List<MailList> mList = new ArrayList<>();

            //得到ContentResolver对象
            ContentResolver cr = mContext.getContentResolver();
            //取得电话本中开始一项的光标
            Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            //向下移动光标
            while (cursor.moveToNext()) {
                MailList mailList = new MailList();
                //取得联系人名字
                int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
                String contact = cursor.getString(nameFieldColumnIndex);
                mailList.setName(contact);
                //取得电话号码
                String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);

                while (phone.moveToNext()) {
                    String PhoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    mailList.setPhone(PhoneNumber);
                    //格式化手机号
                    PhoneNumber = PhoneNumber.replace("-", "");
                }


                if (mailList != null)
                    mList.add(mailList);
            }

            mView.getphonenemeCallback(mList);


        } catch (Exception e) {
            e.getMessage();
        }


    }






}
