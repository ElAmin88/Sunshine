package com.example.amin.mobilecomputing;

import android.database.Cursor;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Amin on 13/12/2017.
 */

public class Weather {
    private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/forecast/daily?q=Cairo%2CEG&appid=c0c4a4b4047b97ebc5948ac9c48c0559&units=metric&cnt=7";

    private double day,night,pressure,humidity;
    private String id,description,city,country,main;

    public Weather(){
        city="Cairo";
        country="Egypt";

    }

    public void setId(String s){
        id=s;
    }
    public String getId(){
        return id;
    }
    public void setDegree(double d,double n)
    {
        day=d;
        night=n;
    }
    public double getDay()
    {
        return day;
    }

    public double getNight()
    {
        return night;
    }

    public void setHumidity(double h)
    {
        humidity=h;
    }

    public double getHumidity()
    {
        return humidity;
    }

    public void setPressure(double p)
    {
        pressure=p;
    }

    public double getPressure()
    {
        return pressure;
    }

    public void setDescription(String d,String m)
    {
        description=d;
        main=m;
    }

    public String getDescription()
    {
        return description;
    }

    public String getMain()
    {
        return main;
    }

    public String getCity(){
        return city;
    }

    public String getCountry(){
        return country;
    }


    private static JSONObject getWeather(){
        try {
            URL url = new URL(OPEN_WEATHER_MAP_API);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(4096);
            String tmp;
            while((tmp=reader.readLine())!= null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            if(data.getInt("cod") != 200){
                return null;
            }
            return data;
        }catch(Exception e){
            Log.e("Nugget>JSON", e.getMessage());
            return null;
        }
    }

    private static Weather ParseJSON(JSONObject json,String id)
    {
        Weather w=new Weather();
        try {
            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            w.setDescription(details.getString("description"), details.getString("main"));
            JSONObject degree = json.getJSONObject("temp");
            w.setDegree(degree.getDouble("day"), degree.getDouble("night"));
            w.setHumidity(json.getDouble("humidity"));
            w.setPressure(json.getDouble("pressure"));
            w.setId(id);
        } catch (Exception e) {
            Log.e("Nugget>Weather", "One or more fields missing in JSON data");
        }

        return w;
    }

    private static void DeleteWeather(DatabaseOperations DB){
        Cursor cr = DB.getAll(DB);
        if(cr.getCount()!=0){
            cr.moveToFirst();
            do {
                DB.DeleteItemById(DB,cr.getString(0));
            }while(cr.moveToNext());
        }


    }

    public static void UpdateWeather(DatabaseOperations DB){
        DeleteWeather(DB);
        ArrayList<Weather> week = new ArrayList<Weather>();
        JSONObject all = getWeather();
        for(int i=0;i<7;i++){
            try{
                week.add(ParseJSON(all.getJSONArray("list").getJSONObject(i),Integer.toString(i)));
            }catch(Exception e){}
        }
        for(int i =0;i<week.size();i++)
        {
            DB.Add(DB,week.get(i));
        }
    }

    public static ArrayList<Weather> getCurrentWeather(DatabaseOperations DB){
        ArrayList<Weather> current = new ArrayList<Weather>();
        Cursor cr = DB.getAll(DB);
        if(cr.getCount()==0){
            return null;
        }
        cr.moveToFirst();
        do {
            Weather w= new Weather();
            w.setId(cr.getString(0));
            w.setDescription(cr.getString(4),cr.getString(3));
            w.setDegree(Double.parseDouble(cr.getString(5)),Double.parseDouble(cr.getString(6)));
            w.setHumidity(Double.parseDouble(cr.getString(8)));
            w.setPressure(Double.parseDouble(cr.getString(7)));
            current.add(w);
        }while(cr.moveToNext());

        return current;
    }
}

