package com.project.TheLittleGardener;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.Button;

/**Manages growing plant process*/
public class PlantManager extends SeedsAndPlantNumber implements GrowingUpParams
{
    private String plantedPlant;
    private boolean isReadyToBeCollected = false;
    private Button currentButton;
    private Boolean plantIsRising = false;
    private int initialSeedsNumber;

    PlantManager(Button button)
    {
        collectOrSetUpPlant(button, GrownPlantsContainer.PLANT.getValue());
        countDownToPlantSeedling(button);
    }

    @Override
    public void countDownToPlantSeedling(final Button button)
    {
        plantedPlant = PlantsCostAndReward.getPlantsIndex().get(getCurrentPlant()).name();

        if (numberOfSeedsAboveZero())
        {
            plantIsRising = true;
            button.setEnabled(false);
            isReadyToBeCollected = false;

            new CountDownTimer(timeOfGrowingSeedling, growingTimeInterval)
            {
                int growingPlantResource = PlantsCostAndReward.getPlantsIndex().get(getCurrentPlant()).getValue();

                @Override
                public void onTick(long l) { }

                @Override
                public void onFinish()
                {
                    button.setBackgroundResource(growingPlantResource);
                    button.setEnabled(true);
                    currentButton = button;
                    isReadyToBeCollected = true;
                    plantIsRising = false;
                }
            }.start();
        }
    }

    private boolean numberOfSeedsAboveZero()
    {
        return initialSeedsNumber - PlantsCostAndReward.getCostOfEachPlant().get((plantedPlant)) >= 0;
    }

    @Override
    public void collectOrSetUpPlant(Button button, int resource)
    {
        if (isReadyToBeCollected)
        {
            button.setBackgroundResource(R.color.brown);
            isReadyToBeCollected = false;
            QuestDataManager.addToPlantedPlantsForQuest(plantedPlant);
            addNumberOfSeedsAndSetText();

            /*checking if quest is completed*/
            if (questIsCompleted())
            {
                int questReward = QuestDataManager.getQuestReward(QuestDataManager.getCurrentNumberOfQuest());
                addAdditionalSeeds(questReward);
                QuestDataManager.changeOccurrenceOfQuest(QuestDataManager.getCurrentNumberOfQuest());
                PlayGameActivity.showQuestMessage("Congratulations! You have completed quest " +
                        QuestDataManager.getCurrentNumberOfQuest() + ".\nNow ");
                setNumberOfSeedsBeforeQuestCompletion(getNumberOfSeeds());
            }
        }

        else
        {
            String nameOfCurrentPlant = PlantsCostAndReward.getPlantsIndex().get(getCurrentPlant()).name();
            int costOfCurrentPlant = PlantsCostAndReward.getCostOfEachPlant().get(nameOfCurrentPlant);
            if (getNumberOfSeeds() - costOfCurrentPlant >= 0)
            {
                decreaseNumberOfSeedsAndSetText(nameOfCurrentPlant);
                button.setBackgroundResource(resource);
                countDownToPlantSeedling(button);
            }
        }
    }

    private boolean questIsCompleted()
    {
        return QuestCompletionChecker.checkIfQuestIsCompleted() && !QuestDataManager.getOccurrenceOfQuest()[QuestDataManager.getCurrentNumberOfQuest()];
    }

    @Override
    public void decreaseNumberOfSeedsAndSetText(String nameOfCurrentPlant)
    {
        int numberOfSeeds;
        numberOfSeeds = getNumberOfSeeds();
        initialSeedsNumber = numberOfSeeds;
        numberOfSeeds -= PlantsCostAndReward.getCostOfEachPlant().get(nameOfCurrentPlant);
        setNumberOfSeeds(numberOfSeeds);
        PlayGameActivity.setScoreText();
    }

    @Override
    public void addNumberOfSeedsAndSetText()
    {
        String nameOfCollectedPlant = plantedPlant;
        int numberOfSeeds;
        plantedPlant = "";
        numberOfSeeds = getNumberOfSeeds();
        numberOfSeeds += PlantsCostAndReward.getRewardForEachPlant().get(nameOfCollectedPlant);
        setNumberOfSeeds(numberOfSeeds);
        PlayGameActivity.setScoreText();
    }

    @Override
    public void addAdditionalSeeds(int number)
    {
        int wholeNumberOfSeeds;
        wholeNumberOfSeeds = getNumberOfSeeds();
        wholeNumberOfSeeds += number;
        setNumberOfSeeds(wholeNumberOfSeeds);
        PlayGameActivity.setScoreText();
    }

    public void clearCurrentPlant()
    {
        if (currentButton != null && plantIsRising == false)
        {
            String backgroundColor = "#FF453313";
            currentButton.setBackgroundColor(Color.parseColor(backgroundColor));
            isReadyToBeCollected = false;
        }
    }
}
