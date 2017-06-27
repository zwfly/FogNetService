package com.yrsd.fognet.device.access.weatherstation.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by admin on 2017/6/15.
 */
public class WSRecordBean extends WSBaseBean implements Serializable {

    private String deviceId;
    private String deviceType;

    private Date recordCreateDate;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Date getRecordCreateDate() {
        return recordCreateDate;
    }

    public void setRecordCreateDate(Date recordCreateDate) {
        this.recordCreateDate = recordCreateDate;
    }
}
