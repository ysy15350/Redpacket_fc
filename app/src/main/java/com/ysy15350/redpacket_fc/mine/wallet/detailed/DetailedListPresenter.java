package com.ysy15350.redpacket_fc.mine.wallet.detailed;

import android.content.Context;

import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;

import api.OrderApi;
import api.impl.OrderApiImpl;


public class DetailedListPresenter extends BasePresenter<DetailedListViewInterface> {

    public DetailedListPresenter(Context context) {
        super(context);

    }

    private OrderApi orderApi = new OrderApiImpl();


    /**
     * 交易明细列表
     * @param page
     * @param pageSize
     */
    public void orderList(int page,int pageSize){
        orderApi.orderList(page, pageSize, new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.orderListCallback(isCache,response);
            }
        });
    }

}
