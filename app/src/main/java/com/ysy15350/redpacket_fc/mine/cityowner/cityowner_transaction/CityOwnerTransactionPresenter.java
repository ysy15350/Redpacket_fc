package com.ysy15350.redpacket_fc.mine.cityowner.cityowner_transaction;

import android.content.Context;

import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;

import api.CommonApi;
import api.impl.CommonApiImpl;


public class CityOwnerTransactionPresenter extends BasePresenter<CityOwnerTransactionViewInterface> {

    public CityOwnerTransactionPresenter(Context context) {
        super(context);

    }

    private CommonApi commonApi = new CommonApiImpl();

    /**
     * 获取用户协议
     */
    public void getProtocol(){
        commonApi.getProtocol(new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.bindProtocolCallback(isCache,response);
            }

            @Override
            public void onFailed(String msg) {
                super.onFailed(msg);
            }
        });
    }

}
