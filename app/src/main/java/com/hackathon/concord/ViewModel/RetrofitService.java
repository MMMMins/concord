package com.hackathon.concord.ViewModel;
import com.hackathon.concord.Model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("accounts/")
    Call<List<UserModel>> getList();

    @POST("accounts/")
    Call<LoginService> login(@Body UserModel userModel);
}
