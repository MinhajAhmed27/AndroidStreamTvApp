package com.example.molytv.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.molytv.MainActivity;
import com.example.molytv.R;
import com.example.molytv.adapters.IOnFocusListenable;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LiveFragment extends Fragment {

    SimpleExoPlayer player;
    PlayerView playerView;

    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    String videoURL = "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4";
//    String videoURL = "https://www.youtube.com/watch?v=4HC-v65q4V8&ab_channel=CafeMusicBGMchannel";

    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity().getApplicationContext();


        View view =  inflater.inflate(R.layout.fragment_live, container, false);

        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav);
//        navBar.setVisibility(View.INVISIBLE);

         playerView = view.findViewById(R.id.video_view);
        ImageButton bt = view.findViewById(R.id.exo_close);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSystemUI();
                getActivity().onBackPressed();
                navBar.setVisibility(View.VISIBLE);
//              getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

            }
        });


        return view;
    }

    private void initializePlayer() {

        player = new SimpleExoPlayer.Builder(context).build();
        playerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp4));
        player.setMediaItem(mediaItem);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare();

    }

    @Override
    public void onStart() {
        super.onStart();
        if(Util.SDK_INT>24){
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if(Util.SDK_INT < 24 || player == null){
            initializePlayer();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if(Util.SDK_INT < 24){
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(Util.SDK_INT >= 24){
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }


    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    private void showSystemUI() {
        View decorView = getActivity().getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        );
    }

//
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        if (hasFocus) {
//            hideSystemUi();
//        }
//        else{
//            showSystemUI();
//        }
//    }

//    private void showSystemUI() {
//        if (Util.SDK_INT >= 30) {
//            pla.setDecorFitsSystemWindows(false)
//            val controller = window.insetsController
//            if (controller != null) {
//                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
//                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//
//            } else {
//            playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }


}