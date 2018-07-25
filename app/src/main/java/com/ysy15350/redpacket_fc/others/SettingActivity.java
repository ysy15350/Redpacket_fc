package com.ysy15350.redpacket_fc.others;

import android.content.Intent;
import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
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
 * 设置
 */
@ContentView(com.ysy15350.ysyutils.R.layout.activity_setting)
public class SettingActivity extends BaseActivity {

    /**
     * 夜间免打扰：1：开；0：关
     */
    public int re_disturb;
    /**
     * 整点红包提醒：1：开；0：关
     */
    public int re_whole_red;

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
     * 夜间免打扰
     * @param view
     */
    @Event(value = R.id.llbtn_dxset_xmhk)
    private void llbtn_dxset_xmhkClick(View view) {

        mHolder.getView(R.id.imgbtn_dxset_xmhk).setEnabled(!mHolder.getView(R.id.imgbtn_dxset_xmhk).isEnabled());
        Boolean isChecked = mHolder.getView(R.id.imgbtn_dxset_xmhk).isEnabled();

        if (isChecked) {
            re_disturb=1;
        } else {
            re_disturb=0;
        }

    }

    /**
     * 整点红包提醒
     * @param view
     */
    @Event(value = R.id.llbtn_whole_red)
    private void llbtn_whole_redClick(View view) {

        mHolder.getView(R.id.imgbtn_whole_red).setEnabled(!mHolder.getView(R.id.imgbtn_whole_red).isEnabled());
        Boolean isChecked = mHolder.getView(R.id.imgbtn_whole_red).isEnabled();

        if (isChecked) {
            re_whole_red=1;
        } else {
            re_whole_red=0;
        }

    }

    /**
     * 版本更新
     */
    private void checkVersion() {

        MessageBox.showWaitDialog(this, "正在检测更新...");

        final int versionCode_system = CommFunAndroid.getAppVersionCode(getApplication());

        CommonApi commonApi = new CommonApiImpl();

        commonApi.checkVersion(versionCode_system, new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);

                try {

                    hideWaitDialog();


                    if (response != null) {
                        ResponseHead responseHead = response.getHead();
                        if (responseHead != null) {
                            int status = responseHead.getResponse_status();
                            String msg = responseHead.getResponse_msg();
                            if (status == 100) {



                                // [{"head":{"response_status":100,"response_code":"SUCCESS","response_msg":"发现最新版本：1.0.1","partner_id":null,"service":null,"response_time":"1531628686403","response_time_str":"2018-07-15 12:24:46 403","input_charset":null,"sign_type":null,"sign":null,"login_uid":null,"sessionid":null},"
                                //    body":{"description":"更新内容：1、修改页面；2、优化逻辑","filesize":"1.3M","versioncode":2,"versionname":"V1.0.1"}}]

                                VersionInfo versionInfo = response.getData(VersionInfo.class);
                                if (versionInfo != null) {
                                    int versionCode = versionInfo.getVersioncode();
                                    if(versionCode>versionCode_system){

                                    }

                                }



                            }
//                    showMsg(msg);
                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }



}
