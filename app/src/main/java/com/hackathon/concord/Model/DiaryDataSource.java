package com.hackathon.concord.Model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DiaryDataSource {
//            "CREATE TABLE diary_table (user_id TEXT, etc TEXT, diary_date TEXT, image_file BLOB)";
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DiaryDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public void deleteDiaryInfo(DiaryModel diaryModel){
        database.delete(DBHelper.TABLE_DIARY, "diary_date=? AND user_id=?", new String[]{diaryModel.getDiary_date(), diaryModel.getUser_id()});
    }

    // 계획 정보 저장
    public void saveDiaryInfo(DiaryModel diaryModel) {
        ContentValues values = new ContentValues();
        values.put("user_id", diaryModel.getUser_id());
        values.put("etc", diaryModel.getEtc());
        values.put("diary_date", diaryModel.getDiary_date());
        database.insert(DBHelper.TABLE_DIARY, null, values);
    }

    // 계획 정보 불러오기
    @SuppressLint("Range")
    public DiaryModel getDiaryInfo(String user_id, String diary_date) {

        DiaryModel diaryModel;

        Cursor cursor = database.query(DBHelper.TABLE_DIARY, null, "diary_date=? AND user_id=?", new String[]{diary_date, user_id}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
               diaryModel = new DiaryModel(
                        cursor.getString(cursor.getColumnIndex("user_id")),
                        cursor.getString(cursor.getColumnIndex("etc")),
                        cursor.getString(cursor.getColumnIndex("diary_date")));
               cursor.close();
            return diaryModel;
        }else{
            return null;
        }
    }

    public void setDiaryInfo(DiaryModel diaryModel){
        ContentValues values = new ContentValues();
        values.put("etc", diaryModel.getEtc());
        database.update(DBHelper.TABLE_DIARY, values, "diary_date=? AND user_id=?", new String[]{diaryModel.getDiary_date(), diaryModel.getUser_id()});
    }
}
