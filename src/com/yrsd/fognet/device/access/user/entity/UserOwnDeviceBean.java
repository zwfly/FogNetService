package com.yrsd.fognet.device.access.user.entity;

import java.io.Serializable;

/**
 * Created by admin on 2017/6/15.
 */
public class UserOwnDeviceBean implements Serializable {

    private String DeviceId;
    private Integer DeviceType;

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public Integer getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(Integer deviceType) {
        DeviceType = deviceType;
    }
}
