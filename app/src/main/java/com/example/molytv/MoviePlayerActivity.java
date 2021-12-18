package com.example.molytv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MoviePlayerActivity extends YouTubeBaseActivity {

    private YouTubePlayerView youTubePlay;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private String videoLink = "";
    private final String KEY="AIzaSyBB-LL4h9jO0c7SHaK_ZHe4ZGIP9EVDVWw";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_player);

        youTubePlay = findViewById(R.id.youtubePlayer);

        //RECEIVE DATA VIA INTENT
        Intent i = getIntent();
        videoLink = i.getStringExtra("videoLink");

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

//                youTubePlayer.loadVideo("kf-PCH-JmeM");

//                for playlist
//                youTubePlayer.loadPlaylist("PLfA45DOnRB1lathPGvJJNgqs5rZQ_RkwD");

                youTubePlayer.loadVideo(videoLink);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youTubePlay.initialize(KEY,onInitializedListener);

//        youTubePlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                youTubePlay.initialize(KEY,onInitializedListener);
//
//            }
//        });


    }
}