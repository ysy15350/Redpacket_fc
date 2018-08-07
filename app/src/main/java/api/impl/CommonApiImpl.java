package api.impl;

import android.content.pm.PackageManager;

import com.ysy15350.ysyutils.Ysy;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.RequestOptions;
import com.ysy15350.ysyutils.service_impl.HttpServiceImpl;

import api.CommonApi;

public class CommonApiImpl implements CommonApi {

    private static final String moduleName = "api/common/";

    @Override
    public void getProtocol(ApiCallBack callBack) {
        try {

            RequestOptions requestOptions = new RequestOptions.Builder()
                    .setRequestMapping(moduleName + "getProtocol")
                    .build();


            new HttpServiceImpl().requestPost(requestOptions, callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkVersion(int versionCode, ApiCallBack callBack) {
        try {
            RequestOptions requestOptions = new RequestOptions.Builder()
                    .setRequestMapping(moduleName + "checkVersion")
                    .addBodyParameter("package","com.ysy15350.readpacket") // 包名
                    .addBodyParameter("versionCode",versionCode) // 当前版本号
                    .addBodyParameter("platform", "1") // 注册平台；0:web;1:Android ;2:IOS;3:wap
                    .build();


            new HttpServiceImpl().requestPost(requestOptions, callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getSystemTime(ApiCallBack callBack) {
        try {

            RequestOptions requestOptions = new RequestOptions.Builder()
                    .setRequestMapping(moduleName + "getSystemTime")
                    .build();


            new HttpServiceImpl().requestPost(requestOptions, callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
