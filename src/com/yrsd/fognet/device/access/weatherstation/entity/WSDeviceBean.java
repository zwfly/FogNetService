package com.yrsd.fognet.device.access.weatherstation.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 气象站设备管理
 * Created by admin on 2017/6/15.
 */
public class WSDeviceBean extends WSBaseBean implements Serializable {

    public String deviceId;
    public String deviceType;
    public String deviceName;
    public String deviceAddr;

    public Float longitude;   //经度
    public Float latitude;    //纬度
    public Integer altitude;    //海拔

    public Integer temperatureAlarmUpper;
    public Integer temperatureAlarmLower;
    public Boolean temperatureAlarmEnable;

    public Integer humidityAlarmUpper;
    public Integer humidityAlarmLower;
    public Boolean humidityAlarmEnable;

    public Integer PM2d5AlarmUpper;
    public Integer PM2d5AlarmLower;
    public Boolean PM2d5AlarmEnable;

    public String phoneNumber;

    public Date lastOnLineDate;
    public Date lastOffLineDate;

    public Date deviceCreateDate;


    @Override
    public String toString() {
        return "WSDeviceBean{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceAddr='" + deviceAddr + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", altitude=" + altitude +
                ", temperatureAlarmUpper=" + temperatureAlarmUpper +
                ", temperatureAlarmLower=" + temperatureAlarmLower +
                ", temperatureAlarmEnable=" + temperatureAlarmEnable +
                ", humidityAlarmUpper=" + humidityAlarmUpper +
                ", humidityAlarmLower=" + humidityAlarmLower +
                ", humidityAlarmEnable=" + humidityAlarmEnable +
                ", PM2d5AlarmUpper=" + PM2d5AlarmUpper +
                ", PM2d5AlarmLower=" + PM2d5AlarmLower +
                ", PM2d5AlarmEnable=" + PM2d5AlarmEnable +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", lastOnLineDate=" + lastOnLineDate +
                ", lastOffLineDate=" + lastOffLineDate +
                ", deviceCreateDate=" + deviceCreateDate +
                '}';
    }

    ///////////////
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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceAddr() {
        return deviceAddr;
    }

    public void setDeviceAddr(String deviceAddr) {
        this.deviceAddr = deviceAddr;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getTemperatureAlarmUpper() {
        return temperatureAlarmUpper;
    }

    public void setTemperatureAlarmUpper(Integer temperatureAlarmUpper) {
        this.temperatureAlarmUpper = temperatureAlarmUpper;
    }

    public Integer getTemperatureAlarmLower() {
        return temperatureAlarmLower;
    }

    public void setTemperatureAlarmLower(Integer temperatureAlarmLower) {
        this.temperatureAlarmLower = temperatureAlarmLower;
    }

    public Boolean getTemperatureAlarmEnable() {
        return temperatureAlarmEnable;
    }

    public void setTemperatureAlarmEnable(Boolean temperatureAlarmEnable) {
        this.temperatureAlarmEnable = temperatureAlarmEnable;
    }

    public Integer getHumidityAlarmUpper() {
        return humidityAlarmUpper;
    }

    public void setHumidityAlarmUpper(Integer humidityAlarmUpper) {
        this.humidityAlarmUpper = humidityAlarmUpper;
    }

    public Integer getHumidityAlarmLower() {
        return humidityAlarmLower;
    }

    public void setHumidityAlarmLower(Integer humidityAlarmLower) {
        this.humidityAlarmLower = humidityAlarmLower;
    }

    public Boolean getHumidityAlarmEnable() {
        return humidityAlarmEnable;
    }

    public void setHumidityAlarmEnable(Boolean humidityAlarmEnable) {
        this.humidityAlarmEnable = humidityAlarmEnable;
    }

    public Integer getPM2d5AlarmUpper() {
        return PM2d5AlarmUpper;
    }

    public void setPM2d5AlarmUpper(Integer PM2d5AlarmUpper) {
        this.PM2d5AlarmUpper = PM2d5AlarmUpper;
    }

    public Integer getPM2d5AlarmLower() {
        return PM2d5AlarmLower;
    }

    public void setPM2d5AlarmLower(Integer PM2d5AlarmLower) {
        this.PM2d5AlarmLower = PM2d5AlarmLower;
    }

    public Boolean getPM2d5AlarmEnable() {
        return PM2d5AlarmEnable;
    }

    public void setPM2d5AlarmEnable(Boolean PM2d5AlarmEnable) {
        this.PM2d5AlarmEnable = PM2d5AlarmEnable;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getLastOnLineDate() {
        return lastOnLineDate;
    }

    public void setLastOnLineDate(Date lastOnLineDate) {
        this.lastOnLineDate = lastOnLineDate;
    }

    public Date getLastOffLineDate() {
        return lastOffLineDate;
    }

    public void setLastOffLineDate(Date lastOffLineDate) {
        this.lastOffLineDate = lastOffLineDate;
    }

    public Date getDeviceCreateDate() {
        return deviceCreateDate;
    }

    public void setDeviceCreateDate(Date deviceCreateDate) {
        this.deviceCreateDate = deviceCreateDate;
    }
}
