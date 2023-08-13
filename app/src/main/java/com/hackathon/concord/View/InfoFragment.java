package com.hackathon.concord.View;

import static android.app.Activity.RESULT_OK;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.hackathon.concord.Model.PetModel;
import com.hackathon.concord.Model.UserModel;
import com.hackathon.concord.Model.UserPetInfoModel;
import com.hackathon.concord.R;

public class InfoFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    UserPetInfoModel userPetInfoModel;
    ListView listView;
    InfoLiveViewAdapter adpater;
    Button btnRegister;
    Button btnTest;
    View rootView;
    Intent intent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_info, container, false);
        Bundle extra = this.getArguments();
        if(extra != null) {
            extra = getArguments();
            userPetInfoModel = (UserPetInfoModel) extra.getSerializable("info");
        }

        //adpater 선언
        adpater = new InfoLiveViewAdapter();
        listView = rootView.findViewById(R.id.petListView);
        btnRegister = rootView.findViewById(R.id.btnRegister);
        btnTest = rootView.findViewById(R.id.btnTest);

        adpater.addItem(userPetInfoModel.getPets());
        listView.setAdapter(adpater);

        //값 변경 알림
        adpater.notifyDataSetChanged();
        listView.setOnItemClickListener(this);
        btnRegister.setOnClickListener(this);
        btnTest.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), PetInfoActivity.class);
        intent.putExtra("pet_info", userPetInfoModel.getPets().get(position));
        startActivityForResult(intent, 400);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (resultCode == AppCompatActivity.RESULT_OK && data != null) {
                // RegisterActivity에서 전달한 데이터 받아오기
                PetModel updatedPet = (PetModel) data.getSerializableExtra("request_info");
                userPetInfoModel.getPets().add(updatedPet);
                adpater.createItem(updatedPet);
                listView.setAdapter(adpater);
                adpater.notifyDataSetChanged();
                // 받아온 데이터 활용
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                intent = new Intent(getActivity(), RegisterActivity.class);
                intent.putExtra("info", userPetInfoModel);
                startActivityForResult(intent, 200);
                break;
            case R.id.btnTest:
                intent = new Intent(getActivity(), TestActivity.class);
                startActivity(intent);
                break;
        }
    }
}