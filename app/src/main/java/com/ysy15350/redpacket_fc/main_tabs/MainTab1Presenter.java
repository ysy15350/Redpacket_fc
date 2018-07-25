package com.ysy15350.redpacket_fc.main_tabs;

import android.content.Context;
import android.os.CountDownTimer;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;
import com.ysy15350.ysyutils.common.CommFun;


public class MainTab1Presenter extends BasePresenter<MainTab1ViewInterface> {

    private static final String TAG = "MainTab1Presenter";

    private Context context;

    public MainTab1Presenter() {
    }

    public MainTab1Presenter(Context context) {
        super(context);

        this.context = context;
    }


}
