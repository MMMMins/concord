package com.hackathon.concord.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hackathon.concord.Model.UserModel;
import com.hackathon.concord.R;
import com.hackathon.concord.ViewModel.LoginService;
import com.hackathon.concord.databinding.ActivityLoginBinding;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private LoginService loginViewModel;
    private ActivityLoginBinding binding;

    EditText edtID;
    EditText edtPwd;
    Button btnLogin;
    LoginService loginService;
    TextView textView2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtID = findViewById(R.id.edtID);
        edtPwd = findViewById(R.id.edtPwd);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this).get(LoginService.class);

        // ViewModel과 레이아웃을 바인딩
        binding.setViewModel(loginViewModel);
        binding.setLifecycleOwner(this);
    }

    @Override
    public void onClick(View v) {
        // ViewModelProvider를 통해 MainViewModel 인스턴스를 얻음
        loginService = new ViewModelProvider(this).get(LoginService.class);

        // ViewModel에서 데이터가 변경되었을 때 UI 업데이트를 수신하여 처리
        loginService.getUserId().observe(this, posts -> {
            // 데이터를 처리하여 UI에 업데이트
        });

        loginService.getPassword().observe(this, errorMessage -> {
            // 에러 메시지를 처리하여 UI에 업데이트
        });

        // 데이터 요청
        loginService.fetchPosts();
    }
}