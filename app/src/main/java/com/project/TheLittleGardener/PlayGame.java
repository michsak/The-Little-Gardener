package com.project.TheLittleGardener;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class PlayGame extends AppCompatActivity
{
    protected MediaPlayer mediaPlayer;
    protected MusicManagement gameMusicManagement;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        //set up music class and play main scene music
        gameMusicManagement = new MusicManagement(mediaPlayer);
        gameMusicManagement.mp = MediaPlayer.create(this, R.raw.menu);      //to be changed
        gameMusicManagement.playMusic();

        //take size of the screen
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        //starts game loop, player movements, updating, etc.
        gameView = new GameView(this, point.x, point.y);

        //show view on the screen
        setContentView(gameView);


        //hide navigation bar and support bar
        /*View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();
    */
    }

    //stop game
    @Override
    protected void onPause()
    {
        super.onPause();
        gameView.pause();
    }

    //resume to game
    @Override
    protected void onResume()
    {
        super.onResume();
        gameView.resume();
    }

}