package api;

import com.ysy15350.ysyutils.api.ApiCallBack;

import java.io.File;

/**
 * 城主相关接口
 */
public interface City_OwnerApi {

    /**
     * 城主信息
     * @param code 地区编号
     * @param callBack
     */
    void getCityOwner(int code, ApiCallBack callBack);

    /**
     * 购买城主
     * @param code 地区编号
     * @param callBack
     */
    void buyCityOwner(int code, ApiCallBack callBack);

}
