package com.hackathon.concord.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hackathon.concord.R;
import com.hackathon.concord.viewModel.LoginViewModel;
import com.hackathon.concord.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // DataBindingUtil을 사용하여 레이아웃과 ViewModel을 바인딩
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // ViewModel과 레이아웃을 바인딩
        binding.setViewModel(loginViewModel);
        binding.setLifecycleOwner(this);

        Button btnLogin = binding.btnLogin;
        btnLogin.setOnClickListener(this);

        // 로그인 성공 여부를 Observer로 감지하여 화면 전환 처리
        loginViewModel.getLoginSuccess().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String loginSuccess) {
                switch (loginSuccess) {
                    case LoginViewModel.LOGIN_SUCCESS_CODE:
                        // 로그인 성공 시 다음 화면으로 전환 (예시: MainActivity로 전환)
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish(); // 현재 LoginActivity 종료
                        break;
                    case LoginViewModel.ID_FAILURE_CODE:
                        // 로그인 실패: 해당 아이디가 없음
                        Toast.makeText(LoginActivity.this, "해당 아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                        break;
                    case LoginViewModel.LOGIN_FAILURE_CODE:
                        // 로그인 실패: 아이디와 비밀번호가 일치하지 않음
                        Toast.makeText(LoginActivity.this, "해당 정보가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        // 기타 오류
                        Toast.makeText(LoginActivity.this, "관리자에게 문의해주세요", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        loginViewModel.loginUser(getApplicationContext());
    }

}
