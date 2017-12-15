package com.example.amin.mobilecomputing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherDetailedActivity extends AppCompatActivity {
    TextView lbl_Name,lbl_Day,lbl_Night,lbl_Main,lbl_Humidity,lbl_Pressure,lbl_Description,lbl_Country,lbl_City;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detailed);
        Weather w =(Weather)getIntent().getSerializableExtra("weather");
        lbl_Name =(TextView)findViewById(R.id.lbl_Name);
        lbl_Name.setText(w.getName());

        lbl_Day =(TextView)findViewById(R.id.lbl_Day);
        lbl_Day.setText(w.getDay()+"");

        lbl_Night =(TextView)findViewById(R.id.lbl_Night);
        lbl_Night.setText(w.getNight()+"");

        lbl_Main =(TextView)findViewById(R.id.lbl_Main);
        lbl_Main.setText(w.getMain());

        lbl_Humidity =(TextView)findViewById(R.id.lbl_Humidity);
        lbl_Humidity.setText(w.getHumidity()+"");

        lbl_Pressure =(TextView)findViewById(R.id.lbl_Pressure);
        lbl_Pressure.setText(w.getPressure()+"");

        lbl_Description =(TextView)findViewById(R.id.lbl_Description);
        lbl_Description.setText(w.getDescription());

        lbl_Country =(TextView)findViewById(R.id.lbl_Country);
        lbl_Country.setText(w.getCountry());

        lbl_City =(TextView)findViewById(R.id.lbl_City);
        lbl_City.setText(w.getCity());
    }
}
