package com.demo.weather.ui.fragment;

import java.util.List;

public class HourlyBean {
    public List<HourlyDataBean> getData() {
        return data;
    }

    public void setData(List<HourlyDataBean> data) {
        this.data = data;
    }

    private List<HourlyDataBean> data;
    public HourlyBean() {
    }
}
