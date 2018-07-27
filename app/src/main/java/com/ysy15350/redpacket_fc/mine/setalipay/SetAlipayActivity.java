package com.ysy15350.redpacket_fc.mine.setalipay;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.adapters.ListViewAdapter_AdsCard;
import com.ysy15350.redpacket_fc.mine.usercenter.UserCenterPresenter;
import com.ysy15350.redpacket_fc.mine.usercenter.UserCenterViewInterface;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.api.model.ResponseHead;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
import com.ysy15350.ysyutils.base.mvp.MVPBaseListViewActivity;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.model.SysUser;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import java.util.ArrayList;
import java.util.List;

import model.AdsCard;

/**
 * 设置支付宝
 */
@ContentView(R.layout.activity_setalipay)
public class SetAlipayActivity extends MVPBaseActivity<SetAlipayViewInterface, SetAlipayPresenter>
        implements SetAlipayViewInterface {



    @Override
    protected SetAlipayPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new SetAlipayPresenter(this);
    }



    @Override
    public void initView() {
        super.initView();

        setFormHead("设置支付宝");
    }

    /**
     * 保护支付宝信息
     *
     * @param view
     */
    @Event(value = R.id.btn_preservation1)
    private void btn_preservation1Click(View view) {

        SysUser sysUser = new SysUser();

        String realname = mHolder.getViewText(R.id.et_realname);
        if (CommFunAndroid.isNullOrEmpty(realname)) {
            showMsg("请输入真实姓名");
            return;
        }

        String alipayAccount = mHolder.getViewText(R.id.et_alipayAccount);
        if (CommFunAndroid.isNullOrEmpty(alipayAccount)) {
            showMsg("请输入支付宝账号");
            return;
        }

        sysUser.setRealname(realname);
        sysUser.setAlipayAccount(alipayAccount);
        MessageBox.showWaitDialog(this,"数据保存中...");
        mPresenter.saveUserInfo(sysUser);
    }

    @Override
    public void saveUserInfoCallback(boolean isCache, Response response) {
        try {

            hideWaitDialog();

            if (response != null) {
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {
                        finish();
                    }
                    showMsg(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
