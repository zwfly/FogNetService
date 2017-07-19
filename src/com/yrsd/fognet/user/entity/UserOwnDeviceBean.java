package com.yrsd.fognet.user.entity;

import java.io.Serializable;

/**
 * Created by admin on 2017/6/15.
 */
public class UserOwnDeviceBean implements Serializable {

    public String deviceId;
    public Integer deviceType;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }
}
