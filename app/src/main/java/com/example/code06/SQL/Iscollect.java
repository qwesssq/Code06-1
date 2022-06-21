package com.example.code06.SQL;

import com.google.gson.annotations.SerializedName;

public class Iscollect {
    @SerializedName("data")
    private cdata data;

    public cdata getData() {
        return data;
    }

    public void setData(cdata data) {
        this.data = data;
    }

    public class cdata{
        @SerializedName("hasCollect")
        private String hascollect;
        @SerializedName("collectId")
        private String collect;

        public void setCollect(String collect) {
            this.collect = collect;
        }

        public String getCollect() {
            return collect;
        }

        public void setHascollect(String hascollect) {
            this.hascollect = hascollect;
        }

        public String getHascollect() {
            return hascollect;
        }
    }
}
