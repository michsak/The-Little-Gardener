package com.project.TheLittleGardener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity
{
    /*music managing*/
    protected MediaPlayer mediaPlayer;
    protected MusicManagement menuMusicManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checks if a MenuInflater object exists in memory, used to delete double bar icons
        getMenuInflater();

        /*set up music class and play menu music*/
        menuMusicManagement = new MusicManagement(mediaPlayer);
        menuMusicManagement.mp = MediaPlayer.create(this, R.raw.menu);
        menuMusicManagement.playMusic();
    }

    /**goes to play Activity*/
    public void playAction(View view)
    {
        startActivity(new Intent(MainActivity.this, PlayGame.class));
        menuMusicManagement.stopMusic();
    }

    /**goes to help Activity*/
    public void helpAction(View view)
    {
        startActivity(new Intent(MainActivity.this, HelpActivity.class));
    }

    /**exits the game*/
    public void exitGame (View view)
    {
        finish();
    }
}