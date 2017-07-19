package com.yrsd.fognet.user.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/6/15.
 */
public class UserInfoBean implements Serializable {

    public String userId;
    public String userName;
    public String loginName;
    public String loginPassword;

    public List<String> ownDeviceList;



    /////////////////////////////////

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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public List<String> getOwnDeviceList() {
        return ownDeviceList;
    }

    public void setOwnDeviceList(List<String> ownDeviceList) {
        this.ownDeviceList = ownDeviceList;
    }
}
