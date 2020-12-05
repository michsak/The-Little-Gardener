package com.project.TheLittleGardener;

import android.os.CountDownTimer;
import android.widget.Button;

import java.util.HashMap;

/**Manages growing plant process*/
public class PlantManager extends CurrentPlantAndNumberOfSeeds implements GrowingUpParams
{
    private HashMap<Integer, InGrowingProcessPlantContainer> growingPlantsContainerWithIndexDict;
    private HashMap<String, Integer> costOfEachPlant;
    private HashMap<String, Integer> rewardForEachPlant;
    private String plantedPlant;
    private boolean isReadyToBeCollected = false;

    PlantManager(Button button)
    {
        indexToPlants();
        giveCostToEachPlant();
        giveRewardForEachPlant();
        collectOrSetUpPlant(button, InGrowingProcessPlantContainer.PLANT.getValue());
        countDownToPlantSeedling(button);
    }

    private void indexToPlants()
    {
        growingPlantsContainerWithIndexDict = new HashMap<>();
        growingPlantsContainerWithIndexDict.put(0, InGrowingProcessPlantContainer.TREE);
        growingPlantsContainerWithIndexDict.put(1, InGrowingProcessPlantContainer.CORN);
        growingPlantsContainerWithIndexDict.put(2, InGrowingProcessPlantContainer.BEAN);
        growingPlantsContainerWithIndexDict.put(3, InGrowingProcessPlantContainer.BUSH);
        growingPlantsContainerWithIndexDict.put(4, InGrowingProcessPlantContainer.DAISY);
        growingPlantsContainerWithIndexDict.put(5, InGrowingProcessPlantContainer.CLOVER);
        growingPlantsContainerWithIndexDict.put(6, InGrowingProcessPlantContainer.CACTUS);
        growingPlantsContainerWithIndexDict.put(7, InGrowingProcessPlantContainer.MUSHROOMS);
        growingPlantsContainerWithIndexDict.put(8, InGrowingProcessPlantContainer.SUNFLOWER);
        growingPlantsContainerWithIndexDict.put(9, InGrowingProcessPlantContainer.NETTLE);
        growingPlantsContainerWithIndexDict.put(10, InGrowingProcessPlantContainer.FERN);
        growingPlantsContainerWithIndexDict.put(11, InGrowingProcessPlantContainer.MOSS);
        growingPlantsContainerWithIndexDict.put(12, InGrowingProcessPlantContainer.CABBAGE);
        growingPlantsContainerWithIndexDict.put(13, InGrowingProcessPlantContainer.CATTAIL);
        growingPlantsContainerWithIndexDict.put(14, InGrowingProcessPlantContainer.DANDELION);
    }

    private void giveRewardForEachPlant()
    {
        rewardForEachPlant = new HashMap<>();
        rewardForEachPlant.put(PlantContainer.TREE.name(), 1);
        rewardForEachPlant.put(PlantContainer.CORN.name(), 2);
        rewardForEachPlant.put(PlantContainer.BEAN.name(), 4);
        rewardForEachPlant.put(PlantContainer.BUSH.name(), 5);
        rewardForEachPlant.put(PlantContainer.DAISY.name(), 4);   //
        rewardForEachPlant.put(PlantContainer.CLOVER.name(), 4);
        rewardForEachPlant.put(PlantContainer.CACTUS.name(), 5);
        rewardForEachPlant.put(PlantContainer.MUSHROOMS.name(), 6);
        rewardForEachPlant.put(PlantContainer.SUNFLOWER.name(), 8);
        rewardForEachPlant.put(PlantContainer.NETTLE.name(), 8);
        rewardForEachPlant.put(PlantContainer.FERN.name(), 10);
        rewardForEachPlant.put(PlantContainer.MOSS.name(), 11);
        rewardForEachPlant.put(PlantContainer.CABBAGE.name(), 12);
        rewardForEachPlant.put(PlantContainer.CATTAIL.name(), 13);
        rewardForEachPlant.put(PlantContainer.DANDELION.name(), 15);
    }

    private void giveCostToEachPlant()
    {
        costOfEachPlant = new HashMap<>();
        costOfEachPlant.put(PlantContainer.TREE.name(), 2);
        costOfEachPlant.put(PlantContainer.CORN.name(), 3);
        costOfEachPlant.put(PlantContainer.BEAN.name(), 5);
        costOfEachPlant.put(PlantContainer.BUSH.name(), 7);
        costOfEachPlant.put(PlantContainer.DAISY.name(), 5);
        costOfEachPlant.put(PlantContainer.CLOVER.name(), 5);
        costOfEachPlant.put(PlantContainer.CACTUS.name(), 7);
        costOfEachPlant.put(PlantContainer.MUSHROOMS.name(), 8);
        costOfEachPlant.put(PlantContainer.SUNFLOWER.name(), 10);
        costOfEachPlant.put(PlantContainer.NETTLE.name(), 10);
        costOfEachPlant.put(PlantContainer.FERN.name(), 12);
        costOfEachPlant.put(PlantContainer.MOSS.name(), 13);
        costOfEachPlant.put(PlantContainer.CABBAGE.name(), 15);
        costOfEachPlant.put(PlantContainer.CATTAIL.name(), 17);
        costOfEachPlant.put(PlantContainer.DANDELION.name(), 20);
    }

    @Override
    public void countDownToPlantSeedling(final Button button)
    {
        button.setEnabled(false);
        isReadyToBeCollected = false;
        plantedPlant = growingPlantsContainerWithIndexDict.get(getCurrentPlant()).name();

        new CountDownTimer(timeOfGrowingSeedling, growingTimeInterval)
        {
            int currentTime = wholeGrowingTime;
            int growingPlantResource = growingPlantsContainerWithIndexDict.get(getCurrentPlant()).getValue();

            @Override
            public void onTick(long l)
            {
                currentTime -= 1;
                if (currentTime <= 0)
                {
                    button.setBackgroundResource(growingPlantResource);
                    button.setEnabled(true);
                    currentTime = wholeGrowingTime;
                    isReadyToBeCollected = true;
                }
            }

            @Override
            public void onFinish()
            {

            }
        }.start();
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
            if (QuestCompletionChecker.checkIfQuestIsCompleted() && !QuestDataManager.getOccurrenceOfQuest()[QuestDataManager.getCurrentNumberOfQuest()])
            {
                addAdditionalSeeds(QuestDataManager.getQuestReward(QuestDataManager.getCurrentNumberOfQuest()));
                QuestDataManager.changeOccurrenceOfQuest(QuestDataManager.getCurrentNumberOfQuest());
                PlayGameActivity.showQuestMessage("Congratulations! You have completed quest " +
                        QuestDataManager.getCurrentNumberOfQuest() + ".\nNow ");
            }
        }

        else
        {
            String nameOfCurrentPlant = growingPlantsContainerWithIndexDict.get(getCurrentPlant()).name();

            if ((getNumberOfSeeds() - costOfEachPlant.get(nameOfCurrentPlant)) >= 0)
            {
                decreaseNumberOfSeedsAndSetText(nameOfCurrentPlant);
                button.setBackgroundResource(resource);
                countDownToPlantSeedling(button);
            }
        }
    }

    @Override
    public void decreaseNumberOfSeedsAndSetText(String nameOfCurrentPlant)
    {
        int numberOfSeeds;
        numberOfSeeds = getNumberOfSeeds();
        numberOfSeeds -= costOfEachPlant.get(nameOfCurrentPlant);
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
        numberOfSeeds += rewardForEachPlant.get(nameOfCollectedPlant);
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
}
