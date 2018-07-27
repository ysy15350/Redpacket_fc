package api;

import com.ysy15350.ysyutils.api.ApiCallBack;

import model.withdraw.Withdraw;

public interface AccountAPi {

    /**
     * 提现
     * @param withdraw  提现实体
     * @param callBack
     */
    void withdraw(Withdraw withdraw, ApiCallBack callBack);

    /**
     * 邀请
     * @param mobile 邀请手机号
     * @param callBack
     */
    void invite(String mobile, ApiCallBack callBack);

    /**
     * 已经邀请过的号码
     * @param callBack
     */
    void getInviteList(String mobile,ApiCallBack callBack);
}
