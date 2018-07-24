package com.ysy15350.redpacket_fc.dialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.common.CommFunAndroid;


/**
 * 红包对话框
 *
 * @author yangshiyou
 */
public class RedPackageDialog extends Dialog {

    private Context mContext;

    private View conentView;

    String mTitle, mContent, mOkText, mCancelText;


    TextView tv_title;
    ImageView btn_ok;

    private View ll_close;

    public RedPackageDialog(Context context) {
        this(context, "系统提示", "是否确定？", "确定", "取消");
    }

    public RedPackageDialog(Context context, String content) {
        this(context, "系统提示", content, "确定", "取消");
    }

    int mImgId = 0;

    public RedPackageDialog(Context context, String title, String content, String okText, String cancelText, int imgId) {
        super(context);

        mContext = context;
        mTitle = title;
        mContent = content;
        mOkText = okText;
        mCancelText = cancelText;


        initView();// 初始化按钮事件
    }


    public RedPackageDialog(Context context, String title, String content, String okText, String cancelText) {
        this(context, title, content, okText, cancelText, 0);
    }

    private void initView() {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        conentView = inflater.inflate(R.layout.dialog_redpackage,null);
        tv_title = conentView.findViewById(R.id.tv_title);

        btn_ok = conentView.findViewById(R.id.imgbtn_open);

        if (!CommFunAndroid.isNullOrEmpty(mTitle)) {
            tv_title.setText(mTitle);
        }

        btn_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (mListener != null) {
                    mListener.onOkClick();
                }
                dismiss();
            }
        });

        conentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
