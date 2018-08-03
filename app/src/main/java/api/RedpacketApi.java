package api;

import com.ysy15350.ysyutils.api.ApiCallBack;

/**
 * 红包相关接口
 */
public interface RedpacketApi {

    /**
     * 整点红包
     * @param callBack
     */
    void grabRedPacket(int type,ApiCallBack callBack);
}
