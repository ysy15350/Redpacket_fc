package com.ysy15350.ysyutils;

import com.ysy15350.ysyutils.service_impl.HttpServiceImpl;

/**
 * Created by yangshiyou on 2017/12/15.
 */

public final class Ysy {

    //框架：mvp+xutils(思想)
    //用户行为统计与性能检测
    //aop架构设计


    private Ysy() {
    }


    public static HttpService http(){
        if (Ext.httpService == null) {
            HttpServiceImpl.registerInstance();
        }
        return Ext.httpService;
    }



    public static class Ext {

        private static boolean debug;


        private static HttpService httpService;

        public static void setHttpService(HttpService httpService){
            Ext.httpService=httpService;
        }

    }

}
