package com.ysy15350.redpacket_fc.mine.share.invitation;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.base.BaseActivity;

import org.xutils.view.annotation.ContentView;

import api.UserApi;
import api.impl.UserApiImpl;

/**
 * 邀请
 */
@ContentView(R.layout.activity_invitation)
public class InvitationActivity extends BaseActivity {

    //测试接口
    UserApi userApi = new UserApiImpl();

    @Override
    public void initView() {

        super.initView();
        setFormHead("分享");

    }


}
