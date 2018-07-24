package com.ysy15350.redpacket_fc.main_tabs;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.RadioGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.dailytasks.DailyTasksActivity;
import com.ysy15350.redpacket_fc.dialog.RedPackageDialog;
import com.ysy15350.redpacket_fc.dialog.WholePointDialog;
import com.ysy15350.redpacket_fc.mine.cityowner.cityowner_transaction.CityOwnerTransactionActivity;
import com.ysy15350.redpacket_fc.mine.invitationfriends.InvitationFriendsListActivity;
import com.ysy15350.redpacket_fc.mine.share.ShareActivity;
import com.ysy15350.redpacket_fc.redpackage.open_treasurebox.OpenTreasureBoxActivity;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseFragment;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.custom_view.TextSwitchView;
import com.ysy15350.ysyutils.custom_view.dialog.AgreementDialog;
import com.ysy15350.ysyutils.gaodemap.util.Constants;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main_tab1)
public class MainTab1Fragment extends MVPBaseFragment<MainTab1ViewInterface, MainTab1Presenter>
        implements MainTab1ViewInterface, LocationSource, AMapLocationListener, AMap.OnMapTouchListener, AMap.OnMarkerClickListener,
        AMap.OnInfoWindowClickListener, AMap.OnMarkerDragListener, AMap.OnMapLoadedListener, AMap.InfoWindowAdapter, RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "MainTab1Fragment";

    // 定位蓝点
    MyLocationStyle myLocationStyle;
    private LocationSource.OnLocationChangedListener mListener;
    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    //自定义定位小蓝点的Marker
    Marker locationMarker;
    boolean useMoveToLocationWithMapMode = true;
    //坐标和经纬度转换工具
    Projection projection;

    // 绘制圆
    public Circle circle;
    CircleOptions circleOptions;

    private MarkerOptions markerOption;
    private RadioGroup radioOption;
    private Marker marker;// 从地上生长的marker对象
    /**
     * 当前坐标
     */
    private LatLng currentLatlng;

    /**
     * 圆的半径（默认500米）
     */
    private int radius = 500;

    /**
     * 地图坐标集合
     */
    List<LatLng> latLngs = new ArrayList<>();

    /**
     * 地图控件
     */
    @ViewInject(R.id.map)
    private MapView mMapView;

    //初始化地图控制器对象
    AMap aMap;

    /**
     * 0：初始化；1：界面已看不见；2：界面再次可见
     */
    private int status =0;


    public MainTab1Fragment() {
    }

    @Override
    public MainTab1Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab1Presenter(getActivity());
    }


    @Override
    public void initView() {
        super.initView();


    }


    private void setUpMap() {
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setOnMapTouchListener(this);

        aMap.setOnMarkerDragListener(this);// 设置marker可拖拽事件监听器
        aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
        aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
        aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器

        addMarkersToMap();// 往地图上添加marker
    }

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {

        ArrayList<MarkerOptions> markerOptionlst = new ArrayList<MarkerOptions>();

        latLngs.add(Constants.LONGKIN);
        latLngs.add(Constants.LATLNG1);
        latLngs.add(Constants.LATLNG2);
        latLngs.add(Constants.LATLNG3);
        latLngs.add(Constants.LATLNG4);
//        latLngs.add(Constants.LATLNG5);
//        latLngs.add(Constants.LATLNG6);
//        latLngs.add(Constants.LATLNG7);

        for (LatLng latLng : latLngs) {
            MarkerOptions markerOptions = newMarkerOptions(latLng);
            Marker marker = aMap.addMarker(markerOptions);
            growInto(marker);
//            markerOptionlst.add(markerOptions);
        }
//        List<Marker> markerlst = aMap.addMarkers(markerOptionlst, true);

    }

    private MarkerOptions newMarkerOptions(LatLng var1) {
        MarkerOptions markerOptions = new MarkerOptions();
        //经纬度
        markerOptions.position(var1);
        markerOptions.draggable(true);
        markerOptions.icon(
                BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),
                                R.mipmap.icon_redpackage_open)));

        return markerOptions;
    }

    String[] messageArray;
    private void setTextSwitch(String result) {
        if (!CommFunAndroid.isNullOrEmpty(result)) {
            messageArray = result.split(",");

            TextSwitchView tv_message = mHolder.getView(R.id.textSwitcher);
            // showMsg(result + (tv_message == null));

            tv_message.setResources(messageArray);
            tv_message.setTextStillTime(5000);
        }
    }

    private int count = 1;
    Bitmap lastMarkerBitMap = null;

    /**
     * 从地上生长效果，实现思路
     * 在较短的时间内，修改marker的图标大小，从而实现动画<br>
     * 1.保存原始的图片；
     * 2.在原始图片上缩放得到新的图片，并设置给marker；
     * 3.回收上一张缩放后的图片资源；
     * 4.重复2，3步骤到时间结束；
     * 5.回收上一张缩放后的图片资源，设置marker的图标为最原始的图片；
     * <p>
     * 其中时间变化由AccelerateInterpolator控制
     *
     * @param marker
     */
    private void growInto(final Marker marker) {
        marker.setVisible(false);
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final long duration = 250;// 动画总时长
        final Bitmap bitMap = marker.getIcons().get(0).getBitmap();// BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        final int width = bitMap.getWidth();
        final int height = bitMap.getHeight();

        final Interpolator interpolator = new AccelerateInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);

                if (t > 1) {
                    t = 1;
                }

                // 计算缩放比例
                int scaleWidth = (int) (t * width);
                int scaleHeight = (int) (t * height);
                if (scaleWidth > 0 && scaleHeight > 0) {

                    // 使用最原始的图片进行大小计算
                    marker.setIcon(BitmapDescriptorFactory.fromBitmap(Bitmap
                            .createScaledBitmap(bitMap, scaleWidth,
                                    scaleHeight, true)));
                    marker.setVisible(true);

                    // 因为替换了新的图片，所以把旧的图片销毁掉，注意在设置新的图片之后再销毁
                    if (lastMarkerBitMap != null
                            && !lastMarkerBitMap.isRecycled()) {
                        lastMarkerBitMap.recycle();
                    }

                    //第一次得到的缩放图片，在第二次回收，最后一次的缩放图片，在动画结束时回收
                    ArrayList<BitmapDescriptor> list = marker.getIcons();
                    if (list != null && list.size() > 0) {
                        // 保存旧的图片
                        lastMarkerBitMap = marker.getIcons().get(0).getBitmap();
                    }

                }

                if (t < 1.0 && count < 10) {
                    handler.postDelayed(this, 16);
                } else {
                    // 动画结束回收缩放图片，并还原最原始的图片
                    if (lastMarkerBitMap != null
                            && !lastMarkerBitMap.isRecycled()) {
                        lastMarkerBitMap.recycle();
                    }
                    marker.setIcon(BitmapDescriptorFactory.fromBitmap(bitMap));
                    marker.setVisible(true);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();

        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        mMapView.onCreate(mSavedInstanceState);

        aMap = mMapView.getMap();
        setUpMap();

        setTextSwitch("消息1,消息2,消息3,消息4,消息5");


        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();

        if(status == 0){
            status = 2;
        }

        timerStart();


        useMoveToLocationWithMapMode = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();

        useMoveToLocationWithMapMode = false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }



    /**
     * 扩大范围
     *
     * @param view
     */
    @Event(value = R.id.btn_whole1)
    private void btn_whole1Click(View view) {

        Repaint();

    }


    /**
     * 监听amap地图加载成功事件回调
     */
    @Override
    public void onMapLoaded() {
        // 设置所有maker显示在当前可视区域地图中
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        // 遍历坐标集合
        for (LatLng latLng : latLngs) {
            builder.include(latLng);
        }
        LatLngBounds bounds = builder.build();
        aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (aMap != null && circle != null) {
//            // marker点击时跳动一下
//            jumpPoint(marker);
            // 从地上生长效果，实现思路
            growInto(marker);

            boolean b = circle.contains(marker.getPosition());

            if (b) {
                String title = "给你发了一个广告红包";
                RedPackageDialog redPackageDialog = new RedPackageDialog(getActivity(), title, "同意", "拒绝", "");
                redPackageDialog.setDialogListener(new RedPackageDialog.DialogListener() {
                    @Override
                    public void onOkClick() {
                        // 打开宝箱
                        startActivity(new Intent(getActivity(), OpenTreasureBoxActivity.class));
                    }
                });

                redPackageDialog.show();
            }
        }


        return false;
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        String curDes = marker.getTitle() + "拖动时当前位置:(lat,lng)\n("
                + marker.getPosition().latitude + ","
                + marker.getPosition().longitude + ")";
        MessageBox.show(curDes);
    }

    /**
     * 监听开始拖动marker事件回调
     */
    @Override
    public void onMarkerDragStart(Marker marker) {
        MessageBox.show(marker.getTitle() + "开始拖动");
    }

    /**
     * 监听拖动marker结束事件回调
     */
    @Override
    public void onMarkerDragEnd(Marker marker) {
        MessageBox.show(marker.getTitle() + "停止拖动");
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(getActivity());
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置每5秒定位一次
            mLocationOption.setInterval(2000);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                LatLng latLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                currentLatlng = latLng;
                addPolylinescircle(currentLatlng, radius);
                //展示自定义定位小蓝点
                if (locationMarker == null) {
                    //首次定位
                    locationMarker = aMap.addMarker(new MarkerOptions().position(latLng)
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.gps_point)));

                    //首次定位,选择移动到地图中心点并修改级别到15级
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));


                } else {

                    if (useMoveToLocationWithMapMode) {
                        //二次以后定位，使用sdk中没有的模式，让地图和小蓝点一起移动到中心点（类似导航锁车时的效果）
                        startMoveLocationAndMap(latLng);
                    } else {
                        startChangeLocation(latLng);
                    }

                }


            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    /**
     * 修改自定义定位小蓝点的位置
     *
     * @param latLng
     */
    private void startChangeLocation(LatLng latLng) {

        if (locationMarker != null) {
            LatLng curLatlng = locationMarker.getPosition();
            if (curLatlng == null || !curLatlng.equals(latLng)) {
                locationMarker.setPosition(latLng);
            }
        }
    }

    /**
     * 同时修改自定义定位小蓝点和地图的位置
     *
     * @param latLng
     */
    private void startMoveLocationAndMap(LatLng latLng) {

        //将小蓝点提取到屏幕上
        if (projection == null) {
            projection = aMap.getProjection();
        }
        if (locationMarker != null && projection != null) {
            LatLng markerLocation = locationMarker.getPosition();
            Point screenPosition = aMap.getProjection().toScreenLocation(markerLocation);
            locationMarker.setPositionByPixels(screenPosition.x, screenPosition.y);

        }

        //移动地图，移动结束后，将小蓝点放到放到地图上
        myCancelCallback.setTargetLatlng(latLng);
        //动画移动的时间，最好不要比定位间隔长，如果定位间隔2000ms 动画移动时间最好小于2000ms，可以使用1000ms
        //如果超过了，需要在myCancelCallback中进行处理被打断的情况
        aMap.animateCamera(CameraUpdateFactory.changeLatLng(latLng), 1000, myCancelCallback);


    }

    MyCancelCallback myCancelCallback = new MyCancelCallback();

    @Override
    public void onTouch(MotionEvent motionEvent) {
        Log.i("amap", "onTouch 关闭地图和小蓝点一起移动的模式");
        useMoveToLocationWithMapMode = false;
    }

    /**
     * 监控地图动画移动情况，如果结束或者被打断，都需要执行响应的操作
     */
    class MyCancelCallback implements AMap.CancelableCallback {

        LatLng targetLatlng;

        public void setTargetLatlng(LatLng latlng) {
            this.targetLatlng = latlng;
        }

        @Override
        public void onFinish() {
            if (locationMarker != null && targetLatlng != null) {
                locationMarker.setPosition(targetLatlng);
            }
        }

        @Override
        public void onCancel() {
            if (locationMarker != null && targetLatlng != null) {
                locationMarker.setPosition(targetLatlng);
            }
        }
    }

    ;

    /**
     * @param centerpoint 中心点坐标
     * @param radius      半径 米
     */
    public void addPolylinescircle(LatLng centerpoint, int radius) {

        // 绘制一个圆形
        if (circle == null || status == 2) {
            circleOptions = new CircleOptions().center(centerpoint)
                    .radius(radius)
                    .strokeColor(Color.argb(1, 1, 1, 1))
                    .fillColor(Color.argb(20, 1, 1, 1))
                    .strokeWidth(25);// 像素
            circle = aMap.addCircle(circleOptions);
            status = 0;
        }

    }

    private void Repaint() {
        aMap.clear();
        addMarkersToMap();// 往地图上添加marker
        circleOptions = new CircleOptions().center(currentLatlng)
                .radius(radius += 100)
                .strokeColor(Color.argb(1, 1, 1, 1))
                .fillColor(Color.argb(20, 1, 1, 1))
                .strokeWidth(25);// 像素
        circle = aMap.addCircle(circleOptions);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        //取消自定义InfoWindow，则使用默认InfoWindow样式
//        aMap.setInfoWindowAdapter(null);
//        aMap.setInfoWindowAdapter(this);
    }

    /**
     * 监听自定义infowindow窗口的infowindow事件回调
     */
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    /**
     * 监听自定义infowindow窗口的infocontents事件回调
     */
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
//        MessageBox.show("点击成功");
    }

    /**
     * 倒数计时器
     */
    private CountDownTimer timer = new CountDownTimer(15 * 60 * 1000, 1000) {
        /**
         * 固定间隔被调用,就是每隔countDownInterval会回调一次方法onTick
         * @param millisUntilFinished
         */
        @Override
        public void onTick(long millisUntilFinished) {
            mHolder.setText(R.id.tv_countdown, CommFun.formatTime(millisUntilFinished));
        }


        /**
         * 倒计时完成时被调用
         */
        @Override
        public void onFinish() {
            mHolder.setText(R.id.tv_countdown,"00:00");
        }
    };

    /**
     * 取消倒计时
     */
    public void timerCancel() {
        timer.cancel();
    }

    /**
     * 开始倒计时
     */
    public void timerStart() {
        timer.start();
    }

    /**
     * 邀请好友
     *
     * @param view
     */
    @Event(value = R.id.img_invitation)
    private void img_invitationClick(View view) {
        startActivity(new Intent(getActivity(), InvitationFriendsListActivity.class));

    }


    /**
     * 打开整点红包/每日任务
     *
     * @param view
     */
    @Event(value = R.id.llbtn_clock)
    private void llbtn_clockClick(View view) {

        WholePointDialog wholePointDialog = new WholePointDialog(getActivity(), "¥654.66", "dsfd", "不知道谁提供的");

        wholePointDialog.show();

        // 每日任务
//        startActivity(new Intent(getActivity(), DailyTasksActivity.class));
    }

    /**
     * 城主
     *
     * @param view
     */
    @Event(value = R.id.ll_cityowner)
    private void ll_cityownerClick(View view) {

        // 跳转城主交易界面
        startActivity(new Intent(getActivity(), CityOwnerTransactionActivity.class));
    }
}