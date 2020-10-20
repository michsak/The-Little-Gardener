package com.project.TheLittleGardener;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity
{
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playMainMenuMusic();

    }

    public void playAction(View view)
    {
        //go to next scene
    }

    public void helpAction(View view)
    {
        //go to help scene
    }

    public void playMainMenuMusic()
    {
        mediaPlayer = MediaPlayer.create(this, R.raw.menu);
        mediaPlayer.start();
    }

    public void exitGame (View view)
    {
        finish();
    }
}