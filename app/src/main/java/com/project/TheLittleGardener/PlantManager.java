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
        rewardForEachPlant.put(PlantContainer.TREE.name(), CostAndRewardForPlants.rewardOfPlants[0]);
        rewardForEachPlant.put(PlantContainer.CORN.name(), CostAndRewardForPlants.rewardOfPlants[1]);
        rewardForEachPlant.put(PlantContainer.BEAN.name(), CostAndRewardForPlants.rewardOfPlants[2]);
        rewardForEachPlant.put(PlantContainer.BUSH.name(), CostAndRewardForPlants.rewardOfPlants[3]);
        rewardForEachPlant.put(PlantContainer.DAISY.name(), CostAndRewardForPlants.rewardOfPlants[4]);   //
        rewardForEachPlant.put(PlantContainer.CLOVER.name(), CostAndRewardForPlants.rewardOfPlants[5]);
        rewardForEachPlant.put(PlantContainer.CACTUS.name(), CostAndRewardForPlants.rewardOfPlants[6]);
        rewardForEachPlant.put(PlantContainer.MUSHROOMS.name(), CostAndRewardForPlants.rewardOfPlants[7]);
        rewardForEachPlant.put(PlantContainer.SUNFLOWER.name(), CostAndRewardForPlants.rewardOfPlants[8]);
        rewardForEachPlant.put(PlantContainer.NETTLE.name(), CostAndRewardForPlants.rewardOfPlants[9]);
        rewardForEachPlant.put(PlantContainer.FERN.name(), CostAndRewardForPlants.rewardOfPlants[10]);
        rewardForEachPlant.put(PlantContainer.MOSS.name(), CostAndRewardForPlants.rewardOfPlants[11]);
        rewardForEachPlant.put(PlantContainer.CABBAGE.name(), CostAndRewardForPlants.rewardOfPlants[12]);
        rewardForEachPlant.put(PlantContainer.CATTAIL.name(), CostAndRewardForPlants.rewardOfPlants[13]);
        rewardForEachPlant.put(PlantContainer.DANDELION.name(), CostAndRewardForPlants.rewardOfPlants[14]);
    }

    private void giveCostToEachPlant()
    {
        costOfEachPlant = new HashMap<>();
        costOfEachPlant.put(PlantContainer.TREE.name(), CostAndRewardForPlants.costOfPlants[0]);
        costOfEachPlant.put(PlantContainer.CORN.name(), CostAndRewardForPlants.costOfPlants[1]);
        costOfEachPlant.put(PlantContainer.BEAN.name(), CostAndRewardForPlants.costOfPlants[2]);
        costOfEachPlant.put(PlantContainer.BUSH.name(), CostAndRewardForPlants.costOfPlants[3]);
        costOfEachPlant.put(PlantContainer.DAISY.name(), CostAndRewardForPlants.costOfPlants[4]);
        costOfEachPlant.put(PlantContainer.CLOVER.name(), CostAndRewardForPlants.costOfPlants[5]);
        costOfEachPlant.put(PlantContainer.CACTUS.name(), CostAndRewardForPlants.costOfPlants[6]);
        costOfEachPlant.put(PlantContainer.MUSHROOMS.name(), CostAndRewardForPlants.costOfPlants[7]);
        costOfEachPlant.put(PlantContainer.SUNFLOWER.name(), CostAndRewardForPlants.costOfPlants[8]);
        costOfEachPlant.put(PlantContainer.NETTLE.name(), CostAndRewardForPlants.costOfPlants[9]);
        costOfEachPlant.put(PlantContainer.FERN.name(), CostAndRewardForPlants.costOfPlants[10]);
        costOfEachPlant.put(PlantContainer.MOSS.name(), CostAndRewardForPlants.costOfPlants[11]);
        costOfEachPlant.put(PlantContainer.CABBAGE.name(), CostAndRewardForPlants.costOfPlants[12]);
        costOfEachPlant.put(PlantContainer.CATTAIL.name(), CostAndRewardForPlants.costOfPlants[13]);
        costOfEachPlant.put(PlantContainer.DANDELION.name(), CostAndRewardForPlants.costOfPlants[14]);
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

    public HashMap<String, Integer> getCostOfEachPlant()
    {
        return costOfEachPlant;
    }
}
