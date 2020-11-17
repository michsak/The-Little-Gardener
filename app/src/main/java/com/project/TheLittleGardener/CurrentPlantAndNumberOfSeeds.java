package com.project.TheLittleGardener;

import java.util.ArrayList;

public class CurrentPlantAndNumberOfSeeds
{
    private static int currentPlant = 0;
    private static int numberOfSeeds = 20;

    public static void setCurrentPlant(int value)
    {
        currentPlant = value;
    }

    public static void setNumberOfSeeds(int value)
    {
        numberOfSeeds = value;
    }

    public static int getCurrentPlant()
    {
        return currentPlant;
    }

    public static int getNumberOfSeeds()
    {
        return numberOfSeeds;
    }
}
