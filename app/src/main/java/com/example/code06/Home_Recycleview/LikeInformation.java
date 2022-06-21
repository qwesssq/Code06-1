package com.example.code06.Home_Recycleview;

import cn.bmob.v3.BmobObject;

public class LikeInformation extends BmobObject {
    private int userId;
    private int itemId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
