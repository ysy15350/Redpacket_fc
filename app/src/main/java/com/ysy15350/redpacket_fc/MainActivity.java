package com.ysy15350.redpacket_fc;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.ysy15350.redpacket_fc.main_tabs.MainTab1Fragment;
import com.ysy15350.redpacket_fc.main_tabs.MainTab2Fragment;
import com.ysy15350.redpacket_fc.main_tabs.MainTab3Fragment;
import com.ysy15350.redpacket_fc.main_tabs.MainTab4Fragment;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
import com.ysy15350.ysyutils.common.ExitApplication;
import com.ysy15350.ysyutils.common.message.MessageBox;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.activity_main)
public class MainActivity extends MVPBaseActivity<MainViewInterface, MainPresenter>
        implements MainViewInterface {

    private static final String TAG = "MainActivity";


    @Override
    protected MainPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainPresenter(MainActivity.this);
    }


    @ViewInject(R.id.pager)
    private ViewPager mTabViewPager;


    MainTab1Fragment mainTab1Fragment;
    MainTab2Fragment mainTab2Fragment;
    MainTab3Fragment mainTab3Fragment;
    MainTab4Fragment mainTab4Fragment;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @ViewInject(R.id.tab)
    private TabLayout tab;


    /**
     * 显示指定选项卡
     */
    public static int tab_position = 0;


    @ViewInject(R.id.ll_tab1)
    private View ll_tab1;

    @ViewInject(R.id.ll_tab2)
    private View ll_tab2;

    @ViewInject(R.id.ll_tab3)
    private View ll_tab3;

    @ViewInject(R.id.ll_tab4)
    private View ll_tab4;

    @Event(value = R.id.ll_tab1)
    private void ll_tab1Click(View view) {
        setSelect(0);
    }

    @Event(value = R.id.ll_tab2)
    private void ll_tab2Click(View view) {
        setSelect(1);
    }

    @Event(value = R.id.ll_tab3)
    private void ll_tab3Click(View view) {
        setSelect(2);
    }

    @Event(value = R.id.ll_tab4)
    private void ll_tab4Click(View view) {
        setSelect(3);
    }




    @Override
    public void initView() {
        super.initView();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mTabViewPager.setAdapter(mSectionsPagerAdapter);

        mTabViewPager.setOffscreenPageLimit(2);//避免界面切换数据消失

        mTabViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = mSectionsPagerAdapter.getItem(position);
                if (fragment != null) {
                    boolean isHidden = fragment.isHidden();
                    Log.d(TAG, "onPageSelected() called with: position = [" + position + "],isHidden=" + isHidden);
                    //fragment.onResume();
                    switch (position) {
                        case 0:

                            break;
                        case 1:
                            break;
                    }
                }
                setTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tab.setupWithViewPager(mTabViewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();

        initView();

        getLocation();


        setSelect(tab_position);

    }

    /**
     * 点击事件1:设置tab(改变图片和字体)和2:切换fragment
     *
     * @param position
     */
    protected void setSelect(int position) {

        mTabViewPager.setCurrentItem(position);
        setTab(position);
    }

    /**
     * 滑动viewpager时设置tab(改变图片和字体)
     *
     * @param position
     */
    private void setTab(int position) {

        tab_position = position;// 记录已打开选项卡位置，当跳转到登录界面或者其他界面，显示此界面

        switch (position) {
            case 0:
                setViewImage(ll_tab1);
                break;
            case 1:
                setViewImage(ll_tab2);
                break;
            case 2:
                setViewImage(ll_tab3);
                break;
            case 3:
                setViewImage(ll_tab4);
                break;
            default:
                break;
        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private List<String> titles = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            titles.add("红包");
            titles.add("广告");
            titles.add("交易");
            titles.add("我的");
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    if (mainTab1Fragment == null)
                        mainTab1Fragment = new MainTab1Fragment();
                    return mainTab1Fragment;
                }
                case 1: {
                    if (mainTab2Fragment == null)
                        mainTab2Fragment = new MainTab2Fragment();
                    return mainTab2Fragment;
                }
                case 2: {
                    if (mainTab3Fragment == null)
                        mainTab3Fragment = new MainTab3Fragment();
                    return mainTab3Fragment;
                }
                case 3: {
                    if (mainTab4Fragment == null)
                        mainTab4Fragment = new MainTab4Fragment();
                    return mainTab4Fragment;
                }
                default:
                    break;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // return super.getPageTitle(position);
            return titles.get(position);
        }
    }


    /**
     * 记录当前view（图片切换）
     */
    private View currentView;
    /**
     * 记录当前textview(切换字体颜色)
     */
    private View currentTeView;

    /**
     * 切换图片（background 设置背景：xml->selector）
     *
     * @param v
     */
    private void setViewImage(View v) {
        if (currentView != null) {
            if (currentView.getId() != v.getId()) {
                View imgview = currentView.findViewWithTag("tab_img");
                View textview = currentView.findViewWithTag("tab_txt");
                if (imgview != null)
                    imgview.setEnabled(true);
                if (textview != null) {
                    textview.setEnabled(true);
                }
            }
        }
        if (v != null) {
            View imgview = v.findViewWithTag("tab_img");
            View textview = v.findViewWithTag("tab_txt");
            if (imgview != null)
                imgview.setEnabled(false);
            if (textview != null) {
                textview.setEnabled(false);
            }
        }
        currentView = v;
    }

    /**
     * 记录当前是否已经检测过更新了，如果已经检测，下次启动再检测，避免多次提醒更新
     */
    private static boolean isCheckVersion = false;

    @Override
    public void updateVersion(String title, String versionName, String content, String fileSize, String url) {
        super.updateVersion(title, versionName, content, fileSize, url);

        isCheckVersion = true;
    }


    /**
     * 点击返回按钮间隔时间
     */
    private long exitTime = 0;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_MENU) {
            // mPopupWindow.setBackgroundDrawable(new
            // ColorDrawable(Color.parseColor("#b0000000")));
            // mPopupWindow.showAtLocation(form_bottom, Gravity.BOTTOM, 0, 0);
            // mPopupWindow.setAnimationStyle(R.style.app_pop);
            // mPopupWindow.setOutsideTouchable(true);
            // mPopupWindow.setFocusable(true);
            // mPopupWindow.update();
//            showExitPop();
        }

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                MessageBox.show("再按一次退出程序");

                //showExitPop();

                exitTime = System.currentTimeMillis();
            } else {
                // stopService(new Intent(getApplicationContext(),
                // TestService.class));// stop

                // CommFunAndroid.setSharedPreferences("JSESSIONID", "");

                ExitApplication.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        return super.onFling(e1, e2, velocityX, velocityY);
        return false;
    }

    @Override
    protected void restartApplication() {
        super.restartApplication();
        Intent intent = new Intent(this, GuideActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        finish();
    }
}

