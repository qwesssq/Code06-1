package com.example.code06.SQL;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Login {
    private String username1;

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public JsonArrayBean getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JsonArrayBean jsonArray) {
        this.jsonArray = jsonArray;
    }


    public class JsonArrayBean{
        public String getID() {
            return ID;
        }

        public void setID(String id1) {
            this.ID = id1;
        }
        public String getNAME() {
            return NAME;
        }

        public void setNAME(String id1) {
            this.NAME = id1;
        }


        @SerializedName("id")
        private String ID;
        @SerializedName("username")
        private String NAME;
    }
    @SerializedName("msg")
    private String id1;

    @SerializedName("data")
    private JsonArrayBean jsonArray;


}
