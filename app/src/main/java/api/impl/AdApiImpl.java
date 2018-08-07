package api.impl;

import com.ysy15350.ysyutils.Ysy;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.RequestOptions;
import com.ysy15350.ysyutils.service_impl.HttpServiceImpl;

import api.AdApi;
import api.OrderApi;

public class AdApiImpl implements AdApi {

    private static final String moduleName = "api/ad/";

    @Override
    public void getAdInfoList(ApiCallBack callBack) {
        try {
            RequestOptions requestOptions = new RequestOptions.Builder()
                    .setRequestMapping(moduleName + "getAdInfoList")
                    .build();

            new HttpServiceImpl().requestPost(requestOptions, callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
