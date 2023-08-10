package com.hackathon.concord.viewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hackathon.concord.API.RetrofitService;
import com.hackathon.concord.Model.LoginDataSource;
import com.hackathon.concord.Model.UserModel;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginViewModel extends ViewModel {

    public static final String LOGIN_SUCCESS_CODE = "0001";
    public static final String ID_FAILURE_CODE = "0002";
    public static final String LOGIN_FAILURE_CODE = "0003";
    private MutableLiveData<String> userId = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();
    private MutableLiveData<String> responseCode = new MutableLiveData<>();


    // Getter 메서드
    public MutableLiveData<String> getUserId() {
        return userId;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public MutableLiveData<String> getLoginSuccess() {
        return responseCode;
    }

    // 로그인 처리 메서드
    public void loginUser(Context context) {
        String user_id = userId.getValue();
        String user_password = password.getValue();

        if (user_id == null || user_id.isEmpty() || user_password == null || user_password.isEmpty()) {
            // 사용자 아이디나 비밀번호가 비어있으면 처리 중단
            return;
        }

        UserModel userModel = new UserModel(user_id, user_password);

        Retrofit retrofit = RetrofitClient.getClient();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ResponseBody> call = retrofitService.login(userModel);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // 로그인 성공
                    LoginDataSource loginDataSource = new LoginDataSource(context);
                    loginDataSource.open();
                    loginDataSource.saveLoginInfo(user_id, user_password);
                    loginDataSource.close();
                    responseCode.setValue(LOGIN_SUCCESS_CODE); // 로그인 성공 상태를 LiveData에 설정
                } else {
                    // 로그인 실패
                    try {
                        String jsonResponse = response.body().string();
                        if(jsonResponse.equals(ID_FAILURE_CODE)){
                            responseCode.setValue(ID_FAILURE_CODE); // 로그인 실패 상태를 LiveData에 설정
                        }else if(jsonResponse.equals(LOGIN_FAILURE_CODE)){
                            responseCode.setValue((LOGIN_FAILURE_CODE));
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // 통신 실패
                responseCode.setValue("0004"); // 로그인 실패 상태를 LiveData에 설정
            }
        });
    }


}
