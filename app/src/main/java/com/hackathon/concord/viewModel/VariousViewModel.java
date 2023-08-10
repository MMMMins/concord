package com.hackathon.concord.viewModel;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hackathon.concord.API.AirKoreaService;
import com.hackathon.concord.API.KakaoMapService;
import com.hackathon.concord.Model.AKResponse_AirQualityIndex;
import com.hackathon.concord.Model.AKResponse_PointCheck;
import com.hackathon.concord.Model.TMResponse;
import com.hackathon.concord.View.RegFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VariousViewModel extends ViewModel {
    private MutableLiveData<TMResponse> TM_Result = new MutableLiveData<>();
    private MutableLiveData<AKResponse_PointCheck> AK_Result_Point = new MutableLiveData<>();
    private MutableLiveData<AKResponse_AirQualityIndex> AK_Result_Quality = new MutableLiveData<>();
    public MutableLiveData<TMResponse> getResult(){
        return TM_Result;
    }

    public MutableLiveData<AKResponse_PointCheck> getAK_Result_Point() {
        return AK_Result_Point;
    }

    public MutableLiveData<AKResponse_AirQualityIndex> getAK_Result_Quality() {
        return AK_Result_Quality;
    }

    public void convertCoordinates(double latitude, double longitude){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dapi.kakao.com/") // 카카오맵 API의 기본 URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KakaoMapService service = retrofit.create(KakaoMapService.class);


        // API 호출
        Call<TMResponse> call = service.convertToTM(longitude, latitude,"TM");

        call.enqueue(new Callback<TMResponse>() {
            @Override
            public void onResponse(Call<TMResponse> call, Response<TMResponse> response) {
                if (response.isSuccessful()) {
                    TMResponse result = response.body();
                    Log.e("TM",result.getDocuments().get(0).getX()+"|"+result.getDocuments().get(0).getY());
                    TM_Result.setValue(result);
                    airKoreaPointCheck();
                    // 변환된 TM 좌표 사용

                }
            }

            @Override
            public void onFailure(Call<TMResponse> call, Throwable t) {

                // API 호출 실패 처리
            }
        });
    }


    public Retrofit airKoreaRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apis.data.go.kr/B552584/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public void airKoreaPointCheck() {
        AirKoreaService service = airKoreaRetrofit().create(AirKoreaService.class);
        double X = TM_Result.getValue().getDocuments().get(0).getX();
        double Y = TM_Result.getValue().getDocuments().get(0).getY();

        // API 호출
        String key = "5kMdJd6R+43DHUqSj7PGyWhpD4kO+NKAAHLUcslALrEVNv+bkNfJho7Iwm6iFksSusSHM9Ioq0GDDJKIdmEN2w=="; // 실제 서비스 키를 입력하세요
        Call<AKResponse_PointCheck> call = service.pointCheck(key, "json", X, Y, "1.0");

        call.enqueue(new Callback<AKResponse_PointCheck>() {
            @Override
            public void onResponse(Call<AKResponse_PointCheck> call, Response<AKResponse_PointCheck> response) {
                if (response.isSuccessful()) {
                    AKResponse_PointCheck akResponse = response.body();
                    Log.e("getStationName", akResponse.getResponse().getBody().getItems().get(0).getStationName());
                    AK_Result_Point.setValue(akResponse);
                    airKoreaAirQuality();
                    // 변환된 TM 좌표 사용
                }
            }

            @Override
            public void onFailure(Call<AKResponse_PointCheck> call, Throwable t) {
                Log.e("AK_Point", "ERROR");
                // API 호출 실패 처리
            }
        });
    }

    public void airKoreaAirQuality(){
        AirKoreaService service = airKoreaRetrofit().create(AirKoreaService.class);

        String stationName = AK_Result_Point.getValue().getResponse().getBody().getItems().get(0).getStationName();
        // API 호출
        String key = "5kMdJd6R+43DHUqSj7PGyWhpD4kO+NKAAHLUcslALrEVNv+bkNfJho7Iwm6iFksSusSHM9Ioq0GDDJKIdmEN2w=="; // 실제 서비스 키를 입력하세요
        Call<AKResponse_AirQualityIndex> call = service.getAirQualityData(key, "json", 100, 1, stationName, "DAILY", "1.0");

        call.enqueue(new Callback<AKResponse_AirQualityIndex>() {
            @Override
            public void onResponse(Call<AKResponse_AirQualityIndex> call, Response<AKResponse_AirQualityIndex> response) {
                if (response.isSuccessful()) {
                    AKResponse_AirQualityIndex akResponse = response.body();
                    AK_Result_Quality.setValue(akResponse);
                    Log.e("aaaa",AK_Result_Quality.getValue().getResponse().getBody().getItems().get(0).getPm25Value());
                    // 변환된 TM 좌표 사용
                }
            }

            @Override
            public void onFailure(Call<AKResponse_AirQualityIndex> call, Throwable t) {
                Log.e("AK_Quality", "ERROR");
                // API 호출 실패 처리
            }
        });
    }
}

