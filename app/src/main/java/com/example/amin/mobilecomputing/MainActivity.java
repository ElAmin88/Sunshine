package com.example.amin.mobilecomputing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static String email = "testing@sunshine.com";
    public static String password = "tf71wqr";
    //public static String email = "";
    //public static String password = "";
    CheckBox Remember_me;
    Button btn_SignIn;
    EditText txt_Email,txt_Password;
    public static SharedPreferences loginPreferences;
    public static SharedPreferences.Editor loginPrefsEditor;
    boolean remembered;
    public static int Day,Month;
    private String PREF_NAME="RememberMe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_Email = (EditText)findViewById(R.id.Email);
        txt_Password = (EditText)findViewById(R.id.Password);
        btn_SignIn= (Button)findViewById(R.id.btn_Login);
        Remember_me = (CheckBox)findViewById(R.id.Remember);

        loginPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        Date d= new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        Month = cal.get(Calendar.MONTH);
        Day = cal.get(Calendar.DAY_OF_MONTH);
        remembered= loginPreferences.getBoolean("saveLogin", false);
        if(remembered){
            if(WithinWeek(1)){
                Intent i = new Intent(MainActivity.this,WeatherActivity.class);
                startActivity(i);
            }

        }

        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_Email.getText().toString().equals(email)){
                    if(txt_Password.getText().toString().equals(password))
                    {
                        if(Remember_me.isChecked())
                        {
                            loginPrefsEditor.putBoolean("saveLogin", true);
                            loginPrefsEditor.putInt("LoginDay",Day);
                            loginPrefsEditor.apply();
                            loginPrefsEditor.commit();

                        } else{
                            loginPrefsEditor.putBoolean("saveLogin", false);
                            loginPrefsEditor.putInt("LoginDay",0);
                            loginPrefsEditor.apply();
                            loginPrefsEditor.commit();
                        }
                        Intent i = new Intent(MainActivity.this,WeatherActivity.class);
                        startActivity(i);
                    }
                }
            }
        });

    }
    public static boolean WithinWeek(int mode){
        int remembered_day;
        if(mode ==1)
            remembered_day =loginPreferences.getInt("LoginDay", 0);
        else
            remembered_day =loginPreferences.getInt("UpdateDay", 0);
        int temp=remembered_day+7;
        if (Month==2&& temp>27)
            temp-=28;
        else if((Month==4|| Month==6||Month==9 || Month==11)&& temp>30)
            temp-=30;
        else if((Month==1|| Month==3||Month==5 || Month==7||Month==8||Month==10||Month==12)&& temp>31)
            temp-=31;
        if(remembered_day!=0 && Day<temp){
            return  true;
        }
        return false;
    }
}
