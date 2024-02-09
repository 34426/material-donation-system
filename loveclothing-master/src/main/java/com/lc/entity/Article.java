package com.lc.entity;

import com.lc.base.BaseEntity;

import java.util.List;

/**
 * 论坛文章
 */
public class Article extends BaseEntity {

    private String title;               //发布标题
    private String content;             //发布内容
    private String userId;              //发布人id
    private String userName;            //发布人姓名
    private String userImg;             //发布者头像
    private Integer kind;               //文章分类(0:爱心交流， 1:捐赠心得, 2:情感分享， 3:闲谈杂话， 4:活动宣传，5:其他 )
    private String picStr;              //图片，用逗号分隔
    private List<String> picList;       //图片列表
    private String coverImg;            //封面图片

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getPicStr() {
        return picStr;
    }

    public void setPicStr(String picStr) {
        this.picStr = picStr;
    }

    public List<String> getPicList() {
        return picList;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

}
