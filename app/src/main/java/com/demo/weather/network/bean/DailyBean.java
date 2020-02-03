package com.demo.weather.network.bean;

import java.util.List;

public class DailyBean {
    public List<DailyDataBean> getData() {
        return data;
    }

    public void setData(List<DailyDataBean> data) {
        this.data = data;
    }

    private List<DailyDataBean> data;
    public DailyBean()
    {

    }
}
