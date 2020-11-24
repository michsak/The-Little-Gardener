package com.project.TheLittleGardener;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashSet;

//TODO
//BETTER QUESTS, BETTER REWARDS, BETTER COST OF PLANTS
//images of plants, add cost to them
//image of player
//watering animation with watering system


public class PlayGameActivity extends AppCompatActivity
{
    private MediaPlayer mediaPlayer;
    private MusicManagement gameMusicManagement;
    private PlantManager[] plantManager;
    private ViewsHolder viewsHolder;
    private Joystick mainJoystick;
    private static TextView scoreTextView;
    private static TextView plantQuestTextView;
    private int[] dropDownListImages;
    private String[] dropDownListText;
    private HashSet<Integer> alreadyClickedPlantingButtons;
    private final float distanceFromButton = 230f;

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
        showQuestMessage("");
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
        plantQuestTextView = findViewById(R.id.plantQuestTextView);
    }

    public static void setScoreText()
    {
        scoreTextView.setText(Integer.toString(CurrentPlantAndNumberOfSeeds.getNumberOfSeeds()));
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
                    plantManager[buttonIndex].collectOrSetUpPlant(button, InGrowingProcessPlantContainer.PLANT.getValue());
                }
               catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            alreadyClickedPlantingButtons.add(buttonIndex);
        }

        /*
        Log.i("player x pos", Float.toString(mainJoystick.getXPosition()));
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
                CurrentPlantAndNumberOfSeeds.setCurrentPlant(0);
                resizePlantTextViewInfoOnBottomButtonClick(PlantContainer.TREE.name());
                break;
            case "corn":
                CurrentPlantAndNumberOfSeeds.setCurrentPlant(1);
                resizePlantTextViewInfoOnBottomButtonClick(PlantContainer.CORN.name());
                break;
            case "bean":
                CurrentPlantAndNumberOfSeeds.setCurrentPlant(2);
                resizePlantTextViewInfoOnBottomButtonClick(PlantContainer.BEAN.name());
                break;
            case "bush":
                CurrentPlantAndNumberOfSeeds.setCurrentPlant(3);
                resizePlantTextViewInfoOnBottomButtonClick(PlantContainer.BUSH.name());
                break;
            case "daisy":
                CurrentPlantAndNumberOfSeeds.setCurrentPlant(4);
                resizePlantTextViewInfoOnBottomButtonClick(PlantContainer.DAISY.name());
                break;
            case "clover":
                CurrentPlantAndNumberOfSeeds.setCurrentPlant(5);
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

    public void showQuestDescription(View view)
    {
        String questDescription = QuestDataManager.getQuestDescription();
        Toast.makeText(getApplicationContext(), questDescription, Toast.LENGTH_LONG).show();
    }

    public static void showQuestMessage(String beforeQuestDescriptionText)
    {
        plantQuestTextView.setVisibility(View.VISIBLE);
        String wholeText = beforeQuestDescriptionText + QuestDataManager.getQuestDescription();
        plantQuestTextView.setText(wholeText);
        CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l)
            {
            }

            @Override
            public void onFinish()
            {
                plantQuestTextView.setText("");
                plantQuestTextView.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    public void buttonContainerOnClick(View view)
    {
        int buttonIndex = Integer.parseInt((String) view.getTag());
        int buttonId = view.getId();
        Button button = findViewById(buttonId);
        checkPositionAndChangeImage(button, buttonIndex);
    }
}
