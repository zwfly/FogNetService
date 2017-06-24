package com.yrsd.fognet.device.access.weatherstation.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by admin on 2017/6/15.
 */
public class WSRecordBean extends WSBaseBean implements Serializable {

    private String DeviceId;
    private String DeviceType;

    private Date RecordCreateDate;


    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }


    public Date getRecordCreateDate() {
        return RecordCreateDate;
    }

    public void setRecordCreateDate(Date recordCreateDate) {
        RecordCreateDate = recordCreateDate;
    }
}
