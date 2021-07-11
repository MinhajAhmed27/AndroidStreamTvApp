package com.example.molytv;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.molytv.fragments.CategoryFragment;
import com.example.molytv.fragments.FavFragment;
import com.example.molytv.fragments.HomeFragment;
import com.example.molytv.fragments.LiveStreamFragment;
import com.example.molytv.fragments.MoreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ConstraintLayout mainLayout;
    BottomNavigationView bottomNavigationView;
    Context mContext;

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainContainer);
        bottomNavigationView =findViewById(R.id.bottom_nav);

        tabLayout = findViewById(R.id.tabLayoutTop);
        tabLayout.setVisibility(View.VISIBLE);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //do stuff here
                switch (tab.getPosition()){
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,GetSelectedFragmentForTab()).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FavFragment()).commit();
                        break;

                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


//        bottomNavigationView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
        //bottomNavigationView.setBackgroundColor(Color.parseColor(ColorTransparentUtils.transparentColor(R.color.colorAccent,90)));

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }

    Fragment DefaultSelectedFragment = new HomeFragment();
    public void SetDefaultSelectedFragmentForTablayout(Fragment selectedFragment){
        DefaultSelectedFragment = selectedFragment;
    }
    public Fragment GetSelectedFragmentForTab(){
        return DefaultSelectedFragment;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.home:
                    selectedFragment=new HomeFragment();
                    break;
                case R.id.category:
                    selectedFragment=new CategoryFragment();
                    break;
                case R.id.live:
                    selectedFragment=new LiveStreamFragment();
                    break;
                case R.id.more:
                    selectedFragment=new MoreFragment();
                    mainLayout.setBackgroundColor(Color.WHITE);
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).addToBackStack(null).commit();
            SetDefaultSelectedFragmentForTablayout(selectedFragment);


            return true;
        }
    };
}