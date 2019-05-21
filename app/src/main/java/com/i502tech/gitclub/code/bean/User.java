package com.i502tech.gitclub.code.bean;

/**
 * description $desc$
 * created by jerry on 2019/4/11.
 */
public class User {

    /**
     * adminStatus : 0
     * avatar :
     * city :
     * date : 2019-04-11 18:18:28
     * gender :
     * userName : 刘磊
     * openId :
     * score : 0
     * sessionSey :
     * userId : 4545
     */

    private int adminStatus;
    private String avatar;
    private String city;
    private String date;
    private String gender;
    private String userName;
    private String openId;
    private int score;
    private String sessionKey;
    private int userId;

    public int getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(int adminStatus) {
        this.adminStatus = adminStatus;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "adminStatus=" + adminStatus +
                ", avatar='" + avatar + '\'' +
                ", city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", gender='" + gender + '\'' +
                ", userName='" + userName + '\'' +
                ", openId='" + openId + '\'' +
                ", score=" + score +
                ", sessionKey='" + sessionKey + '\'' +
                ", userId=" + userId +
                '}';
    }
}
