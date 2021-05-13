package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MusicDetail extends AppCompatActivity {
    public TextView mTextView;
    public ImageButton playButton;
    public ImageButton nextButton;
    public ImageButton previousButton;
    public SeekBar seekbar;
    public TextView durationTextView;
    public TextView runningTextView;


    Runnable runnable;
    Handler handler;

    MediaPlayer media = new MediaPlayer();
    ArrayList<String> getList;

    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);
        setVariable();
        setTextView();
        seekbar.setMax(100);
        handler = new Handler();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextSong();
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousSong();
            }
        });

        Intent intent = getIntent();

        Uri uri = intent.getParcelableExtra("name");
        String name = intent.getStringExtra("musicName");


        try {
            media.setDataSource(this, uri);
            media.prepare();

        } catch (Exception e) {
            e.printStackTrace();
        }

        media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nextSong();
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int pos = (media.getDuration() / 100) * seekBar.getProgress();
                    media.seekTo(pos);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        getList = (ArrayList<String>) getIntent().getSerializableExtra("list");
        pos = getList.indexOf(name);

        durationTextView.setText(setTime(media.getDuration()));
    }

    public void setVariable() {
        mTextView = findViewById(R.id.musicName);
        playButton = findViewById(R.id.imageButton);
        nextButton = findViewById(R.id.imageButton2);
        previousButton = findViewById(R.id.imageButton3);
        seekbar = findViewById(R.id.seekBar);
        durationTextView = findViewById(R.id.timeDuration);
        runningTextView = findViewById(R.id.timeLeft);

    }
    public void setTextView() {
        Intent intent = getIntent();

        Uri uri = intent.getParcelableExtra("name");


        String musicName = intent.getStringExtra("musicName");





    }
    public Runnable update = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long curr = media.getCurrentPosition();
            runningTextView.setText(setTime(curr));

        }
    };

    public void updateSeekBar() {
        if (media.isPlaying()) {
            seekbar.setProgress((int) (((float) media.getCurrentPosition() / media.getDuration()) * 100));

            handler.postDelayed(update, 1000);
        }
    }




//function play music
    public void playMusic() {
        if (!media.isPlaying()) {
            media.start();
            updateSeekBar();
            playButton.setImageResource(R.drawable.ic_baseline_pause_24);
        } else {
            handler.removeCallbacks(update);
            media.pause();
            playButton.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        }
        Log.d("time", String.valueOf(media.getDuration()));
    }

    //function play next song
    public void nextSong() {
        if (pos < getList.size() - 1) {
            pos++;
        } else {
            pos = 0;
        }
        Uri uriNextSong = Uri.parse("android.resource://" + getPackageName() + "/raw/" + getList.get(pos));

        media.reset();
        try {
            media.setDataSource(this, uriNextSong);
            media.prepare();
            media.start();
            mTextView.setText(getList.get(pos));
            updateSeekBar();
            playButton.setImageResource(R.drawable.ic_baseline_pause_24);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //function play previous song
    public void previousSong() {
        if (pos == 0) {
            pos = getList.size() - 1;
        } else {
            pos--;
        }
        Uri uriNextSong = Uri.parse("android.resource://" + getPackageName() + "/raw/" + getList.get(pos));

        media.reset();
        try {
            media.setDataSource(this, uriNextSong);
            media.prepare();
            media.start();
            mTextView.setText(getList.get(pos));
            updateSeekBar();
            playButton.setImageResource(R.drawable.ic_baseline_pause_24);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("music", String.valueOf(pos));
    }

    //set timer for music player app
    public String setTime(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }
//back button to stop song from playing
    @Override
    protected void onStop() {
        super.onStop();
        media.stop();
    }
//click to play song
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String name = intent.getStringExtra("musicName");
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + name);

        media.reset();
        try {
            media.setDataSource(this, uri);
            media.prepare();
            media.start();
            mTextView.setText(name);
            updateSeekBar();
            playButton.setImageResource(R.drawable.ic_baseline_pause_24);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

