package com.hackathon.concord.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserModel implements Serializable {
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserModel() {
    }

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
