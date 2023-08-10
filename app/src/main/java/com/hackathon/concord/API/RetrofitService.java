package com.hackathon.concord.API;

import com.hackathon.concord.Model.UserModel;
import com.hackathon.concord.Model.UserPetInfoModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    @POST("api/users/login/")
    Call<ResponseBody> login(@Body UserModel userModel);

    @GET("api/users/pets/info/{user_id}/")
    Call<UserPetInfoModel> info(@Path("user_id") String user_id);

    @GET("api/pets/imgload/{regnumber}/")
    Call<ResponseBody> getImage(@Path("regnumber") String regNumber);
}
