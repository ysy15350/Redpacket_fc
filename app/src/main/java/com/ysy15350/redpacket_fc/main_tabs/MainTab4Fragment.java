package com.ysy15350.redpacket_fc.main_tabs;


import android.content.Intent;
import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.mine.usercenter.UserCenterActivity;
import com.ysy15350.redpacket_fc.others.SettingActivity;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseFragment;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.model.SysUser;

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

    @Override
    public void bindData() {
        super.bindData();

        SysUser sysUser = BaseData.getSysUser();
        if (null != sysUser) {

            String nickName = CommFun.isNullOrEmpty(sysUser.getUsername()) ? CommFun.getPhone(sysUser.getMobile()) : sysUser.getUsername();
            mHolder.setText(R.id.tv_nickname, nickName);
        }
    }

    @Event(value = R.id.ll_menu_1)
    private void ll_menu_1Click(View view) {
        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(getActivity(), UserCenterActivity.class));
        else
            startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    @Event(value = R.id.ll_menu_6)
    private void ll_menu_6Click(View view) {
        startActivity(new Intent(getActivity(), SettingActivity.class));
    }

}
