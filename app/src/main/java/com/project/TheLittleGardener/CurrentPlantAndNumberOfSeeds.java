package com.project.TheLittleGardener;

public class CurrentPlantAndNumberOfSeeds
{
    private static int currentPlant = 0;
    private static int numberOfSeeds = 35;

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