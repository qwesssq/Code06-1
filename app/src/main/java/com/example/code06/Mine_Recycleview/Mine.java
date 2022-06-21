package com.example.code06.Mine_Recycleview;

import android.net.Uri;

import java.io.Serializable;

public class Mine implements Serializable {
    private Uri hUri;
    private String Title;
    private String Name;
    private Uri shareUri;
    public Mine(Uri hUri,String Title,String Name,Uri shareUri){
        this.sethUri(hUri);
        this.setTitle(Title);
        this.setName(Name);
        this.setShareUri(shareUri);
    }

    public Uri gethUri() {
        return hUri;
    }

    public void sethUri(Uri hUri) {
        this.hUri = hUri;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Uri getShareUri() {
        return shareUri;
    }

    public void setShareUri(Uri shareUri) {
        this.shareUri = shareUri;
    }
}
