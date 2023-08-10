package com.hackathon.concord.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.hackathon.concord.Model.LoginDataSource;
import com.hackathon.concord.R;
import com.hackathon.concord.viewModel.InfoViewModel;
import com.hackathon.concord.Model.UserPetInfoModel;

public class LoadActivity extends AppCompatActivity {

    private LoginDataSource dataSource;
    private InfoViewModel infoViewModel;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        dataSource = new LoginDataSource(this);
        dataSource.open();

        imageView = findViewById(R.id.imageView);
        infoViewModel = new ViewModelProvider(this).get(InfoViewModel.class);

        Loading();
    }

    private void Loading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // 로그인 정보를 불러옴
                String[] loginInfo = dataSource.getLoginInfo();
                String savedUsername = loginInfo[0];
                String savedPassword = loginInfo[1];

                if (savedUsername != null && !savedUsername.isEmpty() && savedPassword != null && !savedPassword.isEmpty()) {
                    // 로그인 기록이 있다면 메인 화면으로 이동
                    infoViewModel.getInfoList(savedUsername); // ViewModel을 사용하여 사용자 정보 가져오기
                    // 사용자 및 애완동물 정보가 성공적으로 가져와졌을 때
                    // 이 정보를 MainActivity로 전달하여 UI를 업데이트할 수 있습니다.
                    infoViewModel.getInfo().observe(LoadActivity.this, new Observer<UserPetInfoModel>() {
                        @Override
                        public void onChanged(UserPetInfoModel userPetInfoModel) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("user_info", infoViewModel.getInfo().getValue());
                            startActivity(intent);
                            finish();
                        }
                    });


                } else {
                    // 로그인 기록이 없다면 로그인 화면으로 이동
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 1500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }
}

