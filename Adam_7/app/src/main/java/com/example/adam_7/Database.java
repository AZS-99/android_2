package com.example.adam_7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    static final String DATABASE = "EmployeesDB";
    static final String TABLE1 = "Employees1";
    static final String TABLE2 = "Employees2";
    static final String COLUMN1 = "NAME";
    static final String COLUMN2 = "DEPARTMENT";
    static final String COLUMN3 = "YEAR";

    public Database(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE1 + "(NAME TEXT PRIMARY KEY, " +
                "DEPARTMENT TEXT, YEAR INTEGER)");
        db.execSQL("create table " + TABLE2 + "(NAME TEXT PRIMARY KEY, " +
                "DEPARTMENT TEXT, YEAR INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2);
    }

    public boolean insert(String table, String name, String dept, int year) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN1, name);
        contentValues.put(COLUMN2, dept);
        contentValues.put(COLUMN3, year);
        return db.insert(table, null, contentValues) != -1;
    }

    public Cursor getAllData(String table) {
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery("select * from " + table, null);
    }
}
