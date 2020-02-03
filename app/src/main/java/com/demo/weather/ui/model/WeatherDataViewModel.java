package com.demo.weather.ui.model;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.demo.weather.network.DarkSkyService;
import com.demo.weather.network.bean.WeatherBean;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherDataViewModel extends AndroidViewModel {
    private MutableLiveData<WeatherBean> weather = new MutableLiveData<>();
    private MutableLiveData<Status> status = new MutableLiveData<>();
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest mLocationRequest;

    public WeatherDataViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<WeatherBean> getWeather() {
        return weather;
    }

    public LiveData<Status> getStatus() {
        return status;
    }



    @SuppressLint("MissingPermission")
    public void requestWeather(){
        // we just use Android API to get location. It probably cause getting location failed.
        status.setValue(Status.LOCATING);
        LocationManager locationManager = (LocationManager)getApplication().getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getAllProviders();
        Location location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        setLocation(location);
    }


    private void setLocation(Location location)
    {
        if (location != null) {
            status.setValue(Status.GETING_WEATHER);
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.darksky.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            DarkSkyService darkSkyService = retrofit.create(DarkSkyService.class);
            darkSkyService.getWeather("e8d04daf016467183ff4c0e5c6a8fd78", location.getLatitude(), location.getLongitude()).enqueue(new Callback<WeatherBean>() {
                @Override
                public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                    Log.d("GH", response.toString());
                    if (response.code() == 200) {
                        weather.setValue(response.body());
                        status.setValue(Status.SUCESS);
                        status.setValue(Status.IDLE);
                    } else {
                        status.setValue(Status.DARK_SKY_SERVICE_ERROR);
                    }
                }

                @Override
                public void onFailure(Call<WeatherBean> call, Throwable t) {
                    t.printStackTrace();
                    status.setValue(Status.DARK_SKY_SERVICE_ERROR);
                }
            });
        }else
        {
            status.setValue(Status.GET_LOCATION_ERROR);
        }
    }
}
