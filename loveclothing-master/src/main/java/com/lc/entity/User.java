package com.lc.entity;

import com.lc.base.BaseEntity;

/**
 * 用户信息类
 */
public class User extends BaseEntity {

    private String userName;            //姓名
    private Integer userType;           //角色(0管理员，1用户)
    private String loginName;           //登录名(唯一登录账号)
    private String userPassword;        //密码
    private String lastLoginDate;       //上次登录日期
    private String userTel;             //手机号
    private String userImg;             //头像

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
