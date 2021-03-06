package com.example.molytv.fragments.LiveTvPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.molytv.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class LiveTvPlayerActivity extends YouTubeBaseActivity {

    private YouTubePlayerView youTubePlay;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private final String KEY="AIzaSyBB-LL4h9jO0c7SHaK_ZHe4ZGIP9EVDVWw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tv_player);

        youTubePlay = findViewById(R.id.LiveYoutubePlayer);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

//                youTubePlayer.loadVideo("kf-PCH-JmeM");

//                for playlist
//                youTubePlayer.loadPlaylist("PLfA45DOnRB1lathPGvJJNgqs5rZQ_RkwD");

//                youTubePlayer.loadVideo("kf-PCH-JmeM");
//                youTubePlayer.loadVideo("aa7thsuTjk4");
                youTubePlayer.loadVideo("zlsdlxe1c3Y");
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