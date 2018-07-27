package com.ysy15350.ysyutils.common.message;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

import com.ysy15350.ysyutils.YSYApplication;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.custom_view.dialog.LoadingDialog;


/**
 * Created by yangshiyou on 2017/10/30.
 */

public class MessageBox {

    private static final String TAG = "MessageBox";

    /**
     * 最后Toast提示的消息内容，用于判断是否短时间重复提示消息
     */
    private static String lastShowMessage = "";


    /**
     * 系统提示
     *
     * @param message
     */
    public static void show(String message) {

        try {

            Log.d(TAG, "show() called with: message = [" + message + "]");

            if (!CommFun.isNullOrEmpty(message)) {
                if (isFastShow() && message.equals(lastShowMessage)) {
                    return;
                }

                lastShowMessage = message;

                Context context = YSYApplication.getContext();
                if (context != null) {
                    Toast toast = Toast.makeText(YSYApplication.getContext(), message, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 200);
                    toast.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static long lastShowTime;


    /**
     * 防止频繁提示相同消息
     *
     * @return
     */
    public synchronized static boolean isFastShow() {
        long time = System.currentTimeMillis();
        if (time - lastShowTime < 3000) {
            return true;
        }
        lastShowTime = time;
        return false;
    }


    /**
     * 系统提示
     *
     * @param pic_id
     */
    public static void showImgBox(int pic_id) {

        try {

            Context context = YSYApplication.getContext();

            if (context != null) {
                // 创建新Toast对象
                Toast showImageToast = new Toast(context);

                // 创建新ImageView对象
                ImageView imageView = new ImageView(context);

                // 从资源中获取图片
                imageView.setImageResource(pic_id);

                // 设置Toast上的View(imageView)
                showImageToast.setView(imageView);

                // 设置Toast显示时间
                showImageToast.setDuration(Toast.LENGTH_SHORT);

                // 显示Toast
                showImageToast.show();
            }
        } catch (Exception e) {

        }

    }


    private static LoadingDialog loadingDialog;

    public static void showWaitDialog(Activity activity, String msg) {
        hideWaitDialog();
        if (activity != null) {
            loadingDialog = new LoadingDialog(activity, msg, false);
            loadingDialog.show();
        }
    }


    public static void hideWaitDialog() {

        try {
            if (loadingDialog != null) {
                loadingDialog.hide();
                loadingDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
