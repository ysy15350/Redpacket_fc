package com.ysy15350.ysyutils.model;

/**
 * 版本信息
 */
public class VersionInfo {

    /**
     * id : 33.0
     * type : 3.0
     * uid : 0.0
     * name : red_packet(1.0.7).apk
     * path : red_packet(1.0.7).apk
     * size : 13.0
     * des : 更新内容:
     * 1、修复BUG;
     * 2、优化逻辑;
     * 3、修改页面;
     * 4、新增"左滑手势"返回功能
     * packagename : com.ysy15350.readpacket
     * versionnew : 1.0
     * versioncode : 8.0
     * versionname : 1.0.7
     * downloadcount : 13.0
     * downloadurl : http://192.168.31.190:8080//api/file/downloadApk/com.ysy15350.readpacket/33.apk
     * createtime : 1.514219457E12
     * status : 0.0
     */

    private double id;
    private double type;
    private double uid;
    private String name;
    private String path;
    /**
     * 安装包大小
     */
    private double size;
    /**
     * 版本描述
     */
    private String des;

    /**
     * 应用程序包名
     */
    private String packagename;
    private double versionnew;

    /**
     * 版本号
     */
    @com.google.gson.annotations.SerializedName("versioncode")
    private double versioncodeX;

    /**
     * 版本名称
     */
    @com.google.gson.annotations.SerializedName("versionname")
    private String versionnameX;
    private double downloadcount;

    /**
     * 下载地址
     */
    private String downloadurl;
    private double createtime;
    private double status;

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public double getType() {
        return type;
    }

    public void setType(double type) {
        this.type = type;
    }

    public double getUid() {
        return uid;
    }

    public void setUid(double uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public double getVersionnew() {
        return versionnew;
    }

    public void setVersionnew(double versionnew) {
        this.versionnew = versionnew;
    }

    public double getVersioncodeX() {
        return versioncodeX;
    }

    public void setVersioncodeX(double versioncodeX) {
        this.versioncodeX = versioncodeX;
    }

    public String getVersionnameX() {
        return versionnameX;
    }

    public void setVersionnameX(String versionnameX) {
        this.versionnameX = versionnameX;
    }

    public double getDownloadcount() {
        return downloadcount;
    }

    public void setDownloadcount(double downloadcount) {
        this.downloadcount = downloadcount;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
    }

    public double getCreatetime() {
        return createtime;
    }

    public void setCreatetime(double createtime) {
        this.createtime = createtime;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }

}
