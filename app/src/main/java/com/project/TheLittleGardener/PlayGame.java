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
    private MediaPlayer mediaPlayer;
    private MusicManagement gameMusicManagement;
    private JoystickView joystickView;
    private Joystick mainJoystick;
    private ImageView player;
    private View parentView;
    private ListView lView;
    private float distanceFromButton = 240f;
    private static int[] dropDownListImages;
    private static String[] dropDownListText;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        /*checks if a MenuInflater object exists in memory, used to delete double bar icons*/
        getMenuInflater();

        /*initialize x and y variable, which enables player move*/
        Point point = new Point();

        /*get params of screen to enable player move*/
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        playMainSceneMusic();
        findNecessaryViews();
        crateJoystick(height, width);
        initializeDropDownListParams();
    }

    private void crateJoystick(int height, int width)
    {
        mainJoystick = new Joystick(joystickView, player, height, width);
        mainJoystick.createJoysticks();
    }

    private void findNecessaryViews()
    {
        player = findViewById(R.id.playerView);
        parentView = findViewById(R.id.constraintLayout);
        joystickView = findViewById(R.id.joystickView);
    }

    private void playMainSceneMusic()
    {
        gameMusicManagement = new MusicManagement(mediaPlayer);
        gameMusicManagement.mp = MediaPlayer.create(this, R.raw.menu);      //to be changed
        gameMusicManagement.playMusic();
    }

    private void initializeDropDownListParams()
    {
        dropDownListText = new String[] {PlantNamesContainer.TREE.name(), PlantNamesContainer.CORN.name(), PlantNamesContainer.BEAN.name(),
                PlantNamesContainer.BUSH.name(), PlantNamesContainer.DAISY.name(), PlantNamesContainer.CLOVER.name(), PlantNamesContainer.MAIZE.name(),
                PlantNamesContainer.MUSHROOMS.name(), PlantNamesContainer.SUNFLOWER.name(), PlantNamesContainer.NETTLE.name(),
                PlantNamesContainer.FERN.name(), PlantNamesContainer.MOSS.name(), PlantNamesContainer.CABBAGE.name(),
                PlantNamesContainer.CATTAIL.name(), PlantNamesContainer.DANDELION.name()};

        dropDownListImages = new int[] {PlantNamesContainer.TREE.getValue(), PlantNamesContainer.CORN.getValue(), PlantNamesContainer.BEAN.getValue(),
                PlantNamesContainer.BUSH.getValue(), PlantNamesContainer.DAISY.getValue(), PlantNamesContainer.CLOVER.getValue(),
                PlantNamesContainer.MAIZE.getValue(), PlantNamesContainer.MUSHROOMS.getValue(), PlantNamesContainer.SUNFLOWER.getValue(),
                PlantNamesContainer.NETTLE.getValue(), PlantNamesContainer.FERN.getValue(), PlantNamesContainer.MOSS.getValue(),
                PlantNamesContainer.CABBAGE.getValue(), PlantNamesContainer.CATTAIL.getValue(), PlantNamesContainer.DANDELION.getValue()};
    }

    public void onClickButtonDropDownBoxList (View view)
    {
        lView = findViewById(R.id.listView);
        MyCustomAdapter adapter = new MyCustomAdapter(this, dropDownListText, dropDownListImages, parentView, player, lView);
        lView.setAdapter(adapter);
    }

    public void checkPositionAndChangeImage (Button button, int resource)
    {
        Log.i("button distance", Float.toString(countDistance(button.getX(), button.getY(),
                mainJoystick.getXPositon(), mainJoystick.getYPosition())));
        if (countDistance(button.getX(), button.getY(), mainJoystick.getXPositon(), mainJoystick.getYPosition()) < distanceFromButton)
        {
            button.setBackgroundResource(resource);
        }
        /*
        Log.i("player x pos", Float.toString(mainJoystick.getXPositon()));
        Log.i("player y pos", Float.toString(mainJoystick.getYPosition()));
        Log.i("player button x pos", Float.toString(button.getX()));
        Log.i("player button y pos", Float.toString(button.getY()));*/
    }

    private float countDistance(float buttonXposition, float buttonYposition, float playerXposition, float playerYposition)
    {
        double xSquareDifference = Math.pow((buttonXposition-playerXposition), 2);
        double ySquareDifference = Math.pow((buttonYposition-playerYposition), 2);
        double difference = xSquareDifference + ySquareDifference;
        float distance = (float) Math.sqrt(difference);
        return distance;
    }


    /*buttons where plants are placed functions*/
    //START
    public void button1ContainerOnClick(View view)
    {
        Button button1 = findViewById(R.id.button);
        checkPositionAndChangeImage(button1, R.drawable.empty_player);
    }
    public void button2ContainerOnClick(View view)
    {
        Button button2 = findViewById(R.id.button2);
        checkPositionAndChangeImage(button2, R.drawable.empty_player);
    }
    public void button3ContainerOnClick(View view)
    {
        Button button3 = findViewById(R.id.button3);
        checkPositionAndChangeImage(button3, R.drawable.empty_player);
    }
    public void button4ContainerOnClick(View view)
    {
        Button button4 = findViewById(R.id.button4);
        checkPositionAndChangeImage(button4, R.drawable.empty_player);
    }
    public void button5ContainerOnClick(View view)
    {
        Button button5 = findViewById(R.id.button5);
        checkPositionAndChangeImage(button5, R.drawable.empty_player);
    }
    public void button6ContainerOnClick(View view)
    {
        Button button6 = findViewById(R.id.button6);
        checkPositionAndChangeImage(button6, R.drawable.empty_player);
    }
    public void button7ContainerOnClick(View view)
    {
        Button button7 = findViewById(R.id.button7);
        checkPositionAndChangeImage(button7, R.drawable.empty_player);
    }
    public void button8ContainerOnClick(View view)
    {
        Button button8 = findViewById(R.id.button8);
        checkPositionAndChangeImage(button8, R.drawable.empty_player);
    }
    public void button9ContainerOnClick(View view)
    {
        Button button9 = findViewById(R.id.button9);
        checkPositionAndChangeImage(button9, R.drawable.empty_player);
    }
    public void button10ContainerOnClick(View view)
    {
        Button button10 = findViewById(R.id.button10);
        checkPositionAndChangeImage(button10, R.drawable.empty_player);
    }
    public void button11ContainerOnClick(View view)
    {
        Button button11 = findViewById(R.id.button11);
        checkPositionAndChangeImage(button11, R.drawable.empty_player);
    }
    public void button12ContainerOnClick(View view)
    {
        Button button12 = findViewById(R.id.button12);
        checkPositionAndChangeImage(button12, R.drawable.empty_player);
    }
    public void button13ContainerOnClick(View view)
    {
        Button button13 = findViewById(R.id.button13);
        checkPositionAndChangeImage(button13, R.drawable.empty_player);
    }
    public void button14ContainerOnClick(View view)
    {
        Button button14 = findViewById(R.id.button14);
        checkPositionAndChangeImage(button14, R.drawable.empty_player);
    }
    public void button15ContainerOnClick(View view)
    {
        Button button15 = findViewById(R.id.button15);
        checkPositionAndChangeImage(button15, R.drawable.empty_player);
    }
    //END
}
