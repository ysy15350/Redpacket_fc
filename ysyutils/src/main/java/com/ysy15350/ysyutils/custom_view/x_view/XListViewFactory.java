package com.ysy15350.ysyutils.custom_view.x_view;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.ysy15350.ysyutils.common.CommFun;


/**
 * Created by yangshiyou on 2017/10/31.
 */

public class XListViewFactory implements XListView.IXListViewListener, AdapterView.OnItemClickListener {

    private static final String TAG = "XListViewFactory";
    private static XListViewFactory xListViewFactory;


    //当前页码，每页显示数据条数
    private int mPage = 1, mPageSize = 10;

    //下拉刷新控件
    private XListView mXListView;

    private View view_nodata;

    public static synchronized XListViewFactory getInstance() {
        if (xListViewFactory == null) {
            xListViewFactory = new XListViewFactory();
        }
        return xListViewFactory;
    }

    private XListViewFactory() {
        init();
    }

    private XListViewFactory(Context context) {
        init();
    }

    private void init() {

    }


    public void setViewNodata(View view) {
        view_nodata = view;
        if (view_nodata != null) {
            view_nodata.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRefresh();//刷新数据
                }
            });
        }

    }


    public void setXListView(XListView xListView) {
        this.mXListView = xListView;

    }


    @Override
    public void onRefresh() {
        mPage = 1;
    }

    @Override
    public void onLoadMore() {

    }


    public void bindListView(BaseAdapter adapter) {
        if (mXListView != null) {
            if (adapter != null) {
                int count = adapter.getCount();

                //---------------------------------------

                if (view_nodata != null) {//如果有默认View
                    if (count == 0) {//无数据
                        mXListView.setVisibility(View.GONE);//隐藏列表
                        view_nodata.setVisibility(View.VISIBLE);//显示"无数据"

                    } else {
                        mXListView.setVisibility(View.VISIBLE);//显示列表
                        view_nodata.setVisibility(View.GONE);//隐藏"无数据"
                    }
                }

                //---------------------------------------

                if (mPage == 1) {
                    String timeStr = CommFun.getDateString("yyyy-MM-dd HH:mm:ss");
                    mXListView.setRefreshTime(timeStr);

                    mXListView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();//加载更多通知列表更新数据
                }
            }


            mXListView.stopRefresh();//停止刷新
            mXListView.stopLoadMore();//停止加载更多

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListener != null) {
            mListener.onItemClick(parent, view, position, id);
        }
    }

    private XListViewFactoryListener mListener;

    public void setXListViewFactoryListener(XListViewFactoryListener listener) {
        mListener = listener;
    }

    public interface XListViewFactoryListener {

        public void onRefresh();//刷新

        public void onLoadMore();//加载更多

        public void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }

}
