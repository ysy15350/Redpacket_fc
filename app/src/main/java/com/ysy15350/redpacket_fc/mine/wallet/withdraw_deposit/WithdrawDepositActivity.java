package com.ysy15350.redpacket_fc.mine.wallet.withdraw_deposit;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;

import org.xutils.view.annotation.ContentView;

/**
 * 提现
 */
@ContentView(R.layout.activity_withdrawdeposit)
public class WithdrawDepositActivity extends MVPBaseActivity<WithdrawDepositViewInterface, WithdrawDepositPresenter>
        implements WithdrawDepositViewInterface {



    @Override
    protected WithdrawDepositPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new WithdrawDepositPresenter(this);
    }



    @Override
    public void initView() {
        super.initView();

        setFormHead("提现");
    }
}
