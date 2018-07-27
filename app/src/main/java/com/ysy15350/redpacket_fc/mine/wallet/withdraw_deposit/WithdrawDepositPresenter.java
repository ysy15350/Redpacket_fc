package com.ysy15350.redpacket_fc.mine.wallet.withdraw_deposit;

import android.content.Context;

import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;

import api.AccountAPi;
import api.UserApi;
import api.impl.AccounApiImpl;
import api.impl.UserApiImpl;
import model.withdraw.Withdraw;


public class WithdrawDepositPresenter extends BasePresenter<WithdrawDepositViewInterface> {

    public WithdrawDepositPresenter(Context context) {
        super(context);

    }

    private AccountAPi accountAPi = new AccounApiImpl();

    private UserApi userApi = new UserApiImpl();

    /**
     * 获取用户信息
     */
    public void userInfo() {
        userApi.userInfo(new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.userInfoCallback(isCache, response);
            }
        });
    }

    /**
     * 提现
     */
    public void withdraw(Withdraw withdraw) {

        accountAPi.withdraw(withdraw, new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
                mView.withdrawCallback(isCache,response);
            }
        });

    }

}
