package com.ysy15350.redpacket_fc.mine.wallet;

import android.content.Intent;
import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.mine.wallet.detailed.DetailedListActivity;
import com.ysy15350.redpacket_fc.mine.wallet.withdraw_deposit.WithdrawDepositActivity;
import com.ysy15350.redpacket_fc.redpackage.open_treasurebox.OpenTreasureBoxActivity;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * 钱包
 */
@ContentView(R.layout.activity_wallet)
public class WalletActivity extends MVPBaseActivity<WalletViewInterface, WalletPresenter>
        implements WalletViewInterface {



    @Override
    protected WalletPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new WalletPresenter(this);
    }

    @Override
    public void initView() {
        super.initView();

        setFormHead("钱包");

    }

    /**
     * 打开宝箱
     * @param view
     */
    @Event(value = R.id.ll_wallet1)
    private void ll_wallet1Click(View view) {

        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(getApplicationContext(), OpenTreasureBoxActivity.class));
        else
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

    }

    /**
     * 明细
     * @param view
     */
    @Event(value = R.id.btn_detailed)
    private void btn_detailedClick(View view) {

        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(getApplicationContext(), DetailedListActivity.class));
        else
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

    }

    /**
     * 提现
     * @param view
     */
    @Event(value = R.id.btn_withdraw)
    private void btn_withdrawClick(View view) {

        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(getApplicationContext(), WithdrawDepositActivity.class));
        else
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

    }

    /**
     * 返回
     * @param view
     */
    @Event(value = R.id.img_wallet_back)
    private void img_wallet_backClick(View view) {

        finish();

    }
}
