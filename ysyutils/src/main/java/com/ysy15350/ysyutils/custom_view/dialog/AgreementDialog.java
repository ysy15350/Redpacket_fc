package com.ysy15350.ysyutils.custom_view.dialog;


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

import com.ysy15350.ysyutils.R;
import com.ysy15350.ysyutils.common.CommFunAndroid;


/**
 * 协议对话框
 *
 * @author yangshiyou
 */
public class AgreementDialog extends Dialog {

    private Context mContext;

    private View conentView;

    String mTitle, mContent, mOkText, mCancelText;


    TextView tv_title, tv_content;
    Button btn_ok, btn_cancel;

    private View ll_close;

    public AgreementDialog(Context context) {
        this(context, "系统提示", "是否确定？", "确定", "取消");
    }

    public AgreementDialog(Context context, String content) {
        this(context, "系统提示", content, "确定", "取消");
    }

    int mImgId = 0;

    public AgreementDialog(Context context, String title, String content, String okText, String cancelText, int imgId) {
        super(context);

        mContext = context;
        mTitle = title;
        mContent = content;
        mOkText = okText;
        mCancelText = cancelText;


        initView();// 初始化按钮事件
    }


    public AgreementDialog(Context context, String title, String content, String okText, String cancelText) {
        this(context, title, content, okText, cancelText, 0);
    }

    private void initView() {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        conentView = inflater.inflate(R.layout.dialog_agreement, null);
        tv_title = conentView.findViewById(R.id.tv_title);
        tv_content = conentView.findViewById(R.id.tv_content);

        btn_cancel = conentView.findViewById(R.id.btn_cancel);
        btn_ok = conentView.findViewById(R.id.btn_ok);

        if (!CommFunAndroid.isNullOrEmpty(mTitle)) {
            tv_title.setText(mTitle);
        }
        if (!CommFunAndroid.isNullOrEmpty(mContent)) {
            tv_content.setText(mContent);
        }

        if (!CommFunAndroid.isNullOrEmpty(mOkText)) {
            btn_ok.setText(mOkText);
        }
        if (!CommFunAndroid.isNullOrEmpty(mCancelText)) {
            btn_cancel.setText(mCancelText);
        }

        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (mListener != null) {
                    mListener.onCancelClick();
                }
                dismiss();
            }
        });

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

        public void onCancelClick();

        public void onOkClick();

    }

}
