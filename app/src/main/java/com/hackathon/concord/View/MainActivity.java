package com.hackathon.concord.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hackathon.concord.Model.UserPetInfoModel;
import com.hackathon.concord.R;
import com.hackathon.concord.viewModel.InfoViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private InfoViewModel infoViewModel;
    private static final int REQUEST_CODE = 200;

    BottomNavigationView nav;
    InfoFragment infoFragment;
    VariousFragment variousFragment;
    RegFragment regFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        infoViewModel = new ViewModelProvider(this).get(InfoViewModel.class);

        UserPetInfoModel userPetInfoModel = (UserPetInfoModel) intent.getSerializableExtra("user_info");
        infoFragment = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("info",userPetInfoModel);
        infoFragment.setArguments(bundle);

        regFragment = new RegFragment();
        variousFragment = new VariousFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.navMain, infoFragment).commit();

        nav = findViewById(R.id.bottomNav);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab1:

                        getSupportFragmentManager().beginTransaction().replace(R.id.navMain, infoFragment).commit();
                        return true;
                    case R.id.tab2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.navMain, regFragment).commit();
                        return true;
                    case R.id.tab3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.navMain, variousFragment).commit();
                        return true;
                }
                return false;
            }
        });


    }

}