package com.example.amin.mobilecomputing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Amin on 13/12/2017.
 */

public class DatabaseOperations extends SQLiteOpenHelper {
    public static String Create_Query="CREATE TABLE "+ TableData.TableInfo.TABLE_NAME+" ("+TableData.TableInfo.Weather_ID+" TEXT,"+ TableData.TableInfo.Weather_City+" TEXT,"
            + TableData.TableInfo.Weather_Country+" TEXT,"
            + TableData.TableInfo.Weather_Main+" TEXT,"
            + TableData.TableInfo.Weather_Description+" TEXT,"
            + TableData.TableInfo.Weather_Day+" REAL,"
            + TableData.TableInfo.Weather_Night+" REAL,"
            + TableData.TableInfo.Weather_Pressure+" REAL,"
            + TableData.TableInfo.Weather_Humidity+" REAL );";
    public DatabaseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Query);
        Log.d("Database Operation","Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Add(DatabaseOperations dob,Weather w)
    {
        SQLiteDatabase sq = dob.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.Weather_ID,w.getId());
        cv.put(TableData.TableInfo.Weather_City,w.getCity());
        cv.put(TableData.TableInfo.Weather_Country,w.getCountry());
        cv.put(TableData.TableInfo.Weather_Main,w.getMain());
        cv.put(TableData.TableInfo.Weather_Description,w.getDescription());
        cv.put(TableData.TableInfo.Weather_Day,w.getDay());
        cv.put(TableData.TableInfo.Weather_Night,w.getNight());
        cv.put(TableData.TableInfo.Weather_Pressure,w.getPressure());
        cv.put(TableData.TableInfo.Weather_Humidity,w.getHumidity());
        long k =sq.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        Log.d("Database Operation","Row Inserted");

    }

    public Cursor getAll(DatabaseOperations dob)
    {
        SQLiteDatabase sq = dob.getReadableDatabase();
        String [] Coulmns = {
                TableData.TableInfo.Weather_ID,
                TableData.TableInfo.Weather_City,
                TableData.TableInfo.Weather_Country,
                TableData.TableInfo.Weather_Main,
                TableData.TableInfo.Weather_Description,
                TableData.TableInfo.Weather_Day,
                TableData.TableInfo.Weather_Night,
                TableData.TableInfo.Weather_Pressure,
                TableData.TableInfo.Weather_Humidity};
        Cursor cr = sq.query(TableData.TableInfo.TABLE_NAME,Coulmns,null,null,null,null,null);
        return cr;

    }

    public void DeleteItemById(DatabaseOperations dob ,String id)
    {
        String selection = TableData.TableInfo.Weather_ID+ " Like ?";
        String args[]={id};
        SQLiteDatabase sq = dob.getWritableDatabase();
        sq.delete(TableData.TableInfo.TABLE_NAME,selection,args);
        Log.d("Database Operation","Row Deleted");

    }


    /*
    DatabaseOperations DB = new DatabaseOperations(this);
        DB.Add(DB,"amin123","amin234");
        Cursor cr = DB.Get(DB);

        cr.moveToFirst();
        do {
            DB.DeleteItem(DB,cr.getString(0),cr.getString(1));
        }while(cr.moveToNext());

        Cursor cn = DB.Get(DB);
        cn.moveToFirst();
        do {
            if(cn!=null)
                Log.d("Database Operation","name: "+cr.getString(0)+"Password :"+cr.getString(1));

        } while(cn.moveToNext());

     */
}
