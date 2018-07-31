package com.ysy15350.redpacket_fc.dialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.common.ViewHolder;


/**
 * 整点红包对话框
 *
 * @author yangshiyou
 */
public class WholePointDialog extends Dialog {

    private Context mContext;

    private View conentView;

    String mTitle, mContent, mOkText, mCancelText,mMoney,mImgurl,mProvider;


    TextView tv_money,tv_provider;
    ImageView img_advertisement,imgbtn_white_fork;

    private View ll_close;

    /**
     * 控件ViewGroup
     */
    protected View mContentView;
    private ViewHolder mHolder;

    public WholePointDialog(Context context) {
        this(context, "系统提示", "是否确定？", "确定", "取消");
    }

    public WholePointDialog(Context context, String content) {
        this(context, "系统提示", content, "确定", "取消");
    }

    int mImgId = 0;

    public WholePointDialog(Context context, String title, String content, String okText, String cancelText, int imgId) {
        super(context);

        mContext = context;
        mTitle = title;
        mContent = content;
        mOkText = okText;
        mCancelText = cancelText;

    }

    public WholePointDialog(Context context,String money,String imgurl,String provider){
        super(context);

        mContext = context;
        mMoney = money;
        mImgurl = imgurl;
        mProvider = provider;

        initView();// 初始化按钮事件
    }


    public WholePointDialog(Context context, String title, String content, String okText, String cancelText) {
        this(context, title, content, okText, cancelText, 0);
    }

    private void initView() {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        conentView = inflater.inflate(R.layout.dialog_wholepoint,null);
        tv_money = conentView.findViewById(R.id.tv_money);
        img_advertisement = conentView.findViewById(R.id.img_advertisement);
        tv_provider = conentView.findViewById(R.id.tv_provider);
        imgbtn_white_fork = conentView.findViewById(R.id.imgbtn_white_fork);

        mHolder = ViewHolder.get(mContext, conentView);

        // 金额
        if (!CommFunAndroid.isNullOrEmpty(mMoney)) {
            tv_money.setText(mMoney);
        }

        // 广告图片
        if (!CommFunAndroid.isNullOrEmpty(mImgurl)) {
            mHolder.setImageURL(img_advertisement,mImgurl);
        }

        // 提供商
        if (!CommFunAndroid.isNullOrEmpty(mProvider)) {
            tv_provider.setText(mProvider);
        }

        imgbtn_white_fork.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dismiss();
            }
        });

        // WindowManager.LayoutParams params = this.getWindow().getAttributes();
        // params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        // params.height = WindowManager.LayoutParams.WRAP_CONTENT;

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

    }

    /**
     * 点击监听
     */
    private DialogListener mListener;

    /**
     * 设置popupwindow中按钮监听
     *
     * @param listener
     */
    public void setDialogListener(DialogListener listener) {
        this.mListener = listener;
    }

    /**
     * 点击事件监听
     *
     * @author yangshiyou
     */
    public interface DialogListener {

        public void onOkClick();

    }

}
