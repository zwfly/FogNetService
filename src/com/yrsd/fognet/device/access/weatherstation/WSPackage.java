package com.yrsd.fognet.device.access.weatherstation;

import java.util.Arrays;

/**
 * Created by admin on 2017/6/15.
 */
public class WSPackage {
    public Integer length;
    public Integer cmd;
    public String deviceId;
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
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
