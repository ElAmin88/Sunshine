package com.example.amin.mobilecomputing;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Amin on 14/12/2017.
 */

public class WeatherCustomAdapter extends ArrayAdapter<Weather> {

    private static class ViewHolder {
        TextView date;
        TextView day;
        TextView night;
        TextView main;
        ImageView img;
    }


    public WeatherCustomAdapter(Context context,ArrayList<Weather> items) {
        super(context, R.layout.weather,items);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Weather wi = getItem(position);
        ViewHolder vh;
        if (view == null) {
            vh = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.weather, parent, false);
            vh.date = (TextView) view.findViewById(R.id.Date);
            vh.day = (TextView) view.findViewById(R.id.Day);
            vh.night = (TextView) view.findViewById(R.id.Night);
            vh.main = (TextView) view.findViewById(R.id.Main);
            vh.img = (ImageView)view.findViewById(R.id.img_weather);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.date.setText(wi.getName());
        vh.day.setText(Double.toString(wi.getDay()));
        vh.night.setText(Double.toString(wi.getNight()));
        vh.main.setText(wi.getMain());
        ChangeImage(wi.getMain(),vh.img);
        return view;
    }

    public static void ChangeImage(String weather ,ImageView img){
        if(weather.toLowerCase().contains("sun")||weather.contains("clear"))
            img.setBackgroundResource(R.drawable.sun);
        else if(weather.toLowerCase().contains("cloud"))
            img.setBackgroundResource(R.drawable.cloud);
        else if(weather.toLowerCase().contains("rain"))
            img.setBackgroundResource(R.drawable.rain);
        else if(weather.toLowerCase().contains("snow"))
            img.setBackgroundResource(R.drawable.snow);
        else
            img.setBackgroundResource(R.drawable.defalt);
    }

}
