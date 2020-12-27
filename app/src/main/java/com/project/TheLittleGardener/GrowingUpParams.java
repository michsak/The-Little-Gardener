package com.project.TheLittleGardener;

import android.widget.Button;

/**Contains time variables of growing plants and essential methods for plant-growing*/
public interface GrowingUpParams
{
    int timeOfGrowingSeedling = 10000;
    int growingTimeInterval = 1000;

    void countDownToPlantSeedling(Button button);
    void collectOrSetUpPlant(Button button, int resource);
    void decreaseNumberOfSeedsAndSetText(String nameOfCurrentPlant);
    void addNumberOfSeedsAndSetText();
    void addAdditionalSeeds(int number);
}
