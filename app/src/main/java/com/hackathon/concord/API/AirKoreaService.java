package com.hackathon.concord.API;

import com.hackathon.concord.Model.AKResponse_AirQualityIndex;
import com.hackathon.concord.Model.AKResponse_PointCheck;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AirKoreaService {
    @GET("MsrstnInfoInqireSvc/getNearbyMsrstnList")
    Call<AKResponse_PointCheck> pointCheck(
            @Query("serviceKey") String serviceKey,
            @Query("returnType") String returnType,
            @Query("tmX") double tmX,
            @Query("tmY") double tmY,
            @Query("ver") String version
    );

    @GET("ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty")
    Call<AKResponse_AirQualityIndex> getAirQualityData(
            @Query("serviceKey") String serviceKey,
            @Query("returnType") String returnType,
            @Query("numOfRows") int numOfRows,
            @Query("pageNo") int pageNo,
            @Query("stationName") String stationName,
            @Query("dataTerm") String dataTerm,
            @Query("ver") String version
    );
}
