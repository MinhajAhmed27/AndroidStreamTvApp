package com.example.molytv.fragments.HomePage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.molytv.MoviePlayerActivity;
import com.example.molytv.R;
import com.example.molytv.models.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MovieDetailFragment extends Fragment {

    private ImageView MovieThumbnailImg;
    private ImageView MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;
    ImageView imgFavItem;
    List<Movie> movieList;
    boolean favItem = false;
    Context context = getActivity();
    private String VideoLink = "";
    private int imgResource = 0;


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String key = "videoKey";
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayoutTop);
        tabLayout.setVisibility(View.GONE);

        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

    }

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



        imgFavItem = view.findViewById(R.id.imgFavItem);

        if(favItem){
            imgFavItem.setImageResource(R.drawable.ic_baseline_favorite_24);
        }
        else {
            imgFavItem.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }

        imgFavItem.setOnClickListener(v -> {
            //TODO save list in shared preferences

//            List<Movie> lstMovies = new ArrayList<>();
//            lstMovies.add(new Movie(VideoLink,imgResource));


            Gson gson = new Gson();
            if(!favItem){
//                if not favorite then make it. and save it to Shared Preferences.
                //Retrieve the values
                String getData = sharedPreferences.getString(key, "");
                if(getData.isEmpty()){
                    List<String> videoLinkList = new ArrayList<>();
                    videoLinkList.add(VideoLink);

                    String jsonList = gson.toJson(videoLinkList);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(key,jsonList );
                    editor.apply(); //before it was .commit!
//                    Toast.makeText(getActivity(),jsonList.toString(),Toast.LENGTH_LONG).show();

                }
                else {
                    Type type = new TypeToken<List<String>>() {
                    }.getType();

                    //deserialize the list
                    List<String> movieList = gson.fromJson(getData, type);

                    imgFavItem.setImageResource(R.drawable.ic_baseline_favorite_24);
                    movieList.add(VideoLink);
                    //then restore the list to SP
                    String jsonList = gson.toJson(movieList);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(key,jsonList );
                    editor.apply(); //before it was .commit!

                    Toast.makeText(getActivity(),"Added in fav",Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(),jsonList,Toast.LENGTH_LONG).show();

                }
                favItem =true;
                imgFavItem.setImageResource(R.drawable.ic_baseline_favorite_24);
            }
            else {
                //if is favorite then remove it
//                Removing movie
                Type type = new TypeToken<List<String>>() {
                }.getType();
                String getData = sharedPreferences.getString(key, "");
                List<String> movieList = gson.fromJson(getData, type);
                movieList.remove(VideoLink);
                Toast.makeText(getActivity(),"Remove from favorite",Toast.LENGTH_SHORT).show();



//                updating SP
                String jsonList = gson.toJson(movieList);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(key,jsonList );
                editor.apply(); //before it was .commit!
                Toast.makeText(getActivity(),jsonList,Toast.LENGTH_LONG).show();


                favItem = false;
                imgFavItem.setImageResource(R.drawable.ic_baseline_favorite_border_24);

            }


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
        imgResource = imageResourceId;

        // setup animation
        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.scale_animation));

        String getData = sharedPreferences.getString(key, "");
        Type type = new TypeToken<List<String>>() {
        }.getType();
        //deserialize the list and add VideoLink
        Gson gson =new Gson();
        List<String> movieList = gson.fromJson(getData, type);
        if(movieList!=null){
            if(movieList.contains(VideoLink)){
                favItem = true;
            }else {
                favItem = false;
            }
        }


    }

}