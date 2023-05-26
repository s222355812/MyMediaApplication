package com.example.mymediaapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class StreamContentActivity extends AppCompatActivity {

    EditText urlET;
    Button fetchBtn;
    StyledPlayerView styledPlayerView;
    ExoPlayer player;
    DefaultDataSource.Factory dataSourceFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streamvideo);

        styledPlayerView = findViewById(R.id.exoPlayer);
        fetchBtn = findViewById(R.id.fetchBtn);
        urlET = findViewById(R.id.urlET);

        fetchBtn.setOnClickListener(v -> {
            if (urlET.getText().toString().isEmpty()) {
                urlET.setError("Please enter a URL");
            } else {
                releasePlayer();
                initializePlayer();
                playStream(urlET.getText().toString());
            }
        });

        // Set the video URL
        String videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
        urlET.setText(videoUrl);

        // Automatically play the video on app launch
        releasePlayer();
        initializePlayer();
        playStream(videoUrl);
    }

    private void initializePlayer() {
        player = new ExoPlayer.Builder(getBaseContext()).build();
        styledPlayerView.setPlayer(player);
        dataSourceFactory = new DefaultDataSource.Factory(this);
        player.setPlayWhenReady(true);
    }

    private void playStream(String url) {
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(Uri.parse(url)));
        player.setMediaSource(mediaSource);
        player.prepare();
    }

    private void releasePlayer() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }
}
