package com.ysy15350.redpacket_fc.main_tabs;


import android.content.Intent;
import android.view.View;

import com.ysy15350.redpacket_fc.MainActivity;
import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.active_area.activearea.ActiveAreaActivity;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.mine.share.ShareActivity;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;


@ContentView(R.layout.activity_main_tab2)
public class MainTab2Fragment extends MVPBaseFragment<MainTab2ViewInterface, MainTab2Presenter>
        implements MainTab2ViewInterface {

    private static final String TAG = "MainTab2Fragment";


    public MainTab2Fragment() {
    }

    @Override
    public MainTab2Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab2Presenter(getActivity());
    }

    /**
     * 收集广告红包
     *
     * @param view
     */
    @Event(value = R.id.ll_btn1)
    private void ll_btn1Click(View view) {

        MainActivity.tab_position = 0;
        startActivity(new Intent(getActivity(), MainActivity.class));

    }

    /**
     * 访问好友广告
     *
     * @param view
     */
    @Event(value = R.id.llbtn_activearea)
    private void llbtn_activeareaClick(View view) {

        startActivity(new Intent(getActivity(), ActiveAreaActivity.class));

    }


}
