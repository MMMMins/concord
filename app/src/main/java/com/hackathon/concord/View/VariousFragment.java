package com.hackathon.concord.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.hackathon.concord.R;

public class VariousFragment extends Fragment {

    // 프래그먼트 레이아웃 내에서 사용할 뷰들을 선언합니다

    public String readDay = null;
    public String str = null;
    public CalendarView calendarView;
    public Button btn_modify, btn_del, btn_save;
    public TextView txtContent, txtTitle;
    public EditText contextEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_various, container, false);

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
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // 날짜를 선택했을 때 처리하는 부분을 여기에 구현합니다
                btn_save.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
                txtContent.setVisibility(View.INVISIBLE);
                btn_modify.setVisibility(View.INVISIBLE);
                btn_del.setVisibility(View.INVISIBLE);
                contextEditText.setText("");
            }
        });

        return rootView;
    }

}
