package com.ysy15350.redpacket_fc.active_area.activearea;

import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * 活跃区
 */
@ContentView(R.layout.activity_activearea)
public class ActiveAreaActivity extends MVPBaseActivity<ActiveAreaViewInterface, ActiveAreaPresenter>
        implements ActiveAreaViewInterface {



    @Override
    protected ActiveAreaPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new ActiveAreaPresenter(this);
    }

    @Override
    public void initView() {
        super.initView();
        setFormHead("");

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
