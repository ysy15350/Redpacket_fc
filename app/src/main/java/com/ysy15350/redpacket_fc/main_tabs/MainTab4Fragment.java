package com.ysy15350.redpacket_fc.main_tabs;


import android.content.Intent;
import android.view.View;

import com.githang.statusbar.StatusBarCompat;
import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.mine.cityowner.City_OwnerActivity;
import com.ysy15350.redpacket_fc.mine.follow.FollowListActivity;
import com.ysy15350.redpacket_fc.mine.share.ShareActivity;
import com.ysy15350.redpacket_fc.mine.usercenter.UserCenterActivity;
import com.ysy15350.redpacket_fc.mine.userinfo.UserInfoActivity;
import com.ysy15350.redpacket_fc.mine.wallet.WalletActivity;
import com.ysy15350.redpacket_fc.others.SettingActivity;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.api.model.ResponseHead;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseFragment;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.string.JsonConvertor;
import com.ysy15350.ysyutils.model.SysUser;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;


@ContentView(R.layout.activity_main_tab4)
public class MainTab4Fragment extends MVPBaseFragment<MainTab4ViewInterface, MainTab4Presenter>
        implements MainTab4ViewInterface {

    private static final String TAG = "MainTab4Fragment";


    public MainTab4Fragment() {
    }

    @Override
    public MainTab4Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab4Presenter(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarCompat.setLightStatusBar(getActivity().getWindow(), false);
    }


    @Override
    public void loadData() {
        super.loadData();


    }

    @Override
    public void bindData() {
        super.bindData();

        SysUser sysUser = BaseData.getSysUser();
        if (null != sysUser) {

            String nickName = CommFun.isNullOrEmpty(sysUser.getNickname()) ? CommFun.getPhone(sysUser.getMobile()) : sysUser.getNickname();
            mHolder.setText(R.id.tv_nickname, nickName);
            if (CommFun.notNullOrEmpty(sysUser.getId() + "")) {
                mHolder.setText(R.id.tv_nameid, sysUser.getId() + "");
            }
        }
    }

    @Override
    public void userInfoCallback(boolean isCache, Response response) {
        try {

            hideWaitDialog();


            if (response != null) {
                String jsonStr = JsonConvertor.toJson(response);
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {


                        SysUser sysUser = response.getData(SysUser.class);
                        if (sysUser != null) {
                            BaseData.setSysUser(sysUser);
                        }


                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 个人资料
     *
     * @param view
     */
    @Event(value = R.id.ll_userinfo)
    private void ll_userinfoClick(View view) {

        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(getActivity(), UserInfoActivity.class));
        else
            startActivity(new Intent(getActivity(), LoginActivity.class));

    }

    /**
     * 个人主页
     *
     * @param view
     */
    @Event(value = R.id.ll_menu_1)
    private void ll_menu_1Click(View view) {
        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(getActivity(), UserCenterActivity.class));
        else
            startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    /**
     * 钱包
     *
     * @param view
     */
    @Event(value = R.id.ll_menu_2)
    private void ll_menu_2Click(View view) {

        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(getActivity(), WalletActivity.class));
        else
            startActivity(new Intent(getActivity(), LoginActivity.class));

    }

    /**
     * 城主
     *
     * @param view
     */
    @Event(value = R.id.ll_menu_3)
    private void ll_menu_3Click(View view) {

        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(getActivity(), City_OwnerActivity.class));
        else
            startActivity(new Intent(getActivity(), LoginActivity.class));

    }


    /**
     * 关注
     *
     * @param view
     */
    @Event(value = R.id.ll_menu_4)
    private void ll_menu_4Click(View view) {

        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(getActivity(), FollowListActivity.class));
        else
            startActivity(new Intent(getActivity(), LoginActivity.class));

    }

    /**
     * 分享
     *
     * @param view
     */
    @Event(value = R.id.ll_menu_5)
    private void ll_menu_5Click(View view) {

        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(getActivity(), ShareActivity.class));
        else
            startActivity(new Intent(getActivity(), LoginActivity.class));

    }


    /**
     * 设置
     *
     * @param view
     */
    @Event(value = R.id.ll_menu_6)
    private void ll_menu_6Click(View view) {
        startActivity(new Intent(getActivity(), SettingActivity.class));
    }

}
