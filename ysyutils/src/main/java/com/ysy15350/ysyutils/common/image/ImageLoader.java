package com.ysy15350.ysyutils.common.image;

import android.widget.ImageView;

public interface ImageLoader {

    /**
     * 显示图片
     *
     * @param imageView 图片控件
     * @param url       url地址
     */
    void displayImage(ImageView imageView, String url);

    /**
     * 显示图片
     *
     * @param imageView
     * @param path      图片路径
     */
    void displayImagePath(ImageView imageView, String path);


    /**
     * 显示图片
     *
     * @param imageView
     * @param url
     * @param width
     * @param heigth
     */
    void displayImage(ImageView imageView, String url, int width, int heigth);

    /**
     * 显示图片
     *
     * @param imageView
     * @param url
     * @param circular  是否显示为圆形图片
     */
    void displayImage(ImageView imageView, String url, boolean circular);

    /**
     * 显示图片
     *
     * @param imageView
     * @param url
     * @param width
     * @param heigth
     * @param circular  是否显示为圆形图片
     */
    void displayImage(ImageView imageView, String url, int width, int heigth, boolean circular);


}