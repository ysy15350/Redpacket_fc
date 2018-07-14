package com.ysy15350.redpacket_fc.authentication.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ysy15350.redpacket_fc.MainActivity;
import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.api.model.ResponseHead;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.model.PageData;
import com.ysy15350.ysyutils.model.SysUser;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;


/**
 * 登录页面
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends MVPBaseActivity<LoginViewInterface, LoginPresenter> implements LoginViewInterface {

    View contentView;

    @Override
    protected LoginPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new LoginPresenter(LoginActivity.this);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        contentView = getWindow().getDecorView();

//        YSYApplication.getContext();

        String userName = CommFunAndroid.getSharedPreferences("userName");
        if (!CommFunAndroid.isNullOrEmpty(userName))
            mHolder.setText(R.id.et_mobile, userName);

    }

    @Override
    protected void onResume() {
        super.onResume();

        String mobile = CommFunAndroid.getSharedPreferences("mobile");
        if (!CommFun.isNullOrEmpty(mobile)) {
            mHolder.setText(R.id.et_mobile, mobile);
        }
    }

    /**
     * 获取验证码
     *
     * @param view
     */
    @Event(value = R.id.btn_get_code)
    private void btn_get_codeClick(View view) {

        String mobile = mHolder.getViewText(R.id.et_mobile);
        if (CommFunAndroid.isNullOrEmpty(mobile)) {
            showMsg("请输入手机号");
            return;
        }

        showWaitDialog("正在请求服务器...");

        mPresenter.getDynCode(mobile);//获取手机验证码

    }

    /**
     * 登录按钮点击事件
     *
     * @param view
     */
    @Event(value = R.id.btn_login)
    private void btn_loginClick(View view) {

        showWaitDialog("正在进行身份验证...");

        String mobile = mHolder.getViewText(R.id.et_mobile);
        if (CommFunAndroid.isNullOrEmpty(mobile)) {
            showMsg("请输入手机号");
            return;
        }

        String code = mHolder.getViewText(R.id.et_code);
        if (CommFunAndroid.isNullOrEmpty(code)) {
            showMsg("请输入验证码");
            return;
        }

        mPresenter.loginByCode(mobile, code);

        // mPresenter.getData();//
        // 调用presenter的获取数据方法，在presenter类中调用bindData接口，本类实现了bindData方法
        //
    }

    @Override
    public void getDynCodeCallback(boolean isCache, Response response) {
        try {

            hideWaitDialog();

            if (response != null) {
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {

                        PageData pageData = response.getData(PageData.class);

                        //为了测试，从接口返回结果中获取验证码并显示，正式使用时注释
                        if (pageData != null) {

                            String code = pageData.getString("code");
                            if (!CommFun.isNullOrEmpty(code)) {
                                mHolder.setText(R.id.et_code, code);
                            }
                        }

                    }
                    showMsg(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loginByCodeCallback(boolean isCache, Response response) {
        try {

            hideWaitDialog();

            if (response != null) {
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {

                        PageData pageData = response.getData(PageData.class);

                        //为了测试，从接口返回结果中获取验证码并显示，正式使用时注释
                        if (pageData != null) {

                            String token = pageData.getString("token");
                            if (!CommFun.isNullOrEmpty(token)) {
                                BaseData.setToken(token);
                            }
                        }

                        mPresenter.activate();

                    } else
                        showMsg(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void activateCallback(boolean isCache, Response response) {
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
                            String mobile = sysUser.getMobile();
                            CommFunAndroid.setSharedPreferences("mobile", mobile);

                            BaseData.setSysUser(sysUser);
                        }

                        gotoMainActivity();

                    }
                    showMsg(msg);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转主页面
     */
    private void gotoMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

        this.finish();
    }


}
