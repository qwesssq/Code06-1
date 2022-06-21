package com.example.code06.SQL;

import cn.bmob.v3.BmobObject;

public class User extends BmobObject {
    private int userId;
    private String name;            //用户名
    private String password;        //密码
    private String hUri;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + getName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", hUri='" + gethUri() +
                ", id='" + getUserId() +
                '}';
    }

    public String gethUri() {
        return hUri;
    }

    public void sethUri(String hUri) {
        this.hUri = hUri;
    }

    public int  getUserId() {
        return userId;
    }

    public void setUserId(int  userId) {
        this.userId = userId;
    }
}
