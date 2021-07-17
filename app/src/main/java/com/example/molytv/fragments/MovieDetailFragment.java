package com.example.molytv.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.molytv.MoviePlayerActivity;
import com.example.molytv.R;
import com.example.molytv.VideoPlayerActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MovieDetailFragment extends Fragment {

    private ImageView MovieThumbnailImg;
    private ImageView MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayoutTop);
        tabLayout.setVisibility(View.GONE);
    }

    private String VideoLink = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        iniViews(view);

        play_fab.setOnClickListener(v -> {
//                BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav);
//                navBar.setVisibility(View.INVISIBLE);
//                Fragment fragment = new LiveFragment();
//                replaceFragment(fragment);

            Intent i = new Intent(getActivity(), MoviePlayerActivity.class);

            i.putExtra("videoLink", VideoLink);

                    startActivity(i);
        });


        return view;
    }
    void iniViews(View view) {
        play_fab = view.findViewById(R.id.play_fab);

        String movieTitle = "";
        int imageResourceId = 0;
        int imgCover = 0;
//        int thumbnail = 0; //thumbnail set default to cover in Movie Model
//        String studio = "";
//        String rating = "";
        String streamingLink = "";
        String description = "";


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            movieTitle = bundle.getString("title");
            imageResourceId = bundle.getInt("imgURL");
            imgCover = bundle.getInt("imgCover");
            description = bundle.getString("description");
            streamingLink = bundle.getString("videoLink");
        }

        MovieThumbnailImg = view.findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
        MovieThumbnailImg.setImageResource(imageResourceId);
        MovieCoverImg = view.findViewById(R.id.detail_movie_cover);
        Glide.with(this).load(imgCover).into(MovieCoverImg);
        tv_title = view.findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);

        tv_description = view.findViewById(R.id.detail_movie_desc);
        tv_description.setText(description);

        VideoLink = streamingLink;
        // setup animation
        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.scale_animation));


    }

}
