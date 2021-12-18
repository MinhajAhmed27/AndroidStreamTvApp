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

import com.example.molytv.fragments.CategoryPage.CategoryFragment;
import com.example.molytv.fragments.FavoritePage.FavFragment;
import com.example.molytv.fragments.FavoritePage.ListOfFavoriteMoviesFragment;
import com.example.molytv.fragments.HomePage.HomeFragment;
import com.example.molytv.fragments.LiveTvPage.LiveStreamFragment;
import com.example.molytv.fragments.MoreSettingsPage.MoreFragment;
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ListOfFavoriteMoviesFragment()).commit();
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

            tabLayout.getTabAt(0).select();//default top navigation

            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.home:
                    selectedFragment=new HomeFragment();
                    break;
                case R.id.category:
                    selectedFragment=new CategoryFragment();
//                    tabLayout.setVisibility(View.VISIBLE);

                    break;
                case R.id.live:
                    selectedFragment=new LiveStreamFragment();
                    break;
                case R.id.more:
                    selectedFragment=new MoreFragment();
//                    tabLayout.setVisibility(View.GONE);
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).addToBackStack(null).commit();
            SetDefaultSelectedFragmentForTablayout(selectedFragment);


            return true;
        }
    };
}