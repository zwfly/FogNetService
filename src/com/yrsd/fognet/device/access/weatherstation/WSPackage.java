package com.yrsd.fognet.device.access.weatherstation;

import java.util.Arrays;

/**
 * Created by admin on 2017/6/15.
 */
public class WSPackage {
    public Integer length;
    public Integer cmd;
    public String deviceId;
    public String toWho;
    public String deviceType;
    public byte[] data;

    @Override
    public String toString() {
        return "WSPackage{" +
                "length=" + length +
                ", cmd=" + cmd +
                ", deviceId='" + deviceId + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    public String getToWho() {
        return toWho;
    }

    public void setToWho(String toWho) {
        this.toWho = toWho;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getCmd() {
        return cmd;
    }

    public void setCmd(Integer cmd) {
        this.cmd = cmd;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
