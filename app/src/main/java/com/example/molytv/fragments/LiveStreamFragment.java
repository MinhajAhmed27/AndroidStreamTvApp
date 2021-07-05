package com.example.molytv.fragments;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.molytv.R;
import com.example.molytv.VideoPlayerActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class LiveStreamFragment extends Fragment {

    FloatingActionButton floatingActionButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_stream, container, false);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav);
//                navBar.setVisibility(View.INVISIBLE);
//                Fragment fragment = new LiveFragment();
//                replaceFragment(fragment);

                Intent intent = new Intent(getActivity(), VideoPlayerActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayoutTop);
        tabLayout.setVisibility(View.GONE);
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}