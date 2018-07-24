package com.ysy15350.ysyutils.service_impl;

import android.util.Log;

import com.ysy15350.ysyutils.HttpService;
import com.ysy15350.ysyutils.Ysy;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.KeyValuePair;
import com.ysy15350.ysyutils.api.model.RequestOptions;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.string.JsonConvertor;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.net.SocketTimeoutException;
import java.util.List;

/**
 * Created by yangshiyou on 2017/12/17.
 */

public class HttpServiceImpl implements HttpService {

    private static final Object lock = new Object();
    private static volatile HttpService instance;

    public static void registerInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new HttpServiceImpl();
                }
            }
        }
        Ysy.Ext.setHttpService(instance);
    }

    private static final String TAG = "HttpServiceImpl";

    /**
     * 请求参数
     */
    RequestParams mRequestParams;

    /**
     * 是否使用缓存
     */
    boolean useCache;

    /**
     * 自定义回调
     */
    private ApiCallBack mApiCallBack;

    @Override
    public void requestPost(RequestOptions requestOptions, ApiCallBack apiCallBack) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗\n");
//        stringBuilder.append("║                                                                                                                    ║\n");
//        stringBuilder.append("║                                                                                                                    ║\n");
        stringBuilder.append("║                                          this.toString()                ║\n".replace("this.toString()", HttpServiceImpl.this.toString()));
        stringBuilder.append("║                                          yyyy-MM-dd HH:mm:ss:SSS                                                   ║\n".replace("yyyy-MM-dd HH:mm:ss:SSS", CommFun.getDateString("yyyy-MM-dd HH:mm:ss:SSS")));
//        stringBuilder.append("║                                                                                                                    ║\n");
//        stringBuilder.append("║                                                                                                                    ║\n");
        stringBuilder.append("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝\n");

        Log.d(TAG, stringBuilder.toString());


        if (requestOptions != null) {

            List<KeyValuePair> bodyParams = requestOptions.getBodyParams();

            if (bodyParams != null) {

                mRequestParams = new RequestParams(requestOptions.getUri());

                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦\n");

                stringBuilder1.append(String.format("◦                    url=%s\n", requestOptions.getUri()));


                for (KeyValuePair param :
                        bodyParams) {
                    mRequestParams.addBodyParameter(param.getKeyStr(), param.getValueStr());

                    stringBuilder1.append(String.format("◦                    key=%s\r\t\r\tvalue=%s\n", param.getKey(), param.getValue().toString()));
                }

                stringBuilder1.append("◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦◦\n");

                Log.d(TAG, stringBuilder1.toString());
            }
        }

        this.mApiCallBack = apiCallBack;


        x.http().post(mRequestParams, cacheCallback);


    }


    /**
     * 框架回调
     */
    Callback.CacheCallback<String> cacheCallback = new Callback.CacheCallback<String>() {

        /**
         *
         */
        public boolean hasError = false;

        private boolean isCache = false;

        private String result = null;


        @Override
        public boolean onCache(String result) {
            Log.d(TAG, "onCache() called with: \nuseCache = [" + useCache + "],\nresult = [" + result.replace("body", "\nbody") + "]");
            // 得到缓存数据, 缓存过期后不会进入这个方法.
            // 如果服务端没有返回过期时间, 参考params.setCacheMaxAge(maxAge)方法.
            //
            // * 客户端会根据服务端返回的 header 中 max-age 或 expires 来确定本地缓存是否给 onCache 方法.
            // 如果服务端没有返回 max-age 或 expires, 那么缓存将一直保存, 除非这里自己定义了返回false的
            // 逻辑, 那么xUtils将请求新数据, 来覆盖它.
            //
            // * 如果信任该缓存返回 true, 将不再请求网络;
            // 返回 false 继续请求网络, 但会在请求头中加上ETag, Last-Modified等信息,
            // 如果服务端返回304, 则表示数据没有更新, 不继续加载数据.
            //

            this.isCache = true;

            this.result = result;
            return useCache; // true: 信任缓存数据, 不在发起网络请求; false不信任缓存数据.
        }


        @Override
        public void onSuccess(String result) {

            hasError = false;

            Log.d(TAG, "onSuccess() called with:\nresult = [" + result.replace("body", "\nbody") + "]");

            // 注意: 如果服务返回304 或 onCache 选择了信任缓存, 这时result为null.
            if (result != null) {
                this.isCache = false;
                this.result = result;
            }
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {

            Log.d(TAG, "onError() called with: ex = [" + ex + "], isOnCallback = [" + isOnCallback + "]");

            hasError = true;


            if (mApiCallBack != null) {

                mApiCallBack.onFailed(ex);
            }

//            if (ex != null)
//                Log.e("RequestServer", ex.getMessage());
        }

        @Override
        public void onCancelled(CancelledException cex) {
            Log.d(TAG, "onCancelled() called with: cex = [" + cex + "]");

            // Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFinished() {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");


            if (!hasError && result != null) {

                Response response = JsonConvertor.fromJson(result, Response.class);


                // 成功获取数据
                if (mApiCallBack != null) {

                    mApiCallBack.onSuccess(isCache, response);

                    mApiCallBack.onSuccess(isCache, result);
                }

            }


//          StringBuilder stringBuilder = new StringBuilder();
//          stringBuilder.append("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
//            stringBuilder.append("┃                                                                                                                    ┃\n");
//            stringBuilder.append("┃                                                                                                                    ┃\n");
            stringBuilder.append("┃                                          this.toString()              ┃\n".replace("this.toString()", this.toString()));
            stringBuilder.append("┃                                          this.toString()                ┃\n".replace("this.toString()", HttpServiceImpl.this.toString()));
            stringBuilder.append("┃                                          yyyy-MM-dd HH:mm:ss:SSS                                                   ┃\n".replace("yyyy-MM-dd HH:mm:ss:SSS", CommFun.getDateString("yyyy-MM-dd HH:mm:ss:SSS")));
//            stringBuilder.append("┃                                                                                                                    ┃\n");
//            stringBuilder.append("┃                                                                                                                    ┃\n");
            stringBuilder.append("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");

            Log.d(TAG, stringBuilder.toString());
        }
    };

}
