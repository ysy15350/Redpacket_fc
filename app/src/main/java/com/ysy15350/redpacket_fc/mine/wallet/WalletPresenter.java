package com.ysy15350.redpacket_fc.mine.wallet;

import android.content.Context;

import com.ysy15350.redpacket_fc.mine.setalipay.SetAlipayViewInterface;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;


public class WalletPresenter extends BasePresenter<WalletViewInterface> {

    public WalletPresenter(Context context) {
        super(context);

    }

    public void getAdsCardList(int page,int pageSize){
//        mView.bindAdsCardListCallback(false,null);
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
