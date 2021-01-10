package com.project.TheLittleGardener;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashSet;

//possible TODO in the future
//gardener watering animation
//buying avatars
//better movement system
//better graphics
//responsiveness for all screens

/**Contains methods connected with game screen and methods connected with buttons, which are placed on the play scene*/
public class PlayGameActivity extends AppCompatActivity
{
    private static TextView scoreTextView;
    private static TextView plantQuestTextView;
    private static Boolean isTextAnimating = false;
    private static float distanceFromButton = 330f;
    private PlantManager[] plantManager;
    private ViewsHolder viewsHolder;
    private Joystick mainJoystick;
    private int[] dropDownListImages;
    private String[] dropDownListText;
    private HashSet<Integer> alreadyClickedPlantingButtons;
    private HashSet<PlantManager> plantManagersInUse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        /*checks if a MenuInflater object exists in memory, used to delete double bar icons*/
        getMenuInflater();

        PlantsCostAndReward.initializePlantsIndexCostAndReward();
        initializePlantsParams();
        playMainSceneMusic();
        findNecessaryViews();
        setScoreText();
        crateJoystick();
        initializeDropDownListParams();
        showQuestMessage("");
    }

    private void initializePlantsParams()
    {
        plantManager = new PlantManager[15];
        alreadyClickedPlantingButtons = new HashSet<Integer>();
        plantManagersInUse = new HashSet<PlantManager>();
    }

    private void playMainSceneMusic()
    {
        MusicManager gameMusicManager = new MusicManager(new MediaPlayer());
        gameMusicManager.mp = MediaPlayer.create(this, R.raw.plantasia);
        gameMusicManager.playMusic();
    }

    private void findNecessaryViews()
    {
        viewsHolder = new ViewsHolder(findViewById(R.id.playerView), findViewById(R.id.constraintLayout),
                findViewById(R.id.joystickView), findViewById(R.id.plantInfoTextView), findViewById(R.id.listView),
                findViewById(R.id.backgroundView2), findViewById(R.id.backgroundView3), findViewById(R.id.backgroundView4));
        scoreTextView = findViewById(R.id.scoreText);
        plantQuestTextView = findViewById(R.id.plantQuestTextView);
    }

    public static void setScoreText()
    {
        scoreTextView.setText(Integer.toString(SeedsAndPlantNumber.getNumberOfSeeds()));
    }

    private void crateJoystick()
    {
        mainJoystick = new Joystick(viewsHolder.joystickView, viewsHolder.player, getApplicationContext());
        mainJoystick.createJoysticks();
    }

    private void initializeDropDownListParams()
    {
        dropDownListText = new String[] {DropDownListResources.TREE.name(), DropDownListResources.CORN.name(), DropDownListResources.BEAN.name(),
                DropDownListResources.BUSH.name(), DropDownListResources.DAISY.name(), DropDownListResources.CLOVER.name(), DropDownListResources.CACTUS.name(),
                DropDownListResources.MUSHROOMS.name(), DropDownListResources.SUNFLOWER.name(), DropDownListResources.NETTLE.name(),
                DropDownListResources.FERN.name(), DropDownListResources.MOSS.name(), DropDownListResources.CABBAGE.name(),
                DropDownListResources.CATTAIL.name(), DropDownListResources.DANDELION.name()};

        dropDownListImages = new int[] {DropDownListResources.TREE.getValue(), DropDownListResources.CORN.getValue(), DropDownListResources.BEAN.getValue(),
                DropDownListResources.BUSH.getValue(), DropDownListResources.DAISY.getValue(), DropDownListResources.CLOVER.getValue(),
                DropDownListResources.CACTUS.getValue(), DropDownListResources.MUSHROOMS.getValue(), DropDownListResources.SUNFLOWER.getValue(),
                DropDownListResources.NETTLE.getValue(), DropDownListResources.FERN.getValue(), DropDownListResources.MOSS.getValue(),
                DropDownListResources.CABBAGE.getValue(), DropDownListResources.CATTAIL.getValue(), DropDownListResources.DANDELION.getValue()};
    }

    /**Checks position of the player. If player is close enough and clicked button is not empty method enables player to collect plant.
     * In case that clicked button is empty sets up growing plant
     * @param button clicked button
     * @param buttonIndex index of the clicked button*/
    public void checkPositionAndChangeImage (Button button, int buttonIndex)
    {
        if (countDistance(button.getX(), button.getY(), mainJoystick.getXPosition(), mainJoystick.getYPosition()) < distanceFromButton)
        {
            if (!alreadyClickedPlantingButtons.contains(buttonIndex))
            {
                plantManager[buttonIndex] = new PlantManager(button);
                plantManagersInUse.add(plantManager[buttonIndex]);
            }
            else
            {
                try
                {
                    plantManager[buttonIndex].collectOrSetUpPlant(button, GrownPlantsContainer.PLANT.getValue());
                }
               catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            alreadyClickedPlantingButtons.add(buttonIndex);
        }
    }

    private float countDistance(float buttonXposition, float buttonYposition, float playerXposition, float playerYposition)
    {
        double xSquareDifference = Math.pow((buttonXposition-playerXposition), 2);
        double ySquareDifference = Math.pow((buttonYposition-playerYposition), 2);
        double difference = xSquareDifference + ySquareDifference;
        float distance = (float) Math.sqrt(difference);

        return distance;
    }

    /**Sets current plant and shows name of the chosen plant
     *@param view current view*/
    public void onBottomButtonWithPlantsClick(View view)
    {
        switch (view.getTag().toString())
        {
            case "tree":
                SeedsAndPlantNumber.setCurrentPlant(0);
                resizePlantTextViewInfoOnBottomButtonClick(DropDownListResources.TREE.name(), viewsHolder.plantInfoTextView);
                break;
            case "corn":
                SeedsAndPlantNumber.setCurrentPlant(1);
                resizePlantTextViewInfoOnBottomButtonClick(DropDownListResources.CORN.name(),viewsHolder.plantInfoTextView);
                break;
            case "bean":
                SeedsAndPlantNumber.setCurrentPlant(2);
                resizePlantTextViewInfoOnBottomButtonClick(DropDownListResources.BEAN.name(), viewsHolder.plantInfoTextView);
                break;
            case "bush":
                SeedsAndPlantNumber.setCurrentPlant(3);
                resizePlantTextViewInfoOnBottomButtonClick(DropDownListResources.BUSH.name(), viewsHolder.plantInfoTextView);
                break;
            case "daisy":
                SeedsAndPlantNumber.setCurrentPlant(4);
                resizePlantTextViewInfoOnBottomButtonClick(DropDownListResources.DAISY.name(), viewsHolder.plantInfoTextView);
                break;
            case "clover":
                SeedsAndPlantNumber.setCurrentPlant(5);
                resizePlantTextViewInfoOnBottomButtonClick(DropDownListResources.CLOVER.name(), viewsHolder.plantInfoTextView);
                break;
        }
    }

    /**Initializes CustomAdapter class
     * @param view current view*/
    public void onClickButtonDropDownBoxList (View view)
    {
        CustomAdapter adapter = new CustomAdapter(this, dropDownListText, dropDownListImages, viewsHolder.parentView,
                viewsHolder.player, viewsHolder.listView, plantQuestTextView, viewsHolder.plantInfoTextView, viewsHolder.backgroundViewUnderPerch,
                viewsHolder.backgroundViewUnderScore, viewsHolder.additionalGrayBackground);
        viewsHolder.listView.setAdapter(adapter);
    }

    public static void resizePlantTextViewInfoOnBottomButtonClick(String text, final TextView textView)
    {
        int startTextSize = 20;
        int endTextSize = 60;
        int animationDuration = 2000;

        if (!isTextAnimating)
        {
            isTextAnimating = true;
            textView.setText(text);
            textView.setVisibility(View.VISIBLE);

            /*animate changing size of TextView*/
            ValueAnimator animator = ValueAnimator.ofFloat(startTextSize, endTextSize);
            animator.setDuration(animationDuration);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
            {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator)
                {
                    float animatedValue = (float) valueAnimator.getAnimatedValue();
                    textView.setTextSize(animatedValue);
                }
            });
            animator.addListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    textView.setVisibility(View.INVISIBLE);
                    isTextAnimating = false;
                }
            });
            animator.start();

        }
    }

    public void showQuestDescription(View view)
    {
        String questDescription = QuestDataManager.getQuestDescription();
        if (plantedPlantsForQuestsAreNotNull())
        {
            Toast.makeText(getApplicationContext(), questDescription + TextCurrentNumberOfQuestPlantedPlants(), Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), questDescription, Toast.LENGTH_LONG).show();
        }
    }

    private boolean plantedPlantsForQuestsAreNotNull()
    {
        return QuestCompletionChecker.getKeys()[0] != "" || QuestCompletionChecker.getKeys()[1] != "";
    }

    private String TextCurrentNumberOfQuestPlantedPlants()
    {
        return "\nCurrent number of planted plants:\n" +
                       QuestCompletionChecker.getKeys()[0] + ": " + QuestCompletionChecker.getValues()[0] + " " +
                        QuestCompletionChecker.getKeys()[1] + ": " + QuestCompletionChecker.getValues()[1];
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

    public void buttonResetQuest (View view)
    {
        for (PlantManager currentPlantManager : plantManagersInUse)
        {
            currentPlantManager.clearCurrentPlant();
        }
        QuestDataManager.clearListOfPlantedPlantsForQuest();
        SeedsAndPlantNumber.setNumberOfSeeds(SeedsAndPlantNumber.getNumberOfSeedsBeforeQuestCompletion());
        QuestCompletionChecker.clearValues();
        setScoreText();
    }

    public static void setDistanceFromButton(float value)
    {
        distanceFromButton = value;
    }
}
