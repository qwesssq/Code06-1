package com.example.code06.Home_Recycleview;

import android.net.Uri;

import com.example.code06.Person_RecycleView.MyCollection;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobObject;
public class Mycomment{
    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private sdata data;

    public sdata getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(sdata data) {
        this.data = data;
    }
    public class sdata{
        @SerializedName("total")
        private String total;
        @SerializedName("records")
        private List<pcomment> records;

        public void setRecords(List<pcomment> records) {
            this.records = records;
        }

        public List<pcomment> getRecords() {
            return records;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getTotal() {
            return total;
        }

        public class pcomment{
            @SerializedName("userName")
            private String username;
            @SerializedName("content")
            private String content;
            @SerializedName("id")
            private String id;
            @SerializedName("shareId")
            private String shareId;

            public String getContent() {
                return content;
            }

            public String getUsername() {
                return username;
            }

            public String getId() {
                return id;
            }

            public String getShareId() {
                return shareId;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setShareId(String shareId) {
                this.shareId = shareId;
            }
        }
    }

}
//public class Mycomment extends BmobObject implements Serializable {
//
//    public int shareId;
//    public int userId;
//    public String name;
//    public String CommentContent;
//    public String huri;
//    public Mycomment(int shareId,
//            int userId,
//            String name,
//            String CommentContent,
//            String huri){
//        this.shareId = shareId;
//        this.userId = userId;
//        this.name = name;
//        this.CommentContent = CommentContent;
//        this.huri = huri;
//    }
//
//}
