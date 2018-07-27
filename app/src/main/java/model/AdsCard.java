package model;

/**
 * 广告卡片
 */
public class AdsCard {


    /**
     * description : 更新内容：1、修改页面；2、优化逻辑
     * filesize : 1.3M
     * versioncode : 2
     * versionname : V1.0.1
     */

    private String description;
    private String filesize;
    private int versioncode;
    private String versionname;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public int getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(int versioncode) {
        this.versioncode = versioncode;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }
}
