package com.lc.entity;

import com.lc.base.BaseEntity;

/**
 * 心愿类
 */
public class Wish extends BaseEntity {

    private String userId;              //发布用户
    private String userName;            //发布用户姓名
    private String userImg;             //发布用户头像
    private String wishTitle;           //心愿标题
    private String wishContent;         //心愿内容

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getWishTitle() {
        return wishTitle;
    }

    public void setWishTitle(String wishTitle) {
        this.wishTitle = wishTitle;
    }

    public String getWishContent() {
        return wishContent;
    }

    public void setWishContent(String wishContent) {
        this.wishContent = wishContent;
    }

}
