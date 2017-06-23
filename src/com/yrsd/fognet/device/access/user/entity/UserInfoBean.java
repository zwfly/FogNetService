package com.yrsd.fognet.device.access.user.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/6/15.
 */
public class UserInfoBean {

    private String UserId;
    private String UserName;
    private String LoginName;
    private String LoginPassword;

    private List<UserOwnDeviceBean> OwnDevicelist;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getLoginPassword() {
        return LoginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        LoginPassword = loginPassword;
    }

    public List<UserOwnDeviceBean> getOwnDevicelist() {
        return OwnDevicelist;
    }

    public void setOwnDevicelist(List<UserOwnDeviceBean> ownDevicelist) {
        OwnDevicelist = ownDevicelist;
    }
}
