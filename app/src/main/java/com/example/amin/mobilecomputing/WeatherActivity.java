package com.example.amin.mobilecomputing;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    ListView lst_view;
    Button btn_Refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        final DatabaseOperations DB = new DatabaseOperations(WeatherActivity.this);



        if(!MainActivity.WithinWeek(2)){
            new Thread() {
                public void run() {
                    Weather.UpdateWeather(DB);
                    MainActivity.loginPrefsEditor.putInt("UpdateDay",MainActivity.Day);
                    MainActivity.loginPrefsEditor.apply();
                    MainActivity.loginPrefsEditor.commit();
                }
            }.start();
        }

        lst_view =(ListView)findViewById(R.id.lst_view);
        final ArrayList<Weather> week = Weather.getCurrentWeather(DB);
        WeatherCustomAdapter adapter = new WeatherCustomAdapter(WeatherActivity.this,week);
        lst_view.setAdapter(adapter);

        btn_Refresh=(Button)findViewById(R.id.btn_Refresh);
        btn_Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        Weather.UpdateWeather(DB);

                    }
                }.start();
                ArrayList<Weather> week = Weather.getCurrentWeather(DB);
                WeatherCustomAdapter adapter = new WeatherCustomAdapter(WeatherActivity.this,week);
                lst_view.setAdapter(adapter);
            }
        });


        lst_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(WeatherActivity.this,WeatherDetailedActivity.class);
                i.putExtra("weather",week.get(position));
                startActivity(i);

            }
        });
    }

}
