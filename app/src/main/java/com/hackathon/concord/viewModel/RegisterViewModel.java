package com.hackathon.concord.viewModel;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hackathon.concord.API.RetrofitService;
import com.hackathon.concord.Model.ImageFilePath;
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

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<Boolean> uploadSuccess = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public MutableLiveData<Boolean> getUploadSuccess() {
        return uploadSuccess;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void uploadImage(String imagePath, String user_id, PetModel petModel) {
        File imageFile = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("pet_image", imageFile.getName(), requestBody);

        // 나머지 데이터 생성
        RequestBody userIdBody = RequestBody.create(MediaType.parse("text/plain"), user_id);
        RequestBody petNameBody = RequestBody.create(MediaType.parse("text/plain"), petModel.getPetName());
        RequestBody petDateBody = RequestBody.create(MediaType.parse("text/plain"), petModel.getPetDate());
        RequestBody petGenderBody = RequestBody.create(MediaType.parse("text/plain"), petModel.getPetGender());
        RequestBody petSizeBody = RequestBody.create(MediaType.parse("text/plain"), petModel.getPetSize());
        RequestBody petBreedBody = RequestBody.create(MediaType.parse("text/plain"), petModel.getPetBreed());
        RequestBody petRegNumberBody = RequestBody.create(MediaType.parse("text/plain"), petModel.getRegisterNumber());


        // Retrofit을 사용하여 데이터 전송
        Retrofit retrofit = RetrofitClient.getClient();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        Call<ResponseBody> call = retrofitService.uploadImage(
                imagePart, userIdBody, petRegNumberBody, petDateBody, petNameBody, petGenderBody, petBreedBody,petSizeBody
                );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // 서버 응답 처리
                if (response.isSuccessful()) {
                    uploadSuccess.setValue(true);
                    // 성공적으로 전송됨
                    // ...
                } else {
                    uploadSuccess.setValue(false);
                    errorMessage.setValue("ERROR 400");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("asd", t.getMessage());

                // 통신 오류 처리
                // ...
            }
        });
    }
}
