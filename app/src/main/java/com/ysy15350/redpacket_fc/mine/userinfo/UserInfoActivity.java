package com.ysy15350.redpacket_fc.mine.userinfo;

import android.os.Bundle;
import android.widget.TextView;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
import com.ysy15350.ysyutils.common.AppStatusManager;

import org.xutils.view.annotation.ContentView;


/**
 * 个人资料
 */
@ContentView(R.layout.activity_userinfo)
public class UserInfoActivity extends MVPBaseActivity<UserInfoViewInterface, UserInfoPresenter>
        implements UserInfoViewInterface {

    private static final String TAG = "GuideActivity";

    private TextView tv_time;

    @Override
    protected UserInfoPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new UserInfoPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppStatusManager.getInstance().setAppStatus(AppStatusManager.AppStatusConstant.APP_NORMAL);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
    }


}
