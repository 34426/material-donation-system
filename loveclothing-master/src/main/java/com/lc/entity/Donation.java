package com.lc.entity;

import com.lc.base.BaseEntity;

/**
 * 捐赠记录类
 */
public class Donation extends BaseEntity {

    private String userId;              //捐赠人id
    private String userName;            //捐赠用户姓名
    private String userTel;             //捐赠用户电话
    private String userImg;             //捐赠用户头像
    private String address;             //联系地址
    private Integer number;             //数量
    private Integer kind;               //类别(0上衣，1裤子，2袜子，3手套，4帽子，5其他)
    private String kindName;            //类别名称
    private Integer verify;            //上门收取状态标志(0:等待上门，1上门回收通过，2上门回收检查不通过)

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
    }

}
