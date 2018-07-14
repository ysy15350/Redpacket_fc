package com.ysy15350.ysyutils.model;

public class SysUser {

    //{  "id": 2359.0,  "type": 0.0,  "token": "54a8d61fa0907a4dd83c25bfbc4a0640",  "mobile": "15215095191",  "introducer": 0.0,  "createtime": 1.531560493E12,  "password": "e36cc3acb8ac9bf20ed3c586554d8512823e28fd",  "rights": "",  "ip": "192.168.31.176",  "status": "0",  "skin": "default"}


    private int id;

    private int type;

    private String token;

    private String mobile;

    private String username;

    private int introducer;

    private long createtime;

    private String password;

    private String rights;

    private String ip;

    private String status;

    private String skin;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIntroducer(int introducer) {
        this.introducer = introducer;
    }

    public int getIntroducer() {
        return this.introducer;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public long getCreatetime() {
        return this.createtime;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getRights() {
        return this.rights;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getSkin() {
        return this.skin;
    }


}
