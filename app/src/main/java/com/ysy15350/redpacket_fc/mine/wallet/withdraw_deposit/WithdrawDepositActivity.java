package com.ysy15350.redpacket_fc.mine.wallet.withdraw_deposit;

import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.api.model.ResponseHead;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
import com.ysy15350.ysyutils.citychoice.Constant;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.common.string.JsonConvertor;
import com.ysy15350.ysyutils.model.SysUser;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import model.withdraw.Withdraw;

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
        bindUserInfo(BaseData.getSysUser());

    }

    @Override
    public void initData() {
        super.initData();

        MessageBox.showWaitDialog(this, "数据加载中...");
        mPresenter.userInfo();
    }

    /**
     * 绑定信息
     *
     * @param sysUser
     */
    private void bindUserInfo(SysUser sysUser) {

        // 支付宝信息
        String alipayAccount = sysUser.getAlipayAccount();
        if (CommFun.notNullOrEmpty(alipayAccount)) {
            mHolder.setVisibility_GONE(R.id.ll_alipay);
            mHolder.setText(R.id.tv_alipayAccount, "当前绑定支付宝账号为" + alipayAccount);
        } else {
            mHolder.setVisibility_VISIBLE(R.id.ll_alipay);
            mHolder.setText(R.id.tv_alipayAccount, "未绑定支付宝");
        }

        // 账户余额
        if (CommFun.notNullOrEmpty(sysUser.getAccount())) {
            mHolder.setText(R.id.tv_account, sysUser.getAccount() + "");
        }
    }

    /**
     * 确认提现
     *
     * @param view
     */
    @Event(value = R.id.btn_withdraw)
    private void btn_withdrawClick(View view) {

        Withdraw withdraw = new Withdraw();

        String realname = "";
        String alipayAccount = "";

        if (CommFun.isNullOrEmpty(BaseData.getSysUser().getAlipayAccount())
                && CommFun.isNullOrEmpty(BaseData.getSysUser().getRealname())) {
            realname = mHolder.getViewText(R.id.et_realname1);
            if (CommFunAndroid.isNullOrEmpty(realname)) {
                showMsg("请输入真实姓名");
                return;
            }

            alipayAccount = mHolder.getViewText(R.id.et_alipayAccount1);
            if (CommFunAndroid.isNullOrEmpty(alipayAccount)) {
                showMsg("请输入支付宝账号");
                return;
            }
        }

        String price = mHolder.getViewText(R.id.et_withdrawAccount);
        if (CommFunAndroid.isNullOrEmpty(price)) {
            showMsg("请输入提现金额");
            return;
        }

        int price_value = CommFun.toInt32(price, 0);
        if (price_value < 25) {
            showMsg("提现金额必须大于25元");
            return;
        }

        withdraw.setRealname(realname);
        withdraw.setAlipayAccount(alipayAccount);
        withdraw.setPrice(CommFun.toDouble(price, 0d));
        MessageBox.showWaitDialog(this, "数据提交中...");
        mPresenter.withdraw(withdraw);

    }

    @Override
    public void userInfoCallback(boolean isCache, Response response) {
        try {

            hideWaitDialog();


            if (response != null) {
                String jsonStr = JsonConvertor.toJson(response);
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {


                        SysUser sysUser = response.getData(SysUser.class);
                        if (sysUser != null) {

                            BaseData.setSysUser(sysUser);
                            bindUserInfo(sysUser);
                        }


                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void withdrawCallback(boolean isCache, Response response) {
        try {

            hideWaitDialog();

            if (response != null) {
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {
                        initData();
                    }
                    showMsg(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
