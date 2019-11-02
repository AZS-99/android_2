package com.example.adam_6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    static final String DATABASE = "Seneca";
    static final String TABLE_NAME = "Student";
    static final String COLUMN1 = "ID";
    static final String COLUMN2 = "NAME";
    static final String COLUMN3 = "MARKS";

    public DataBase(Context context) {
        super(context, DATABASE, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insert(String id, String name, int marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN1, id);
        contentValues.put(COLUMN2, name);
        contentValues.put(COLUMN3, marks);
        return db.insert(TABLE_NAME, null, contentValues) != -1;

    }

    public Cursor getAllData() {
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery("select * from " + TABLE_NAME, null);

    }

    public Cursor getStudentById(String id) {
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery("select * from " + TABLE_NAME + " where id = " + id, null);
    }

    public Integer deleteStudentById(String id) {
        SQLiteDatabase database = getWritableDatabase();
        return database.delete(TABLE_NAME, "id = ?", new String[]{id});

    }
}
