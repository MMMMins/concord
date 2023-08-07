package com.hackathon.concord.View;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.hackathon.concord.Model.DiaryDataSource;
import com.hackathon.concord.Model.DiaryModel;
import com.hackathon.concord.Model.LoginDataSource;
import com.hackathon.concord.R;
import com.hackathon.concord.viewModel.VariousViewModel;

public class VariousFragment extends Fragment implements View.OnClickListener, CalendarView.OnDateChangeListener {

    public CalendarView calendarView;
    public Button btn_modify, btn_del, btn_save;
    public TextView txtContent, txtTitle;
    public EditText contextEditText;
    private DiaryDataSource diaryDataSource;
    private LoginDataSource loginDataSource;
    private DiaryModel diaryModel;
    private String diary_date;
    private View rootView;
    private VariousViewModel viewModel; // ViewModel 선언

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_various, container, false);
        loginDataSource = new LoginDataSource(rootView.getContext());
        loginDataSource.open();

        // ViewModel 초기화
        viewModel = new ViewModelProvider(this).get(VariousViewModel.class);

        // 프래그먼트 레이아웃 파일의 뷰들을 초기화합니다
        calendarView = rootView.findViewById(R.id.calendarView);
        btn_save = rootView.findViewById(R.id.btn_save);
        btn_del = rootView.findViewById(R.id.btn_del);
        btn_modify = rootView.findViewById(R.id.btn_modify);
        txtContent = rootView.findViewById(R.id.txtContent);
        txtTitle = rootView.findViewById(R.id.txtTitle);
        contextEditText = rootView.findViewById(R.id.contextEditText);
        // 기타 필요한 뷰들을 초기화합니다

        // 달력 날짜 선택 리스너를 설정합니다
        calendarView.setOnDateChangeListener(this);

        // 데이터 변경을 감지하여 화면 업데이트
        viewModel.getFlagBtn().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String content) {
                String[] date =  diary_date.split("-");
                onSelectedDayChange(calendarView, Integer.parseInt(date[0]), Integer.parseInt(date[1])-1, Integer.parseInt(date[2]));
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                diaryModel = new DiaryModel(loginDataSource.getIDInfo(), contextEditText.getText().toString(), diary_date);
                diaryDataSource.saveDiaryInfo(diaryModel);
                viewModel.setFlagBtn("0"); // ViewModel 데이터 초기화

                break;
            case R.id.btn_modify:
                diaryModel.setEtc(contextEditText.getText().toString());
                diaryDataSource.setDiaryInfo(diaryModel);
                viewModel.setFlagBtn("1"); // ViewModel 데이터 초기화

                break;
            case R.id.btn_del:
                diaryDataSource.deleteDiaryInfo(diaryModel);
                viewModel.setFlagBtn("2"); // ViewModel 데이터 초기화

                break;
        }
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        diary_date = String.format("%04d-%02d-%02d",year,month+1,dayOfMonth);
        Log.e("에러",diary_date);
        diaryDataSource = new DiaryDataSource(rootView.getContext());
        diaryDataSource.open();
        diaryModel = diaryDataSource.getDiaryInfo(loginDataSource.getIDInfo(), diary_date);
        if(diaryModel == null){
            btn_save.setVisibility(View.VISIBLE);
            contextEditText.setVisibility(View.VISIBLE);
            txtContent.setVisibility(View.INVISIBLE);
            btn_modify.setVisibility(View.INVISIBLE);
            btn_del.setVisibility(View.INVISIBLE);
            contextEditText.setText("");
            btn_save.setOnClickListener(VariousFragment.this);
        }else{
            btn_save.setVisibility(View.INVISIBLE);
            contextEditText.setVisibility(View.VISIBLE);
            txtContent.setVisibility(View.VISIBLE);
            btn_modify.setVisibility(View.VISIBLE);
            btn_del.setVisibility(View.VISIBLE);
            contextEditText.setText(diaryModel.getEtc());
            btn_modify.setOnClickListener(VariousFragment.this);
            btn_del.setOnClickListener(VariousFragment.this);
            diaryModel.setDiary_date(diary_date);
        }
    }
}
