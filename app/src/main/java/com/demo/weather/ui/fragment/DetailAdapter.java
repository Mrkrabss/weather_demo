package com.demo.weather.ui.fragment;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.weather.R;
import com.demo.weather.databinding.DetailItemBinding;
import com.demo.weather.network.bean.HourlyDataBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder>{

    List<HourlyDataBean> mDetailList;
    String timeZone;
    long time;

    public void setData(List<HourlyDataBean> mDetailList,String timeZone,long time)
    {
        this.timeZone=timeZone;
        this.time=time;
        this.mDetailList=mDetailList;
        notifyDataSetChanged();
    }

    @Override
    @NonNull
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DetailItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.detail_item,
                        parent, false);
        return new DetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        holder.binding.temperature.setText("Temperature:"+mDetailList.get(position).getTemperature()+" ℉");
        SimpleDateFormat formatter = new SimpleDateFormat ("MM-dd HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone(timeZone));
        holder.binding.datetime.setText("Time:"+formatter.format(new Date(mDetailList.get(position).getTime()*1000)));
        holder.binding.icon.setText("Icon:"+mDetailList.get(position).getIcon());
        holder.binding.background.setBackgroundColor(mDetailList.get(position).getTime()>time? Color.TRANSPARENT :Color.GRAY);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mDetailList == null ? 0 : mDetailList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class DetailViewHolder extends RecyclerView.ViewHolder {

        final DetailItemBinding binding;

        public DetailViewHolder(DetailItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
