package com.project.TheLittleGardener;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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
    private JoystickView joystick;
    private ImageView player;
    private View parentView;
    public ListView lView;

    private MenuInflater mMenuInflater;

    /*variables for drop down box list*/
    public static int [] ddListImages={R.drawable.gardenerbackground,R.drawable.gardenerbackground,
            R.drawable.gardenerbackground, R.drawable.gardenerbackground};
    public static String [] ddListText={"item 1","item 2", "item3", "item4"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        //checks if a MenuInflater object exists in memory, used to delete double bar icons
        getMenuInflater();

        /*set up music class and play main scene music*/
        gameMusicManagement = new MusicManagement(mediaPlayer);
        gameMusicManagement.mp = MediaPlayer.create(this, R.raw.menu);      //to be changed
        gameMusicManagement.playMusic();

        //initialize x and y variable, which enables player move*/
        Point point = new Point();

        /*find necessary Views*/
        player = findViewById(R.id.playerView);
        parentView = findViewById(R.id.constraintLayout);

        /*hide navigation bar*
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);*/

        /*get params of screen to enable player move*/
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        /*create joystick - player movement system*/
        joystick = findViewById(R.id.joystickView);
        Joystick mainJoystick = new Joystick(joystick, player, height, width);
        mainJoystick.createJoysticks();
    }

    /*executes on click drop down box list button*/
    public void dropDownBoxList (View view)
    {
        lView = findViewById(R.id.listView);
        MyCustomAdapter adapter = new MyCustomAdapter(this, ddListText, ddListImages, parentView, player, lView);
        lView.setAdapter(adapter);
    }
}
