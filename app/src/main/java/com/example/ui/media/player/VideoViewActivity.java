package com.example.ui.media.player;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.ui.R;


public class VideoViewActivity extends AppCompatActivity {
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        //layout 中VideoView 外层加了一个RelativeLayout，这样MediaController的位置，就在Video View的底部
        setContentView(R.layout.activity_video_view);

        videoView = findViewById(R.id.videoView);

        videoView.setVideoURI(Uri.parse("http://192.168.0.104/%E9%92%A2%E7%9A%84%E7%90%B4.mkv"));

        videoView.setMediaController(new MediaController(this));


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });

        videoView.start();


    }

}
