package com.ysy15350.redpacket_fc.mine.invitationfriends;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_Invitation_Friends;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import model.invitation.MailList;


public class InvitationFriendsListPresenter extends BasePresenter<InvitationFriendsListViewInterface> {

    public InvitationFriendsListPresenter(Context context) {
        super(context);

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

                mList.add(mailList);


                if (mailList != null)
                    mList.add(mailList);


                mView.getphonenemeCallback(mList);


            }


        } catch (Exception e) {
            e.getMessage();
        }


    }


    public void getFollowList(int page, int pageSize) {
//        mView.bindFollowListCallback(false,null);
    }

//    private UserApi userApi=new UserApiImpl();
//
//    public void login(){
//        userApi.login("test", "test", new ApiCallBack() {
//            @Override
//            public void onSuccess(boolean isCache, Response response) {
//                super.onSuccess(isCache, response);
//            }
//        });
//    }
//
//    public void activate() {
//        userApi.activate(new ApiCallBack() {
//            @Override
//            public void onSuccess(boolean isCache, Response response) {
//                super.onSuccess(isCache, response);
//                mView.activateCallback(isCache, response);
//            }
//        });
//    }

}
