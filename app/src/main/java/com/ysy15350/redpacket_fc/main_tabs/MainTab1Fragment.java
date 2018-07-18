package com.ysy15350.redpacket_fc.main_tabs;


import android.content.Intent;
import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.mine.invitationfriends.InvitationFriendsListActivity;
import com.ysy15350.redpacket_fc.mine.share.ShareActivity;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

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
        setrightIcon(true, R.mipmap.icon_invitation);

    }

    /**
     * 邀请好友
     *
     * @param view
     */
    @Event(value = R.id.img_menu)
    private void img_menuClick(View view) {
        startActivity(new Intent(getActivity(), InvitationFriendsListActivity.class));

    }


}