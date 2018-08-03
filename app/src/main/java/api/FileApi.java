package api;

import com.ysy15350.ysyutils.api.ApiCallBack;

import java.io.File;

/**
 * 文件相关接口
 */
public interface FileApi {

    /**
     * 版本更新
     * @param versionCode
     * @param callBack
     */
    void checkversion(int versionCode, ApiCallBack callBack);

    /**
     *  图片上传
     * @param type
     * @param imgName
     * @param imgData
     * @param callBack
     */
    public void imgUp(int type,String imgName, String imgData, ApiCallBack callBack);

    /**
     *  图片上传
     * @param type
     * @param imgName
     * @param imgData
     * @param callBack
     */
    public void imgUp(int type, String imgName, File imgData, ApiCallBack callBack);
}
