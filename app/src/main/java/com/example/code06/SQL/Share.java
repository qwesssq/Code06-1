package com.example.code06.SQL;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Share implements Serializable {
    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private data data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Share.data getData() {
        return data;
    }

    public void setData(Share.data data) {
        this.data = data;
    }

    public class data{
        @SerializedName("total")
        private String total;
        @SerializedName("records")
        private List<record> record;

        public class record{
            @SerializedName("id")
            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            @SerializedName("imageUrlList")
            private String[] imgurl;
            @SerializedName("username")
            private String username;
            @SerializedName("title")
            private String title;
            @SerializedName("likeNum")
            private String likenum;
            //点赞数
            @SerializedName("content")
            private String content;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getLikenum() {
                return likenum;
            }

            public void setLikenum(String likenum) {
                this.likenum = likenum;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            //
            public String[] getImgurl() {
                return imgurl;
            }

            public void setImgurl(String[] imgurl) {
                this.imgurl = imgurl;
            }
        }

        public List<record> getRecord() {
            return record;
        }

        public void setRecord(List<record> record) {
            this.record = record;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }
}
