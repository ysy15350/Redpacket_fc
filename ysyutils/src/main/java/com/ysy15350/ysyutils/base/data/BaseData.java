package com.ysy15350.ysyutils.base.data;

import android.content.Context;
import android.util.Log;

import com.ysy15350.ysyutils.YSYApplication;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.cache.ACache;
import com.ysy15350.ysyutils.model.UserInfo;

import java.io.ObjectStreamException;

/**
 * Created by yangshiyou on 2017/12/22.
 */

public class BaseData {

    private static ACache aCache;


    private final static String TAG = "BaseData";

    private BaseData() {
    }

    public static BaseData getInstance() {
        init();
        return BaseDataHolder.sInstance;
    }


    private static class BaseDataHolder {
        private static final BaseData sInstance = new BaseData();
    }

    // 杜绝单例对象在反序列化时重新生成对象
    private Object readResolve() throws ObjectStreamException {
        return BaseDataHolder.sInstance;
    }

    private static void init() {
        if (aCache == null) {
            Context context = YSYApplication.getContext();
            aCache = ACache.get(context);
        }
    }


    /**
     * 清除缓存
     */
    public void clearCache() {
        init();
        aCache.clear();
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     */
    public static void setCache(String key, String value) {
        int uid = getUid();
        if (aCache != null && value != null) {
            Log.d(TAG, "setCache() called with: key = [" + key + uid + "], value = [" + value + "]");
            aCache.put(key + uid, value);
        }
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     */
    public static void setCache(String key, String value, int time) {
        if (aCache != null && value != null) {
            int uid = getUid();
            Log.d(TAG, "setCache() called with: key = [" + key + uid + "], value = [" + value + "], time = [" + time + "]");
            aCache.put(key + uid, value, time);
        }
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public static String getCache(String key) {
        if (aCache != null) {
            int uid = getUid();
            String cacheStr = aCache.getAsString(key + uid);
            Log.d(TAG, "getCache() called with: key = [" + key + uid + "],value=" + cacheStr);
            return cacheStr;
        }
        return "";
    }


    /**
     * 用户信息
     */
    private static UserInfo mUserInfo;
    /**
     * 用户uid
     */
    private static int mUid;
    /**
     * token
     */
    private static String mToken;


    public static void setToken(String token) {
        try {
            setCache("token", token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getToken() {


        try {
            return getCache("token");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "";
    }

    /**
     * 是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        try {
            return !CommFun.isNullOrEmpty(getToken());
        } catch (Exception ex) {
        }

        return false;
    }


    /**
     * 获取用户ID
     *
     * @return
     */
    public static int getUid() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    /**
     * 更新用户信息
     *
     * @param userInfo
     */
    public static void setUserInfo(UserInfo userInfo) {
        mUserInfo = userInfo;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserInfo getUserInfo() {
        return mUserInfo;
    }




}
