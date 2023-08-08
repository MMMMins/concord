package com.hackathon.concord.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hackathon.concord.Model.UserModel;
import com.hackathon.concord.Model.UserPetInfoModel;
import com.hackathon.concord.R;

public class InfoFragment extends Fragment {
    UserPetInfoModel userPetInfoModel;
    ListView listView;
    InfoLiveViewAdapter adpater;

    public InfoFragment(UserPetInfoModel userPetInfoModel){
        this.userPetInfoModel = userPetInfoModel;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info, container, false);

        //adpater 선언
        adpater = new InfoLiveViewAdapter();
        listView = (ListView) v.findViewById(R.id.petListView);
        listView.setAdapter(adpater);

        adpater.addItem(userPetInfoModel.getPets());

        //값 변경 알림
        adpater.notifyDataSetChanged();

        return v;
    }
}