package com.hackathon.concord.viewModel;

import com.hackathon.concord.Model.DiaryModel;
import com.hackathon.concord.Model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("schedules/{user_id}/{diary_date}")
    Call<DiaryModel> getSchedules(
            @Path("user_id") String userId,
            @Path("diary_date") String diaryDate
    );

    @GET("accounts/")
    Call<List<UserModel>> getList();

    @POST("accounts/")
    Call<LoginService> login(@Body UserModel userModel);
}
