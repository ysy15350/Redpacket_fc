package api.impl;

import com.ysy15350.ysyutils.Ysy;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.RequestOptions;

import api.RedpacketApi;

public class RedpacketApiImpl implements RedpacketApi {

    private static final String moduleName = "api/redpacket/";

    @Override
    public void grabRedPacket(int type, ApiCallBack callBack) {
        RequestOptions requestOptions = new RequestOptions.Builder()
                .setRequestMapping(moduleName + "grabRedPacket")
                .addBodyParameter("type", type)
                .build();

        Ysy.http().requestPost(requestOptions, callBack);
    }
}
