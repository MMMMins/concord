package com.hackathon.concord.Model;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("user_id")
    private String user_id;

    @SerializedName("password")
    private String password;

    @SerializedName("username")
    private String username;

    @SerializedName("gender")
    private int gender;

    @SerializedName("phone")
    private String phone;

    public UserModel(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }

    public UserModel(String user_id, String password, String username, int gender, String phone) {
        this.user_id = user_id;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.phone = phone;
    }

}
