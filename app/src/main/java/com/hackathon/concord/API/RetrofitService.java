package com.hackathon.concord.API;

import com.hackathon.concord.Model.AiResultModel;
import com.hackathon.concord.Model.UserModel;
import com.hackathon.concord.Model.UserPetInfoModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RetrofitService {

    @POST("api/users/login/")
    Call<ResponseBody> login(@Body UserModel userModel);

    @GET("api/users/pets/info/{user_id}/")
    Call<UserPetInfoModel> info(@Path("user_id") String user_id);

    @GET("api/pets/imgload/{regnumber}/")
    Call<ResponseBody> getImage(@Path("regnumber") String regNumber);

    @Multipart
    @POST("api/pets/test/") // Django 엔드포인트 URL
    Call<ResponseBody> uploadImage(
            @Part MultipartBody.Part imageFile,
            @Part("user_id") RequestBody userId,
            @Part("register_number") RequestBody registerNumber,
            @Part("pet_date") RequestBody petDate,
            @Part("pet_name") RequestBody petName,
            @Part("pet_gender") RequestBody petGender,
            @Part("pet_breed") RequestBody petBreed,
            @Part("pet_size") RequestBody petSize
    );

    @Multipart
    @POST("api/pets/aistart/")
    Call<AiResultModel> aiResult(@Part MultipartBody.Part imageFile);
}
