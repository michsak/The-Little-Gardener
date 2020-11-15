package com.project.TheLittleGardener;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashSet;


//TODO
//quests
//watering animation with watering system
//help description with changing languages


public class PlayGameActivity extends AppCompatActivity
{
    private MediaPlayer mediaPlayer;
    private MusicManagement gameMusicManagement;
    private PlantManager[] plantManager;
    private ViewsHolder viewsHolder;
    private Joystick mainJoystick;
    private static TextView scoreTextView;
    private static int[] dropDownListImages;
    private static String[] dropDownListText;
    private HashSet<Integer> alreadyClickedPlantingButtons;
    private float distanceFromButton = 240f;
    public static int currentPlant = 0;
    public static int numberOfSeeds = 20;

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

        initializePlantsParams();
        playMainSceneMusic();
        findNecessaryViews();
        setScoreText();
        crateJoystick(height, width);
        initializeDropDownListParams();
    }

    private void initializePlantsParams()
    {
        plantManager = new PlantManager[15];
        alreadyClickedPlantingButtons = new HashSet<>();
    }

    private void playMainSceneMusic()
    {
        gameMusicManagement = new MusicManagement(mediaPlayer);
        gameMusicManagement.mp = MediaPlayer.create(this, R.raw.menu);      //to be changed
        gameMusicManagement.playMusic();
    }

    private void findNecessaryViews()
    {
        viewsHolder = new ViewsHolder(findViewById(R.id.playerView), findViewById(R.id.constraintLayout),
                findViewById(R.id.joystickView), findViewById(R.id.plantInfoTextView), findViewById(R.id.listView));
        scoreTextView = findViewById(R.id.scoreText);
    }

    public static void setScoreText()
    {
        scoreTextView.setText(Integer.toString(numberOfSeeds));
    }

    private void crateJoystick(int height, int width)
    {
        mainJoystick = new Joystick(viewsHolder.joystickView, viewsHolder.player, height, width);
        mainJoystick.createJoysticks();
    }

    private void initializeDropDownListParams()
    {
        dropDownListText = new String[] {PlantContainer.TREE.name(), PlantContainer.CORN.name(), PlantContainer.BEAN.name(),
                PlantContainer.BUSH.name(), PlantContainer.DAISY.name(), PlantContainer.CLOVER.name(), PlantContainer.MAIZE.name(),
                PlantContainer.MUSHROOMS.name(), PlantContainer.SUNFLOWER.name(), PlantContainer.NETTLE.name(),
                PlantContainer.FERN.name(), PlantContainer.MOSS.name(), PlantContainer.CABBAGE.name(),
                PlantContainer.CATTAIL.name(), PlantContainer.DANDELION.name()};

        dropDownListImages = new int[] {PlantContainer.TREE.getValue(), PlantContainer.CORN.getValue(), PlantContainer.BEAN.getValue(),
                PlantContainer.BUSH.getValue(), PlantContainer.DAISY.getValue(), PlantContainer.CLOVER.getValue(),
                PlantContainer.MAIZE.getValue(), PlantContainer.MUSHROOMS.getValue(), PlantContainer.SUNFLOWER.getValue(),
                PlantContainer.NETTLE.getValue(), PlantContainer.FERN.getValue(), PlantContainer.MOSS.getValue(),
                PlantContainer.CABBAGE.getValue(), PlantContainer.CATTAIL.getValue(), PlantContainer.DANDELION.getValue()};
    }

    public void checkPositionAndChangeImage (Button button, int buttonIndex)
    {
        if (countDistance(button.getX(), button.getY(), mainJoystick.getXPosition(), mainJoystick.getYPosition()) < distanceFromButton)
        {
            if (!alreadyClickedPlantingButtons.contains(buttonIndex))
            {
                plantManager[buttonIndex] = new PlantManager(button);
            }
            else
            {
                try
                {
                    plantManager[buttonIndex].collectOrSetUpPlant(button ,InGrowingProcessPlantContainer.PLANT.getValue());
                }
               catch (Exception e)
                {
                    e.printStackTrace();
                    plantManager[buttonIndex] = new PlantManager(button);
                }
            }
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

    public void onBottomButtonWithPlantsClick(View view)
    {
        switch (view.getTag().toString())
        {
            case "tree":
                this.currentPlant = 0;
                resizePlantTextViewInfoOnBottomButtonClick(PlantContainer.TREE.name());
                break;
            case "corn":
                this.currentPlant = 1;
                resizePlantTextViewInfoOnBottomButtonClick(PlantContainer.CORN.name());
                break;
            case "bean":
                this.currentPlant = 2;
                resizePlantTextViewInfoOnBottomButtonClick(PlantContainer.BEAN.name());
                break;
            case "bush":
                this.currentPlant = 3;
                resizePlantTextViewInfoOnBottomButtonClick(PlantContainer.BUSH.name());
                break;
            case "daisy":
                this.currentPlant = 4;
                resizePlantTextViewInfoOnBottomButtonClick(PlantContainer.DAISY.name());
                break;
            case "clover":
                this.currentPlant = 5;
                resizePlantTextViewInfoOnBottomButtonClick(PlantContainer.CLOVER.name());
                break;
        }
    }

    public void onClickButtonDropDownBoxList (View view)
    {
        CustomAdapter adapter = new CustomAdapter(this, dropDownListText, dropDownListImages, viewsHolder.parentView,
                viewsHolder.player, viewsHolder.listView);
        viewsHolder.listView.setAdapter(adapter);
    }

    private void resizePlantTextViewInfoOnBottomButtonClick(String text)
    {
        int startTextSize = 20;
        int endTextSize = 60;
        int animationDuration = 2000;
        viewsHolder.plantInfoTextView.setText(text);
        viewsHolder.plantInfoTextView.setVisibility(View.VISIBLE);

        /*animate changing size of TextView*/
        ValueAnimator animator = ValueAnimator.ofFloat(startTextSize, endTextSize);
        animator.setDuration(animationDuration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                viewsHolder.plantInfoTextView.setTextSize(animatedValue);
            }
        });
        animator.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                viewsHolder.plantInfoTextView.setVisibility(View.INVISIBLE);
            }
        });
        animator.start();
    }

    /*buttons where plants are placed functions*/
    //START
    public void button1ContainerOnClick(View view)
    {
        int buttonIndex = 0;
        Button button1 = findViewById(R.id.button);
        checkPositionAndChangeImage(button1, buttonIndex);
        alreadyClickedPlantingButtons.add(buttonIndex);
    }
    public void button2ContainerOnClick(View view)
    {
        int buttonIndex = 1;
        Button button2 = findViewById(R.id.button2);
        checkPositionAndChangeImage(button2, buttonIndex);
        alreadyClickedPlantingButtons.add(buttonIndex);
    }
    public void button3ContainerOnClick(View view)
    {
        int buttonIndex = 2;
        Button button3 = findViewById(R.id.button3);
        checkPositionAndChangeImage(button3, buttonIndex);
        alreadyClickedPlantingButtons.add(buttonIndex);
    }
    public void button4ContainerOnClick(View view)
    {
        int buttonIndex = 3;
        Button button4 = findViewById(R.id.button4);
        checkPositionAndChangeImage(button4, buttonIndex);
        alreadyClickedPlantingButtons.add(buttonIndex);
    }
    public void button5ContainerOnClick(View view)
    {
        int buttonIndex = 4;
        Button button5 = findViewById(R.id.button5);
        checkPositionAndChangeImage(button5, buttonIndex);
        alreadyClickedPlantingButtons.add(buttonIndex);
    }
    public void button6ContainerOnClick(View view)
    {
        int buttonIndex = 5;
        Button button6 = findViewById(R.id.button6);
        checkPositionAndChangeImage(button6, buttonIndex);
        alreadyClickedPlantingButtons.add(buttonIndex);
    }
    public void button7ContainerOnClick(View view)
    {
        int buttonIndex = 6;
        Button button7 = findViewById(R.id.button7);
        checkPositionAndChangeImage(button7, buttonIndex);
        alreadyClickedPlantingButtons.add(buttonIndex);
    }
    public void button8ContainerOnClick(View view)
    {
        int buttonIndex = 7;
        Button button8 = findViewById(R.id.button8);
        checkPositionAndChangeImage(button8, buttonIndex);
        alreadyClickedPlantingButtons.add(buttonIndex);
    }
    public void button9ContainerOnClick(View view)
    {
        int buttonIndex = 8;
        Button button9 = findViewById(R.id.button9);
        checkPositionAndChangeImage(button9, buttonIndex);
        alreadyClickedPlantingButtons.add(buttonIndex);
    }
    public void button10ContainerOnClick(View view)
    {
        int buttonIndex = 9;
        Button button10 = findViewById(R.id.button10);
        checkPositionAndChangeImage(button10, buttonIndex);
        alreadyClickedPlantingButtons.add(buttonIndex);
    }
    public void button11ContainerOnClick(View view)
    {
        int buttonIndex = 10;
        Button button11 = findViewById(R.id.button11);
        checkPositionAndChangeImage(button11, buttonIndex);
        alreadyClickedPlantingButtons.add(buttonIndex);
    }
    public void button12ContainerOnClick(View view)
    {
        int buttonIndex = 11;
        Button button12 = findViewById(R.id.button12);
        checkPositionAndChangeImage(button12, buttonIndex);
        alreadyClickedPlantingButtons.add(buttonIndex);
    }
    public void button13ContainerOnClick(View view)
    {
        int buttonIndex = 12;
        Button button13 = findViewById(R.id.button13);
        checkPositionAndChangeImage(button13, buttonIndex);
        alreadyClickedPlantingButtons.add(buttonIndex);
    }
    public void button14ContainerOnClick(View view)
    {
        int buttonIndex = 13;
        Button button14 = findViewById(R.id.button14);
        checkPositionAndChangeImage(button14, buttonIndex);
        alreadyClickedPlantingButtons.add(buttonIndex);
    }
    public void button15ContainerOnClick(View view)
    {
        int buttonIndex = 14;
        Button button15 = findViewById(R.id.button15);
        checkPositionAndChangeImage(button15, buttonIndex);
        alreadyClickedPlantingButtons.add(buttonIndex);
    }
    //END
}
