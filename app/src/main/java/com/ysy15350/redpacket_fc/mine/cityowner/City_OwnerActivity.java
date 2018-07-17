package com.ysy15350.redpacket_fc.mine.cityowner;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.mine.wallet.WalletPresenter;
import com.ysy15350.redpacket_fc.mine.wallet.WalletViewInterface;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;

import org.xutils.view.annotation.ContentView;

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

    }
}
