package com.ysy15350.redpacket_fc.mine.cityowner.cityowner_transaction;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;

import org.xutils.view.annotation.ContentView;

/**
 * 城主交易界面
 */
@ContentView(R.layout.activity_cityowner_transaction)
public class CityOwnerTransactionActivity extends MVPBaseActivity<CityOwnerTransactionViewInterface, CityOwnerTransactionPresenter>
        implements CityOwnerTransactionViewInterface {



    @Override
    protected CityOwnerTransactionPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new CityOwnerTransactionPresenter(this);
    }

    @Override
    public void initView() {
        super.initView();

        setFormHead("当前地区");
        setMenuText("规则");

    }
}
