package com.ysy15350.ysyutils.custom_view.dialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.ysy15350.ysyutils.R;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.image.ImageUtils;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.custom_view.qrcode.CanvasRQ;


/**
 * 清除缓存
 *
 * @author yangshiyou
 */
public class QrCodeDialog extends Dialog {

    private static final String TAG = "QrCodeDialog";
    private Context mContext;


    private View conentView;

    private CanvasRQ qr_code;


    private View ll_close;
    private Button btn_1, btn_2;

    public QrCodeDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

        mContext = context;

        initView();// 初始化按钮事件

    }


    private void initView() {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        conentView = inflater.inflate(R.layout.dialog_qrcode, null);

        qr_code = conentView.findViewById(R.id.qr_code);


        ll_close = conentView.findViewById(R.id.ll_close);
        btn_1 = (Button) conentView.findViewById(R.id.btn_1);
        btn_2 = (Button) conentView.findViewById(R.id.btn_2);

        ll_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (mListener != null) {
                    mListener.onCancelClick();
                }
                dismiss();
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Bitmap bitmap = qr_code.getBitmap();
                if (bitmap != null) {
                    String path = ImageUtils.saveBitmap(mContext, bitmap);
                    if (!CommFun.isNullOrEmpty(path)) {
                        MessageBox.show("保存成功" + path);
                    } else {
                        MessageBox.show("保存失败");
                    }
                }

                if (mListener != null) {
                    mListener.onBtn1Click();
                }
                dismiss();
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (mListener != null) {
                    mListener.onBtn2Click();
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

        public void onBtn1Click();

        public void onBtn2Click();

        public void onCancelClick();

    }


}
