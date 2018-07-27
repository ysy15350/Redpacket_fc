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
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ysy15350.redpacket_fc.MyApplication;
import com.ysy15350.ysyutils.Ysy;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.wxAuth.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

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

        String loginUrl1 = "https://api.weixin.qq.com/sns/oauth2/access_token"+
                "?appid="+Constants.WEIXIN_APP_ID +
                "&secret="+Constants.WEIXIN_APP_Secret +
                "&code="+code+
                "&grant_type=authorization_code";

        Ysy.http().requestWXPost(loginUrl, new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, String data) {
                super.onSuccess(isCache, data);
                String access = null;
                String openId = null;
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    access = jsonObject.getString("access_token");
                    openId = jsonObject.getString("openid");
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
            }
        });
    }
}
