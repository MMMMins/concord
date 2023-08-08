package com.hackathon.concord.viewModel;

import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.hackathon.concord.Model.LoginDataSource;
import com.hackathon.concord.Model.PetModel;
import com.hackathon.concord.Model.UserPetInfoModel;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
public class InfoViewModel extends ViewModel implements Serializable {
    private MutableLiveData<UserPetInfoModel> info = new MutableLiveData<>();

    public MutableLiveData<UserPetInfoModel> getInfo() {
        return info;
    }

    public void getInfoList(String user_id){
        Retrofit retrofit = RetrofitClient.getClient();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<UserPetInfoModel> call = retrofitService.info(user_id);
        call.enqueue(new Callback<UserPetInfoModel>() {
            @Override
            public void onResponse(Call<UserPetInfoModel> call, Response<UserPetInfoModel> response) {
                if (response.isSuccessful()) {
                    UserPetInfoModel userPetInfoModel = response.body();
                    List<PetModel> tempPetList = new ArrayList<>();
                    if (userPetInfoModel != null) {
                        for(PetModel petModel : userPetInfoModel.getPets()){
                            if (petModel.getPetImage() != null) {
                                petModel.setPetImage(petModel.getPetImage().replace("/pet_image/", ""));
                                petModel.setPetImage(petModel.getPetImage().replace("%2B", "+"));
                                petModel.setPetImage(petModel.getPetImage().replace("%3D", "="));
//
//                                Log.e("", String.valueOf(petModel.getPetImage().length()));
//                                byte[] decodedImage = Base64.decode(petModel.getPetImage(), Base64.DEFAULT);
//
//                                Log.e("ee", String.valueOf(decodedImage.length));
//                                petModel.setImageByte(decodedImage);
                                tempPetList.add(petModel);
                            }
                        }
                        userPetInfoModel.setPets(tempPetList);
                        info.setValue(userPetInfoModel);
                    }
                } else {
                    // API 호출에 실패한 경우
                }
            }

            @Override
            public void onFailure(Call<UserPetInfoModel> call, Throwable t) {
                Log.e("에러",t.getMessage());
                // 통신 실패
            }
        });
    }
}

