package com.hackathon.concord.Model;

import com.google.gson.annotations.SerializedName;

public class DiaryModel {

    @SerializedName("user_id")
    private String user_id;
    @SerializedName("etc")
    private String etc;
    @SerializedName("diary_date")
    private String diary_date;
    private String image_file;

    public DiaryModel(String user_id, String etc, String diary_date) {
        this.user_id = user_id;
        this.etc = etc;
        this.diary_date = diary_date;
    }

    public DiaryModel(String user_id, String etc, String diary_date, String image_file) {
        this.user_id = user_id;
        this.etc = etc;
        this.diary_date = diary_date;
        this.image_file = image_file;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public String getDiary_date() {
        return diary_date;
    }

    public void setDiary_date(String diary_date) {
        this.diary_date = diary_date;
    }

    public String getImage_file() {
        return image_file;
    }

    public void setImage_file(String image_file) {
        this.image_file = image_file;
    }
}
