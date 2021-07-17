package com.example.molytv.fragments;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.example.molytv.LiveTvPlayerActivity;
import com.example.molytv.MoviePlayerActivity;
import com.example.molytv.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class CategoryMovieDetailFragment extends Fragment {

    private FloatingActionButton play_fab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayoutTop);
        tabLayout.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_category_movie_detail_fragment, container, false);

        iniViews(view);

        play_fab.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), MoviePlayerActivity.class);
            i.putExtra("videoLink", "xMylvQZ_L_Q");

            startActivity(i);

        });

        return view;
    }
    void iniViews(View view) {
        play_fab = view.findViewById(R.id.play_fab2);

        // setup animation
        play_fab.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.scale_animation));


    }

}