package com.demo.weather.ui.fragment;

public class DailyDataBean {
    private double temperatureLow;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private String icon;
    public double getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(double temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    public double getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(double temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    private double temperatureHigh;

    public DailyDataBean() {
    }
}
