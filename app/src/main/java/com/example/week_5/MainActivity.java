package com.example.week_5;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.VideoView;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Playing App";
    private RadioGroup radioGrp; //Radio group object
    //private RadioButton radioGoogle, radioMusic, radioYouTube, radioVideo;

    private Button btnTry, btnClose; //Buttons

    //Video
    private VideoView videoView;
    private MediaController mController;

    //Music
    private MediaPlayer mPlayer;

    private int rSelected, rGoogle, rMusic, rYouTube, rVideo; //Radio buttons id's

    private String googleMapUrl, youTubeUrl; //URL's

    private Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate"); //Logs

        //URL's
        googleMapUrl = "https://www.google.ca/maps";
        youTubeUrl = "https://www.youtube.com/";

        //RadioGroup
        radioGrp = (RadioGroup) findViewById(R.id.radioGrp);

        //Create button object
        btnTry = (Button) findViewById(R.id.btnTry);
        btnClose = (Button) findViewById(R.id.btnClose);

        //Music player
        mPlayer = MediaPlayer.create(this, R.raw.sleep);

        //Video player
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVisibility(View.INVISIBLE);
        mController = new MediaController(this);
        mController.setAnchorView(videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.samplemov);
        videoView.setMediaController(mController);
        videoView.setVideoURI(uri);

        //All radio-buttons ids
        rGoogle = R.id.radioGoogle;
        Log.d("rGoogle", String.format("%d", rGoogle)); //Log
        rMusic = R.id.radioMusic;
        Log.d("rMusic", String.format("%d", rMusic)); //Log
        rYouTube = R.id.radioYouTube;
        Log.d("rYouTube", String.format("%d", rYouTube)); //Log
        rVideo = R.id.radioVideo;
        Log.d("rVideo", String.format("%d", rVideo)); //Log

        mPlayer = MediaPlayer.create(this, R.raw.sleep);

        //Try button event listener
        btnTry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("btnTry", "OnClickListener"); //Log

                try{
                    //Pause music
                    if(mPlayer.isPlaying()){
                        mPlayer.pause();
                    }
                    //Pause video
                    if(videoView.isPlaying()){
                        videoView.pause();
                    }

                    videoView.setVisibility(View.INVISIBLE); //Set video player invisible

                    rSelected = radioGrp.getCheckedRadioButtonId(); //Get selected option

                    Log.d("rSelected", String.format("%d", rSelected)); //Log

                    if(rSelected == rGoogle){
                        //Open google map
                        myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(googleMapUrl));
                        startActivity(myIntent);
                    }
                    else if(rSelected == rMusic){
                        //Start play music
                        mPlayer.start();
                    }
                    else if(rSelected == rYouTube){
                        //Open YouTube website
                        myIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(youTubeUrl));
                        startActivity(myIntent);
                    }
                    else if(rSelected == rVideo){
                        //Play video
                        videoView.setVisibility(View.VISIBLE);
                        videoView.requestFocus();
                        videoView.start();
                    }
                }
                catch(Exception ex){
                    ///Error
                    //Toast.makeText(getApplicationContext(), "Please select one of the listed options first", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), String.format("%s", ex.getMessage()), Toast.LENGTH_LONG).show();
                }
            }
        });

        //Close button event handler
        btnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Stop music
                if(mPlayer.isPlaying()){
                    mPlayer.stop();
                }

                //Stop video
                if(videoView.isPlaying()){
                    videoView.stopPlayback();
                }

                Log.d("btnClose", "Closing app"); //Log
                //Close the app
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart"); //Logs
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause"); //Logs
    }
}
