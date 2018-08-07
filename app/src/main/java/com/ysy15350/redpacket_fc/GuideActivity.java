package com.ysy15350.redpacket_fc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;

import com.kuaiyou.loader.AdViewNativeManager;
import com.kuaiyou.loader.loaderInterface.AdViewNativeListener;
import com.ysy15350.ysyutils.YSYApplication;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.api.model.ResponseHead;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
import com.ysy15350.ysyutils.common.AppStatusManager;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.model.AdViewConfig;
import com.ysy15350.ysyutils.model.SysUser;

import java.util.HashMap;
import java.util.List;


/**
 * 启动页
 */
//@ContentView(R.layout.activity_guide)
public class GuideActivity extends MVPBaseActivity<GuideViewInterface, GuidePresenter>
        implements GuideViewInterface, AdViewNativeListener {

    private static final String TAG = "GuideActivity";

    private TextView tv_time;

    public static String HTML = "<meta charset='utf-8'><style type='text/css'>* { padding: 0px; margin: 0px;}a:link { text-decoration: none;}</style><div  style='width: 100%; height: 100%;'><img src=\"image_path\" width=\"100%\" height=\"100%\" ></div>";

    private AdViewNativeManager adViewNative;
    private HashMap<String, Object> nativeAd;

    @Override
    protected GuidePresenter createPresenter() {
        // TODO Auto-generated method stub
        return new GuidePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppStatusManager.getInstance().setAppStatus(AppStatusManager.AppStatusConstant.APP_NORMAL);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
    }


    @Override
    public void initView() {
        super.initView();

        //判断根Activity代码
        if (!isTaskRoot()) {
            Log.d(TAG, "onCreate() called with: isTaskRoot = [" + isTaskRoot() + "]");
            finish();
            return;
        }


        //显示全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 填充状态栏
        CommFunAndroid.fullScreenStatuBar(this);


        //http://blog.csdn.net/wangshihui512/article/details/50768294
        //view自带的定时器：postDelayed方法
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                GuideActivity.this.startActivity(intent);
                GuideActivity.this.finish();

                //MessageBox.show("跳转");

            }
        }, 4 * 1000);//4秒后执行

        tv_time = this.findViewById(R.id.tv_time);

        adViewNative = new AdViewNativeManager(this, AdViewConfig.AD_APP_ID, AdViewConfig.AD_POS_TD, this);
        adViewNative.requestAd();


        YSYApplication.getContext();//初始化上下文
    }


    @Override
    protected void onResume() {
        super.onResume();

        getLocation();

        mPresenter.activate();

        handler.postDelayed(runnable, 1000);//每两秒执行一次runnable.
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

                            BaseData.setSysUser(sysUser);
                        }
                    }

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int time = 3;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //要做的事情

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_time.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                            GuideActivity.this.startActivity(intent);
                            GuideActivity.this.finish();
                        }
                    });
                }
            });

            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onNativeAdReceived(List nativeAdList) {
        if (null != nativeAdList && !nativeAdList.isEmpty()) {
            nativeAd = (HashMap) nativeAdList.get(0);
            if (!nativeAdList.toString().contains("videoUrl")) {

                WebView webimg = mHolder.getView(R.id.webimg);
                // 广告图片
                mHolder.setImageURL(R.id.img, (String) nativeAd.get("adIcon"), 300, 300);

            }

            // 汇报展示
            adViewNative.reportImpression((String) nativeAd.get("adId"));

            mHolder.setOnClickListener(R.id.img, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adViewNative.reportClick(
                            (String) nativeAd.get("adId"),
                            100,
                            100);
                }
            });
        }
    }

    @Override
    public void onNativeAdReceiveFailed(String s) {

    }

    @Override
    public void onDownloadStatusChange(int i) {

    }
}
