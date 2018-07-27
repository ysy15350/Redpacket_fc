package com.ysy15350.ysyutils.custom_view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ysy15350.ysyutils.R;
import com.ysy15350.ysyutils.common.image.ImageUtils;


/**
 * Created by yangshiyou on 2018/1/5.
 */

public class ShareDialog extends Dialog {

    private Context mContext;

    private View conentView;

    ImageUtils imageUtil;

    RelativeLayout rl_pop_main;

    LinearLayout ll_pop_main, ll_weixin, ll_friend, ll_qzone, ll_qq;

    public ShareDialog(Context context) {
        super(context);
        this.mContext = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setGravity(Gravity.BOTTOM);

        init();
    }

    public void init() {

        imageUtil = ImageUtils.getInstance(mContext);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        conentView = inflater.inflate(R.layout.dialog_share, null);


        rl_pop_main = (RelativeLayout) conentView.findViewById(R.id.rl_pop_main);
        rl_pop_main.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dismiss();
            }
        });

        ll_weixin = (LinearLayout) conentView.findViewById(R.id.ll_weixin);
        ll_friend = (LinearLayout) conentView.findViewById(R.id.ll_friend);
        ll_qzone = (LinearLayout) conentView.findViewById(R.id.ll_qzone);
        ll_qq = (LinearLayout) conentView.findViewById(R.id.ll_qq);

        Bitmap bitmap = imageUtil.getBitmap(R.mipmap.ic_launcher);

        // final UMImage image = new UMImage(mContext, bitmap);

        ll_weixin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                if (mPopListener != null) {
                    mPopListener.weixinShare();
                }

                // UMImage image = new UMImage(mActivity,
                // "http://www.umeng.com/images/pic/social/integrated_3.png");
                // UMusic music = new
                // UMusic("http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
                // music.setTitle("sdasdasd");
                // music.setThumb(new UMImage(mActivity,
                // "http://www.umeng.com/images/pic/social/chart_1.png"));
                //
                // music.setTargetUrl("http://www.baidu.com");

                // new
                // ShareAction(mActivity).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(umShareListener).withMedia(image)
                // .withText("一款可以用颜值来赚钱的APP").withTitle("约人看电影APP").withTargetUrl("http://app.023yue.com/")
                // // .withMedia(new
                // //
                // UMEmoji(ShareActivity.this,"http://img.newyx.net/news_img/201306/20/1371714170_1812223777.gif"))
                // .share();

                dismiss();
            }
        });

        ll_friend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                if (mPopListener != null) {
                    mPopListener.wxFriendShare();
                }

                // UMImage image = new UMImage(mActivity,
                // "http://www.umeng.com/images/pic/social/integrated_3.png");
                // UMusic music = new
                // UMusic("http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
                // music.setTitle("sdasdasd");
                // music.setThumb(new UMImage(mActivity,
                // "http://www.umeng.com/images/pic/social/chart_1.png"));
                //
                // music.setTargetUrl("http://www.baidu.com");

                // new
                // ShareAction(mActivity).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener)
                // .withMedia(image).withText("一款可以用颜值来赚钱的APP").withTitle("约人看电影APP")
                // .withTargetUrl("http://app.023yue.com/")
                // // .withMedia(new
                // //
                // UMEmoji(ShareActivity.this,"http://img.newyx.net/news_img/201306/20/1371714170_1812223777.gif"))
                // .share();

                dismiss();

            }
        });

        ll_qzone.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings({"unchecked", "rawtypes"})
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                if (mPopListener != null) {
                    mPopListener.qzoneShare();
                }

                // UMusic music = new
                // UMusic("http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
                // music.setTitle("sdasdasd");
                // music.setThumb(new UMImage(mActivity,
                // "http://www.umeng.com/images/pic/social/chart_1.png"));
                //
                // music.setTargetUrl("http://www.baidu.com");


//				new ShareAction(mActivity).setPlatform(SHARE_MEDIA.QZONE).setCallback(umShareListener).withMedia(image)
//						.withText("一款可以用颜值来赚钱的APP").withTitle("约人看电影APP").withTargetUrl("http://app.023yue.com/")
//						.share();

                dismiss();

            }
        });

        ll_qq.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                if (mPopListener != null) {
                    mPopListener.qqShare();
                }

                // UMusic music = new
                // UMusic("http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
                // music.setTitle("sdasdasd");
                // music.setThumb(new UMImage(mActivity,
                // "http://www.umeng.com/images/pic/social/chart_1.png"));
                //
                // music.setTargetUrl("http://www.baidu.com");


                // new
                // ShareAction(mActivity).setPlatform(SHARE_MEDIA.QQ).setCallback(umShareListener).withMedia(image)
                // .withText("一款可以用颜值来赚钱的APP").withTitle("约人看电影APP").withTargetUrl("http://app.023yue.com/")
                // .share();

                dismiss();
            }
        });

//        WindowManager.LayoutParams params = this.getWindow().getAttributes();
//        params.width = WindowManager.LayoutParams.MATCH_PARENT;
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        // dialog.getWindow().setBackgroundDrawable(new
        // ColorDrawable(android.R.color.transparent));
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#b0000000")));
        this.setCanceledOnTouchOutside(false);

        // 解决圆角黑边
        // getWindow().setBackgroundDrawable(new BitmapDrawable());
        // 或者
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(conentView);

        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);
    }

    private PopListener mPopListener;

    public void setPopListener(PopListener popListener) {
        this.mPopListener = popListener;
    }

    public interface PopListener {

        void weixinShare();

        void wxFriendShare();

        void qzoneShare();

        void qqShare();
    }

}
