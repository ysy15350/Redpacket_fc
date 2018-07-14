package api.impl;

import com.ysy15350.ysyutils.Ysy;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.RequestOptions;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.model.UserInfo;

import api.UserApi;

/**
 * Created by yangshiyou on 2017/12/15.
 */

public class UserApiImpl implements UserApi {

    private static final String moduleName = "api/user/";

    @Override
    public void getDynCode(String mobile, int type, ApiCallBack callBack) {
        try {

            RequestOptions requestOptions = new RequestOptions.Builder()
                    .setRequestMapping(moduleName + "getDynCode")
                    .addBodyParameter("mobile", mobile)
                    .addBodyParameter("type", type + "")
                    .build();


            Ysy.http().requestPost(requestOptions, callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void register(String mobile, String password, String code, String refereeCode, ApiCallBack callBack) {
        try {

            RequestOptions requestOptions = new RequestOptions.Builder()
                    .setRequestMapping(moduleName + "register")
                    .addBodyParameter("mobile", mobile)
                    .addBodyParameter("password", password)
                    .addBodyParameter("code", code)//验证码
                    .addBodyParameter("refereeCode", refereeCode)//邀请码
                    .addBodyParameter("platform", "1")//注册平台；0:web;1:Android ;2:IOS;3:wap
                    .addBodyParameter("deviceId", CommFunAndroid.getSharedPreferences("device_id"))//UUID，设备唯一编号
                    .build();


            Ysy.http().requestPost(requestOptions, callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void login(String userName, String password, ApiCallBack callBack) {


        //RequestOptions.Builder builder = new RequestOptions.Builder();

        RequestOptions requestOptions = new RequestOptions.Builder()
                .setRequestMapping(moduleName + "info")
                .addBodyParameter("mobile", userName)
                .addBodyParameter("password", password)
                .addBodyParameter("platform", "1")
                .addBodyParameter("deviceId", CommFunAndroid.getSharedPreferences("device_id"))
                .build();


        Ysy.http().requestPost(requestOptions, callBack);

    }

    @Override
    public void loginByCode(String mobile, String code, ApiCallBack callBack) {

        RequestOptions requestOptions = new RequestOptions.Builder()
                .setRequestMapping(moduleName + "loginByCode")
                .addBodyParameter("mobile", mobile)
                .addBodyParameter("code", code)//验证码
                .addBodyParameter("platform", "1")//1:Android；2:ios
                .addBodyParameter("deviceId", CommFunAndroid.getSharedPreferences("device_id"))//设备唯一编码
                .build();

        Ysy.http().requestPost(requestOptions, callBack);
    }

    @Override
    public void activate(ApiCallBack callBack) {


        String token = BaseData.getToken();
        String deviceInfo = CommFunAndroid.getDeviceInfoStr();


        RequestOptions requestOptions = new RequestOptions.Builder()
                .setRequestMapping(moduleName + "activate")
                .addBodyParameter("token", token)
                .addBodyParameter("deviceInfo", deviceInfo)
                .addBodyParameter("platform", "1")
                .addBodyParameter("deviceId", CommFunAndroid.getSharedPreferences("device_id"))
                .build();


        Ysy.http().requestPost(requestOptions, callBack);

    }

    @Override
    public void userInfo(ApiCallBack callBack) {
        RequestOptions requestOptions = new RequestOptions.Builder()
                .setRequestMapping(moduleName + "userInfo")
                .build();


        Ysy.http().requestPost(requestOptions, callBack);
    }

    @Override
    public void saveUserInfo(UserInfo userInfo, ApiCallBack callBack) {
        try {
            if (userInfo == null)
                return;

            RequestOptions requestOptions = new RequestOptions.Builder()
                    .setRequestMapping(moduleName + "saveUserInfo")
                    .addBodyParameter("nickName", userInfo.getNickname())
                    .addBodyParameter("realName", userInfo.getRealname())
                    .addBodyParameter("headimg", userInfo.getHeadimg() + "")
                    .build();

            Ysy.http().requestPost(requestOptions, callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateMobile(String mobile, String code, ApiCallBack callBack) {

        RequestOptions requestOptions = new RequestOptions.Builder()
                .setRequestMapping(moduleName + "updateMobile")
                .addBodyParameter("mobile", mobile)
                .addBodyParameter("code", code)
                .build();

        Ysy.http().requestPost(requestOptions, callBack);
    }
}
