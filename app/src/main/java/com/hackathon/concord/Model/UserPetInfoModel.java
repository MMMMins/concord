package com.hackathon.concord.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserPetInfoModel implements Serializable {
    private UserModel user = new UserModel();
    private List<PetModel> pets = new ArrayList<>();

    public UserPetInfoModel() {
        user = new UserModel();
        pets = new ArrayList<>();
    }
    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<PetModel> getPets() {
        return pets;
    }

    public void setPets(List<PetModel> pets) {
        this.pets = pets;
    }
}
