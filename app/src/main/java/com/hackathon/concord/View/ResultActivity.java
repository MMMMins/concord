package com.hackathon.concord.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hackathon.concord.Model.AiResultModel;
import com.hackathon.concord.R;

public class ResultActivity extends AppCompatActivity {

    private TextView txtResultName;
    private TextView txtResultPhone;
    private TextView txtResultPetName;
    private TextView txtResultPetBreed;
    private Button btnMainGo;
    private TextView txtResult;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        AiResultModel aiResultModel = (AiResultModel) intent.getSerializableExtra("aiResult");

        txtResultName = findViewById(R.id.txtResultName);
        txtResultPhone = findViewById(R.id.txtResultPhone);
        txtResultPetName = findViewById(R.id.txtResultPetName);
        txtResultPetBreed = findViewById(R.id.txtResultBreed);
        txtResult = findViewById(R.id.txtResultP);
        btnMainGo = findViewById(R.id.btnMainGo);


        txtResultName.setText(aiResultModel.getUser_id());
        txtResultPhone.setText(aiResultModel.getUser_phone());
        txtResultPetName.setText(aiResultModel.getPet_name());
        txtResultPetBreed.setText(aiResultModel.getPet_breed());
        double accuracy = Double.parseDouble(aiResultModel.getResult()) * 100;
        String formattedAccuracy = String.format("%.1f", accuracy);
        txtResult.setText("일치율: " + formattedAccuracy + "%");

        btnMainGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}