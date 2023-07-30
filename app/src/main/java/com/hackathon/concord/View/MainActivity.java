package com.hackathon.concord.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hackathon.concord.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView nav;
    InfoFragment infoFragment;
    VariousFragment variousFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoFragment = new InfoFragment();
        variousFragment = new VariousFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.bottomNav, infoFragment).commit();

        nav = findViewById(R.id.bottomNav);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.bottomNav, infoFragment).commit();
                        return true;
                    case R.id.tab2:
                        return true;
                    case R.id.tab3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.bottomNav, variousFragment).commit();
                        return true;
                }
                return false;
            }
        });


    }
}