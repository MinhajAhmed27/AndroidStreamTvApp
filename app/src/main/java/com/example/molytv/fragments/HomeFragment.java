package com.example.molytv.fragments;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.molytv.R;
import com.example.molytv.adapters.MovieAdapter;
import com.example.molytv.adapters.MovieItemClickListener;
import com.example.molytv.adapters.SliderPagerAdapter;
import com.example.molytv.models.Movie;
import com.example.molytv.models.Slide;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment implements MovieItemClickListener {

    private List<Slide> lstSlides ;
    private ViewPager sliderpager,sliderpager2,sliderpager3,sliderpager4;
    private TabLayout indicator,indicator2,indicator3,indicator4,tabLayout;
    private RecyclerView MoviesRV,MoviesRV2 ;
    Context thisContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home,container,false);
//        super.onCreate(savedInstanceState);

        thisContext = container.getContext();
        tabLayout = getActivity().findViewById(R.id.tabLayoutTop);
        tabLayout.setVisibility(View.VISIBLE);
        sliderpager = view.findViewById(R.id.slider_pager) ;
        sliderpager2 = view.findViewById(R.id.slider_pager2) ;
        sliderpager3 = view.findViewById(R.id.slider_pager3) ;
        sliderpager4 = view.findViewById(R.id.slider_pager4) ;


        MoviesRV = view.findViewById(R.id.Rv_movies);
        MoviesRV2 = view.findViewById(R.id.Rv_movies2);

        indicator = view.findViewById(R.id.indicator);
        indicator2 = view.findViewById(R.id.indicator2);
        indicator3 = view.findViewById(R.id.indicator3);
        indicator4 = view.findViewById(R.id.indicator4);
//
//        // prepare a list of slides ..
        lstSlides = new ArrayList<>() ;
        lstSlides.add(new Slide(R.drawable.slide1,"Slide Title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide2,"Slide Title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide1,"Slide Title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide2,"Slide Title \nmore text here"));
        SliderPagerAdapter adapter = new SliderPagerAdapter(thisContext,lstSlides);
        sliderpager.setAdapter(adapter);
        sliderpager2.setAdapter(adapter);
        sliderpager3.setAdapter(adapter);
        sliderpager4.setAdapter(adapter);

//        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeFragment.SliderTimer(),4000,6000);
        indicator.setupWithViewPager(sliderpager,true);
        indicator2.setupWithViewPager(sliderpager2,true);
        indicator3.setupWithViewPager(sliderpager,true);
        indicator4.setupWithViewPager(sliderpager2,true);

//        // Recyclerview Setup
//        // ini data

        List<Movie> lstMovies = new ArrayList<>();
        lstMovies.add(new Movie("Moana",R.drawable.moana,R.drawable.spidercover));
        lstMovies.add(new Movie("Black P",R.drawable.blackp,R.drawable.spidercover));
        lstMovies.add(new Movie("The Martian",R.drawable.themartian,R.drawable.spidercover));
        lstMovies.add(new Movie("Moana",R.drawable.moana,R.drawable.spidercover));
        lstMovies.add(new Movie("The Martian",R.drawable.themartian,R.drawable.spidercover));
        lstMovies.add(new Movie("Black P",R.drawable.blackp,R.drawable.spidercover));
        lstMovies.add(new Movie("The Martian",R.drawable.themartian,R.drawable.spidercover));

        MovieAdapter movieAdapter = new MovieAdapter(thisContext,lstMovies,this);
        MoviesRV.setAdapter(movieAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(thisContext,LinearLayoutManager.HORIZONTAL,false));
        MoviesRV2.setAdapter(movieAdapter);
        MoviesRV2.setLayoutManager(new LinearLayoutManager(thisContext,LinearLayoutManager.HORIZONTAL,false));

        return view;
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
//         here we send movie information to detail activity
//         also we ll create the transition animation between the two activity

        //Put the value
        MovieDetailFragment fragmentMovieDetail = new MovieDetailFragment ();
        Bundle bundle = new Bundle();
        bundle.putString("title", movie.getTitle());
        bundle.putInt("imgURL", movie.getThumbnail());
        bundle.putInt("imgCover", movie.getCoverPhoto());
        fragmentMovieDetail.setArguments(bundle);

        // lets crezte the animation



        replaceFragment(fragmentMovieDetail,movieImageView);


//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this,
//                movieImageView,"sharedName");
//
//        startActivity(intent,options.toBundle());

    }

    private void replaceFragment(Fragment fragment, ImageView movieImageView) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addSharedElement(movieImageView, "sharedName");
        fragmentTransaction.replace(R.id.fragment_container, fragment);

        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.addSharedElement(movieImageView, "sharedName");

        fragmentTransaction.commit();
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());


    class SliderTimer extends TimerTask {
        @Override
        public void run() {
            //https://stackoverflow.com/questions/16425146/runonuithread-in-fragment
            //https://stackoverflow.com/questions/8215308/using-context-in-a-fragment

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem()<lstSlides.size()-1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                        sliderpager2.setCurrentItem(sliderpager2.getCurrentItem()+1);
                    }
                    else
                        sliderpager.setCurrentItem(0);
                    sliderpager2.setCurrentItem(0);
                }
            });
        }
    }

}
