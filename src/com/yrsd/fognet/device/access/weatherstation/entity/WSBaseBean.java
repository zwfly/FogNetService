package com.yrsd.fognet.device.access.weatherstation.entity;

import java.io.Serializable;

/**
 * Created by admin on 2017/6/24.
 */
public class WSBaseBean implements Serializable {

    private Float windSpeed;
    private String windDirection;
    private Integer temperature;
    private Integer humidity;
    private Integer PM2d5;
    private Integer PM10;


    public Float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
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
