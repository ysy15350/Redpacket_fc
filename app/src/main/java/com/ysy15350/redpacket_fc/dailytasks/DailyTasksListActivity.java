package com.ysy15350.redpacket_fc.dailytasks;

import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_DailyTasks;
import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_Follow;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.MVPBaseListViewActivity;
import com.ysy15350.ysyutils.common.message.MessageBox;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import model.dailytasks.DailyTasksListInfo;
import model.follow.FollowListInfo;

/**
 * 每日任务
 */
@ContentView(R.layout.activity_dailytasks)
public class DailyTasksListActivity extends MVPBaseListViewActivity<DailyTasksListViewInterface, DailyTasksListPresenter>
        implements DailyTasksListViewInterface {

    // 任务列表接口、领取FCB接口

    /**
     * 任务进度条
     */
    @ViewInject(R.id.progress)
    private ProgressBar progress;

    /**
     * adapter
     */
    ListViewAdapter_DailyTasks mAdapter;

    List<DailyTasksListInfo> mList = new ArrayList<>();

    @Override
    protected DailyTasksListPresenter createPresenter() {
        return new DailyTasksListPresenter(this);
    }

    @Override
    public void initView() {
        super.initView();

        setFormHead("每日任务");

    }

    @Override
    protected void onResume() {
        super.onResume();

        page = 1;//从第一页开始
        initData(page, pageSize);

        setParameter(60);


    }

    @Override
    public void initData() {
        super.initData();

        mHolder.setImageURL(R.id.img_head, "", true);
    }

    @Override
    public void initData(int page, int pageSize) {
        MessageBox.showWaitDialog(this, "正在加载");
        mPresenter.getDailyTasksList(page, pageSize);
        //获取个人资料
    }


    private List<DailyTasksListInfo> getDailyTasksListInfoListFromResponse(Response response) {

        try {
            List<DailyTasksListInfo> list = null;
            if (response != null) {
                Object body = response.getBody();
                if (body != null) {

                }
            }

            list = new ArrayList<>();
            list.add(new DailyTasksListInfo());
            list.add(new DailyTasksListInfo());
            list.add(new DailyTasksListInfo());
            list.add(new DailyTasksListInfo());
            list.add(new DailyTasksListInfo());
            list.add(new DailyTasksListInfo());
            list.add(new DailyTasksListInfo());
            list.add(new DailyTasksListInfo());
            list.add(new DailyTasksListInfo());

            return list;

        } catch (Exception ex) {
        }

        return null;
    }

    @Override
    public void bindDailyTasksListCallback(boolean isCache, Response response) {

        MessageBox.hideWaitDialog();

        List<DailyTasksListInfo> list = getDailyTasksListInfoListFromResponse(response);
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
        mAdapter = new ListViewAdapter_DailyTasks(this, mList);

        bindListView(mAdapter);// 调用父类绑定数据方法

        if (list != null && !list.isEmpty()) {
            page++;
        }
    }

    /**
     * 活跃度使用值
     */
    int stractive = 0;

    /**
     * 进度条使用值
     */
    int per = 0;

    /**
     * 下标使用值
     */
    int marginStart = 0;

    int number = 0;

    /**
     * 设置进度条参数
     * @param active
     */
    private void setParameter(int active) {
        stractive = active;
        if(active<=100){
            //20(10:40);40(32:215);60(54:393);80(76:565);100(100:750)
            switch (active) {
                case 20:
                    per = 10;
                    marginStart = 40;
                    break;
                case 40:
                    per = 32;
                    marginStart = 215;
                    break;
                case 60:
                    per = 54;
                    marginStart = 393;
                    break;
                case 80:
                    per = 76;
                    marginStart = 565;
                    break;
                case 100:
                    per = 100;
                    marginStart = 750;
                    break;
            }
        }else {
            per = 100;
            marginStart = 750;
        }
        mHandler.sendEmptyMessage(0);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            // 进度条
            if (number <= per) {
                mHandler.sendEmptyMessageDelayed(0, 10); // 执行速度
                progress.setProgress(number);
                number++;
                mHolder.setVisibility_GONE(R.id.ll_activity);
            } else {
                number = 0;
                // 下标
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mHolder.getView(R.id.ll_activity).getLayoutParams();
                layoutParams.setMarginStart(marginStart);
                mHolder.getView(R.id.ll_activity).setLayoutParams(layoutParams);
                // 活跃值
                mHolder.setText(R.id.tv_activityvalue, "活跃度：" + stractive);
                mHolder.setVisibility_VISIBLE(R.id.ll_activity);
            }
        }
    };

    @Override
    protected void bindListView(BaseAdapter mAdapter) {
        super.bindListView(mAdapter);
    }

}
