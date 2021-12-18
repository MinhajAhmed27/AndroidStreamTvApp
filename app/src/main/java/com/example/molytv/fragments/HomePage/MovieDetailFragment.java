package com.example.molytv.fragments.HomePage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
import com.example.molytv.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    FirebaseDatabase firbaseInstance;
    DatabaseReference DB_Reference;
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
        Movie favMovie = null;
        iniViews(view,favMovie);
        imgFavItem = view.findViewById(R.id.imgFavItem);
        Gson gson = new Gson();

        play_fab.setOnClickListener(v -> {
//                BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav);
//                navBar.setVisibility(View.INVISIBLE);
//                Fragment fragment = new LiveFragment();
//                replaceFragment(fragment);

            Intent i = new Intent(getActivity(), MoviePlayerActivity.class);

            i.putExtra("videoLink", VideoLink);

            startActivity(i);
        });



        imgFavItem.setOnClickListener(v -> {
            ArrayList wishList = new ArrayList();
            wishList.add(1);
            User newUser = new User(1576,"Some","Some@gmail.com","090078601", wishList);//Later will be fetch from Auth

            firbaseInstance = FirebaseDatabase.getInstance();
            DB_Reference = firbaseInstance.getReference("user");

//            //READ
//            DB_Reference.child("randomId").child("wishList").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DataSnapshot> task) {
//                    if (!task.isSuccessful()) {
//                        Log.e("firebase", "Error getting data", task.getException());
//                    }
//                    else {
//                        Log.d("firebase Get Value", String.valueOf(task.getResult().getValue()));
//                    }
//                }
//            });

//            WRITE
//            DB_Reference.child("randomId").setValue(newUser);

            String movieId = "17"; //Later come with the movie object in Bundle.!
            DB_Reference.child("randomId").child("wishList").child(movieId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase Get Value", String.valueOf(task.getResult().getValue()));
                        String getWish = String.valueOf(task.getResult().getValue());
                        if(!getWish.equals(movieId))
                        {
                            Toast.makeText(getContext(),"Added to Wishlist",Toast.LENGTH_SHORT).show();
                            DB_Reference.child("randomId").child("wishList").child(movieId).setValue(movieId);
                            imgFavItem.setImageResource(R.drawable.ic_baseline_favorite_24);
//                            TODO: Fill color red to heart

                        }else{
                            Toast.makeText(getContext(),"Remove to Wishlist",Toast.LENGTH_SHORT).show();
                            //Remove from firebase wishlist]
                            DB_Reference.child("randomId").child("wishList").child(movieId).removeValue();
                            imgFavItem.setImageResource(R.drawable.ic_baseline_favorite_border_24);


                        }
                    }
                }
            });

//            String uid = "Mik9IJF0";
//            String movieId = "WETWEaz5";
//            Map<String,Object> map = new HashMap<>();
//            map.put(movieId, movieId);
//            DB_Reference.child(uid).child("wishlist").updateChildren(map);

        });



//        if(favItem){
//            imgFavItem.setImageResource(R.drawable.ic_baseline_favorite_24);
//        }
//        else {
//            imgFavItem.setImageResource(R.drawable.ic_baseline_favorite_border_24);
//        }
//
//        imgFavItem.setOnClickListener(v -> {
//            //TODO save list in shared preferences
//
//            //TODO save the complete object in sp and deserialize it on favFargment to use it.
////            List<Movie> lstMovies = new ArrayList<>();
////            lstMovies.add(new Movie(VideoLink,imgResource));
//
//
//            if(!favItem){
////                if not favorite then make it. and save it to Shared Preferences.
//                //Retrieve the values
//                String getData = sharedPreferences.getString(key, "null");
//                Log.d("getDataList", "sharedPreferences data" + getData);
//
//                if(getData==""){
//                    List<String> videoLinkList = new ArrayList<>();
//                    videoLinkList.add(VideoLink);
//                    Log.d("videoLinkList", String.valueOf(videoLinkList));
//                    String jsonList = gson.toJson(videoLinkList);
//                    Log.d("jsonList", String.valueOf(jsonList));
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString(key,jsonList );
//                    editor.apply(); //before it was .commit!
////                    Toast.makeText(getActivity(),jsonList.toString(),Toast.LENGTH_LONG).show();
//
//                }
//                else {
//                    Type type = new TypeToken<List<String>>() {
//                    }.getType();
//
//                    //deserialize the list
//                    List<String> movieList = gson.fromJson(getData, type);
//                    imgFavItem.setImageResource(R.drawable.ic_baseline_favorite_24);
//                    movieList.add(VideoLink);
//                    Log.d("movieList", "Raw MovieList:"+movieList);
//
//                    //then restore the list to SP
//                    String jsonList = gson.toJson(movieList);
//                    Log.d("jsonList", "json MovieList:"+jsonList);
//
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString(key,jsonList );
//                    editor.apply(); //before it was .commit!
//                    String getDatas = sharedPreferences.getString(key, "null");
//                    Log.d("getDataList", "sharedPreferences data After" + getDatas);
//
//                    Toast.makeText(getActivity(),"Added in fav",Toast.LENGTH_SHORT).show();
////                    Toast.makeText(getActivity(),jsonList,Toast.LENGTH_LONG).show();
//
//                }
//                favItem =true;
//                imgFavItem.setImageResource(R.drawable.ic_baseline_favorite_24);
//            }
//            else {
//                //if is favorite then remove it
////                Removing movie
//                Type type = new TypeToken<List<String>>() {
//                }.getType();
//                String getData = sharedPreferences.getString(key, "");
//                List<String> movieList = gson.fromJson(getData, type);
//                movieList.remove(VideoLink);
//                Toast.makeText(getActivity(),"Remove from favorite",Toast.LENGTH_SHORT).show();
//
//
////                updating SP
//                String jsonList = gson.toJson(movieList);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString(key,jsonList );
//                editor.apply(); //before it was .commit!
//                Toast.makeText(getActivity(),jsonList,Toast.LENGTH_LONG).show();
//                String getDatass = sharedPreferences.getString(key, "null");
//                Log.d("getDataList", "sharedPreferences data After update" + getDatass);
//
//
//                favItem = false;
//                imgFavItem.setImageResource(R.drawable.ic_baseline_favorite_border_24);
//
//            }
//
//
//        });

        return view;
    }

    void iniViews(View view, Movie favMovie) {
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

        favMovie = new Movie(movieTitle,imageResourceId,imgCover);

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

//        Gson gson =new Gson();
//        List<String> movieList = gson.fromJson(getData, type);
//        if(movieList!=null){
//            if(movieList.contains(VideoLink)){
//                favItem = true;
//            }else {
//                favItem = false;
//            }
//        }


    }

}