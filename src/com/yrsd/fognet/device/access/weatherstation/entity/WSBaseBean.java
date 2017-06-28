package com.yrsd.fognet.device.access.weatherstation.entity;

import java.io.Serializable;

/**
 * Created by admin on 2017/6/24.
 */
public class WSBaseBean implements Serializable {

    public Double windSpeed;
    public String windDirection;
    public Double temperature;
    public Double humidity;
    public Integer PM2d5;
    public Integer PM10;

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
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
}
