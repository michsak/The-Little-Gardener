package com.project.TheLittleGardener;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;

import java.util.HashMap;

public class PlantManager
{
    private int timeOfGrowingSeedling = 10000;
    private int wholeGrowingTime = 10;
    private int growingTimeInterval = 1000;
    private HashMap<Integer, InGrowingProcessPlantContainer> growingPlantsContainerWithIndexDict;
    private boolean isReadyToBeCollected[] = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
    private int currentButton;

    PlantManager(Button button, int currentButton)
    {
        this.currentButton = currentButton;
        indexToPlants();
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

    private void countDownToPlantSeedling(final Button button)
    {
        button.setEnabled(false);
        isReadyToBeCollected[PlayGameActivity.currentPlant] = false;
        new CountDownTimer(timeOfGrowingSeedling, growingTimeInterval)
        {
            int currentTime = wholeGrowingTime;
            int growingPlantResource = growingPlantsContainerWithIndexDict.get(PlayGameActivity.currentPlant).getValue();
            int growingPlantIndex = PlayGameActivity.currentPlant;

            @Override
            public void onTick(long l)
            {
                currentTime -= 1;
                if (currentTime <= 0)
                {
                    button.setBackgroundResource(growingPlantResource);
                    button.setEnabled(true);
                    currentTime = wholeGrowingTime;
                    isReadyToBeCollected[currentButton] = true;
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
        if (isReadyToBeCollected[currentButton])
        {
            button.setBackgroundResource(R.color.brown);
            isReadyToBeCollected[currentButton] = false;
            //TODO
            //add points
        }

        else
        {
            button.setBackgroundResource(resource);
            countDownToPlantSeedling(button);
            //TODO
            //take points for planting
        }
    }
}
