package com.hackathon.concord.API;

import com.hackathon.concord.Model.TMResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface KakaoMapService {

    @Headers("Authorization: KakaoAK 9c5c48e4962ec74cc7b6726edc8f22a8")
    @GET("v2/local/geo/transcoord.json")
    Call<TMResponse> convertToTM(
            @Query("x") double longitude,
            @Query("y") double latitude,
            @Query("output_coord") String outputCoord
    );
}
