package com.example.molytv.fragments.LiveTvPage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.molytv.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LiveStreamFragment extends Fragment {

    FloatingActionButton floatingActionButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_stream, container, false);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(v -> {
//                BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav);
//                navBar.setVisibility(View.INVISIBLE);
//                Fragment fragment = new LiveFragment();
//                replaceFragment(fragment);

            Intent intent = new Intent(getActivity(), LiveTvPlayerActivity.class);
            startActivity(intent);

        });
        return view;
    }

//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayoutTop);
//        tabLayout.setVisibility(View.GONE);
//    }

}