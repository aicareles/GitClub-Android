package com.i502tech.gitclub.code.bean;

/**
 * description $desc$
 * created by jerry on 2019/4/11.
 */
public class User {

    /**
     * admin_status : 0
     * avatar :
     * city :
     * date : 2019-04-11 18:18:28
     * gender :
     * nick_name : 刘磊
     * open_id :
     * score : 0
     * session_key :
     * user_id : 4545
     */

    private int admin_status;
    private String avatar;
    private String city;
    private String date;
    private String gender;
    private String nick_name;
    private String open_id;
    private int score;
    private String session_key;
    private int user_id;

    public int getAdmin_status() {
        return admin_status;
    }

    public void setAdmin_status(int admin_status) {
        this.admin_status = admin_status;
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

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "admin_status=" + admin_status +
                ", avatar='" + avatar + '\'' +
                ", city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", gender='" + gender + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", open_id='" + open_id + '\'' +
                ", score=" + score +
                ", session_key='" + session_key + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
