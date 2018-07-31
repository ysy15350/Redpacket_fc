package api;

import com.ysy15350.ysyutils.api.ApiCallBack;

/**
 * 公共接口
 */
public interface CommonApi {

    /**
     * 获取用户协议
     *
     * @param callBack
     */
    public void getProtocol(ApiCallBack callBack);

    /**
     * 版本更新
     * @param versionCode
     * @param callBack
     */
    public void checkVersion(int versionCode,ApiCallBack callBack);

    /**
     * 获取系统时间
     * @param callBack
     */
    public void getSystemTime(ApiCallBack callBack);

}
