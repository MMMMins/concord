package com.hackathon.concord.viewModel;

import com.hackathon.concord.API.RetrofitService;
import com.hackathon.concord.Model.UserPetInfoModel;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.io.Serializable;
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
                    Log.e("에러","a");
                    UserPetInfoModel userPetInfoModel = response.body();
                    if (userPetInfoModel != null) {
                        info.setValue(userPetInfoModel);
                    }
                } else {
                    // API 호출에 실패한 경우
                    Log.e("에러","b");

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

