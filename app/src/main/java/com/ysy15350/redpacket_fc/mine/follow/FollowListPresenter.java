package com.ysy15350.redpacket_fc.mine.follow;

import android.content.Context;

import com.ysy15350.redpacket_fc.mine.usercenter.UserCenterViewInterface;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;


public class FollowListPresenter extends BasePresenter<FollowListViewInterface> {

    public FollowListPresenter(Context context) {
        super(context);

    }

    public void getFollowList(int page,int pageSize){
        mView.bindFollowListCallback(false,null);
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
