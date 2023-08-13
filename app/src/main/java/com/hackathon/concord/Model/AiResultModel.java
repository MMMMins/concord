package com.hackathon.concord.Model;

import java.io.Serializable;

public class AiResultModel implements Serializable {

    private String user_id;
    private String user_phone;
    private String register_number;
    private String pet_name;
    private String pet_breed;
    private String result;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getRegister_number() {
        return register_number;
    }

    public void setRegister_number(String register_number) {
        this.register_number = register_number;
    }

    public String getPet_name() {
        return pet_name;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public String getPet_breed() {
        return pet_breed;
    }

    public void setPet_breed(String pet_breed) {
        this.pet_breed = pet_breed;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
