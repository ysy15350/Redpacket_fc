package com.ysy15350.redpacket_fc.main_tabs;


import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.base.mvp.MVPBaseFragment;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_main_tab1)
public class MainTab1Fragment extends MVPBaseFragment<MainTab1ViewInterface, MainTab1Presenter>
        implements MainTab1ViewInterface {

    private static final String TAG = "MainTab1Fragment";


    public MainTab1Fragment() {
    }

    @Override
    public MainTab1Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab1Presenter(getActivity());
    }

    @Override
    public void initView() {
        super.initView();

        setFormHead("首页");


    }


}
