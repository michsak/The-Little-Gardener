package com.project.TheLittleGardener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class PlayGame extends AppCompatActivity
{
    Boolean isActive = true;
    protected MediaPlayer mediaPlayer;
    protected MusicManagement gameMusicManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        //set up music class and play main scene music
        gameMusicManagement = new MusicManagement(mediaPlayer);
        gameMusicManagement.mp = MediaPlayer.create(this, R.raw.menu);      //to be changed
        gameMusicManagement.playMusic();

        //hide status bar
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //objects in class Game are rendered to screen
        setContentView(new Game(this));
    }
}