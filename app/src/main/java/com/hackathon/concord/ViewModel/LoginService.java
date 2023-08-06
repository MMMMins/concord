package com.hackathon.concord.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hackathon.concord.Model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginService extends ViewModel {
    private MutableLiveData<String> userId = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    // Getter 메서드
    public MutableLiveData<String> getUserId() {
        return userId;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }
    public void onLoginClick() {
        String userIdValue = userId.getValue();
        String passwordValue = password.getValue();
        // 로그인 처리 등의 로직
    }
    public void fetchPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.200.72.191:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<List<UserModel>> call = retrofitService.getList();
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (response.isSuccessful()) {
                    Log.d("성공","성공");
                } else {
                    Log.d("실패","실패");
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
            }
        });
    }
}
