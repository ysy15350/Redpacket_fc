package com.ysy15350.redpacket_fc.mine.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.mine.setalipay.SetAlipayActivity;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.api.model.ResponseHead;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
import com.ysy15350.ysyutils.common.AppStatusManager;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.model.PageData;
import com.ysy15350.ysyutils.model.SysUser;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;


/**
 * 个人资料
 */
@ContentView(R.layout.activity_userinfo)
public class UserInfoActivity extends MVPBaseActivity<UserInfoViewInterface, UserInfoPresenter>
        implements UserInfoViewInterface {

    private static final String TAG = "UserInfoActivity";

    private TextView tv_time;

    @Override
    protected UserInfoPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new UserInfoPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        bindUserInfo(BaseData.getSysUser());
        MessageBox.showWaitDialog(this,"数据加载中...");
        mPresenter.userInfo();
    }

    @Override
    public void userInfoCallback(boolean isCache, Response response) {


        try {

            hideWaitDialog();


            if (response != null) {
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {


                        SysUser sysUser = response.getData(SysUser.class);
                        if (sysUser != null) {

                            BaseData.setSysUser(sysUser);
                            bindUserInfo(sysUser);
                        }



                    }
//                    showMsg(msg);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 绑定个人资料
     * @param sysUser
     */
    private void bindUserInfo(SysUser sysUser){
        try{
            if(sysUser != null){
                Log.d(TAG, "bindUserInfo: "+sysUser.getMobile());
                showMsg(sysUser.getMobile()+"");
            }
        }catch (Exception e){}
    }

    /**
     * 设置支付宝
     * @param view
     */
    @Event(value = R.id.btn_setalipay)
    private void btn_setalipayClick(View view) {

        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(this, SetAlipayActivity.class));
        else
            startActivity(new Intent(this, LoginActivity.class));

    }


}
