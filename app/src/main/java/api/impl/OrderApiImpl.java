package api.impl;

import com.ysy15350.ysyutils.Ysy;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.RequestOptions;
import com.ysy15350.ysyutils.service_impl.HttpServiceImpl;

import java.io.File;

import api.FileApi;
import api.OrderApi;

public class OrderApiImpl implements OrderApi {

    private static final String moduleName = "api/order/";

    @Override
    public void orderList(int page, int pageSize, ApiCallBack callBack) {
        try {
            RequestOptions requestOptions = new RequestOptions.Builder()
                    .setRequestMapping(moduleName + "orderList")
                    .addBodyParameter("page",page) // 页数
                    .addBodyParameter("pageSize",pageSize) // 条数
                    .build();


            new HttpServiceImpl().requestPost(requestOptions, callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
