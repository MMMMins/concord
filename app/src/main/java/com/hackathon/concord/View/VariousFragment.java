package com.hackathon.concord.View;

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
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.hackathon.concord.Model.DiaryModel;
import com.hackathon.concord.R;
import com.hackathon.concord.databinding.FragmentVariousBinding;
import com.hackathon.concord.viewModel.DiaryService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VariousFragment extends Fragment implements View.OnClickListener {

    private String readDay = null;
    private CalendarView calendarView;
    private Button btn_modify, btn_del, btn_save;
    private TextView txtContent, txtTitle, txtSelDate;
    private EditText contextEditText;
    private DiaryService diaryService;
    private FragmentVariousBinding binding;

    private String testDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_various, container, false);

        calendarView = binding.calendarView;
        btn_save = binding.btnSave;
        btn_del = binding.btnDel;
        btn_modify = binding.btnModify;
        txtContent = binding.txtContent;
        txtTitle = binding.txtTitle;
        contextEditText = binding.contextEditText;
        txtSelDate = binding.txtSelDate;

        // 달력 날짜 선택 리스너를 설정합니다
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // 날짜를 선택했을 때 처리하는 부분을 여기에 구현합니다
            btn_save.setVisibility(View.VISIBLE);
            contextEditText.setVisibility(View.VISIBLE);
            txtContent.setVisibility(View.INVISIBLE);
            btn_modify.setVisibility(View.INVISIBLE);
            btn_del.setVisibility(View.INVISIBLE);
            contextEditText.setText("");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

            txtSelDate.setText(String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth));
            // 클릭 이벤트를 VariousFragment 클래스 내에서 처리하도록 설정합니다
            btn_save.setOnClickListener(VariousFragment.this);
        });

        // ViewModel을 생성하고 바인딩합니다
        diaryService = new ViewModelProvider(this).get(DiaryService.class);
        binding.setViewModel(diaryService);
        binding.setLifecycleOwner(this);

        // ViewModel에서 데이터가 변경되었을 때 UI 업데이트를 수신하여 처리
        diaryService.getDiaryModel().observe(getViewLifecycleOwner(), new Observer<DiaryModel>() {
            @Override
            public void onChanged(DiaryModel diaryModel) {
                // 데이터를 처리하여 UI에 업데이트
                contextEditText.setText(diaryModel.getEtc());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        // 버튼 클릭 이벤트 처리
        if (v.getId() == R.id.btn_save) {
            // 저장 버튼을 클릭한 경우
            // ViewModel의 데이터 저장 메서드를 호출하고, 저장 결과를 처리합니다
            diaryService.onSaveClick();
        }
    }
}
