package com.ysy15350.redpacket_fc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
import com.ysy15350.ysyutils.common.AppStatusManager;


//@ContentView(R.layout.activity_guide)
public class GuideActivity extends MVPBaseActivity<GuideViewInterface, GuidePresenter>
        implements GuideViewInterface {

    private static final String TAG = "GuideActivity";

    private TextView tv_time;

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
        }, 4 * 1000);//3秒后执行

        tv_time = this.findViewById(R.id.tv_time);


    }

    @Override
    protected void onResume() {
        super.onResume();

        handler.postDelayed(runnable, 1000);//每两秒执行一次runnable.
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
                    tv_time.setText("" + time--);
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
}
