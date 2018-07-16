package com.ysy15350.redpacket_fc.mine.usercenter;

import android.widget.BaseAdapter;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_AdsCard;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.MVPBaseListViewActivity;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.model.PageData;

import org.xutils.view.annotation.ContentView;

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

    @Override
    protected void bindListView(BaseAdapter mAdapter) {
        super.bindListView(mAdapter);
    }
}
