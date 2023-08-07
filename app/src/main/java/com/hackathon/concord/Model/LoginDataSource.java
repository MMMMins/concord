package com.hackathon.concord.Model;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginDataSource {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public LoginDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() {

        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // 로그인 정보 저장
    public void saveLoginInfo(String username, String password) {
        logoutInfo();
        ContentValues values = new ContentValues();
        values.put("user_id", username);
        values.put("password", password);
        database.insert("login_table", null, values);
    }

    public void logoutInfo(){
        database.delete("login_table", null, null);
    }

    @SuppressLint("Range")
    public String getIDInfo(){
        String idInfo ="";

        Cursor cursor = database.query(DBHelper.TABLE_LOGIN, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            idInfo = cursor.getString(cursor.getColumnIndex("user_id"));
            cursor.close();
        }

        return idInfo;
    }

    // 로그인 정보 불러오기
    @SuppressLint("Range")
    public String[] getLoginInfo() {
        String[] loginInfo = new String[2];

        Cursor cursor = database.query(DBHelper.TABLE_LOGIN, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            loginInfo[0] = cursor.getString(cursor.getColumnIndex("user_id"));
            loginInfo[1] = cursor.getString(cursor.getColumnIndex("password"));
            cursor.close();
        }

        return loginInfo;
    }
}
