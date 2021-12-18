package com.example.molytv;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.util.SparseArray;
//import android.view.View;
//import android.widget.ImageButton;
//
//import com.google.android.exoplayer2.MediaItem;
//import com.google.android.exoplayer2.SimpleExoPlayer;
//import com.google.android.exoplayer2.source.MediaSource;
//import com.google.android.exoplayer2.source.MergingMediaSource;
//import com.google.android.exoplayer2.source.ProgressiveMediaSource;
//import com.google.android.exoplayer2.ui.PlayerView;
//import com.google.android.exoplayer2.upstream.DataSource;
//import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
//import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
//import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
//import com.google.android.exoplayer2.util.Util;
//
//import at.huber.youtubeExtractor.VideoMeta;
//import at.huber.youtubeExtractor.YouTubeExtractor;
//import at.huber.youtubeExtractor.YtFile;
//
//public class VideoPlayerActivity extends AppCompatActivity {
//    SimpleExoPlayer player;
//    PlayerView playerView;
//
//    private boolean playWhenReady = true;
//    private int currentWindow = 0;
//    private long playbackPosition = 0;
//
//    String videoURL = "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4";
////    String videoURL = "https://www.youtube.com/watch?v=4HC-v65q4V8&ab_channel=CafeMusicBGMchannel";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_video_player);
//
//
//        playerView = findViewById(R.id.video_view);
//        ImageButton bt = findViewById(R.id.exo_close);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                VideoPlayerActivity.this.finish();
//            }
//        });
//    }
//
//    private void initializePlayer() {
//
//        player = new SimpleExoPlayer.Builder(this).build();
//        playerView.setPlayer(player);
//
////        playYoutubeVideo("https://www.youtube.com/watch?v=d_n9cB9jVJw");
//        playYoutubeVideo("https://www.youtube.com/get_video_info?video_id=mciibqxFOk8&html5=1&eurl=https%3A%2F%2Fyoutube.googleapis.com%2Fv%2FmciibqxFOk8");
//
////        for normal video
////        MediaItem mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp4));
////        player.setMediaItem(mediaItem);
////        player.setPlayWhenReady(playWhenReady);
////        player.seekTo(currentWindow, playbackPosition);
////        player.prepare();
//
//    }
//
//    private void playYoutubeVideo(String youtubeURL) {
//
//        new YouTubeExtractor(this)
//        {
//
//            @Override
//            protected void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta videoMeta) {
//
//                if(ytFiles != null){
//                    int videoTag = 137;
//                    int audioTag = 140;
//                    MediaSource audioSource = new ProgressiveMediaSource
//                            .Factory(new DataSource.Factory() {
//                        @Override
//                        public DataSource createDataSource() {
//                            return null;
//                        }
//                    })
//                            .createMediaSource((MediaItem.fromUri(ytFiles.get(audioTag).getUrl())));
//
//                    MediaSource videoSource = new ProgressiveMediaSource
//                            .Factory(new DataSource.Factory() {
//                        @Override
//                        public DataSource createDataSource() {
//                            return null;
//                        }
//                    })
//                            .createMediaSource((MediaItem.fromUri(ytFiles.get(videoTag).getUrl())));
//
//                    player.setMediaSource(new MergingMediaSource(
//                            true,
//                            videoSource,
//                            audioSource),
//                            true
//                    );
//                    player.prepare();
//                    player.setPlayWhenReady(playWhenReady);
//                    player.seekTo(currentWindow,playbackPosition);
//                }
//            }
//        }.extract(youtubeURL, true, true);
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        if(Util.SDK_INT>=24){
//            initializePlayer();
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if(Util.SDK_INT < 24 || player == null){
//            initializePlayer();
//            hideSystemUi();
//
//        }
//    }
//
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        if(Util.SDK_INT < 24){
//            releasePlayer();
//        }
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if(Util.SDK_INT >= 24){
//            releasePlayer();
//        }
//    }
//
//    private void releasePlayer() {
//        if (player != null) {
//            playWhenReady = player.getPlayWhenReady();
//            playbackPosition = player.getCurrentPosition();
//            currentWindow = player.getCurrentWindowIndex();
//            player.release();
//            player = null;
//        }
//    }
//
//
//    @SuppressLint("InlinedApi")
//    private void hideSystemUi() {
//        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//
//    }
//
////    @Override
////    public void onDetach() {
////        super.onDetach();
////        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
////    }
//
//    private void showSystemUI() {
//        View decorView = this.getWindow().getDecorView();
//        decorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        );
//    }
//
////
////    @Override
////    public void onWindowFocusChanged(boolean hasFocus) {
////        if (hasFocus) {
////            hideSystemUi();
////        }
////        else{
////            showSystemUI();
////        }
////    }
//
////    private void showSystemUI() {
////        if (Util.SDK_INT >= 30) {
////            pla.setDecorFitsSystemWindows(false)
////            val controller = window.insetsController
////            if (controller != null) {
////                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
////                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
////
////            } else {
////            playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
////                    |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
////                    |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
////        }
//
//
//
//}
//
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoPlayerActivity extends AppCompatActivity {
    // id of the video
    // which we are playing.
//    String video_id = "vG2PNdI8axo";
    String video_id = "kf-PCH-JmeM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // below two lines are used to set our
        // screen orientation in landscape mode.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        requestWindowFeature(Window.FEATURE_SWIPE_TO_DISMISS);
//        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LOCAL_FOCUS_MODE,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

//        View.SYSTEM_UI_FLAG_LOW_PROFILE
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        setContentView(R.layout.activity_video_player);

        // below line of code is
        // to hide our action bar.
//        getSupportActionBar().hide();

        // declaring variable for youtubeplayer view
        final YouTubePlayerView youTubePlayerView = findViewById(R.id.videoPlayer);

        // below line is to place your youtube player in a full screen mode (i.e landscape mode)
        youTubePlayerView.enterFullScreen();
        youTubePlayerView.toggleFullScreen();

        // here we are adding observer to our youtubeplayerview.
        getLifecycle().addObserver(youTubePlayerView);

        // below method will provides us the youtube player
        // ui controller such as to play and pause a video
        // to forward a video
        // and many more features.
        youTubePlayerView.getPlayerUiController();

        // below line is to enter full screen mode.
        youTubePlayerView.enterFullScreen();
        youTubePlayerView.toggleFullScreen();

        // adding listener for our youtube player view.
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                // loading the selected video into the YouTube Player
                youTubePlayer.loadVideo(video_id, 0);
            }

            @Override
            public void onStateChange(@NonNull YouTubePlayer youTubePlayer,
                                      @NonNull PlayerConstants.PlayerState state) {
                // this method is called if video has ended,
                super.onStateChange(youTubePlayer, state);
            }
        });
    }


}