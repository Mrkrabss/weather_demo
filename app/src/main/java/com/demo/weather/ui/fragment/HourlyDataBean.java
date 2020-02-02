package com.demo.weather.ui.fragment;

public class HourlyDataBean {
    private long time;

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    private String currentTime;



    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;

    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }



    private String icon;
    private double temperature;
    public HourlyDataBean() {
    }
}
