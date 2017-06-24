package com.yrsd.fognet.device.access.weatherstation.entity;

import java.io.Serializable;

/**
 * Created by admin on 2017/6/24.
 */
public class WSBaseBean implements Serializable {

    private Float WindSpeed;
    private String WindDirection;
    private Integer Temperature;
    private Integer Humidity;
    private Integer PM2d5;
    private Integer PM10;


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
}
