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
import androidx.recyclerview.widget.DividerItemDecoration;

import com.demo.weather.databinding.FragmentDetailBinding;
import com.demo.weather.network.bean.WeatherBean;
import com.demo.weather.ui.model.WeatherDataViewModel;


public class DetailFragment extends Fragment {

    DetailAdapter detailAdapter;
    private WeatherDataViewModel model;
    FragmentDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        detailAdapter= new DetailAdapter();
        binding.list.setAdapter(detailAdapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = ViewModelProviders.of(this.getActivity()).get(WeatherDataViewModel.class);
        binding.list.addItemDecoration(new DividerItemDecoration(this.getActivity(),DividerItemDecoration.VERTICAL));
        model.getWeather().observe(getViewLifecycleOwner(),new Observer<WeatherBean>() {
            @Override
            public void onChanged(WeatherBean weatherBean) {
                detailAdapter.setData(weatherBean.getHourly().getData(),weatherBean.getTimezone(),weatherBean.getCurrently().getTime());
            }
        });
    }
}
