package com.ysy15350.redpacket_fc.others;

import android.content.Intent;
import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.BaseActivity;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.common.message.MessageBox;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import api.UserApi;
import api.impl.UserApiImpl;

@ContentView(com.ysy15350.ysyutils.R.layout.activity_setting)
public class SettingActivity extends BaseActivity {

    //测试接口
    UserApi userApi = new UserApiImpl();

    @Override
    public void initView() {

        super.initView();
        setFormHead("设置");

    }

    @Event(value = R.id.ll_menu_2)
    private void ll_menu_2Click(View view) {
        checkVersion();
    }


    @Event(value = R.id.ll_menu_3)
    private void ll_menu_3Click(View view) {
        BaseData.loginout();
        startActivity(new Intent(this, LoginActivity.class));
    }

    /**
     * 版本更新
     */
    private void checkVersion() {

        MessageBox.showWaitDialog(this, "正在检测更新...");


        userApi.login("13372705137", "123456", new ApiCallBack() {

            //此处为测试，按照接口规范，一般重写第一种方法

            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);

                if (response != null) {
                    MessageBox.show("检查到新版本");
                }
                MessageBox.hideWaitDialog();
            }

            @Override
            public void onSuccess(boolean isCache, String data) {
                super.onSuccess(isCache, data);
                if (data != null) {
                    MessageBox.show("检查到新版本");
                }
                MessageBox.hideWaitDialog();
            }
        });

    }

}
