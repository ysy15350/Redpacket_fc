package com.ysy15350.redpacket_fc.mine.follow;

import android.widget.BaseAdapter;

import com.ysy15350.redpacket_fc.R;

import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_Follow;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.MVPBaseListViewActivity;
import com.ysy15350.ysyutils.common.message.MessageBox;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;


import model.follow.FollowListInfo;

/**
 * 关注
 */
@ContentView(R.layout.activity_follow)
public class FollowListActivity extends MVPBaseListViewActivity<FollowListViewInterface, FollowListPresenter>
        implements FollowListViewInterface {


    /**
     * adapter
     */
    ListViewAdapter_Follow mAdapter;

    List<FollowListInfo> mList = new ArrayList<>();

    @Override
    protected FollowListPresenter createPresenter() {
        return new FollowListPresenter(this);
    }

    @Override
    public void initView() {
        super.initView();
        setFormHead("关注");

    }

    @Override
    protected void onResume() {
        super.onResume();

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
        mPresenter.getFollowList(page, pageSize);
        //获取个人资料
    }


    private List<FollowListInfo> getFollowListInfoListFromResponse(Response response) {

        try {
            List<FollowListInfo> list = null;
            if (response != null) {
                Object body = response.getBody();
                if (body != null) {

                }
            }
            list = new ArrayList<>();
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());
            list.add(new FollowListInfo());


            return list;

        } catch (Exception ex) {
        }

        return null;
    }

    @Override
    public void bindFollowListCallback(boolean isCache, Response response) {

        MessageBox.hideWaitDialog();

        List<FollowListInfo> list = getFollowListInfoListFromResponse(response);
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
        mAdapter = new ListViewAdapter_Follow(this, mList);

        bindListView(mAdapter);// 调用父类绑定数据方法

        if (list != null && !list.isEmpty()) {
            page++;
        }
    }



    @Override
    protected void bindListView(BaseAdapter mAdapter) {
        super.bindListView(mAdapter);
    }



}
