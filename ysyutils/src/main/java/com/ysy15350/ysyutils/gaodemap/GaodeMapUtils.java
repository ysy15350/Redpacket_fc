package com.ysy15350.ysyutils.gaodemap;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.model.LocationInfo;

import java.util.Date;

/**
 * Created by yangshiyou on 2018/3/6.
 */

public class GaodeMapUtils {

    private static final String TAG = "GaodeMapUtils";

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            super.onLocationChanged(aMapLocation);
            try {
                if (aMapLocation != null) {

                    Log.d(TAG, "onLocationChanged() called with: aMapLocation = [" + aMapLocation + "]");

                    if (aMapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。

                        LocationInfo locationInfo = LocationInfo.parseAMapLocation(aMapLocation);
                        if (locationInfo != null) {
                            String locationInfoJson = locationInfo.getLocationInfoJson();
                            BaseData.setCache("locationInfoJson", locationInfoJson, 60);
                        }


                        Log.d(TAG, "onLocationChanged: " + aMapLocation.getLatitude() + "," + aMapLocation.getLongitude());


                        int locationType = aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        double latitude = aMapLocation.getLatitude();//获取纬度
                        double longitude = aMapLocation.getLongitude();//获取经度
                        float accuracy = aMapLocation.getAccuracy();//获取精度信息
                        String address = aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                        String country = aMapLocation.getCountry();//国家信息
                        String province = aMapLocation.getProvince();//省信息
                        String city = aMapLocation.getCity();//城市信息
                        String district = aMapLocation.getDistrict();//城区信息
                        String street = aMapLocation.getStreet();//街道信息
                        String streetNum = aMapLocation.getStreetNum();//街道门牌号信息
                        String cityCode = aMapLocation.getCityCode();//城市编码
                        String adCode = aMapLocation.getAdCode();//地区编码
                        String aoiName = aMapLocation.getAoiName();//获取当前定位点的AOI信息
                        String buildingId = aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                        String floor = aMapLocation.getFloor();//获取当前室内定位的楼层
                        int gpsAccuracyStatus = aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                        //获取定位时间

                        Date date = new Date(aMapLocation.getTime());
                        String locationtime = CommFun.toDateString(date, "yyyy-MM-dd HH:mm:ss");

                        BaseData.setCache("latitude", latitude + "");//纬度
                        BaseData.setCache("longitude", longitude + "");//精度


                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    public void initAMapLocation(Context context, AMapLocationListener locationListener) {
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        //设置定位回调监听
        mLocationClient.setLocationListener(locationListener);

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        //mLocationOption.setLocationMode(AMapLocationMode.Battery_Saving);

        //设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式。
        //mLocationOption.setLocationMode(AMapLocationMode.Device_Sensors);


        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，
        // setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);

        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        //mLocationOption.setInterval(1000);

        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);

        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(true);

        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);

        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);


        Log.d(TAG, "initAMapLocation() called");
    }

    /**
     * 开始定位
     *
     * @param context
     */
    public void startLocation(Context context, AMapLocationListener locationListener) {

        if(mLocationClient!=null)
            mLocationClient.stopLocation();

        if (locationListener == null)
            locationListener = mLocationListener;

        initAMapLocation(context, locationListener);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }

}
