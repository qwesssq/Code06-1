package com.example.code06.Home_Recycleview;


import android.net.Uri;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Mydynamic extends BmobObject implements Serializable {
//    BmobObject 表示后端云的类
    private Integer sharerId;
    private String sharerName;
    private String hUri;
    private Integer itemId;
    private String Mytitle;
    private String Mydetail;
    private int imgheight;
    private String imgUri;
    private int NumberOfLikes;
    private BmobFile picture;



    public Integer getSharerId() {
        return sharerId;
    }

    public void setSharerId(Integer sharerId) {
        this.sharerId = sharerId;
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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getMytitle() {
        return Mytitle;
    }

    public void setMytitle(String mytitle) {
        Mytitle = mytitle;
    }

    public String getMydetail() {
        return Mydetail;
    }

    public void setMydetail(String mydetail) {
        Mydetail = mydetail;
    }

    public int getImgheight() {
        return imgheight;
    }

    public void setImgheight(int imgheight) {
        this.imgheight = imgheight;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public int getNumberOfLikes() {
        return NumberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        NumberOfLikes = numberOfLikes;
    }

    public BmobFile getPicture() {
        return picture;
    }

    public void setPicture(BmobFile picture) {
        this.picture = picture;
    }
}
