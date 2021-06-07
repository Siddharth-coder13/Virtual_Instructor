package com.release.virtualinstructor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.PlayerUiController;

public class Youtube extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        // Hide status bar
        /*View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent i= getIntent();
        final String VideoId = i.getStringExtra("VideoId");

        final YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube);
        getLifecycle().addObserver(youTubePlayerView);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("To see in VR mode, click youtube icon below, and attach your phone to a VR set.");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                youTubePlayerView.getYouTubePlayerWhenReady(new YouTubePlayerCallback() {
                    @Override
                    public void onYouTubePlayer(YouTubePlayer youTubePlayer) {
                        if(VideoId.isEmpty()){
                            Toast.makeText(Youtube.this, "No video link found", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            youTubePlayer.loadVideo(VideoId, 0);
                        }
                    }
                });
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }
}
