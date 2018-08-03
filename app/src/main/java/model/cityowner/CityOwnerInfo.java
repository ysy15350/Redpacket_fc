package model.cityowner;

import com.ysy15350.ysyutils.model.SysUser;

public class CityOwnerInfo {
    /**
     * id : 6.0
     * areaId : 542372.0
     * code : 500112000000
     * provence : 重庆市
     * city : 市辖区
     * district : 渝北区
     * price : 123.4
     */

    private int id;
    //地区id
    private int areaId;
    //地区编码
    private String code;
    //省，如四川省
    private String provence;
    //市，如资阳市
    private String city;
    //区、县，如安岳县
    private String district;
    //当前城主购买所需金额
    private double price;

    //当前购买城主的用户id
    private int uid;
    //购买来源平台类型，1:android;2:ios;3:web
    private int platform;
    //状态
    private int status;

    // 用户信息
    private SysUser sysUser;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProvence() {
        return provence;
    }

    public void setProvence(String provence) {
        this.provence = provence;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
