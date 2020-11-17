package com.project.TheLittleGardener;

import android.widget.Button;

/**contains time variables of growing plants and essential for plant-growing methods*/
public interface GrowingTimeContainer
{
    int timeOfGrowingSeedling = 10000;
    int wholeGrowingTime = 10;
    int growingTimeInterval = 1000;

    void countDownToPlantSeedling(Button button);
    void collectOrSetUpPlant(Button button, int resource);
}
