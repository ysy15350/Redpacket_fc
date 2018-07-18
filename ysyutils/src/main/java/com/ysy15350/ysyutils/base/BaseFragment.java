package com.ysy15350.ysyutils.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ysy15350.ysyutils.BuildConfig;
import com.ysy15350.ysyutils.R;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.common.RequestPermissionType;
import com.ysy15350.ysyutils.common.ViewHolder;
import com.ysy15350.ysyutils.common.message.MessageBox;

import org.xutils.x;


/**
 * Created by yangshiyou on 2016/11/29.
 */

public class BaseFragment extends Fragment implements IView {


    private static final String TAG = "BaseFragment";

    private boolean injected = false;

    protected Context mContext;

    /**
     * 控件ViewGroup
     */
    protected ViewGroup mContentView;

    protected ViewHolder mHolder;

    /**
     * 界面标题
     */
    protected String mTitle = "";

    /**
     * 是否需要登录
     */
    boolean mNeedLogin = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;

        mContext = getActivity();


        mContentView = (ViewGroup) x.view().inject(this, inflater, container);

        mHolder = ViewHolder.get(getActivity(), mContentView);

        init();

        return mContentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        bindData();
    }

    /**
     * 初始化，1：initView；2：readCahce；3：loadData；4：bindData
     */
    private void init() {
        init("");
    }

    public void init(String title) {
        init(title, false);
    }


    public void init(String title, boolean isNeedLogin) {

        setTitle(title);

        mNeedLogin = isNeedLogin;

        initData();

        initView();

        readCahce();

        loadData();

        bindData();
    }


    protected void setFormHead(String title) {
        setTitle(title);
        setBtnBack(true);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    protected void setTitle(String title) {
        if (!CommFunAndroid.isNullOrEmpty(title))
            mHolder.setText(R.id.tv_form_title, title);
//        setrightIcon(false,0);
    }

    protected void setBtnBack(boolean isBack) {
        if (isBack)
            mHolder.setVisibility_VISIBLE(R.id.btn_back);
        else
            mHolder.setVisibility_GONE(R.id.btn_back);
    }

    /**
     * 设置顶部右边图片
     * @param drawableId
     */
    protected void setrightIcon(boolean isBack,int drawableId){

        if (isBack)
            mHolder.setVisibility_VISIBLE(R.id.img_menu);
        else
            mHolder.setVisibility_GONE(R.id.img_menu);

        if(drawableId == 0)
            return;
        mHolder.setBackground(R.id.img_menu,drawableId);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void readCahce() {
    }

    @Override
    public void loadData() {
    }

    @Override
    public void bindData() {

    }

    @Override
    public void showMsg(String msg) {
        if (msg == null)
            return;
        MessageBox.show(msg);
    }

    @Override
    public void showWaitDialog(String msg) {
        if (CommFunAndroid.isNullOrEmpty(msg))
            return;

        MessageBox.showWaitDialog(getActivity(), msg);
    }

    @Override
    public void hideWaitDialog() {
        MessageBox.hideWaitDialog();
    }


    @Override
    public void setViewText(int id, CharSequence text) {
        if (mHolder != null)
            mHolder.setText(id, text);
    }


    @Override
    public String getViewText(int id) {
        if (mHolder != null)
            return mHolder.getViewText(id);
        return "";
    }

    @Override
    public void setTextColor(int id, int color) {
        if (mHolder != null)
            mHolder.setTextColor(id, color);
    }

    @Override
    public void setBackgroundColor(int id, int color) {
        if (mHolder != null)
            mHolder.setBackgroundColor(id, color);
    }

    @Override
    public void setVisibility_GONE(int id) {
        if (mHolder != null)
            mHolder.setVisibility_GONE(id);
    }

    @Override
    public void setVisibility_VISIBLE(int id) {
        if (mHolder != null)
            mHolder.setVisibility_VISIBLE(id);

    }


    protected boolean isLogin() {
        boolean isLogin = BaseData.isLogin();
        return isLogin;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() called" + this);
        System.gc();
        super.onDestroy();
    }

    @Override
    protected void finalize() throws Throwable {
        Log.d(TAG, "finalize() called" + this);
        super.finalize();
    }


    /**
     * 检查是否拥有权限
     *
     * @param permission
     * @param requestCode
     */
    protected boolean checkPermission(String permission, int requestCode, PermissionsResultListener permissionsResultListener) {

        this.mPermissionsResultListener = permissionsResultListener;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            //判断当前Activity是否已经获得了该权限
            if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {

                //如果App的权限申请曾经被用户拒绝过，就需要在这里跟用户做出解释
                boolean isTip = shouldShowRequestPermissionRationale(
                        permission);
                if (isTip) {////表明用户没有彻底禁止弹出权限请求

                    //进行权限请求
                    requestPermissions(
                            new String[]{permission},
                            requestCode);

                } else {//表明用户已经彻底禁止弹出权限请求

                    //这里一般会提示用户进入权限设置界面

                    //进行权限请求
                    requestPermissions(
                            new String[]{permission},
                            requestCode);
                }
            } else {
                if (mPermissionsResultListener != null) {
                    mPermissionsResultListener.onRequestPermissionsResult(requestCode, new String[]{permission}, new int[]{PackageManager.PERMISSION_GRANTED});
                }
                return true;
            }
        } else {
            if (mPermissionsResultListener != null) {
                mPermissionsResultListener.onRequestPermissionsResult(requestCode, new String[]{permission}, new int[]{PackageManager.PERMISSION_GRANTED});
            }
            return true;
        }

        return false;
    }

    /**
     * 注册权限申请回调
     *
     * @param requestCode  申请码
     * @param permissions  申请的权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {


        for (int i = 0; i < permissions.length; i++) {
            Log.d(TAG, "onRequestPermissionsResult() called with: requestCode = [" + requestCode + "], permissions = [" + permissions[i] + "], grantResults = [" + grantResults[i] + "]");
        }

        if (mPermissionsResultListener != null) {
            mPermissionsResultListener.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }


        switch (requestCode) {
            case RequestPermissionType.REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // showMsg("允许");
                    CommFunAndroid.callPhone(mContext, CommFunAndroid.getSharedPreferences("phone"));
                } else {
                    //showMsg("拒绝");
                    // Permission Denied
                    showMsg("你已拒绝拨打手机权限");
                }
                break;
            case RequestPermissionType.REQUEST_CODE_ASK_READ_EXTERNAL_STORAGE:
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    showMsg("你允许读取文件");
//                } else {
//                    showMsg("你已拒绝读取文件请求");
//                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private PermissionsResultListener mPermissionsResultListener;

    public interface PermissionsResultListener {
        void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
    }


    /**
     * 跳转到miui的权限管理页面
     */
    protected void gotoMiuiPermission() {
        Intent i = new Intent("miui.intent.action.APP_PERM_EDITOR");
        ComponentName componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        i.setComponent(componentName);
        i.putExtra("extra_pkgname", getActivity().getPackageName());
        try {
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
            gotoMeizuPermission();
        }
    }


    /**
     * 跳转到魅族的权限管理系统
     */
    private void gotoMeizuPermission() {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            gotoHuaweiPermission();
        }
    }

    /**
     * 华为的权限管理页面
     */
    private void gotoHuaweiPermission() {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
            intent.setComponent(comp);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            startActivity(getAppDetailSettingIntent());
        }

    }

    /**
     * 获取应用详情页面intent
     *
     * @return
     */
    private Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getActivity().getPackageName());
        }
        return localIntent;
    }

}
