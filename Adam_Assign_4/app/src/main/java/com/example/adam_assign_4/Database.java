package com.example.adam_assign_4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//Try Parcelable
public class Database extends SQLiteOpenHelper {

    String database;

    public Database(Context context, String name) {
        super(context, name, null, 1);
        database = name;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TEAMS(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CITY TEXT," +
                "NAME TEXT," +
                "SPORT TEXT," +
                "MVP TEXT," +
                "STADIUM TEXT, " +
                "IMGPATH TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TEAMS");
    }

    public boolean insert(String city, String name, String sport, String MVP, String stadium, String imgPath) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CITY", city);
        contentValues.put("NAME", name);
        contentValues.put("SPORT", sport);
        contentValues.put("MVP", MVP);
        contentValues.put("STADIUM", stadium);
        contentValues.put("IMGPATH", imgPath);
        return database.insert("TEAMS", null, contentValues) != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery("SELECT * FROM TEAMS", null);
    }

    public int delete (String id) {
        SQLiteDatabase database = getWritableDatabase();
        return database.delete("TEAMS", "ID = ?", new String[] {id});
    }

    public int update (String id, String city, String name, String sport, String MVP, String stadium, String imgPath) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CITY", city);
        contentValues.put("NAME", name);
        contentValues.put("SPORT", sport);
        contentValues.put("MVP", MVP);
        contentValues.put("STADIUM", stadium);
        contentValues.put("IMGPATH", imgPath);
        return database.update("TEAMS", contentValues, "ID = ?", new String[] {id});

    }


}