package com.ysy15350.ysyutils.model;

public class SysUser {

    //{  "id": 2359.0,  "type": 0.0,  "token": "54a8d61fa0907a4dd83c25bfbc4a0640",  "mobile": "15215095191",  "introducer": 0.0,  "createtime": 1.531560493E12,  "password": "e36cc3acb8ac9bf20ed3c586554d8512823e28fd",  "rights": "",  "ip": "192.168.31.176",  "status": "0",  "skin": "default"}


    private int id;

    private int type;

    private String token;

    private String avatar;

    private String mobile;

    private String nickname;

    private String realname;

    private String personalitySignature;

    private String alipayAccount;

    private int sex;

    private int birthdayYear;

    private int birthdayMonth;

    private int birthdayDay;

    private String habitualResidence;

    private String ip;

    private String status;

    private String skin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPersonalitySignature() {
        return personalitySignature;
    }

    public void setPersonalitySignature(String personalitySignature) {
        this.personalitySignature = personalitySignature;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getBirthdayYear() {
        return birthdayYear;
    }

    public void setBirthdayYear(int birthdayYear) {
        this.birthdayYear = birthdayYear;
    }

    public int getBirthdayMonth() {
        return birthdayMonth;
    }

    public void setBirthdayMonth(int birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }

    public int getBirthdayDay() {
        return birthdayDay;
    }

    public void setBirthdayDay(int birthdayDay) {
        this.birthdayDay = birthdayDay;
    }

    public String getHabitualResidence() {
        return habitualResidence;
    }

    public void setHabitualResidence(String habitualResidence) {
        this.habitualResidence = habitualResidence;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }
}
