package com.yrsd.fognet.device.access.weatherstation.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by admin on 2017/6/15.
 */
public class WSRecordBean implements Serializable {

    private String DeviceId;
    private Float WindSpeed;
    private String WindDirection;
    private Integer Temperature;
    private Integer Humidity;
    private Integer PM2d5;
    private Integer PM10;
    private Date CreateDate;

//    private String IPAddr;
//    private String Port;


    @Override
    public String toString() {
        return "WSRecordBean{" +
                "DeviceId='" + DeviceId + '\'' +
                ", WindSpeed=" + WindSpeed +
                ", WindDirection='" + WindDirection + '\'' +
                ", Temperature=" + Temperature +
                ", Humidity=" + Humidity +
                ", PM2d5=" + PM2d5 +
                ", PM10=" + PM10 +
                ", CreateDate=" + CreateDate +
                '}';
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public Float getWindSpeed() {
        return WindSpeed;
    }

    public void setWindSpeed(Float windSpeed) {
        WindSpeed = windSpeed;
    }

    public String getWindDirection() {
        return WindDirection;
    }

    public void setWindDirection(String windDirection) {
        WindDirection = windDirection;
    }

    public Integer getTemperature() {
        return Temperature;
    }

    public void setTemperature(Integer temperature) {
        Temperature = temperature;
    }

    public Integer getHumidity() {
        return Humidity;
    }

    public void setHumidity(Integer humidity) {
        Humidity = humidity;
    }

    public Integer getPM2d5() {
        return PM2d5;
    }

    public void setPM2d5(Integer PM2d5) {
        this.PM2d5 = PM2d5;
    }

    public Integer getPM10() {
        return PM10;
    }

    public void setPM10(Integer PM10) {
        this.PM10 = PM10;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }


}
