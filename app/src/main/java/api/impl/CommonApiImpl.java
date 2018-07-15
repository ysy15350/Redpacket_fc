package api.impl;

import com.ysy15350.ysyutils.Ysy;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.RequestOptions;

import api.CommonApi;

public class CommonApiImpl implements CommonApi {

    private static final String moduleName = "api/common/";

    @Override
    public void getProtocol(ApiCallBack callBack) {
        try {

            RequestOptions requestOptions = new RequestOptions.Builder()
                    .setRequestMapping(moduleName + "getProtocol")
                    .build();


            Ysy.http().requestPost(requestOptions, callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
