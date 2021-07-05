package com.example.molytv.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.molytv.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MovieDetailFragment extends Fragment {

    private ImageView MovieThumbnailImg,MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        iniViews(view);


        return view;

    }
    void iniViews(View view) {
//        play_fab = view.findViewById(R.id.play_fab);
//        String movieTitle = getIntent().getExtras().getString("title");
//        int imageResourceId = getIntent().getExtras().getInt("imgURL");
//        int imagecover = getIntent().getExtras().getInt("imgCover");
//        MovieThumbnailImg = findViewById(R.id.detail_movie_img);
//        Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
//        MovieThumbnailImg.setImageResource(imageResourceId);
//        MovieCoverImg = findViewById(R.id.detail_movie_cover);
//        Glide.with(this).load(imagecover).into(MovieCoverImg);
//        tv_title = findViewById(R.id.detail_movie_title);
//        tv_title.setText(movieTitle);
//        getSupportActionBar().setTitle(movieTitle);
//        tv_description = findViewById(R.id.detail_movie_desc);
//        // setup animation
//        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
//        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));





    }

}
