package com.ysy15350.redpacket_fc.redpackage.open_treasurebox;

import android.content.Context;

import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;

import api.RedpacketApi;
import api.impl.RedpacketApiImpl;


public class OpenTreasureBoxPresenter extends BasePresenter<OpenTreasureBoxViewInterface> {

    public OpenTreasureBoxPresenter(Context context) {
        super(context);

    }

    private RedpacketApi redpacketApi = new RedpacketApiImpl();

    /**
     *  整点红包
     */
    public void grabRedPacket(int type) {

        redpacketApi.grabRedPacket(type,new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.grabRedPacketCallback(isCache,response);
            }
        });
    }

}
