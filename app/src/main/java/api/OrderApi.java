package api;

import com.ysy15350.ysyutils.api.ApiCallBack;

import java.io.File;

/**
 * 相关接口
 */
public interface OrderApi {

    /**
     * 明细
     */
    void orderList(int page,int pageSize, ApiCallBack callBack);

}
