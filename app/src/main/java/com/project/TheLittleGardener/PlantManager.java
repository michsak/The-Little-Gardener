package com.project.TheLittleGardener;

import android.os.CountDownTimer;
import android.widget.Button;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

public class PlantManager
{
    private int timeOfGrowingSeedling = 10000;
    private int wholeGrowingTime = 10;
    private int growingTimeInterval = 1000;
    private HashMap<Integer, InGrowingProcessPlantContainer> growingPlantsContainerWithIndexDict;
    private HashMap<String, Integer> costOfEachPlant;
    private Queue<String> plantedPlants = new ArrayDeque<>();
    private boolean isReadyToBeCollected = false;

    PlantManager(Button button)
    {
        indexToPlants();
        giveCostToEachPlant();
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
        growingPlantsContainerWithIndexDict.put(6, InGrowingProcessPlantContainer.MAIZE);
        growingPlantsContainerWithIndexDict.put(7, InGrowingProcessPlantContainer.MUSHROOMS);
        growingPlantsContainerWithIndexDict.put(8, InGrowingProcessPlantContainer.SUNFLOWER);
        growingPlantsContainerWithIndexDict.put(9, InGrowingProcessPlantContainer.NETTLE);
        growingPlantsContainerWithIndexDict.put(10, InGrowingProcessPlantContainer.FERN);
        growingPlantsContainerWithIndexDict.put(11, InGrowingProcessPlantContainer.MOSS);
        growingPlantsContainerWithIndexDict.put(12, InGrowingProcessPlantContainer.CABBAGE);
        growingPlantsContainerWithIndexDict.put(13, InGrowingProcessPlantContainer.CATTAIL);
        growingPlantsContainerWithIndexDict.put(14, InGrowingProcessPlantContainer.DANDELION);
    }

    private void giveCostToEachPlant()
    {
        costOfEachPlant = new HashMap<>();
        costOfEachPlant.put(PlantContainer.TREE.name(), 1);
        costOfEachPlant.put(PlantContainer.CORN.name(), 1);
        costOfEachPlant.put(PlantContainer.BEAN.name(), 2);
        costOfEachPlant.put(PlantContainer.BUSH.name(), 2);
        costOfEachPlant.put(PlantContainer.DAISY.name(), 2);
        costOfEachPlant.put(PlantContainer.CLOVER.name(), 3);
        costOfEachPlant.put(PlantContainer.MAIZE.name(), 3);
        costOfEachPlant.put(PlantContainer.MUSHROOMS.name(), 4);
        costOfEachPlant.put(PlantContainer.SUNFLOWER.name(), 4);
        costOfEachPlant.put(PlantContainer.NETTLE.name(), 5);
        costOfEachPlant.put(PlantContainer.FERN.name(), 6);
        costOfEachPlant.put(PlantContainer.MOSS.name(), 8);
        costOfEachPlant.put(PlantContainer.CABBAGE.name(), 10);
        costOfEachPlant.put(PlantContainer.CATTAIL.name(), 12);
        costOfEachPlant.put(PlantContainer.DANDELION.name(), 15);
    }

    private void countDownToPlantSeedling(final Button button)
    {
        button.setEnabled(false);
        isReadyToBeCollected = false;
        plantedPlants.add(growingPlantsContainerWithIndexDict.get(PlayGameActivity.currentPlant).name());

        new CountDownTimer(timeOfGrowingSeedling, growingTimeInterval)
        {
            int currentTime = wholeGrowingTime;
            int growingPlantResource = growingPlantsContainerWithIndexDict.get(PlayGameActivity.currentPlant).getValue();

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

    public void collectOrSetUpPlant(Button button, int resource)
    {
        if (isReadyToBeCollected)
        {
            button.setBackgroundResource(R.color.brown);
            isReadyToBeCollected = false;
            addNumberOfSeedsAndSetText();
        }

        else
        {
            String nameOfCurrentPlant = growingPlantsContainerWithIndexDict.get(PlayGameActivity.currentPlant).name();

            if ((PlayGameActivity.numberOfSeeds - costOfEachPlant.get(nameOfCurrentPlant)) >= 0)
            {
                decreaseNumberOfSeedsAndSetText(nameOfCurrentPlant);
                button.setBackgroundResource(resource);
                countDownToPlantSeedling(button);
            }
        }
    }

    private void decreaseNumberOfSeedsAndSetText(String nameOfCurrentPlant)
    {
        PlayGameActivity.numberOfSeeds -= costOfEachPlant.get(nameOfCurrentPlant);
        PlayGameActivity.setScoreText();
    }

    private void addNumberOfSeedsAndSetText()
    {
        try
        {
            String nameOfCollectedPlant = plantedPlants.remove();
            PlayGameActivity.numberOfSeeds += costOfEachPlant.get(nameOfCollectedPlant);
            PlayGameActivity.setScoreText();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}