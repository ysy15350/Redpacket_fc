package com.ysy15350.redpacket_fc.main_tabs;


import android.content.Intent;
import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.others.SettingActivity;
import com.ysy15350.ysyutils.base.mvp.MVPBaseFragment;
import com.ysy15350.ysyutils.common.message.MessageBox;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;


@ContentView(R.layout.activity_main_tab4)
public class MainTab4Fragment extends MVPBaseFragment<MainTab4ViewInterface, MainTab4Presenter>
        implements MainTab3ViewInterface {

    private static final String TAG = "MainTab4Fragment";


    public MainTab4Fragment() {
    }

    @Override
    public MainTab4Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab4Presenter(getActivity());
    }

    @Event(value = R.id.ll_menu_1)
    private void ll_menu_1Click(View view) {
        //startActivity(new Intent(getActivity(), MyNoteInfoListActivity.class));
        MessageBox.show("菜单1");
    }

    @Event(value = R.id.ll_menu_6)
    private void ll_menu_6Click(View view) {
        startActivity(new Intent(getActivity(), SettingActivity.class));
    }

}
