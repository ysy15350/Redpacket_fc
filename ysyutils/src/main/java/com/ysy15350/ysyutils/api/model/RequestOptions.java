package com.ysy15350.ysyutils.api.model;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangshiyou on 2017/12/15.
 */

public class RequestOptions {


    private String uri;

    private String mappingValue;

    private String bodyContent;

    private final List<KeyValuePair> bodyParams = new ArrayList<KeyValuePair>();

    protected RequestOptions() {

    }


    public String getUri() {

        if (uri == null) {
            uri = String.format("%s/%s", Config.getUri(), mappingValue);
        }

        return uri;
    }

    /**
     * 获取参数列表
     *
     * @return
     */
    public List<KeyValuePair> getBodyParams() {
        //checkBodyParams();
        return new ArrayList<KeyValuePair>(bodyParams);
    }


    public static class Builder {

        protected RequestOptions options;

        public Builder() {
            newRequestOptions();
        }

        protected void newRequestOptions() {
            options = new RequestOptions();
        }

        public RequestOptions build() {
            return options;
        }


        /**
         * 设置请求地址
         *
         * @param uri
         * @return
         */
        public Builder setUri(String uri) {
            options.uri = uri;
            return this;
        }

        /**
         * 设置请求路径（不包含服务器地址）：如http://www.ysy15350.com/api/test  ，value=api/test
         *
         * @param value
         * @return
         */
        public Builder setRequestMapping(String value) {
            options.mappingValue = value;
            return this;
        }


        /**
         * 添加参数至Body
         *
         * @param name
         * @param value
         */
        public Builder addBodyParameter(String name, Object value) {
            options.bodyParams.add(new KeyValuePair(name, value));
            return this;
        }


    }

}
