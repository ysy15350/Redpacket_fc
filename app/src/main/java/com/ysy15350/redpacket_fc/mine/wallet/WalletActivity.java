package com.ysy15350.redpacket_fc.mine.wallet;

import android.content.Intent;
import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.mine.setalipay.SetAlipayActivity;
import com.ysy15350.redpacket_fc.mine.wallet.detailed.DetailedListActivity;
import com.ysy15350.redpacket_fc.mine.wallet.withdraw_deposit.WithdrawDepositActivity;
import com.ysy15350.redpacket_fc.redpackage.open_treasurebox.OpenTreasureBoxActivity;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.model.SysUser;

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

    @Override
    protected void onResume() {
        super.onResume();

        bindUserInfo(BaseData.getSysUser());
    }

    /**
     * 绑定信息
     *
     * @param sysUser
     */
    private void bindUserInfo(SysUser sysUser) {

        // 账户余额
        if(CommFun.notNullOrEmpty(sysUser.getAccount())){
            mHolder.setText(R.id.tv_balance,sysUser.getAccount()+"");
        }

        // 支付宝账号
        if(CommFun.notNullOrEmpty(sysUser.getAlipayAccount())){
            mHolder.setText(R.id.tv_alipay,"当前绑定支付宝账号为"+sysUser.getAlipayAccount());
        }else {
            mHolder.setText(R.id.tv_alipay,"未设置支付宝账号");
            mHolder.setVisibility_VISIBLE(R.id.tv_setalipay);
        }
    }

    /**
     * 设置支付宝
     *
     * @param view
     */
    @Event(value = R.id.tv_setalipay)
    private void tv_setalipayClick(View view) {

        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(this, SetAlipayActivity.class));
        else
            startActivity(new Intent(this, LoginActivity.class));

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
