package com.hackathon.concord.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hackathon.concord.API.RetrofitService;
import com.hackathon.concord.Model.AiResultModel;
import com.hackathon.concord.Model.PetModel;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TestViewModel extends ViewModel {
    private MutableLiveData<Boolean> uploadSuccess = new MutableLiveData<>();
    private MutableLiveData<AiResultModel> aiResultModels = new MutableLiveData<>();

    public MutableLiveData<Boolean> getUploadSuccess() {
        return uploadSuccess;
    }

    public MutableLiveData<AiResultModel> getAiResultModels() {
        return aiResultModels;
    }

    public void uploadImage(String imagePath) {
        File imageFile = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("pet_image", imageFile.getName(), requestBody);

        // Retrofit을 사용하여 데이터 전송
        Retrofit retrofit = RetrofitClient.getClient();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        // 요청 보내기
        Call<AiResultModel> call = retrofitService.aiResult(imagePart);
        call.enqueue(new Callback<AiResultModel>() {
            @Override
            public void onResponse(Call<AiResultModel> call, Response<AiResultModel> response) {
                // 서버 응답 처리
                if (response.isSuccessful()) {
                    // 성공적으로 응답을 받았을 때 처리
                    AiResultModel responseData = response.body();
                    aiResultModels.setValue(responseData);
                    uploadSuccess.setValue(true);
                } else {
                    // 에러 처리
                    uploadSuccess.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<AiResultModel> call, Throwable t) {
                // 네트워크 에러 등의 실패 처리
            }
        });
    }
}
