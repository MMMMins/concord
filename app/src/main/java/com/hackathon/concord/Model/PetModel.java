package com.hackathon.concord.Model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PetModel  implements Serializable {
    @SerializedName("register_number")
    private String registerNumber;
    @SerializedName("register_date")
    private String registerDate;
    @SerializedName("pet_date")
    private String petDate;
    @SerializedName("pet_name")
    private String petName;
    @SerializedName("pet_gender")
    private String petGender; // pet_gender를 String으로 변경
    @SerializedName("pet_image")
    private String petImage;

    @SerializedName("pet_size")
    private String petSize;


    @SerializedName("pet_breed")
    private String petBreed;
    @SerializedName("pet_lost")
    private String petLost;
    private Bitmap bitmapImage;


    public String getPetSize() {
        return petSize;
    }

    public void setPetSize(String petSize) {
        this.petSize = petSize;
    }

    public Bitmap getBitmapImage() {
        return bitmapImage;
    }

    public void setBitmapImage(Bitmap bitmapImage) {
        this.bitmapImage = bitmapImage;
    }


// 생성자와 Getter 메서드

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getPetDate() {
        return petDate;
    }

    public void setPetDate(String petDate) {
        this.petDate = petDate;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public String getPetImage() {
        return petImage;
    }

    public void setPetImage(String petImage) {
        this.petImage = petImage;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetLost() {
        return petLost;
    }

    public void setPetLost(String petLost) {
        this.petLost = petLost;
    }

    // Parcelable 구현 메서드
    // 이 메서드들을 구현하여 객체를 Parcelable로 만듭니다.
}
