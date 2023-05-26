package com.example.mymediaapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 101;

    private Button playLocalAudioButton;
    private Button playUrlAudioButton;
    private Button openCameraButton;
    private Button playVideoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playLocalAudioButton = findViewById(R.id.playLocalAudioButton);
        playUrlAudioButton = findViewById(R.id.playUrlAudioButton);
        openCameraButton = findViewById(R.id.openCameraButton);
        playVideoButton = findViewById(R.id.playVideoButton);
        openCameraButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, CameraActivity.class);
            startActivity(intent);
        });
        playVideoButton.setOnClickListener(v -> {
            openStreamContentActivity();
        });
        // Play audio from a local file
        playLocalAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.sample_audio);
                mediaPlayer.start();
            }
        });

        // Play audio from a URL
        playUrlAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String audioUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3";
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(audioUrl);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        // Play video from the internet
//        playVideoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String videoUrl = "https://vod-progressive.akamaized.net/exp=1685098351~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F4467%2F14%2F372335193%2F1547101002.mp4~hmac=a13827b3ee850423ee7b1a3b539c51bf612e84cf6baddb6f52750c81609636f3/vimeo-prod-skyfire-std-us/01/4467/14/372335193/1547101002.mp4";
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
//                startActivity(intent);
//            }
//        });
//    }
    }

        public void openStreamContentActivity () {
            Intent intent = new Intent(this, StreamContentActivity.class);
            startActivity(intent);
        }
        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        }
    }