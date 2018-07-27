package com.ysy15350.ysyutils.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.Date;

/**
 * Created by yangshiyou on 2018/3/6.
 */

@Table(name = "imageInfo")
public class ImageInfo {

    /**
     * 主键
     */
    @Column(name = "id", isId = true, autoGen = true)
    private int id;

    /**
     * 订单号
     */
    @Column(name = "orderNum")
    private String orderNum;

    @Column(name = "uid")
    private int uid;

    /**
     * 类型
     */
    @Column(name = "type")
    private int type;

    /**
     * 图片名称
     */
    @Column(name = "imgName")
    private String imgName;

    /**
     * 图片本地地址
     */
    @Column(name = "path")
    private String path;

    /**
     * 图片网络路径
     */
    @Column(name = "url")
    private String url;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;


    @Column(name = "description")
    private String description;

    @Column(name = "createTime")
    private Date createTime;

    @Column(name = "status")
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
