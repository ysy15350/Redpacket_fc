package com.ysy15350.redpacket_fc.mine.cityowner;

import android.content.Intent;
import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.mine.cityowner.cityowner_transaction.CityOwnerTransactionActivity;
import com.ysy15350.redpacket_fc.mine.share.ShareActivity;
import com.ysy15350.redpacket_fc.mine.wallet.WalletPresenter;
import com.ysy15350.redpacket_fc.mine.wallet.WalletViewInterface;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.SystemModels;
import com.ysy15350.ysyutils.common.message.MessageBox;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * 城主
 */
@ContentView(R.layout.activity_city_owner)
public class City_OwnerActivity extends MVPBaseActivity<City_OwnerViewInterface, City_OwnerPresenter>
        implements City_OwnerViewInterface {



    @Override
    protected City_OwnerPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new City_OwnerPresenter(this);
    }

    @Override
    public void initView() {
        super.initView();

        setFormHead("城主");
        setMenuText("规则");

    }

    /**
     * 我要成为城主
     * @param view
     */
    @Event(value = R.id.btn_city)
    private void btn_cityClick(View view) {

        if (BaseData.isLogin())//如果需要登录
            if(CommFun.notNullOrEmpty(SystemModels.locationInfo.getDistrict())){
                startActivity(new Intent(this, CityOwnerTransactionActivity.class));
            }else {
                MessageBox.show("定位失败");
                return;
            }
        else
            startActivity(new Intent(this, LoginActivity.class));

    }
}
