package com.example.amin.mobilecomputing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static String email = "";
    public static String password = "";
    CheckBox Remember_me;
    Button btn_SignIn;
    EditText txt_Email,txt_Password;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    boolean remembered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_Email = (EditText)findViewById(R.id.Email);
        txt_Password = (EditText)findViewById(R.id.Password);
        btn_SignIn= (Button)findViewById(R.id.btn_Login);
        Remember_me = (CheckBox)findViewById(R.id.Remember);

        loginPreferences = getSharedPreferences("MainActivity", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        remembered= loginPreferences.getBoolean("saveLogin", false);
        if(remembered){
            Intent i = new Intent(MainActivity.this,WeatherActivity.class);
            startActivity(i);
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

                        }
                        Intent i = new Intent(MainActivity.this,WeatherActivity.class);
                        startActivity(i);
                    }
                }
            }
        });

    }
}
