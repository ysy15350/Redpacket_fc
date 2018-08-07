package com.ysy15350.redpacket_fc.main_tabs;


import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.githang.statusbar.StatusBarCompat;
import com.google.gson.reflect.TypeToken;
import com.ysy15350.redpacket_fc.MainActivity;
import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.active_area.activearea.ActiveAreaActivity;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.mine.share.ShareActivity;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseFragment;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.common.string.JsonConvertor;
import com.ysy15350.ysyutils.viewpager.MyViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import java.util.ArrayList;
import java.util.List;

import model.active_area.ActiveAreaInfo;
import model.adInfo.AdInfo;
import model.order.OrderListInfo;


@ContentView(R.layout.activity_main_tab2)
public class MainTab2Fragment extends MVPBaseFragment<MainTab2ViewInterface, MainTab2Presenter>
        implements MainTab2ViewInterface {

    private static final String TAG = "MainTab2Fragment";

    private MyViewPager myViewPager;

    /**
     * banner图片地址集合
     */

    private List<ImageView> imageViews = new ArrayList<ImageView>();

    /**
     * 是否有广告
     */
    private boolean isAdvertisement = true;


    public MainTab2Fragment() {
    }

    @Override
    public MainTab2Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab2Presenter(getActivity());
    }

    @Override
    public void initView() {
        super.initView();

        if(isAdvertisement){
            mHolder.setVisibility_VISIBLE(R.id.ll_activearea);
            mHolder.setVisibility_GONE(R.id.ll_noadvertisement);
        }else {
            mHolder.setVisibility_GONE(R.id.ll_activearea);
            mHolder.setVisibility_VISIBLE(R.id.ll_noadvertisement);
        }
    }

    @Override
    public void loadData() {
        super.loadData();

        MessageBox.showWaitDialog(getActivity(),"数据加载中...");
        mPresenter.getAdInfoList();
    }

    @Override
    public void getAdInfoListCallback(boolean isCache, Response response) {
        try {
            MessageBox.hideWaitDialog();

            String jsonStr = JsonConvertor.toJson(response);
            List<ActiveAreaInfo> list = null;
            if (response != null) {
                Object responseBod = response.getBody();
                if (responseBod != null) {
                    String body = JsonConvertor.toJson(responseBod);
                    list = JsonConvertor.fromJson(body, new TypeToken<List<ActiveAreaInfo>>() {
                    }.getType());
                    getPictures(list);
                }
            }
        } catch (Exception ex) {
        }

    }

    private void getPictures(List<ActiveAreaInfo> activeAreaInfos){
//        List<ActiveAreaInfo> activeAreaInfos = new ArrayList<>();

//        String[] img = new String[3];
//
//        img[0] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532109279512&di=465c4b8fb671b221651f07de982f1bcd&imgtype=0&src=http%3A%2F%2Fpic48.nipic.com%2Ffile%2F20140919%2F18645249_112722210805_2.jpg";
//        img[1] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532109279512&di=fe02918f705605113d2337a7b6379a57&imgtype=0&src=http%3A%2F%2Fpic21.photophoto.cn%2F20111217%2F0008020202694178_b.jpg";
//        img[2] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532113113575&di=68a5eb419b46d301045292ff9af966c0&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D3424208319%2C497009274%26fm%3D214%26gp%3D0.jpg";
//
//        for (int y = 0;y<img.length;y++){
//            ActiveAreaInfo areaInfo = new ActiveAreaInfo();
//            areaInfo.setImgurl(img[y]);
//            activeAreaInfos.add(areaInfo);
//        }

        if(activeAreaInfos.size()<9){
            mHolder.setBackground(R.id.btn_fcb,R.drawable.shape_btn_gray);
            mHolder.getView(R.id.btn_fcb).setEnabled(false);
        }else {
            mHolder.setBackground(R.id.btn_fcb,R.drawable.shape_btn2_blue);
            mHolder.getView(R.id.btn_fcb).setEnabled(true);
        }

        bindPictures(activeAreaInfos);
    }

    /**
     * 绑定图片
     */
    private void bindPictures(List<ActiveAreaInfo> activeAreaInfos){
        try {
            if (activeAreaInfos != null && activeAreaInfos.size() > 0) {
                if(activeAreaInfos.size() > 1){
                    if (myViewPager == null) {
                        myViewPager = new MyViewPager(getActivity(), (ViewPager) mHolder.getView(R.id.id_viewpager), true);
                    }
                }


                imageViews.clear();
                for (ActiveAreaInfo activeAreaInfo : activeAreaInfos) {
                    ImageView imageView = new ImageView(getActivity());
                    String imgurl = activeAreaInfo.getImgurl();

                    mHolder.setImageURL(imageView,imgurl);

                    imageView.setTag(activeAreaInfo);

                    imageViews.add(imageView);
                    imageView = null;

                }

                try {
                    myViewPager.setAdapter(imageViews);
                    myViewPager.setOnPageChangeListener();
                    myViewPager.setMyViewPagerListener(new MyViewPager.MyViewPagerListener() {
                        @Override
                        public void onPageChanged(int index) {
                            setFormHead("广告"+index+"/10");
                            switch (index){
                                case 0:
                                    mHolder.setBackground(R.id.ll_round1,R.drawable.shape_circle1);
                                    mHolder.setBackground(R.id.ll_round2,R.drawable.shape_circle_gary);
                                    mHolder.setBackground(R.id.ll_round3,R.drawable.shape_circle_gary);
                                    mHolder.setBackground(R.id.ll_round4,R.drawable.shape_circle_gary);
                                    mHolder.setBackground(R.id.ll_round5,R.drawable.shape_circle_gary);
                                    mHolder.setBackground(R.id.ll_round6,R.drawable.shape_circle_gary);
                                    mHolder.setBackground(R.id.ll_round7,R.drawable.shape_circle_gary);
                                    mHolder.setBackground(R.id.ll_round8,R.drawable.shape_circle_gary);
                                    mHolder.setBackground(R.id.ll_round9,R.drawable.shape_circle_gary);
                                    mHolder.setBackground(R.id.ll_round10,R.drawable.shape_circle_gary);
                                    break;
                                case 1:
                                    mHolder.setBackground(R.id.ll_round2,R.drawable.shape_circle2);
                                    break;
                                case 2:
                                    mHolder.setBackground(R.id.ll_round3,R.drawable.shape_circle3);
                                    break;
                                case 3:
                                    mHolder.setBackground(R.id.ll_round4,R.drawable.shape_circle4);
                                    break;
                                case 4:
                                    mHolder.setBackground(R.id.ll_round5,R.drawable.shape_circle5);
                                    break;
                                case 5:
                                    mHolder.setBackground(R.id.ll_round6,R.drawable.shape_circle6);
                                    break;
                                case 6:
                                    mHolder.setBackground(R.id.ll_round7,R.drawable.shape_circle7);
                                    break;
                                case 7:
                                    mHolder.setBackground(R.id.ll_round8,R.drawable.shape_circle8);
                                    break;
                                case 8:
                                    mHolder.setBackground(R.id.ll_round9,R.drawable.shape_circle9);
                                    break;
                                case 9:
                                    mHolder.setBackground(R.id.ll_round10,R.drawable.shape_circle9);
                                    break;
                            }
                        }
                    });
                    myViewPager.setClickListener(new MyViewPager.ClickListener() {
                        @Override
                        public void imgClick(View v) {


                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                MyViewPager.isInfiniteLoop = false;
                mHolder.setVisibility_VISIBLE(R.id.img_default);// 没有图片数据时，显示默认banner图片
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成FCB
     * @param view
     */
    @Event(value = R.id.btn_fcb)
    private void btn_fcbClick(View view) {

    }

    /**
     * 收集广告红包
     *
     * @param view
     */
    @Event(value = R.id.ll_btn1)
    private void ll_btn1Click(View view) {

        MainActivity.tab_position = 0;
        startActivity(new Intent(getActivity(), MainActivity.class));

    }

    /**
     * 访问好友广告
     *
     * @param view
     */
    @Event(value = R.id.llbtn_activearea)
    private void llbtn_activeareaClick(View view) {

        startActivity(new Intent(getActivity(), ActiveAreaActivity.class));

    }


}
