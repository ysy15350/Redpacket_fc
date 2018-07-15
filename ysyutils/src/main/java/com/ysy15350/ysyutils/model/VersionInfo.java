package com.ysy15350.ysyutils.model;

/**
 * 版本信息
 */
public class VersionInfo {

    /**
     * description : 更新内容：1、修改页面；2、优化逻辑
     * filesize : 1.3M
     * versioncode : 2
     * versionname : V1.0.1
     */

    /**
     * 版本描述
     */
    private String description;

    /**
     * 安装包大小
     */
    private String filesize;

    /**
     * 版本号
     */
    private int versioncode;

    /**
     * 版本名称
     */
    private String versionname;

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setFilesize(String filesize){
        this.filesize = filesize;
    }
    public String getFilesize(){
        return this.filesize;
    }
    public void setVersioncode(int versioncode){
        this.versioncode = versioncode;
    }
    public int getVersioncode(){
        return this.versioncode;
    }
    public void setVersionname(String versionname){
        this.versionname = versionname;
    }
    public String getVersionname(){
        return this.versionname;
    }
}
