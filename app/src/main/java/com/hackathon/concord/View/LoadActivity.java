package com.hackathon.concord.View;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.hackathon.concord.Model.LoginDataSource;
import com.hackathon.concord.R;

public class LoadActivity extends AppCompatActivity {

    private LoginDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        dataSource = new LoginDataSource(this);
        dataSource.open();

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
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    // 로그인 기록이 없다면 로그인 화면으로 이동
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }

                finish();
            }
        }, 1500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }
}
