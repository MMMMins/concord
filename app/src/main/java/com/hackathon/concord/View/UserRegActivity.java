package com.hackathon.concord.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hackathon.concord.R;

public class UserRegActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtFindID;
    TextView txtFindPwd;
    Button btnLoginPage;
    Button btnSignUPPage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);

        txtFindID = findViewById(R.id.txtFindID);
        txtFindID.setOnClickListener(this);

        txtFindPwd = findViewById(R.id.txtFindPwd);
        txtFindID.setOnClickListener(this);

        btnLoginPage = findViewById(R.id.btnLoginPage);
        btnLoginPage.setOnClickListener(this);

        btnSignUPPage = findViewById(R.id.btnSignUpPage);
        btnSignUPPage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.txtFindID:

                break;
            case R.id.txtFindPwd:
                break;

            case R.id.btnLoginPage:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSignUpPage:

                break;
        }
    }
}