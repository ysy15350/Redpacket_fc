package com.ysy15350.ysyutils.api;


import android.util.Log;

import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.common.message.MessageBox;

import org.xutils.ex.HttpException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import static com.ysy15350.ysyutils.common.message.MessageBox.hideWaitDialog;

public abstract class ApiCallBack {

    private static final String TAG = "ApiCallBack";


    public void onSuccess(boolean isCache, String data) {
    }

    public void onSuccess(boolean isCache, Response response) {

    }

    public void onFailed(String msg) {
        MessageBox.show(msg);
    }

    /**
     * 错误回调
     *
     * @param ex
     */
    public void onFailed(Throwable ex) {

        MessageBox.hideWaitDialog();

        int code = 0;
        String msg = "服务器错误";

        try {

            if (ex instanceof HttpException) { // 网络错误
                HttpException httpEx = (HttpException) ex;
                int responseCode = httpEx.getCode();
                String responseMsg = httpEx.getMessage();
                String errorResult = httpEx.getResult();


                code = responseCode;
                msg = responseMsg;
                msg="网络异常";

                Log.d(TAG, "onFailed: code=" + code + ",msg=" + msg);

                // ...
            } else if (ex instanceof SocketTimeoutException) {
                SocketTimeoutException socketException = (SocketTimeoutException) ex;

                msg = socketException.getMessage();
                msg = "服务连接超时";
            }
            else if(ex instanceof ConnectException){
                msg = "服务器未响应";
            }
            else { // 其他错误
                // ...
                msg = ex.getMessage();
                if (msg == null || "".equals(msg)) {
                    Throwable throwable = ex.getCause();
                    if (throwable != null) {
                        msg = throwable.getMessage();
                        if (msg == null || "".equals(msg)) {
                            msg = throwable.getLocalizedMessage();
                        }
                    }
                }
            }

            if (msg == null || "".equals(msg))
                msg = "服务器错误";


        } catch (Exception e) {
            e.printStackTrace();

        }

        MessageBox.show(msg);

    }


}
