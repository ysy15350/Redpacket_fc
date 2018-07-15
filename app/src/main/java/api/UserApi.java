package api;

import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.model.UserInfo;

/**
 * 用户相关接口
 * Created by yangshiyou on 2017/12/15.
 */

public interface UserApi {


    /**
     * 获取验证码
     *
     * @param mobile
     * @param type     1：注册；2：其他（默认）
     * @param callBack
     */
    public void getDynCode(String mobile, int type, ApiCallBack callBack);

    /**
     * 用户注册
     *
     * @param mobile
     * @param password
     * @param code
     * @param refereeCode
     * @param callBack
     */
    public void register(String mobile, String password, String code, String refereeCode, ApiCallBack callBack);

    /**
     * 登录
     *
     * @param mobile
     * @param password
     * @param callBack
     */
    public void login(String mobile, String password, ApiCallBack callBack);

    /**
     * 手机验证码登录
     *
     * @param mobile
     * @param code
     * @param callBack
     */
    public void loginByCode(String mobile, String code, ApiCallBack callBack);

    /**
     * 激活账户
     *
     * @param callBack
     */
    public void activate(ApiCallBack callBack);

    /**
     * 获取用户信息
     *
     * @param callBack
     */
    public void userInfo(ApiCallBack callBack);

    /**
     * 修改用户信息
     *
     * @param userInfo
     * @param callBack
     */
    public void saveUserInfo(UserInfo userInfo, ApiCallBack callBack);

    /**
     * 修改手机号
     *
     * @param mobile
     * @param code
     * @param callBack
     */
    public void updateMobile(String mobile, String code, ApiCallBack callBack);

}
