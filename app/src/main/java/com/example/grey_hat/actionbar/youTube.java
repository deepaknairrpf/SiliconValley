package com.example.grey_hat.actionbar;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.OrientationEventListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by grey-hat on 26/12/16.
 */

public class youTube extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayer player;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private static final String DEVELOPER_KEY="AIzaSyBI7q2aWR8VAfyigl-QVSfj0nUwA_0y61E";
    private static  String YOUTUBE_VIDEO_CODE;

    // YouTube player view
    private YouTubePlayerView youTubeView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.you_tube_playe);
            Intent intent = getIntent();
        YOUTUBE_VIDEO_CODE = intent.getStringExtra("Video_ID");
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtubePlayer);

        // Initializing video player with developer key
        youTubeView.initialize(DEVELOPER_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {

        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            player.loadVideo(YOUTUBE_VIDEO_CODE);
            this.player=player;

        }
    }



    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = "Error! ";
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtubePlayer);
    }


}
