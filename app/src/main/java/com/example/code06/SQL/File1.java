package com.example.code06.SQL;

import com.google.gson.annotations.SerializedName;

public class File1 {

    private DataDTO data;
    @SerializedName("code")
    private String code;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    @SerializedName("data")
    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}
