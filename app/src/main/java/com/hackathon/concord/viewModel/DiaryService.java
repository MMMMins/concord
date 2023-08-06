package com.hackathon.concord.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hackathon.concord.Model.DiaryModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiaryService extends ViewModel {
    private MutableLiveData<DiaryModel> diaryModel=new MutableLiveData<>();
    private String userId;
    private MutableLiveData<String> selDate = new MutableLiveData<>();
    private MutableLiveData<String> content = new MutableLiveData<>();
    private MutableLiveData<String> newContent = new MutableLiveData<>();

    public MutableLiveData<DiaryModel> getDiaryModel() {
        return diaryModel;
    }

    public MutableLiveData<String> getSelDate() {
        return selDate;
    }

    public MutableLiveData<String> getContent() {
        return content;
    }

    public MutableLiveData<String> getNewContent() {
        return newContent;
    }

    public void onModifyClick(){

    }
    public void onDelClick(){

    }
    public void onSaveClick(){
        fetchPosts();
    }

    public void fetchPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.200.72.191:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<DiaryModel> call = retrofitService.getSchedules("min", selDate.getValue());
        Log.e("에러", String.valueOf(selDate.getValue()));
        call.enqueue(new Callback<DiaryModel>() {
            @Override
            public void onResponse(Call<DiaryModel> call, Response<DiaryModel> response) {
                if (response.isSuccessful()) {
                    diaryModel.setValue(response.body());
                } else {
                    Log.d("실패", "실패");
                }
            }
            @Override
            public void onFailure(Call<DiaryModel> call, Throwable t) {
            }
        });
    }
}
