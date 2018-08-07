package com.ysy15350.redpacket_fc.mine.wallet.detailed;

import android.widget.BaseAdapter;

import com.google.gson.reflect.TypeToken;
import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_AdsCard;
import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_Order;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.MVPBaseListViewActivity;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.common.string.JsonConvertor;

import org.xutils.view.annotation.ContentView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import model.AdsCard;
import model.order.OrderListInfo;

/**
 * 明细
 */
@ContentView(R.layout.activity_detailedlist)
public class DetailedListActivity extends MVPBaseListViewActivity<DetailedListViewInterface, DetailedListPresenter>
        implements DetailedListViewInterface {


    /**
     * adapter
     */
    ListViewAdapter_Order mAdapter;

    List<OrderListInfo> mList = new ArrayList<>();

    @Override
    protected DetailedListPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new DetailedListPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setFormHead("明细");

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
        mPresenter.orderList(page, pageSize);
    }


    private List<OrderListInfo> getOrderListFromResponse(Response response) {

        try {
            List<OrderListInfo> list = null;
            if (response != null) {
                Object responseBod = response.getBody();
                if (responseBod != null) {
                    String body = JsonConvertor.toJson(responseBod);
                    list = JsonConvertor.fromJson(body, new TypeToken<List<OrderListInfo>>() {
                    }.getType());
                }
            }

            return list;

        } catch (Exception ex) {
        }

        return null;
    }

    @Override
    public void orderListCallback(boolean isCache, Response response) {
        MessageBox.hideWaitDialog();

        String jsonStr = JsonConvertor.toJson(response);
        List<OrderListInfo> list = getOrderListFromResponse(response);
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
        mAdapter = new ListViewAdapter_Order(this, mList);

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
