package com.ysy15350.redpacket_fc.mine.share.invitation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.ysy15350.redpacket_fc.MyApplication;
import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.mine.cityowner.cityowner_transaction.CityOwnerTransactionActivity;
import com.ysy15350.redpacket_fc.wxapi.WXEntryActivity;
import com.ysy15350.ysyutils.base.BaseActivity;
import com.ysy15350.ysyutils.custom_view.dialog.ShareDialog;
import com.ysy15350.ysyutils.custom_view.qrcode.CanvasRQ;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 邀请
 */
@ContentView(R.layout.activity_invitation)
public class InvitationActivity extends BaseActivity {

    Bitmap canvasRQBitmap;

    /**
     * 弹窗二维码
     */
    @ViewInject(R.id.crq_code)
    CanvasRQ canvasRQ;


    @ViewInject(R.id.img_QR)
    private ImageView img_QR;


    @Override
    public void initView() {

        super.initView();
        setFormHead("分享");

        if (canvasRQ != null) {
            canvasRQBitmap = canvasRQ.getBitmap();

            if(canvasRQBitmap != null){
                img_QR.setVisibility(View.VISIBLE);// 显示下载二维码按钮
            }
        }

        img_QR.setImageBitmap(canvasRQBitmap);

    }

    /**
     * 分享
     * @param view
     */
    @Event(value = R.id.btn_share)
    private void btn_shareClick(View view) {
        ShareDialog shareDialog = new ShareDialog(this);
        shareDialog.setPopListener(new ShareDialog.PopListener() {
            @Override
            public void weixinShare() {
                WXEntryActivity.shareWeixin("分享到朋友",1, MyApplication.iwxapi);
            }

            @Override
            public void wxFriendShare() {
                WXEntryActivity.shareWeixin("分享到朋友圈",2, MyApplication.iwxapi);
            }

            @Override
            public void qzoneShare() {

            }

            @Override
            public void qqShare() {

            }
        });

        shareDialog.show();
    }


}
