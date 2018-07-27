package com.ysy15350.ysyutils.viewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by John on 2017/1/5.
 */
public class MyViewPager extends ViewPager {

    Context mContext;

    private ViewPager mViewPager;

    /**
     * 是否无限循环
     */
    public static boolean isInfiniteLoop = false;

    /**
     * 图片集合
     */
    private List<ImageView> mImageViews;

    public MyViewPager(Context context, ViewPager viewPager, boolean isInfiniteLoop) {
        super(context);
        mContext = context;
        mViewPager = viewPager;
        this.isInfiniteLoop = isInfiniteLoop;


        // 设置自动循环
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    /**
     * 设置适配器
     *
     * @param imageViews
     */
    public void setAdapter(List<ImageView> imageViews) {
        mViewPager.setAdapter(new MyPagerAdapter(imageViews));
    }

    class MyPagerAdapter extends PagerAdapter {

        public MyPagerAdapter(List<ImageView> imageViews) {
            mImageViews = imageViews;
        }

        @Override
        public int getCount() {
            int count = 0;
            count = isInfiniteLoop ? Integer.MAX_VALUE : mImageViews.size();
            return mImageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(mContext);

            // 从图片集合中，通过position页面位置把imageview取出来
            imageView = mImageViews.get(position % mImageViews.size());

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.imgClick(v);
                    }
                }
            });

            // 把imageview添加到容器container中
            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (mImageViews != null && mImageViews.size() > position) {
                ImageView imageView = mImageViews.get(position);
                if (imageView != null) {

                    ((ViewPager) container).removeView(imageView);
                }
            }
//            super.destroyItem(container, position, object);//这个必须注释掉 ，不然会报错
        }
    }

    /**
     * 记录上次被高亮显示页面的位置
     */
    private int lastIndex;

    /**
     * 点的控件
     */
    private LinearLayout spot;

    /**
     * 设置页面改变监听 设置图片标题文本
     */
    public void setOnPageChangeListener(LinearLayout spot) {

        this.spot = spot;

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            boolean isAutoPlay = false;
            /**
             * 当某页面被选中的时候回调 position：被选中的页面的位置
             */
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub

                if(mMyViewPagerListener!=null){
                    mMyViewPagerListener.onPageChanged(position);
                }

                // 在图片集合中取摸 也就是集合总条数的倍数
                int myIndex = position % mImageViews.size();

                // 把之前高亮位置的点变成暗色
                MyViewPager.this.spot.getChildAt(lastIndex).setEnabled(false);
                // 把当前位置的点设置成亮色
                MyViewPager.this.spot.getChildAt(myIndex).setEnabled(true);



                lastIndex = myIndex;

            }

            /**
             * position:当前正在滑动的页面位置 positionOffset:滑动了页面的百分比
             * positionOffsetPixels:在屏幕上滑动的像素
             */
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                // TODO Auto-generated method stub

            }

            /**
             * 当页面方式变化是回调 静态-->滑动 滑动-->静态
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                // TODO Auto-generated method stub
                switch (state){
                    case 1:// 手势滑动，空闲中
                        isAutoPlay = false;
                        break;
                    case 2:// 界面切换中
                        isAutoPlay = true;
                        break;
                    case 0:// 滑动结束，即切换完毕或者加载完毕
                        // 当前为最后一张，此时从右向左滑，则切换到第一张
                        if (mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                            mViewPager.setCurrentItem(0);
                        }
                        // 当前为第一张，此时从左向右滑，则切换到最后一张
                        else if (mViewPager.getCurrentItem() == 0 && !isAutoPlay) {
                            mViewPager.setCurrentItem(mViewPager.getAdapter().getCount() - 1);
                        }
                        break;
                }
            }
        });

        // 设置中心位置
        int currentpoint = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2
                % mImageViews.size();// 减去后面那个值是为了得到的值一定是图片集合大小的整数倍
        mViewPager.setCurrentItem(currentpoint);

    }

    /**
     * 设置页面改变监听 设置图片标题文本
     */
    public void setOnPageChangeListener() {

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            boolean isAutoPlay = false;
            /**
             * 当某页面被选中的时候回调 position：被选中的页面的位置
             */
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub

                if(mMyViewPagerListener!=null){
                    mMyViewPagerListener.onPageChanged(position);
                }

                // 在图片集合中取摸 也就是集合总条数的倍数
                int myIndex = position % mImageViews.size();


                lastIndex = myIndex;

            }

            /**
             * position:当前正在滑动的页面位置 positionOffset:滑动了页面的百分比
             * positionOffsetPixels:在屏幕上滑动的像素
             */
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                // TODO Auto-generated method stub

            }

            /**
             * 当页面方式变化是回调 静态-->滑动 滑动-->静态
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                // TODO Auto-generated method stub
                switch (state){
                    case 1:// 手势滑动，空闲中
                        isAutoPlay = false;
                        break;
                    case 2:// 界面切换中
                        isAutoPlay = true;
                        break;
                    case 0:// 滑动结束，即切换完毕或者加载完毕
                        // 当前为最后一张，此时从右向左滑，则切换到第一张
                        if (mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                            mViewPager.setCurrentItem(0);
                        }
                        // 当前为第一张，此时从左向右滑，则切换到最后一张
                        else if (mViewPager.getCurrentItem() == 0 && !isAutoPlay) {
                            mViewPager.setCurrentItem(mViewPager.getAdapter().getCount() - 1);
                        }
                        break;
                }
            }
        });

        // 设置中心位置
        int currentpoint = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2
                % mImageViews.size();// 减去后面那个值是为了得到的值一定是图片集合大小的整数倍
        mViewPager.setCurrentItem(currentpoint);

    }

    /**
     * 创建点
     * @param width      点的宽
     * @param height     点的高
     * @param leftMargin 点间距左边像素
     * @param resId      点的背景
     * @return
     */
    public ImageView setPoint(int width, int height, int leftMargin, int resId) {
        // 创建点
        ImageView iv_point = new ImageView(mContext);
        // 设置点的图片或者背景
        iv_point.setImageResource(resId);
        // 设置点的大小和间距
        // 创建LinearLayout的布局参数LayoutParams，并在构造函数中设置点的宽高
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        // 设置间距离左边10个像素
        params.leftMargin = leftMargin;
        // 设置点的参数
        iv_point.setLayoutParams(params);
        return iv_point;
    }

    private ClickListener listener;

    public void setClickListener(ClickListener listener){
        this.listener = listener;
    }

    /**
     * 图片点击事件接口
     */
    public interface ClickListener{
        public void imgClick(View v);

    }

    private MyViewPagerListener mMyViewPagerListener;

    public interface MyViewPagerListener{
        public void onPageChanged(int index);
    }

    public void setMyViewPagerListener(MyViewPagerListener listener){
        if(listener!=null){
            mMyViewPagerListener=listener;
        }
    }

    /**
     * 程序是否已经销毁
     */
    private boolean isRuning = false;

    private Handler mHandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1 ) {
                        mViewPager.setCurrentItem(0);
                    }
                    else{
                        // 自动滑动页面

                        int index=mViewPager.getCurrentItem() + 1;

                        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                    }

                    if (!isRuning) {
                        mHandler.sendEmptyMessageDelayed(0, 4000);
                    }
                    break;
            }
        }
    };

    public void onDestroy(){
        isRuning = true;
    }
}
