package com.hackathon.concord.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;

import com.hackathon.concord.R;
import com.hackathon.concord.databinding.ActivityLoginBinding;
import com.hackathon.concord.viewModel.LoginService;

public class LoginActivity extends AppCompatActivity {

    private LoginService loginViewModel;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // DataBindingUtil을 사용하여 레이아웃과 ViewModel을 바인딩
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this).get(LoginService.class);

        // ViewModel과 레이아웃을 바인딩
        binding.setViewModel(loginViewModel);
        binding.setLifecycleOwner(this);

        Button btnLogin = binding.btnLogin;
        btnLogin.setOnClickListener(v -> onLoginClick());
    }

    private void onLoginClick() {
        // 데이터 요청
        loginViewModel.fetchPosts();
    }
}
