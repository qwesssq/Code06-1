package com.example.code06.Person_RecycleView;



import android.net.Uri;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class MyCollection  extends BmobObject {
    private int userId;
    private int sharerId;
    private String sharerName;
    private String hUri;
    private int itemId;
    private String imgUri;
    private String title;
    private String detail;
    private int imgheight;

    public MyCollection(int userId, int sharerId, String sharerName, String hUri, int itemId, String imgUri, String title, String detail, int imgheight) {
        this.userId = userId;
        this.sharerId = sharerId;
        this.sharerName = sharerName;
        this.hUri = hUri;
        this.itemId = itemId;
        this.imgUri = imgUri;
        this.title = title;
        this.detail = detail;
        this.imgheight = imgheight;
    }

    public MyCollection() {
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getImgheight() {
        return imgheight;
    }

    public void setImgheight(int imgheight) {
        this.imgheight = imgheight;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSharerName() {
        return sharerName;
    }

    public void setSharerName(String sharerName) {
        this.sharerName = sharerName;
    }

    public String gethUri() {
        return hUri;
    }

    public void sethUri(String hUri) {
        this.hUri = hUri;
    }

    public int getSharerId() {
        return sharerId;
    }

    public void setSharerId(int sharerId) {
        this.sharerId = sharerId;
    }
}