package com.ysy15350.redpacket_fc.mine.share;

import android.content.Intent;
import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.mine.share.invitation.InvitationActivity;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.api.model.ResponseHead;
import com.ysy15350.ysyutils.base.BaseActivity;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.model.VersionInfo;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import api.CommonApi;
import api.UserApi;
import api.impl.CommonApiImpl;
import api.impl.UserApiImpl;

/**
 * 分享
 */
@ContentView(R.layout.activity_share)
public class ShareActivity extends BaseActivity {

    //测试接口
    UserApi userApi = new UserApiImpl();

    @Override
    public void initView() {

        super.initView();
        setFormHead("分享");

    }

    /**
     * 立即邀请
     * @param view
     */
    @Event(value = R.id.imgbtn_invitation)
    private void imgbtn_invitationClick(View view) {

        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(this, InvitationActivity.class));
        else
            startActivity(new Intent(this, LoginActivity.class));

    }


}
