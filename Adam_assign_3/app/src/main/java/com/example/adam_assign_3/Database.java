package com.example.adam_assign_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


//Try Parcelable
public class Database extends SQLiteOpenHelper {

    String database;
    List<String> tables;
    public Database(Context context, String name) {
        super(context, name, null, 1);

        database = name;
        tables = new ArrayList<>();
        tables.add("TEAMS");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TEAMS(" +
                "CITY TEXT," +
                "NAME TEXT," +
                "SPORT TEXT," +
                "MVP TEXT," +
                "STADIUM TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TEAMS");
    }

    public boolean insert(String city, String name, String sport, String MVP, String stadium) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CITY", city);
        contentValues.put("NAME", name);
        contentValues.put("SPORT", sport);
        contentValues.put("MVP", MVP);
        contentValues.put("STADIUM", stadium);
        return database.insert("TEAMS", null, contentValues) != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery("SELECT * FROM TEAMS", null);
    }


}
