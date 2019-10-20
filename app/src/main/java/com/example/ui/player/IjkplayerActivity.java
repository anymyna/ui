package com.example.ui.player;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TextView;
import com.example.ui.R;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.example.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;


public class IjkplayerActivity extends AppCompatActivity {
    private IjkVideoView mVideoView;
    private AndroidMediaController mMediaController;
    private TextView mTextView;
    private String mVideoPath;
    private TableLayout mHudView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijkplayer);
        startVideo();

    }

    private void startVideo() {

        //mVideoPath = "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8";
        mVideoPath = "http://192.168.0.104/%E9%92%A2%E7%9A%84%E7%90%B4.mkv";//局域网测试视频
        mMediaController = new AndroidMediaController(this, false);

        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        mVideoView = (IjkVideoView) findViewById(R.id.videoView);
        mHudView = (TableLayout) findViewById(R.id.hud_view);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setHudView(mHudView);

        mVideoView.setVideoURI(Uri.parse(mVideoPath));
        mVideoView.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mVideoView.stopPlayback();
        mVideoView.release(true);
        mVideoView.stopBackgroundPlay();
        IjkMediaPlayer.native_profileEnd();
    }

}
