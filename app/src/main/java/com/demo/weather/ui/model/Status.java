package com.demo.weather.ui.model;

public enum  Status {
    SUCESS("get weather sucess"),IDLE("IDLE"),GET_LOCATION_ERROR("get location error"),DARK_SKY_SERVICE_ERROR("get weather error"),LOCATING("LOCATING"),GETING_WEATHER("GETING_WEATHER");
    private String date;
    private Status(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
