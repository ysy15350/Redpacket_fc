package com.ysy15350.redpacket_fc.main_tabs;


import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.base.mvp.MVPBaseFragment;

import org.xutils.view.annotation.ContentView;


@ContentView(R.layout.activity_main_tab3)
public class MainTab3Fragment extends MVPBaseFragment<MainTab3ViewInterface, MainTab3Presenter>
        implements MainTab3ViewInterface {

    private static final String TAG = "MainTab3Fragment";


    public MainTab3Fragment() {
    }

    @Override
    public MainTab3Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab3Presenter(getActivity());
    }


}
