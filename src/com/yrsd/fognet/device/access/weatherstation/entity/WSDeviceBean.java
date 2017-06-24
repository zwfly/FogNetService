package com.yrsd.fognet.device.access.weatherstation.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 气象站设备管理
 * Created by admin on 2017/6/15.
 */
public class WSDeviceBean extends WSBaseBean implements Serializable {

    private String DeviceId;
    private String DeviceType;
    private String DeviceName;
    private String DeviceAddr;

    private Float Longitude;   //经度
    private Float latitude;    //纬度
    private Integer altitude;    //海拔

    private Integer TemperatureAlarmUpper;
    private Integer TemperatureAlarmLower;
    private Boolean TemperatureAlarmEnable;

    private Integer HumidityAlarmUpper;
    private Integer HumidityAlarmLower;
    private Boolean HumidityAlarmEnable;

    private Integer PM2d5AlarmUpper;
    private Integer PM2d5AlarmLower;
    private Boolean PM2d5AlarmEnable;

    private String phoneNumber;

    private Date LastOnLineDate;
    private Date LastOffLineDate;

    private Date DeviceCreateDate;


    @Override
    public String toString() {
        return "WSDeviceBean{" +
                "DeviceId='" + DeviceId + '\'' +
                ", DeviceType='" + DeviceType + '\'' +
                ", DeviceName='" + DeviceName + '\'' +
                ", DeviceAddr='" + DeviceAddr + '\'' +
                ", TemperatureAlarmUpper=" + TemperatureAlarmUpper +
                ", TemperatureAlarmLower=" + TemperatureAlarmLower +
                ", TemperatureAlarmEnable=" + TemperatureAlarmEnable +
                ", HumidityAlarmUpper=" + HumidityAlarmUpper +
                ", HumidityAlarmLower=" + HumidityAlarmLower +
                ", HumidityAlarmEnable=" + HumidityAlarmEnable +
                ", PM2d5AlarmUpper=" + PM2d5AlarmUpper +
                ", PM2d5AlarmLower=" + PM2d5AlarmLower +
                ", PM2d5AlarmEnable=" + PM2d5AlarmEnable +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", LastOnLineDate=" + LastOnLineDate +
                ", LastOffLineDate=" + LastOffLineDate +
                ", DeviceCreateDate=" + DeviceCreateDate +
                '}';
    }

    public Float getLongitude() {
        return Longitude;
    }

    public void setLongitude(Float longitude) {
        Longitude = longitude;
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

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public String getDeviceAddr() {
        return DeviceAddr;
    }

    public void setDeviceAddr(String deviceAddr) {
        DeviceAddr = deviceAddr;
    }

    public Integer getTemperatureAlarmUpper() {
        return TemperatureAlarmUpper;
    }

    public void setTemperatureAlarmUpper(Integer temperatureAlarmUpper) {
        TemperatureAlarmUpper = temperatureAlarmUpper;
    }

    public Integer getTemperatureAlarmLower() {
        return TemperatureAlarmLower;
    }

    public void setTemperatureAlarmLower(Integer temperatureAlarmLower) {
        TemperatureAlarmLower = temperatureAlarmLower;
    }

    public Boolean getTemperatureAlarmEnable() {
        return TemperatureAlarmEnable;
    }

    public void setTemperatureAlarmEnable(Boolean temperatureAlarmEnable) {
        TemperatureAlarmEnable = temperatureAlarmEnable;
    }

    public Integer getHumidityAlarmUpper() {
        return HumidityAlarmUpper;
    }

    public void setHumidityAlarmUpper(Integer humidityAlarmUpper) {
        HumidityAlarmUpper = humidityAlarmUpper;
    }

    public Integer getHumidityAlarmLower() {
        return HumidityAlarmLower;
    }

    public void setHumidityAlarmLower(Integer humidityAlarmLower) {
        HumidityAlarmLower = humidityAlarmLower;
    }

    public Boolean getHumidityAlarmEnable() {
        return HumidityAlarmEnable;
    }

    public void setHumidityAlarmEnable(Boolean humidityAlarmEnable) {
        HumidityAlarmEnable = humidityAlarmEnable;
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
        return LastOnLineDate;
    }

    public void setLastOnLineDate(Date lastOnLineDate) {
        LastOnLineDate = lastOnLineDate;
    }

    public Date getLastOffLineDate() {
        return LastOffLineDate;
    }

    public void setLastOffLineDate(Date lastOffLineDate) {
        LastOffLineDate = lastOffLineDate;
    }

    public Date getDeviceCreateDate() {
        return DeviceCreateDate;
    }

    public void setDeviceCreateDate(Date deviceCreateDate) {
        DeviceCreateDate = deviceCreateDate;
    }
}
