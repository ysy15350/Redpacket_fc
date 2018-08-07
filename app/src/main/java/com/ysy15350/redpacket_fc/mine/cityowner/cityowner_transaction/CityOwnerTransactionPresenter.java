package com.ysy15350.redpacket_fc.mine.cityowner.cityowner_transaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;
import com.ysy15350.ysyutils.common.string.JsonConvertor;

import java.util.Map;

import alipay.AuthResult;
import alipay.PayResult;
import api.AccountAPi;
import api.City_OwnerApi;
import api.CommonApi;
import api.impl.AccounApiImpl;
import api.impl.City_OwnerApiImpl;
import api.impl.CommonApiImpl;


public class CityOwnerTransactionPresenter extends BasePresenter<CityOwnerTransactionViewInterface> {

    private static final String TAG = "CityOwnerTransactionPresenter";

    public CityOwnerTransactionPresenter(Context context) {
        super(context);

    }

    private CommonApi commonApi = new CommonApiImpl();
    private City_OwnerApi city_ownerApi = new City_OwnerApiImpl();
    private AccountAPi accountAPi = new AccounApiImpl();

    /**
     * 获取用户协议
     */
    public void getProtocol(){
        commonApi.getProtocol(new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.bindProtocolCallback(isCache,response);
            }

            @Override
            public void onFailed(String msg) {
                super.onFailed(msg);
            }
        });
    }

    /**
     * 购买城主
     */
    public void buyCityOwner(int code){

        city_ownerApi.buyCityOwner(code, new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.buyCityOwnerCallback(isCache,response);
            }
        });
    }

    /**
     * 生成城主支付订单
     */
    public void buildCityOwnerPayOrder(int code, int type){

        accountAPi.buildCityOwnerPayOrder(code, type, new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.buildCityOwnerPayOrderCallback(isCache,response);
            }
        });
    }


    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "ddd";
    public static final String RSA_PRIVATE = "";
    public static final String RSA2_PRIVATE = "ddd";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("LongLogTag")
    public void alipay(final String orderInfo, final Activity context) {

        Log.d(TAG, "alipay() called with: authInfo = [" + orderInfo + "], context = [" + context + "]");

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(context);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressLint("LongLogTag")
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();

                    Log.d(TAG, "支付结果: " + JsonConvertor.toJson(payResult));

                    mView.showAliPayResult(JsonConvertor.toJson(payResult));

                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                        Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
//                        Toast.makeText(PayDemoActivity.this,
//                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
//                                .show();
                    } else {
                        // 其他状态值则为授权失败
//                        Toast.makeText(PayDemoActivity.this,
//                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

}
