package com.hackathon.concord.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hackathon.concord.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView nav;
    InfoFragment infoFragment;
    VariousFragment variousFragment;
    RegFragment regFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoFragment = new InfoFragment();
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