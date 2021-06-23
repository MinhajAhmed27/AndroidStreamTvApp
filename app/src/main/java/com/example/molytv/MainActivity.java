package com.example.molytv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.molytv.fragments.CategoryFragment;
import com.example.molytv.fragments.HomeFragment;
import com.example.molytv.fragments.MoreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout topNavTablayout;
    ConstraintLayout mainLayout;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainContainer);
        topNavTablayout = findViewById(R.id.tabLayoutTop);
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_nav);
//        bottomNavigationView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
        //bottomNavigationView.setBackgroundColor(Color.parseColor(ColorTransparentUtils.transparentColor(R.color.colorAccent,90)));
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.home:
                    selectedFragment=new HomeFragment();
                    topNavTablayout.setVisibility(View.VISIBLE);
                    break;
                case R.id.category:
                    selectedFragment=new CategoryFragment();
                    topNavTablayout.setVisibility(View.VISIBLE);
                    break;
                case R.id.more:
                    selectedFragment=new MoreFragment();
                    topNavTablayout.setVisibility(View.GONE);
                    mainLayout.setBackgroundColor(Color.WHITE);
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

            return true;
        }
    };
}