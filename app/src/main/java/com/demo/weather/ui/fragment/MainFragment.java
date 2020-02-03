package com.demo.weather.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.demo.weather.R;
import com.demo.weather.databinding.FragmentMianBinding;
import com.demo.weather.network.bean.WeatherBean;
import com.demo.weather.ui.model.Status;
import com.demo.weather.ui.model.WeatherDataViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainFragment extends Fragment {

    private WeatherDataViewModel model;

    FragmentMianBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMianBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = ViewModelProviders.of(this.getActivity()).get(WeatherDataViewModel.class);
        model.getStatus().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if(status==Status.LOCATING||status==Status.GETING_WEATHER)
                {
                    binding.swipe.setRefreshing(true);
                }else
                {
                    if(status!=Status.IDLE) {
                        Snackbar.make(view, status.getDate(), 3000).show();
                    }
                    binding.swipe.setRefreshing(false);
                }
            }
        });
        model.getWeather().observe(this,new Observer<WeatherBean>() {
            @Override
            public void onChanged(WeatherBean weatherBean) {
                SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
                TimeZone timeZone = TimeZone.getTimeZone(weatherBean.getTimezone());
                formatter.setTimeZone(timeZone);
                binding.icon.setText("Icon:"+ weatherBean.getDaily().getData().get(0).getIcon());
                binding.datetime.setText("Time:"+formatter.format(new Date(weatherBean.getCurrently().getTime()*1000)));
                binding.highTemperature.setText("HighTemperature:"+weatherBean.getDaily().getData().get(0).getTemperatureHigh()+" ℉");
                binding.lowTemperature.setText("LowTemperature:"+weatherBean.getDaily().getData().get(0).getTemperatureLow()+" ℉");
                getActivity().setTitle(weatherBean.getTimezone());
            }
        });
        binding.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.to_detail);
            }
        });
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                model.requestWeather();
            }
        });
    }


}
