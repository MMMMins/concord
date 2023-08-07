package com.hackathon.concord.viewModel;

import com.hackathon.concord.Model.UserModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface RetrofitService {

    @POST("api/users/login/")
    Call<ResponseBody> login(@Body UserModel userModel);
}
