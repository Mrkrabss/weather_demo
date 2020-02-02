package com.demo.weather;

import com.demo.weather.ui.fragment.WeatherBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DarkSkyService {
    @GET("forecast/{token}/{latitude},{longitude}")
    Call<WeatherBean> getWeather(@Path("token") String token, @Path("latitude") double latitude, @Path("longitude") double longitude);
}

