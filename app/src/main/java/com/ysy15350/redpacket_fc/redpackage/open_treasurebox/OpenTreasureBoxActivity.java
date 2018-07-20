package com.ysy15350.redpacket_fc.redpackage.open_treasurebox;

import android.content.Intent;
import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.mine.wallet.detailed.DetailedListActivity;
import com.ysy15350.redpacket_fc.mine.wallet.withdraw_deposit.WithdrawDepositActivity;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * 打开宝箱
 */
@ContentView(R.layout.activity_openred)
public class OpenTreasureBoxActivity extends MVPBaseActivity<OpenTreasureBoxViewInterface, OpenTreasureBoxPresenter>
        implements OpenTreasureBoxViewInterface {



    @Override
    protected OpenTreasureBoxPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new OpenTreasureBoxPresenter(this);
    }

    @Override
    public void initView() {
        super.initView();
        setFormHead("打开宝箱");

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
