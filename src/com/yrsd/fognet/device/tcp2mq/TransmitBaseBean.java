package com.yrsd.fognet.device.tcp2mq;

import java.io.Serializable;

public class TransmitBaseBean implements Serializable {

    private String fromDeviceType;
    private String toId;
    private String toDeviceType;
    private String frameId;

    private Object obj;


    public String getFromDeviceType() {
        return fromDeviceType;
    }

    public void setFromDeviceType(String fromDeviceType) {
        this.fromDeviceType = fromDeviceType;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getToDeviceType() {
        return toDeviceType;
    }

    public void setToDeviceType(String toDeviceType) {
        this.toDeviceType = toDeviceType;
    }

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
