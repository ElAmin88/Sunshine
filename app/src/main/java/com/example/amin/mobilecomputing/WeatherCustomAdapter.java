package com.example.amin.mobilecomputing;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    }


    public WeatherCustomAdapter(Context context,ArrayList<Weather> items) {
        super(context, R.layout.weather);
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
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.date.setText(wi.getId());
        vh.day.setText(Double.toString(wi.getDay()));
        vh.night.setText(Double.toString(wi.getNight()));
        vh.main.setText(wi.getId());
        return view;
    }

}
