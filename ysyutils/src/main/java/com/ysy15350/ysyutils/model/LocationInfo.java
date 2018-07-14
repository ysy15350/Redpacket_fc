package com.ysy15350.ysyutils.model;

import com.amap.api.location.AMapLocation;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.string.JsonConvertor;

import java.util.Date;

/**
 * Created by yangshiyou on 2018/3/6.
 * 定位信息，高德地图实体类Json无法解析，使用此类
 */

public class LocationInfo {


//    {
//        "accuracy": 25.0,
//            "adCode": "500105",
//            "address": "重庆市江北区洋河中路119号靠近重庆市网商产业园",
//            "aoiName": "嘉乐汇购物公园",
//            "buildingId": "",
//            "city": "重庆市",
//            "cityCode": "023",
//            "country": "中国",
//            "district": "江北区",
//            "floor": "",
//            "gpsAccuracyStatus": -1,
//            "latitude": 29.582125,
//            "locationType": 5,
//            "locationtime": "2018-03-21 15:13:17",
//            "longitude": 106.536741,
//            "province": "重庆市",
//            "street": "洋河中路",
//            "streetNum": "119号"
//    }


    private int locationType;//获取当前定位结果来源，如网络定位结果，详见定位类型表
    private double latitude;//获取纬度
    private double longitude;//获取经度
    private float accuracy;//获取精度信息
    private String address;//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
    private String country;//国家信息
    private String province;//省信息
    private String city;//城市信息
    private String district;//城区信息
    private String street;//街道信息
    private String streetNum;//街道门牌号信息
    private String cityCode;//城市编码
    private String adCode;//地区编码
    private String aoiName;//获取当前定位点的AOI信息
    private String buildingId;//获取当前室内定位的建筑物Id
    String floor;//获取当前室内定位的楼层
    private int gpsAccuracyStatus;//获取GPS的当前状态
    private String locationtime;//定位时间

    public int getLocationType() {
        return locationType;
    }

    public void setLocationType(int locationType) {
        this.locationType = locationType;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getAoiName() {
        return aoiName;
    }

    public void setAoiName(String aoiName) {
        this.aoiName = aoiName;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getGpsAccuracyStatus() {
        return gpsAccuracyStatus;
    }

    public void setGpsAccuracyStatus(int gpsAccuracyStatus) {
        this.gpsAccuracyStatus = gpsAccuracyStatus;
    }

    public String getLocationtime() {
        return locationtime;
    }

    public void setLocationtime(String locationtime) {
        this.locationtime = locationtime;
    }

    public String getLocationInfoJson() {
        return JsonConvertor.toJson(this);
    }

    public static LocationInfo getLocationFromJson(String json) {
        try {
            if (!CommFun.isNullOrEmpty(json)) {
                return JsonConvertor.fromJson(json, LocationInfo.class);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 转换高德地图实体类
     *
     * @param aMapLocation
     * @return
     */
    public static LocationInfo parseAMapLocation(AMapLocation aMapLocation) {

        try {
            if (aMapLocation != null) {
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

                LocationInfo locationInfo = new LocationInfo();
                locationInfo.setLocationType(locationType);
                locationInfo.setLatitude(latitude);
                locationInfo.setLongitude(longitude);
                locationInfo.setAccuracy(accuracy);
                locationInfo.setAddress(address);
                locationInfo.setCountry(country);
                locationInfo.setProvince(province);
                locationInfo.setCity(city);
                locationInfo.setDistrict(district);
                locationInfo.setStreet(street);
                locationInfo.setStreetNum(streetNum);
                locationInfo.setCityCode(cityCode);
                locationInfo.setAdCode(adCode);
                locationInfo.setAoiName(aoiName);
                locationInfo.setBuildingId(buildingId);
                locationInfo.setFloor(floor);
                locationInfo.setGpsAccuracyStatus(gpsAccuracyStatus);
                locationInfo.setLocationtime(locationtime);

                return locationInfo;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;

    }

}
