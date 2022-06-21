package com.example.code06.Message_ListView;



import java.io.Serializable;

public class MyMessage implements Serializable {

    private String imgUri;
    private String title;
    private String detail;
    private String time;

    public MyMessage(String imgUris, String title, String detail, String time) {
         this.setImgUri(imgUris);
         this.setTitle(title);
         this.setDetail(detail);
         this.setTime(time);
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}