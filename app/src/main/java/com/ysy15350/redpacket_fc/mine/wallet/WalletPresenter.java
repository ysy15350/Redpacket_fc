package com.ysy15350.redpacket_fc.mine.wallet;

import android.content.Context;

import com.ysy15350.redpacket_fc.mine.setalipay.SetAlipayViewInterface;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;
import com.ysy15350.ysyutils.model.SysUser;

import api.AccountAPi;
import api.UserApi;
import api.impl.AccounApiImpl;
import api.impl.UserApiImpl;
import model.withdraw.Withdraw;


public class WalletPresenter extends BasePresenter<WalletViewInterface> {

    public WalletPresenter(Context context) {
        super(context);

    }

}
