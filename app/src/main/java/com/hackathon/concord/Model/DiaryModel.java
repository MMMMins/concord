package com.hackathon.concord.Model;

import com.google.gson.annotations.SerializedName;

public class DiaryModel {
    @SerializedName("user_id")
    private String user_id;

    @SerializedName("diary_date")
    private String diary_date;

    @SerializedName("etc")
    private String etc;

    @SerializedName("image_pass")
    private String image_pass;

    public DiaryModel(String user_id, String diary_date, String etc) {
        this.user_id = user_id;
        this.diary_date = diary_date;
        this.etc = etc;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDiary_date() {
        return diary_date;
    }

    public void setDiary_date(String diary_date) {
        this.diary_date = diary_date;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public String getImage_pass() {
        return image_pass;
    }

    public void setImage_pass(String image_pass) {
        this.image_pass = image_pass;
    }

}
