package com.example.wathingvieos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    public final static String EXTRA_MESSAGER = "com.example.wathingvieos.messager213";

    VideoView videoView;

    String[] masvideoaddress = {"https://archive.org/download/sinema-trailer_caligula/Caligula%20%281979%29%20ORIGINAL%20TRAILER%20%28480p_30fps_H264-128kbit_AAC%29.mp4",
            "https://archive.org/download/CC_1914_08_31_TheGoodforNothing/CC_1914_08_31_TheGoodforNothing_512kb.mp4",
            "https://archive.org/download/charlie_chaplin_film_fest/charlie_chaplin_film_fest_512kb.mp4",
            "https://archive.org/download/disorder_in_the_court/disorder_in_the_court_512kb.mp4"};
    String videoAddress;
    int pos;

    Button pause;
    Button start;
    Button stop;
    Button kommentarii;

    public String videoid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        videoid = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        videoView = (VideoView) findViewById(R.id.videoView);

        pause = (Button) findViewById(R.id.button);
        start = (Button) findViewById(R.id.button2);
        stop = (Button) findViewById(R.id.button3);
        kommentarii = (Button) findViewById(R.id.button4);
        pause.setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        kommentarii.setOnClickListener(this);

        pos = Integer.parseInt(videoid);
        videoAddress = masvideoaddress[pos];
        Uri videoUri = Uri.parse(videoAddress);
        videoView.setVideoURI(videoUri);
        MediaController videocontroller = new MediaController(this);
        videocontroller.setAnchorView(videoView);
        videoView.setMediaController(videocontroller);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        videocontroller.setAnchorView(videoView);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                PauseVideo();
                break;
            case R.id.button2:
                StartVideo();
                break;
            case R.id.button3:
                StopVideo();
                break;
            case R.id.button4:
                Intent q = new Intent(MainActivity2.this, MainActivity3.class);
                q.putExtra(EXTRA_MESSAGER, videoid);
                startActivity(q);
                break;
        }
    }
    private void PauseVideo() {
        videoView.pause();
    }
    private void StartVideo(){
        videoView.start();
    }
    private void StopVideo(){
        videoView.stopPlayback();
        videoView.resume();
    }
}