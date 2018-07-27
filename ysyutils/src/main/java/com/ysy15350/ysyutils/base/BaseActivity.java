package com.ysy15350.ysyutils.base;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.githang.statusbar.StatusBarCompat;
import com.ysy15350.ysyutils.BuildConfig;
import com.ysy15350.ysyutils.R;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.api.model.ResponseHead;
import com.ysy15350.ysyutils.common.AppStatusManager;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.common.RequestPermissionType;
import com.ysy15350.ysyutils.common.SystemModels;
import com.ysy15350.ysyutils.common.ViewHolder;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.custom_view.dialog.ConfirmDialog;
import com.ysy15350.ysyutils.custom_view.dialog.DownloadDialog;
import com.ysy15350.ysyutils.gaodemap.GaodeMapUtils;
import com.ysy15350.ysyutils.gaodemap.LocationListener;
import com.ysy15350.ysyutils.model.FileInfo;
import com.ysy15350.ysyutils.model.LocationInfo;

import org.xutils.common.util.LogUtil;
import org.xutils.x;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by yangshiyou on 2016/11/29.
 */

public class BaseActivity extends AppCompatActivity implements IView, GestureDetector.OnGestureListener {

    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;

    /**
     * 控件ViewGroup
     */
    protected View mContentView;

    protected ViewHolder mHolder;

    /**
     * 界面标题
     */
    protected String mTitle = "";

    /**
     * 是否需要登录
     */
    boolean mNeedLogin = false;


    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ACTIVITY_COUNT++;
        String str = this.toString();
        activityNames.add(str);

        StringBuilder sb = new StringBuilder();

        for (String activityName :
                activityNames) {
            sb.append(activityName + "\n");
        }
        Log.d(TAG, "onCreate() called with: ******************activityNames****************** = [" + sb.toString() + "]");

        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]" + "------------------ACTIVITY_COUNT=" + ACTIVITY_COUNT);


        x.view().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkAppStatus();//如果是重新打开的应用，重新从入口进入，缺陷：不会回到选择照片的页面

        initGesture();//手势


        //ExitApplication.getInstance().addActivity(this);// 添加当前Activity


        mContentView = getWindow().getDecorView();

        mHolder = ViewHolder.get(this, mContentView);

        init();
    }

    //http://blog.csdn.net/u011511577/article/details/54603256
    protected void checkAppStatus() {
        if (AppStatusManager.getInstance().getAppStatus() == AppStatusManager.AppStatusConstant.APP_FORCE_KILLED) {
            //该应用已被回收，应用启动入口SplashActivity，走重启流程
//            Intent intent = new Intent(this, GuideActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
            restartApplication();
            showMsg("应用被回收重新执行");
        }
    }

    protected void restartApplication() {
        finish();
    }


    /**
     * 初始化，1：initView；2：readCahce；3：loadData；4：bindData
     */
    private void init() {

        initView();

        initData();

        readCahce();

        loadData();

        bindData();
    }

    /**
     * 初始化，1：initView；2：readCahce；3：loadData；4：bindData
     *
     * @param context
     * @param contentView
     * @param title
     * @param isNeedLogin
     */
    public void init(Context context, View contentView, String title, boolean isNeedLogin) {

        mContentView = contentView;
        mTitle = title;
        mNeedLogin = isNeedLogin;


        initView();

        initData();

        readCahce();

        loadData();

        bindData();
    }

    /**
     * 设置头部(带返回箭头)
     *
     * @param title
     */
    protected void setFormHead(String title) {
        setTitle(title);
        setBtnBack(true);
    }

    /**
     * 设置菜单
     *
     * @param menu
     */
    protected void setMenuText(String menu) {
        if (!CommFunAndroid.isNullOrEmpty(menu)) {
            mHolder.setVisibility_VISIBLE(R.id.tv_menu).setText(R.id.tv_menu, menu);
        }
    }

    /**
     * 设置标题（不带返回箭头）
     *
     * @param title
     */
    protected void setTitle(String title) {
        if (!CommFunAndroid.isNullOrEmpty(title))
            mHolder.setText(R.id.tv_form_title, title);
    }

    /**
     * 设置是否显示返回箭头
     *
     * @param isBack
     */
    protected void setBtnBack(boolean isBack) {
        if (isBack)
            mHolder.setVisibility_VISIBLE(R.id.btn_back);
        else
            mHolder.setVisibility_GONE(R.id.btn_back);
    }

    /**
     * 设置顶部右边图片
     *
     * @param drawableId
     */
    protected void setrightIcon(boolean isBack, int drawableId) {

        if (isBack)
            mHolder.setVisibility_VISIBLE(R.id.img_menu);
        else
            mHolder.setVisibility_GONE(R.id.img_menu);

        if (drawableId == 0)
            return;
        mHolder.setBackground(R.id.img_menu, drawableId);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        // 填充状态栏
        CommFunAndroid.fullScreenStatuBar(this);


        int color = getResources().getColor(R.color.blue);

        //StatusBarCompat.setStatusBarColor(this, color, true);
        StatusBarCompat.setLightStatusBar(getWindow(), true);

        if (mHolder == null)
            return;
        View btn_back = mHolder.getView(R.id.btn_back);
        if (btn_back != null) {
            btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
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
        if (CommFunAndroid.isNullOrEmpty(msg))
            return;

        MessageBox.show(msg);
    }

    @Override
    public void showWaitDialog(String msg) {
        if (CommFunAndroid.isNullOrEmpty(msg))
            return;

        MessageBox.showWaitDialog(this, msg);
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

        mNeedLogin = true;


        return mNeedLogin;
    }


    /**
     * 获取版本信息
     *
     * @param response
     */
    protected void getFileInfo(Response response) {

        try {

            hideWaitDialog();

            if (response != null) {
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {//检测到新版本
                        FileInfo fileInfo = response.getData(FileInfo.class);
                        if (fileInfo != null) {
                            String title = "版本更新(" + fileInfo.getVersionname() + ")";
                            String versionName = fileInfo.getVersionname();
                            String content = fileInfo.getDes();
                            String fileSize = fileInfo.getSize() + " M";
                            String url = fileInfo.getDownloadurl();
                            updateVersion(title, versionName, content, fileSize, url);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 打开更新弹窗，下载更新
     *
     * @param title
     * @param versionName
     * @param content
     * @param fileSize
     * @param url
     */
    protected void updateVersion(final String title, final String versionName, final String content, final String fileSize, final String url) {

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

                    DownloadDialog dialog = new DownloadDialog(BaseActivity.this, title,
                            versionName, content, fileSize,
                            url);

                    dialog.setDialogListener(new DownloadDialog.DialogListener() {
                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onOkClick() {

                        }
                    });

                    dialog.show();


                } else {
                    ConfirmDialog confirmDialog = new ConfirmDialog(BaseActivity.this, "你已拒绝读写手机存储，去权限设置页面打开？");
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


    //重写会导致Android4.4报错
    //java.lang.ClassNotFoundException: Didn’t find class “android.os.PersistableBundle” on path: DexPathList
    //    @Override
    //    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
    //        super.onSaveInstanceState(outState, outPersistentState);
    //        Log.i(TAG, "<<<<<<<<<<<<<<<**********onSaveInstanceState*************>>>>>>>>>>>>>>" + this.toString());
    //    }

    //    在手机上调用系统相机的时候，有很大的几率会导致内存不足从而调用相机的app或者app的调用相机的Activity界面被强制回收
    // ，所以调用相机的Activity重写 onSaveInstanceState 是非常必要的，
    // 在onSaveInstanceState方法中将局部变量保存起来，
    // 同时在  onRestoreInstanceState  方法中重新获取这些局部变量并做必要的逻辑处理。

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "<<<<<<<<<<<<<<<**********onRestoreInstanceState*************>>>>>>>>>>>>>>" + this.toString());
    }

    //    @Override
    //    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
    //        super.onRestoreInstanceState(savedInstanceState, persistentState);
    //        Log.i(TAG, "<<<<<<<<<<<<<<<**********onRestoreInstanceState*************>>>>>>>>>>>>>>" + this.toString());
    //    }

    //    @Override
    //    public void onConfigurationChanged(Configuration newConfig) {
    //        super.onConfigurationChanged(newConfig);
    //        Log.i(TAG, "<<<<<<<<<<<<<<<**********onConfigurationChanged*************>>>>>>>>>>>>>>" + this.toString());
    //    }

    /**
     * Requests given permission.
     * If the permission has been denied previously, a Dialog will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    protected void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            showAlertDialog(getString(R.string.permission_title_rationale), rationale,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(BaseActivity.this,
                                    new String[]{permission}, requestCode);
                        }
                    }, getString(R.string.btn_ok), null, getString(R.string.btn_cancel));
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    private AlertDialog mAlertDialog;

    /**
     * This method shows dialog with given title & message.
     * Also there is an option to pass onClickListener for positive & negative button.
     *
     * @param title                         - dialog title
     * @param message                       - dialog message
     * @param onPositiveButtonClickListener - listener for positive button
     * @param positiveText                  - positive button text
     * @param onNegativeButtonClickListener - listener for negative button
     * @param negativeText                  - negative button text
     */
    protected void showAlertDialog(@Nullable String title, @Nullable String message,
                                   @Nullable DialogInterface.OnClickListener onPositiveButtonClickListener,
                                   @NonNull String positiveText,
                                   @Nullable DialogInterface.OnClickListener onNegativeButtonClickListener,
                                   @NonNull String negativeText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, onPositiveButtonClickListener);
        builder.setNegativeButton(negativeText, onNegativeButtonClickListener);
        mAlertDialog = builder.show();
    }


    /**
     * 申请权限
     */
    public boolean callPhoneCheckPermission(Activity context, String phone) {

        Log.d(TAG, "callPhoneCheckPermission() called with: context = [" + context + "], phone = [" + phone + "]");

        //判断Android版本是否大于23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CALL_PHONE},
                        RequestPermissionType.REQUEST_CODE_ASK_CALL_PHONE);

                CommFunAndroid.setSharedPreferences("phone", phone);
                return false;
            } else {
                CommFunAndroid.callPhone(context, phone);
            }
        } else {
            CommFunAndroid.callPhone(context, phone);
        }

        return true;
    }

    /**
     * 申请读取文件权限
     */
    public boolean callReadExtrnalStoreagePermission(Activity activity) {
        Log.d(TAG, "callReadExtrnalStoreagePermission() called with: activity = [" + activity + "]");

        //判断Android版本是否大于23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            Log.d(TAG, "callReadExtrnalStoreagePermission() called with: checkCallPhonePermission = [" + checkCallPhonePermission + "]");

            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(
                        activity
                        , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}
                        , RequestPermissionType.REQUEST_CODE_ASK_READ_EXTERNAL_STORAGE);
                return false;
            } else {
                return true;
            }
        }

        return true;
    }

    public void getRequestPermissions(int requestCode) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            //动态注册权限：
            // 首先是判断
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // 显示给用户的解释
                    MessageBox.show("请同意权限");
                } else {
                    // No explanation needed, we can request the permission.
                    String[] mPermissionList = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.CALL_PHONE, android.Manifest.permission.READ_LOGS, android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.SET_DEBUG_APP, android.Manifest.permission.SYSTEM_ALERT_WINDOW, android.Manifest.permission.GET_ACCOUNTS, android.Manifest.permission.WRITE_APN_SETTINGS};
                    ActivityCompat.requestPermissions(this, mPermissionList, requestCode);
                }
            } else {
                //umShare();
            }


        } else {
            //umShare();
        }


    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        getWindow().setBackgroundDrawable(null);// android的默认背景是不是为空。
        super.onDestroy();
        Log.d(TAG, "onDestroy() called" + this + "------------------ACTIVITY_COUNT=" + ACTIVITY_COUNT);
        System.gc();

        //mHolder.destoryImageView();
    }

    public static int ACTIVITY_COUNT = 0;
    public static List<String> activityNames = new ArrayList<>();

    @Override
    protected void finalize() throws Throwable {
        ACTIVITY_COUNT--;
        String str = this.toString();
        if (activityNames.contains(str)) {
            activityNames.remove(str);
        }
        Log.d(TAG, "finalize() called" + this + "------------------ACTIVITY_COUNT=" + ACTIVITY_COUNT);
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
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

                //如果App的权限申请曾经被用户拒绝过，就需要在这里跟用户做出解释
                boolean isTip = ActivityCompat.shouldShowRequestPermissionRationale(this,
                        permission);
                if (isTip) {////表明用户没有彻底禁止弹出权限请求

                    //进行权限请求
                    ActivityCompat.requestPermissions(this,
                            new String[]{permission},
                            requestCode);

                } else {//表明用户已经彻底禁止弹出权限请求

                    //这里一般会提示用户进入权限设置界面

                    //进行权限请求
                    ActivityCompat.requestPermissions(this,
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
                    CommFunAndroid.callPhone(this, CommFunAndroid.getSharedPreferences("phone"));
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
            case RequestPermissionType.READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
                    startLocation();
                } else {
                    // 没有获取到权限，做特殊处理
                    Toast.makeText(getApplicationContext(), "获取位置权限失败，请手动开启", Toast.LENGTH_SHORT).show();
                }
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
        i.putExtra("extra_pkgname", getPackageName());
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
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        return localIntent;
    }


    //*************定位******************


    public void getLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            showContacts();
        } else {
            startLocation();
        }
    }


    public void showContacts() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "没有权限,请手动开启定位权限", Toast.LENGTH_SHORT).show();
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE}, RequestPermissionType.READ_PHONE_STATE);
        } else {
            startLocation();
        }
    }

    public void startLocation() {

        GaodeMapUtils gaodeMapUtils = new GaodeMapUtils();

        gaodeMapUtils.startLocation(this, new LocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                super.onLocationChanged(aMapLocation);
                try {
                    if (aMapLocation != null) {

                        Log.d(TAG, "onLocationChanged() called with: aMapLocation = [" + aMapLocation + "]");

                        if (aMapLocation.getErrorCode() == 0) {
                            //可在其中解析amapLocation获取相应内容。

                            LocationInfo locationInfo = LocationInfo.parseAMapLocation(aMapLocation);

                            onLocationChange(locationInfo);

                        } else {
                            showMsg(aMapLocation.getErrorInfo());
                        }
                    }
                } catch (Exception ex) {

                }
            }
        });
    }

    protected LocationInfo mLocationInfo;

    /**
     * 定位改变事件
     *
     * @param locationInfo
     */
    protected void onLocationChange(LocationInfo locationInfo) {
        mLocationInfo = locationInfo;
        if (locationInfo != null) {
            SystemModels.locationInfo = locationInfo;
            Log.d(TAG, "onLocationChanged: " + locationInfo.getAddress());
        }
    }

    /**
     * 输出日志
     *
     * @param msg
     */
    protected void writeLog(String msg) {
        LogUtil.d(msg);
    }

    /**
     * 输出错误日志
     *
     * @param msg
     */
    protected void writeErrorLog(String msg) {
        LogUtil.e(msg);
    }


    /*
     *  http://www.jb51.net/article/49294.htm
     *  for左右手势
     *  1.复制下面的内容到目标Activity
     *  2.目标Activity的onCreate()调用initGesture()
     *  3.目标Activity需implements OnTouchListener, OnGestureListener
     */
    private GestureDetector mGestureDetector;
    private int verticalMinDistance = 180;
    private int minVelocity = 0;

    private void initGesture() {
        mGestureDetector = new GestureDetector((GestureDetector.OnGestureListener) this);
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if (e1.getX() - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {

            // 切换Activity
            // Intent intent = new Intent(ViewSnsActivity.this, UpdateStatusActivity.class);
            // startActivity(intent);
            //Toast.makeText(this, "向左手势", Toast.LENGTH_SHORT).show();
        } else if (e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {

            // 切换Activity
            // Intent intent = new Intent(ViewSnsActivity.this, UpdateStatusActivity.class);
            // startActivity(intent);
            //Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();
            finish();
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }

        return false;
    }

    @Override
    public void onLongPress(MotionEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
                            float arg3) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        return mGestureDetector.onTouchEvent(event);
    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        // TODO Auto-generated method stub
//        return mGestureDetector.onTouchEvent(event);
//    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mGestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }


}