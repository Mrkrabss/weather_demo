package com.demo.weather.network.bean;

public class WeatherBean {
    public CurrentlyBean getCurrently() {
        return currently;
    }

    private CurrentlyBean currently;

    public void setCurrently(CurrentlyBean currently) {
        this.currently = currently;
    }

    public HourlyBean getHourly() {
        return hourly;
    }

    public void setHourly(HourlyBean hourly) {
        this.hourly = hourly;
    }

    public DailyBean getDaily() {
        return daily;
    }

    public void setDaily(DailyBean daily) {
        this.daily = daily;
    }

    private HourlyBean hourly;
    private DailyBean daily;


    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    private String timezone;
    public WeatherBean() {
    }

}
