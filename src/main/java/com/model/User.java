package com.model;

import java.sql.Timestamp;

/**
 * Created by Andrew on 2016/3/9.
 */
public class User {

    private int id;

    private String password;

    private String name;

    private String selfIntroduction;

    private Boolean isSister;

    private Boolean isSingle;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }
    public Boolean getIsSister() {
        return isSister;
    }

    public void setIsSister(Boolean isSister) {
        this.isSister = isSister;
    }

    public Boolean getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(Boolean isSingle) {
        this.isSingle = isSingle;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
