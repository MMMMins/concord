package com.hackathon.concord.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.hackathon.concord.R;

public class LoadActivity extends AppCompatActivity {

    // 로딩창 여긴 순수 디자인 용도
    // 프로그램 설계 과정에서 여기서 데이터베이스 값을 호출해서 앱의 메인에서 데이터 호출하는 것을 줄이는 방안도 될 것 같음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Loading();
    }
    private void Loading(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run(){
                Intent intent = new Intent(getApplicationContext(), UserRegActivity.class);
                startActivity(intent);
                finish();;
            }
        },  1500);
    }
}