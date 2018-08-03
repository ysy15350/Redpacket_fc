package com.ysy15350.redpacket_fc.redpackage.open_treasurebox;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.kuaiyou.loader.AdViewNativeManager;
import com.kuaiyou.loader.loaderInterface.AdViewNativeListener;
import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.dialog.WholePointDialog;
import com.ysy15350.redpacket_fc.main_tabs.MainTab1Fragment;
import com.ysy15350.redpacket_fc.mine.setalipay.SetAlipayActivity;
import com.ysy15350.redpacket_fc.mine.wallet.detailed.DetailedListActivity;
import com.ysy15350.redpacket_fc.mine.wallet.withdraw_deposit.WithdrawDepositActivity;
import com.ysy15350.redpacket_fc.redpackage.Other;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.api.model.ResponseHead;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.model.AdViewConfig;
import com.ysy15350.ysyutils.model.PageData;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import java.util.HashMap;
import java.util.List;

/**
 * 打开宝箱
 */
@ContentView(R.layout.activity_openred)
public class OpenTreasureBoxActivity extends MVPBaseActivity<OpenTreasureBoxViewInterface, OpenTreasureBoxPresenter>
        implements OpenTreasureBoxViewInterface, AdViewNativeListener {

    public static String HTML = "<meta charset='utf-8'><style type='text/css'>* { padding: 0px; margin: 0px;}a:link { text-decoration: none;}</style><div  style='width: 100%; height: 100%;'><img src=\"image_path\" width=\"100%\" height=\"100%\" ></div>";

    private AdViewNativeManager adViewNative;
    private HashMap<String, Object> nativeAd;

    @Override
    protected OpenTreasureBoxPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new OpenTreasureBoxPresenter(this);
    }

    @Override
    public void initView() {
        super.initView();
        setFormHead("打开宝箱");

        adViewNative = new AdViewNativeManager(this, AdViewConfig.AD_APP_ID, AdViewConfig.AD_POS_TD, this);
        adViewNative.requestAd();
    }

    @Override
    public void loadData() {
        super.loadData();

        MessageBox.showWaitDialog(this, "数据加载中...");
        mPresenter.grabRedPacket(1);
    }


    private String strprice;
    private String strimgurl;
    private String strcompany;

    @Override
    public void grabRedPacketCallback(boolean isCache, Response response) {
        try {

            hideWaitDialog();

            if (response != null) {
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {
                        PageData pageData = response.getData(PageData.class);

                        if (pageData != null) {

                            double price = (double) pageData.get("price");
                            if (CommFun.notNullOrEmpty(price)) {
                                strprice = "¥" + price;
                                mHolder.setText(R.id.tv_redpackageaccont, price + "");
                            }

                            String imgurl = pageData.getString("imgurl");
                            if (CommFun.notNullOrEmpty(imgurl)) {
                                strimgurl = imgurl;
                                mHolder.setImageURL(R.id.img_pictures, strimgurl);
                            }

                            String company = pageData.getString("company");
                            if (CommFun.notNullOrEmpty(company)) {
                                strcompany = company;
                            }

                        }
                    } else {
                        showMsg(msg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 当请求成功时调用该方法
     *
     * @param nativeAdList 类型为List<HashMap<String,Object>>，返回得字段内容如下：
     *                     title--> 广告标题
     *                     adImage-->大图url
     *                     adIcon--> Icon图片链接
     *                     adFlagLogo--> 广告logo
     *                     desc--> 广告描述文字
     *                     sec_description--> 广告描述文字2
     *                     请着重关注 title,adImage,adIcon,desc 这四个字段
     */
    @Override
    public void onNativeAdReceived(List nativeAdList) {
        if (null != nativeAdList && !nativeAdList.isEmpty()) {
            nativeAd = (HashMap) nativeAdList.get(0);
            if (!nativeAdList.toString().contains("videoUrl")) {

                // 广告语
                mHolder.setText(R.id.tv_ad, (CharSequence) nativeAd.get("description"));

                // 广告图片
                mHolder.setImageURL(R.id.img_pictures, (String) nativeAd.get("adIcon"), 300, 300);

                // 广告描述2
                mHolder.setText(R.id.tv_description,(CharSequence) nativeAd.get("sec_description"));
            }

            // 汇报展示
            adViewNative.reportImpression((String) nativeAd.get("adId"));

            mHolder.setOnClickListener(R.id.img_pictures, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adViewNative.reportClick(
                            (String) nativeAd.get("adId"),
                            100,
                            100);
                }
            });

//            if (null != nativeAd) {
//                desc.setText((CharSequence) nativeAd.get("description"));
//                desc2.setText((CharSequence) nativeAd.get("sec_description"));
//                title.setText((CharSequence) nativeAd.get("title"));
//                if (!TextUtils
//                        .isEmpty((CharSequence) nativeAd.get("adImage"))
//                        && null != image)
//                    image.loadData((new String(HTML)).replace("image_path",
//                            (CharSequence) nativeAd.get("adImage")),
//                            "text/html; charset=UTF-8", null);
//
//                if (!TextUtils.isEmpty((CharSequence) nativeAd.get("adIcon"))
//                        && null != icon)
//                    icon.loadData((new String(HTML)).replace("image_path",
//                            (CharSequence) nativeAd.get("adIcon")),
//                            "text/html; charset=UTF-8", null);
//
//                popupWindow.update();
//            }
        }
    }

    /**
     * 请求失败时调用该方法
     *
     * @param s 失败信息
     */
    @Override
    public void onNativeAdReceiveFailed(String s) {

    }

    @Override
    public void onDownloadStatusChange(int i) {

    }


    /**
     * 返回
     *
     * @param view
     */
    @Event(value = R.id.img_wallet_back)
    private void img_wallet_backClick(View view) {

        finish();

    }


}
