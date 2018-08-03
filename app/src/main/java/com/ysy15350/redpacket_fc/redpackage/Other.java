package com.ysy15350.redpacket_fc.redpackage;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.kuaiyou.loader.AdViewNativeManager;
import com.kuaiyou.loader.loaderInterface.AdViewNativeListener;
import com.ysy15350.redpacket_fc.R;

import java.util.HashMap;
import java.util.List;

public class Other extends Activity implements AdViewNativeListener {

    public static String HTML = "<meta charset='utf-8'><style type='text/css'>* { padding: 0px; margin: 0px;}a:link { text-decoration: none;}</style><div  style='width: 100%; height: 100%;'><img src=\"image_path\" width=\"100%\" height=\"100%\" ></div>";

    private final String appId = "SDK20161629040641z7snyxkrbndasty";
    private final String posId = "NATIVEchzdqc0qachn";
    private AdViewNativeManager adViewNative;
    private HashMap<String, Object> nativeAd;
    View contentView=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        adViewNative = new AdViewNativeManager(this, appId, posId, this);
        adViewNative.requestAd();
    }

    @Override
    public void onNativeAdReceived(List list) {

    }

    @Override
    public void onNativeAdReceiveFailed(String s) {

    }

    @Override
    public void onDownloadStatusChange(int i) {

    }
}
