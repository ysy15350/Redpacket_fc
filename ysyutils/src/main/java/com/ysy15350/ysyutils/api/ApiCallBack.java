package com.ysy15350.ysyutils.api;


import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.common.message.MessageBox;

public abstract class ApiCallBack {


    public void onSuccess(boolean isCache, String data) {
    }

    public void onSuccess(boolean isCache, Response response) {

    }

    public void onFailed(String msg) {
        MessageBox.show(msg);
    }
}
