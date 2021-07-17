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

import com.example.molytv.MoviePlayerActivity;
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

    private List<Slide> lstSlides,lstSlides2, lstSlides3 ;
    private ViewPager sliderpager,sliderpager2,sliderpager3,sliderpager4;
    private TabLayout indicator,indicator2,indicator3,indicator4,tabLayout;
    private RecyclerView MoviesRV,MoviesRV2 ;
    Context thisContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home,container,false);


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
        lstSlides2 = new ArrayList<>() ;
        lstSlides3 = new ArrayList<>() ;

        lstSlides.add(new Slide(R.drawable.silder1one,"Exclusive","zIzI0SPYdWY"));
        lstSlides.add(new Slide(R.drawable.img11,"Escape II","P6iZhLvKbVE"));
        lstSlides.add(new Slide(R.drawable.img13,"HOW TO VLOG - FIRST TIME","xMylvQZ_L_Q"));



        lstSlides3.add(new Slide(R.drawable.silder2two,"LAX PERFORMS @ SXSW2018","P6iZhLvKbVE"));
        lstSlides3.add(new Slide(R.drawable.img13,"LAX PERFORMS @ SXSW2018","a2a-ynXL65M"));
        lstSlides3.add(new Slide(R.drawable.img12,"MORIENTEZ- Acoustic Version of His single “See you Smile”","HR2fQ4fDVMw"));

        lstSlides2.add(new Slide(R.drawable.img14,"Escape II","P6iZhLvKbVE"));
        lstSlides2.add(new Slide(R.drawable.slider14,"\"Her\" Youtube Series, Black Women in the US","wcTVpw_vD8M"));

        SliderPagerAdapter adapter = new SliderPagerAdapter(thisContext,lstSlides,this);
        SliderPagerAdapter adapter2 = new SliderPagerAdapter(thisContext,lstSlides2,this);
        SliderPagerAdapter adapter3 = new SliderPagerAdapter(thisContext,lstSlides3,this);
        SliderPagerAdapter adapter4 = new SliderPagerAdapter(thisContext,lstSlides,this);

        sliderpager.setAdapter(adapter);
        sliderpager2.setAdapter(adapter2);
        sliderpager3.setAdapter(adapter3);
        sliderpager4.setAdapter(adapter4);

//        // setup timer
        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new HomeFragment.SliderTimer(),4000,6000);
        indicator.setupWithViewPager(sliderpager,true);
        indicator2.setupWithViewPager(sliderpager2,true);
        indicator3.setupWithViewPager(sliderpager,true);
        indicator4.setupWithViewPager(sliderpager2,true);

//        // Recyclerview Setup
//        // ini data

        List<Movie> lstMovies = new ArrayList<>();

//        Adding recylerview content

        lstMovies.add(new Movie("MOLY TEASER",
                "MOLY TV is an online television platform celebrating and showcasing the best of entertainment",
                R.drawable.molyshow,
                R.drawable.molyshow,
                "Moly Tv", "5-Start",
                "OgGx131D6bc"));

        lstMovies.add(new Movie("ESCAPE I",
                "ESCAPE IS AN ACTION DRAMA, IT IS ABOUT  RELATIONSHIP, FRIENDSHIP AND IT ALSO INVOLVES CRIME",
                R.drawable.image1rv,
                R.drawable.slider14,
                "Moly Tv", "5-Start",
                "P6iZhLvKbVE"));

        lstMovies.add(new Movie("Black Women",
                "Her story, her Dream, Her Life. Discover the woman in Her. Different women from all works of life share their stories... as real as it can be.",
                R.drawable.molyshow2,
                R.drawable.img11,
                "Moly Tv", "5-Start",
                "wcTVpw_vD8M"));

        lstMovies.add(new Movie("MOLY TEASER",
                "ESCAPE IS AN ACTION DRAMA, IT IS ABOUT  RELATIONSHIP, FRIENDSHIP AND IT ALSO INVOLVES CRIME",
                R.drawable.imgrv2,
                R.drawable.img12,
                "Moly Tv", "5-Start",
                "P6iZhLvKbVE"));

//        lstMovies.add(new Movie("Moana",R.drawable.image1rv,R.drawable.slider14));
//        lstMovies.add(new Movie("Black P",R.drawable.blackp,R.drawable.molyshow2));
//        lstMovies.add(new Movie("The Martian",R.drawable.themartian,R.drawable.img11));
//        lstMovies.add(new Movie("Moana",R.drawable.moana,R.drawable.spidercover));
//        lstMovies.add(new Movie("The Martian",R.drawable.themartian,R.drawable.spidercover));
//        lstMovies.add(new Movie("Black P",R.drawable.blackp,R.drawable.spidercover));
//        lstMovies.add(new Movie("The Martian",R.drawable.themartian,R.drawable.spidercover));


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
        MovieDetailFragment fragmentMovieDetail = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", movie.getTitle());
        bundle.putInt("imgURL", movie.getThumbnail());
        bundle.putInt("imgCover", movie.getCoverPhoto());
        bundle.putString("description", movie.getDescription());
        bundle.putString("videoLink", movie.getStreamingLink());
        fragmentMovieDetail.setArguments(bundle);

        // lets crezte the animation


        replaceFragment(fragmentMovieDetail,movieImageView);


//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this,
//                movieImageView,"sharedName");
//
//        startActivity(intent,options.toBundle());

    }

    @Override
    public void onMovieSlideClick(Slide slide) {
        Intent i = new Intent(getActivity(), MoviePlayerActivity.class);

        i.putExtra("videoLink", slide.getLink());

        startActivity(i);
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


//    class SliderTimer extends TimerTask {
//        @Override
//        public void run() {
//            //https://stackoverflow.com/questions/16425146/runonuithread-in-fragment
//            //https://stackoverflow.com/questions/8215308/using-context-in-a-fragment
//
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    if (sliderpager.getCurrentItem()<lstSlides.size()-1) {
//                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
//                        sliderpager2.setCurrentItem(sliderpager2.getCurrentItem()+1);
//                    }
//                    else
//                        sliderpager.setCurrentItem(0);
//                    sliderpager2.setCurrentItem(0);
//                }
//            });
//        }
//    }

}
