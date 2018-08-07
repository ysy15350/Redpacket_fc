package api.impl;

import com.ysy15350.ysyutils.Ysy;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.RequestOptions;
import com.ysy15350.ysyutils.service_impl.HttpServiceImpl;

import java.io.File;

import api.City_OwnerApi;
import api.FileApi;

public class City_OwnerApiImpl implements City_OwnerApi {

    private static final String moduleName = "api/city_owner/";

//    @Override
//    public void getCityOwner(int code, ApiCallBack callBack) {
//        try {
//            RequestOptions requestOptions = new RequestOptions.Builder()
//                    .setRequestMapping(moduleName + "getCityOwner")
//                    .addBodyParameter("code",code) // 地区编号
//                    .build();
//
//
//            new HttpServiceImpl().requestPost(requestOptions, callBack);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void buyCityOwner(int code, ApiCallBack callBack) {
        try {
            RequestOptions requestOptions = new RequestOptions.Builder()
                    .setRequestMapping(moduleName + "buyCityOwner")
                    .addBodyParameter("code",code) // 地区编号
                    .build();


            new HttpServiceImpl().requestPost(requestOptions, callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
