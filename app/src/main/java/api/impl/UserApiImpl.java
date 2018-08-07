package api.impl;

import com.ysy15350.ysyutils.Ysy;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.RequestOptions;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.model.SysUser;
import com.ysy15350.ysyutils.service_impl.HttpServiceImpl;

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
                    .addBodyParameter("mobile", mobile)//手机号
                    .addBodyParameter("type", type + "")//类型
                    .build();


            new HttpServiceImpl().requestPost(requestOptions, callBack);

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


            new HttpServiceImpl().requestPost(requestOptions, callBack);

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


        new HttpServiceImpl().requestPost(requestOptions, callBack);

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

        new HttpServiceImpl().requestPost(requestOptions, callBack);
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


        new HttpServiceImpl().requestPost(requestOptions, callBack);

    }

    @Override
    public void userInfo(ApiCallBack callBack) {
        RequestOptions requestOptions = new RequestOptions.Builder()
                .setRequestMapping(moduleName + "userInfo")
                .build();


        new HttpServiceImpl().requestPost(requestOptions, callBack);
    }


    @Override
    public void saveUserInfo(SysUser sysUser, ApiCallBack callBack) {
        try {


            RequestOptions.Builder builder = new RequestOptions.Builder();
            builder.setRequestMapping(moduleName + "saveUserInfo");


            // 头像

            // 用户昵称
            builder.addBodyParameter("nickname", sysUser.getNickname());

            // 个性签名
            builder.addBodyParameter("personalitySignature", sysUser.getPersonalitySignature());


            // 支付宝账号
            builder.addBodyParameter("alipayAccount", sysUser.getAlipayAccount());

            // 性别
            builder.addBodyParameter("sex", sysUser.getSex());


            // 生日
            builder.addBodyParameter("birthday", sysUser.getBirthday());

            // 常驻地区
            builder.addBodyParameter("habitualResidence", sysUser.getHabitualResidence());

            // 手机号
            builder.addBodyParameter("mobile", sysUser.getMobile());

            RequestOptions requestOptions = builder.build();

            new HttpServiceImpl().requestPost(requestOptions, callBack);

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

        new HttpServiceImpl().requestPost(requestOptions, callBack);
    }
}
