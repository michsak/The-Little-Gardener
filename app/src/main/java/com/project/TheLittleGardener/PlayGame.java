package com.project.TheLittleGardener;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zerokol.views.joystickView.JoystickView;

import java.util.ArrayList;


public class PlayGame extends AppCompatActivity
{
    protected MediaPlayer mediaPlayer;
    protected MusicManagement gameMusicManagement;
    private ImageView player;
    private JoystickView joystick;

    //used for custom adapter
    public static int [] ddListImages={R.drawable.gardenerbackground,R.drawable.gardenerbackground, R.drawable.gardenerbackground, R.drawable.gardenerbackground};
    public static String [] ddListText={"item 1","item 2", "item3", "item4"};

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

        //take player imageView
        player = findViewById(R.id.playerView);

        //hide navigation bar and support bar
        /*View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();
    */
        //params of screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        //create joystick and player movement system
        joystick = findViewById(R.id.joystickView);
        Joystick mainJoystick = new Joystick(joystick, player, height, width);
        mainJoystick.createJoysticks();


    }

    public void dropDownBoxList (View view)
    {
        //instantiate custom adapter
        MyCustomAdapter adapter = new MyCustomAdapter(this, ddListText, ddListImages);

        //handle listview and assign adapter
        ListView lView = findViewById(R.id.listView);
        lView.setAdapter(adapter);
    }
}
