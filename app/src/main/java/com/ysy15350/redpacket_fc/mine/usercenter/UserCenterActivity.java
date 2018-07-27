package com.ysy15350.redpacket_fc.mine.usercenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_AdsCard;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.mine.follow.FollowListActivity;
import com.ysy15350.redpacket_fc.mine.setalipay.SetAlipayActivity;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseListViewActivity;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.model.PageData;
import com.ysy15350.ysyutils.model.SysUser;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import java.util.ArrayList;
import java.util.List;

import model.AdsCard;

/**
 * 个人主页
 */
@ContentView(R.layout.activity_usercenter)
public class UserCenterActivity extends MVPBaseListViewActivity<UserCenterViewInterface, UserCenterPresenter>
        implements UserCenterViewInterface {


    /**
     * adapter
     */
    ListViewAdapter_AdsCard mAdapter;

    List<AdsCard> mList = new ArrayList<>();

    @Override
    protected UserCenterPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new UserCenterPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setFormHead("个人主页");

        bindUserInfo(BaseData.getSysUser());

        page = 1;//从第一页开始
        initData(page, pageSize);


    }

    @Override
    public void initData() {
        super.initData();

        mHolder.setImageURL(R.id.img_head, "", true);
    }

    @Override
    public void initData(int page, int pageSize) {
        MessageBox.showWaitDialog(this, "正在加载");
        mPresenter.getAdsCardList(page, pageSize);
        //获取个人资料
    }


    private List<AdsCard> getAdsCardListFromResponse(Response response) {

        try {
            List<AdsCard> list = null;
            if (response != null) {
                Object body = response.getBody();
                if (body != null) {

                }
            }
            list = new ArrayList<>();
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());
            list.add(new AdsCard());


            return list;

        } catch (Exception ex) {
        }

        return null;
    }

    @Override
    public void bindAdsCardListCallback(boolean isCache, Response response) {

        MessageBox.hideWaitDialog();

        List<AdsCard> list = getAdsCardListFromResponse(response);
        if (page == 1) {
            mList.clear();
        } else {

            if (list == null) {
                showMsg("没有更多了");
                xListView.stopLoadMore();
            } else if (list.isEmpty()) {
                showMsg("没有更多了");
                xListView.stopLoadMore();
            }
        }

        if (list != null)
            mList.addAll(list);
        mAdapter = new ListViewAdapter_AdsCard(this, mList);

        bindListView(mAdapter);// 调用父类绑定数据方法

        if (list != null && !list.isEmpty()) {
            page++;
        }
    }

    /**
     * 绑定个人资料
     *
     * @param sysUser
     */
    private void bindUserInfo(SysUser sysUser) {
        try {
            if (sysUser != null) {


                // 头像图片

                // 用户名称
                if(!CommFun.isNullOrEmpty(sysUser.getNickname())){
                    mHolder.setText(R.id.tv_username, sysUser.getNickname());
                }

                // 个性签名
                if(!CommFun.isNullOrEmpty(sysUser.getPersonalitySignature())){
                    mHolder.setText(R.id.tv_autograph, sysUser.getPersonalitySignature());
                }


                // 性别
                if (sysUser.getSex() == 1) { // 男
                    mHolder.setBackground(R.id.img_sex,R.mipmap.icon_boy);
                }else if (sysUser.getSex() == 2) {
                    mHolder.setBackground(R.id.img_sex,R.mipmap.icon_girl);
                }
                // 常驻地区
                if(!CommFun.isNullOrEmpty(sysUser.getHabitualResidence())){
                    mHolder.setText(R.id.tv_habitualResidence, sysUser.getHabitualResidence());
                }

            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void bindListView(BaseAdapter mAdapter) {
        super.bindListView(mAdapter);
    }

    /**
     * 关注
     *
     * @param view
     */
    @Event(value = R.id.llbtn_follow)
    private void llbtn_followClick(View view) {

        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(this, FollowListActivity.class));
        else
            startActivity(new Intent(this, LoginActivity.class));

    }

    /**
     * 联系
     *
     * @param view
     */
    @Event(value = R.id.llbtn_contact)
    private void llbtn_contactClick(View view) {

        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(this, FollowListActivity.class));
        else
            startActivity(new Intent(this, LoginActivity.class));

    }

    /**
     * 返回
     * @param view
     */
    @Event(value = R.id.imgbtn_back)
    private void imgbtn_backClick(View view) {

        finish();

    }
}
