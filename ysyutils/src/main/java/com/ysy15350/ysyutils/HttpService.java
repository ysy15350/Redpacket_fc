package com.ysy15350.ysyutils;

import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.RequestOptions;

/**
 * Created by yangshiyou on 2017/12/17.
 */

/**
 * 网络请求
 */
public interface HttpService {

    /**
     * 执行网络请求
     */
    public void requestPost(RequestOptions requestOptions, ApiCallBack apiCallBack);

}

