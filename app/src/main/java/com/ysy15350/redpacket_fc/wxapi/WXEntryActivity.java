package com.ysy15350.redpacket_fc.wxapi;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.service.carrier.CarrierMessagingService;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ysy15350.redpacket_fc.MyApplication;
import com.ysy15350.ysyutils.Ysy;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.model.SysUser;
import com.ysy15350.ysyutils.service_impl.HttpServiceImpl;
import com.ysy15350.ysyutils.wxAuth.Constants;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    public static IWXAPI iwxapi;

    private static final String WEIXIN_ACCESS_TOKEN_KEY = "wx_access_token_key";
    private static final String WEIXIN_OPENID_KEY = "wx_openid_key";
    private static final String WEIXIN_REFRESH_TOKEN_KEY = "wx_refresh_token_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.iwxapi.handleIntent(getIntent(), this);
    }

    /**
     * 微信组件注册初始化
     * @param context 上下文
     * @param weixin_app_id
     * @return 微信组件api对象
     */
    public static IWXAPI initWeiXin(Context context, @NonNull String weixin_app_id) {
        if (CommFun.isNullOrEmpty(weixin_app_id)) {
            MessageBox.show("app_id 不能为空");
            }
        IWXAPI api = WXAPIFactory.createWXAPI(context, weixin_app_id, true);
        api.registerApp(weixin_app_id);
        return api;
    }


    /**
     * 登录微信
     *
     * @param api 微信服务api
     */
    public static void loginWeixin(Context context, IWXAPI api) {
        // 判断是否安装了微信客户端
        if (!api.isWXAppInstalled()) {
            MessageBox.show("您还未安装微信客户端！");
            return;
        }
        // 发送授权登录信息，来获取code
        SendAuth.Req req = new SendAuth.Req();
        // 应用的作用域，获取个人信息
        req.scope = "snsapi_userinfo";
        /**
         * 用于保持请求和回调的状态，授权请求后原样带回给第三方
         * 为了防止csrf攻击（跨站请求伪造攻击），后期改为随机数加session来校验
         */
        req.state = "app_wechat";
        api.sendReq(req);
    }

    /**
     * 微信分享
     * @param text
     */
    public static void shareWeixin(String text,int type,IWXAPI api){
        WXTextObject textObject = new WXTextObject();
        textObject.text = text;

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObject;
        msg.description = text;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = text;

        req.message = msg;
        switch (type) {
            case 1: // 设置发送给朋友
                req.scene = SendMessageToWX.Req.WXSceneSession;
                break;
            case 2: //设置发送到朋友圈
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                break;
        }
        api.sendReq(req);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        switch (baseReq.getType()) {
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }
    /**
     * 请求回调结果处理
     */
    @Override
    public void onResp(BaseResp baseResp) {
        //登录回调
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;
                //获取用户信息
                getAccessToken(code);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                finish();
                break;
            default:
                finish();
                break;
        }

        // 分享
//        String result = "";
//
//        switch (baseResp.errCode) {
//            case BaseResp.ErrCode.ERR_OK:
//                result = "发送成功";
//                break;
//            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                result = "发送取消";
//                break;
//            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//                result = "发送被拒绝";
//                break;
//            default:
//                result = "发送返回";
//                break;
//        }
//
//        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }


    private void getAccessToken(String code) {
        //获取授权
        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token")
                .append("?appid=")
                .append(Constants.WEIXIN_APP_ID)
                .append("&secret=")
                .append(Constants.WEIXIN_APP_Secret)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");

//        String loginUrl1 = "https://api.weixin.qq.com/sns/oauth2/access_token"+
//                "?appid="+Constants.WEIXIN_APP_ID +
//                "&secret="+Constants.WEIXIN_APP_Secret +
//                "&code="+code+
//                "&grant_type=authorization_code";

//        x.http().post(new RequestParams(loginUrl.toString()), new Callback.CacheCallback<String>() {
//
//            @Override
//            public boolean onCache(String result) {
//                MessageBox.show(result);
//                return false;
//            }
//
//            @Override
//            public void onSuccess(String result) {
//                String access = null;
//                String openId = null;
//                try {
//                    JSONObject jsonObject = new JSONObject(result);
//                    access = jsonObject.getString("access_token");
//                    openId = jsonObject.getString("openid");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                MessageBox.show(ex.toString());
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                MessageBox.show(cex.toString());
//            }
//
//            @Override
//            public void onFinished() {
//                MessageBox.show("请求完成");
//            }
//        });

        new HttpServiceImpl().requestWXPost(loginUrl, new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, String data) {
                super.onSuccess(isCache, data);
                String access = null;
                String openId = null;
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    access = jsonObject.getString("access_token");
                    openId = jsonObject.getString("openid");

                    //获取个人信息
                    getUserInfo(access,openId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailed(String msg) {
                super.onFailed(msg);
                MessageBox.show(msg);
            }
        });
    }

    private void getUserInfo(String access,String openId){
        //获取用户信息
        StringBuffer userUrl = new StringBuffer();
        userUrl.append("https://api.weixin.qq.com/sns/userinfo")
                .append("?access_token=")
                .append(access)
                .append("&openid==")
                .append(openId);

        Ysy.http().requestWXPost(userUrl, new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, String data) {
                super.onSuccess(isCache, data);
                String datas = data;

                String openid = null;
                // 姓名
                String nickName = null;
                // 性别
                String sex = null;
                String city = null;
                String province = null;
                String country = null;
                // 头像
                String headimgurl = null;
                String unionid = null;

                try {
                    JSONObject jsonObject = new JSONObject(data);

                    openid = jsonObject.getString("openid");
                    nickName = jsonObject.getString("nickname");
                    sex = jsonObject.getString("sex");
                    city = jsonObject.getString("city");
                    province = jsonObject.getString("province");
                    country = jsonObject.getString("country");
                    headimgurl = jsonObject.getString("headimgurl");
                    unionid = jsonObject.getString("unionid");
                }catch (Exception e){

                }
                BaseData.setToken(openid);

                SysUser sysUser = new SysUser();
                sysUser.setNickname(nickName);
                sysUser.setSex(CommFun.toInt32(sex,0));
                sysUser.setAvatar(headimgurl);

                BaseData.setSysUser(sysUser);

            }
        });
    }
}
