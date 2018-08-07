package api.impl;

import com.ysy15350.ysyutils.Ysy;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.RequestOptions;
import com.ysy15350.ysyutils.service_impl.HttpServiceImpl;

import java.io.File;

import api.FileApi;
import api.RedpacketApi;

public class FileApiImpl implements FileApi {

    private static final String moduleName = "api/file/";

    @Override
    public void checkversion(int versionCode, ApiCallBack callBack) {
        try {
            RequestOptions requestOptions = new RequestOptions.Builder()
                    .setRequestMapping(moduleName + "checkversion")
                    .addBodyParameter("package","com.ysy15350.readpacket") // 包名
                    .addBodyParameter("versionCode",versionCode) // 当前版本号
                    .build();


            new HttpServiceImpl().requestPost(requestOptions, callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void imgUp(int type, String imgName, String imgData, ApiCallBack callBack) {

    }

    @Override
    public void imgUp(int type, String imgName, File imgData, ApiCallBack callBack) {
        try {
            RequestOptions requestOptions = new RequestOptions.Builder()
                    .setRequestMapping(moduleName + "imgUp")
                    .addBodyParameter("type",type) // 头像
                    .addBodyParameter("imgName",imgName)
                    .addBodyParameter("imgData",imgData)
                    .build();


            new HttpServiceImpl().requestPost(requestOptions, callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
