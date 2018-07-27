package com.ysy15350.redpacket_fc.mine.setalipay;

import android.os.Bundle;
import android.widget.BaseAdapter;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_AdsCard;
import com.ysy15350.redpacket_fc.mine.usercenter.UserCenterPresenter;
import com.ysy15350.redpacket_fc.mine.usercenter.UserCenterViewInterface;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
import com.ysy15350.ysyutils.base.mvp.MVPBaseListViewActivity;
import com.ysy15350.ysyutils.common.message.MessageBox;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

import model.AdsCard;

/**
 * 设置支付宝
 */
@ContentView(R.layout.activity_setalipay)
public class SetAlipayActivity extends MVPBaseActivity<SetAlipayViewInterface, SetAlipayPresenter>
        implements SetAlipayViewInterface {



    @Override
    protected SetAlipayPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new SetAlipayPresenter(this);
    }



    @Override
    public void initView() {
        super.initView();

        setFormHead("设置支付宝");
    }
}
