package com.ysy15350.redpacket_fc.others;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.api.model.ResponseHead;
import com.ysy15350.ysyutils.base.BaseActivity;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.common.RequestPermissionType;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.common.string.JsonConvertor;
import com.ysy15350.ysyutils.custom_view.dialog.ConfirmDialog;
import com.ysy15350.ysyutils.model.VersionInfo;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import api.CommonApi;
import api.FileApi;
import api.UserApi;
import api.impl.CommonApiImpl;
import api.impl.FileApiImpl;
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

        if (BaseData.isLogin())//如果需要登录
            mHolder.setText(R.id.tv_Exitaccount,"注销账号");
        else
            mHolder.setText(R.id.tv_Exitaccount,"未登录");


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
        FileApi fileApi = new FileApiImpl();

        fileApi.checkversion(versionCode_system, new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);

                try {

                    hideWaitDialog();


                    if (response != null) {
                        String jsonStr = JsonConvertor.toJson(response);
                        ResponseHead responseHead = response.getHead();
                        if (responseHead != null) {
                            int status = responseHead.getResponse_status();
                            String msg = responseHead.getResponse_msg();
                            if (status == 100) {



                                // [{"head":{"response_status":100,"response_code":"SUCCESS","response_msg":"发现最新版本：1.0.1","partner_id":null,"service":null,"response_time":"1531628686403","response_time_str":"2018-07-15 12:24:46 403","input_charset":null,"sign_type":null,"sign":null,"login_uid":null,"sessionid":null},"
                                //    body":{"description":"更新内容：1、修改页面；2、优化逻辑","filesize":"1.3M","versioncode":2,"versionname":"V1.0.1"}}]

                                final VersionInfo versionInfo = response.getData(VersionInfo.class);
                                if (versionInfo != null) {
                                    double versionCode = versionInfo.getVersioncodeX();
                                    if(versionCode>versionCode_system){
                                        boolean isGranted = checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, RequestPermissionType.REQUEST_CODE_ASK_READ_EXTERNAL_STORAGE, new PermissionsResultListener() {
                                            @Override
                                            public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
                                                boolean isGranted = false;
                                                if (grantResults != null && grantResults != null) {
                                                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                                                        isGranted = true;

                                                    }
                                                }

                                                if (isGranted) {
                                                    String title = "版本更新(" + "" + ")";
                                                    String versionName = versionInfo.getVersionnameX();
                                                    String content = versionInfo.getDes();
                                                    double fileSize = versionInfo.getSize();
                                                    //http://www.360vrdh.com:8080/api/file/downloadApk/com.ysy15350.readpacket/32.apk
                                                    //http://www.360vrdh.com:8080/upload/uploadFiles/apks/red_packet(1.0.1).apk
                                                    //"http://www.mg0607.cn/Public/qrcode/qsk1.0.19_legu_signed_zipalign.apk";
                                                    //http://192.168.0.108:8080/api/file/downloadApk/com.ysy15350.readpacket/32.apk
                                                    //http://192.168.0.108:8080/api/file/download
                                                    //http://192.168.0.108:8080/api/file/downloadApk/com.ysy15350.readpacket/32.apk
                                                    //http://www.mg0607.cn/Public/qrcode/qsk1.0.19_legu_signed_zipalign.apk

                                                    String url = versionInfo.getDownloadurl();
                                                    updateVersion(title, versionName, content, fileSize+"", url);
                                                } else {
                                                    ConfirmDialog confirmDialog = new ConfirmDialog(SettingActivity.this, "你已拒绝读写手机存储，去权限设置页面打开？");
                                                    confirmDialog.setDialogListener(new ConfirmDialog.DialogListener() {
                                                        @Override
                                                        public void onCancelClick() {

                                                        }

                                                        @Override
                                                        public void onOkClick() {
                                                            gotoMiuiPermission();
                                                        }
                                                    });
                                                    confirmDialog.show();

                                                }

                                            }
                                        });

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
