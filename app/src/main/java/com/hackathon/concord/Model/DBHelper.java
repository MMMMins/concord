package com.hackathon.concord.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myapp.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_DIARY = "diary_table";
    public static final String TABLE_LOGIN = "login_table";

    private static final String CREATE_DIARY_TABLE =
            "CREATE TABLE diary_table (user_id TEXT, etc TEXT, diary_date TEXT, image_file BLOB)";

    private static final String CREATE_LOGIN_TABLE =
            "CREATE TABLE login_table (user_id TEXT PRIMARY Key, password TEXT)";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onDrop(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DIARY);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_LOGIN);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOGIN_TABLE);
        db.execSQL(CREATE_DIARY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIARY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        onCreate(db);
    }
}

