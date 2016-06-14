package com.model;

import java.sql.Timestamp;

/**
 * Created by Andrew on 2016/3/9.
 */
public class User implements BModel{

    private int id;

    private String password;

    private String username;

    private String introduction;

    private String img_src;

    private String is_sister;

    private String is_single;

    private String mobile;

    private Timestamp loginTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }
    public String getIs_sister() {
        return is_sister;
    }

    public void setIs_sister(String is_sister) {
        this.is_sister = is_sister;
    }

    public String getIs_single() {
        return is_single;
    }

    public void setIs_single(String is_single) {
        this.is_single = is_single;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }
}
