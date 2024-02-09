package com.lc.entity;

import com.lc.base.BaseEntity;

/**
 * 留言
 */
public class Message extends BaseEntity {

    private String userId;              //留言者id
    private String userImg;             //留言者头像
    private String userName;            //留言者姓名
    private String messageContent;      //留言内容
    private String articleId;           //留言文章id
    private String title;               //留言文章标题

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
