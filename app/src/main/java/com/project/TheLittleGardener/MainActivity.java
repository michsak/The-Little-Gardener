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
    /**music managing*/
    protected MediaPlayer mediaPlayer;
    protected MusicManagement menuMusicManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up music class and play menu music
        menuMusicManagement = new MusicManagement(mediaPlayer);
        menuMusicManagement.mp = MediaPlayer.create(this, R.raw.menu);
        menuMusicManagement.playMainMenuMusic();


    }

    public void playAction(View view)
    {
        //go to next scene
    }

    public void helpAction(View view)
    {
        //go to help scene
    }


    public void exitGame (View view)
    {
        finish();
    }
}