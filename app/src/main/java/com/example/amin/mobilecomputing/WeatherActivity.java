package com.example.amin.mobilecomputing;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    ListView lst_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        final DatabaseOperations DB = new DatabaseOperations(WeatherActivity.this);
        new Thread() {
            public void run() {
                Weather.UpdateWeather(DB);
            }
        }.start();

        lst_view =(ListView)findViewById(R.id.lst_view);
        ArrayList<Weather> week = Weather.getCurrentWeather(DB);
        WeatherCustomAdapter adapter = new WeatherCustomAdapter(WeatherActivity.this,week);
        lst_view.setAdapter(adapter);
    }
}
