package com.example.amin.mobilecomputing;

import android.provider.BaseColumns;

/**
 * Created by Amin on 13/12/2017.
 */

public class TableData {
    public TableData()
    {

    }
    public static abstract class TableInfo implements BaseColumns
    {
        public static final String Weather_ID= "ID";
        public static final String Weather_City= "city";
        public static final String Weather_Country= "country";
        public static final String Weather_Main= "main";
        public static final String Weather_Description= "description";
        public static final String Weather_Day= "day";
        public static final String Weather_Night= "night";
        public static final String Weather_Pressure= "pressure";
        public static final String Weather_Humidity= "humidity";
        public static final String DATABASE_NAME ="MyDatabase";
        public static final String TABLE_NAME ="Weather";

    }
}
