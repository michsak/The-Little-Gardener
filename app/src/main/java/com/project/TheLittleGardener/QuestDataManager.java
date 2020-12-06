package com.project.TheLittleGardener;

import java.util.ArrayList;
import java.util.HashMap;

/**Contains all quests data*/
public class QuestDataManager extends QuestGeneralDescription
{
    private static ArrayList<String> PlantedPlantsForQuest = new ArrayList<>();
    private static boolean[] firstOccurrenceOfQuest = {false, false, false, false, false, false, false, false};
    private static int currentNumberOfQuest = 0;
    private static int[] questReward = {20, 60, 50, 100, 150, 100, 200, 250};

    /*dictonaries with name of plant and number of those plants required to get quest points*/
    private static HashMap <String, Integer> firstQuestRequirements = new HashMap<String, Integer>(){
        {put(PlantContainer.TREE.name(), 5); put(PlantContainer.CORN.name(), 5);}};
    private static HashMap <String, Integer> secondQuestRequirements = new HashMap<String, Integer>(){
        {put(PlantContainer.BEAN.name(), 7); put(PlantContainer.BUSH.name(), 4);}};
    private static HashMap <String, Integer> thirdQuestRequirements = new HashMap<String, Integer>(){
        {put(PlantContainer.DAISY.name(), 10); put(PlantContainer.CLOVER.name(), 10);}};
    private static HashMap <String, Integer> fourthQuestRequirements = new HashMap<String, Integer>(){
        {put(PlantContainer.CACTUS.name(), 8); put(PlantContainer.MUSHROOMS.name(), 15);}};
    private static HashMap <String, Integer> fifthQuestRequirements = new HashMap<String, Integer>(){
        {put(PlantContainer.SUNFLOWER.name(), 10); put(PlantContainer.NETTLE.name(), 15);}};
    private static HashMap <String, Integer> sixthQuestRequirements = new HashMap<String, Integer>(){
        {put(PlantContainer.FERN.name(), 15); put(PlantContainer.MOSS.name(), 15);}};
    private static HashMap <String, Integer> seventhQuestRequirements = new HashMap<String, Integer>(){
        {put(PlantContainer.CABBAGE.name(), 20); put(PlantContainer.CATTAIL.name(), 20);}};
    private static HashMap <String, Integer> eightQuestRequirements = new HashMap<String, Integer>(){
        {put(PlantContainer.DANDELION.name(), 50);}};


    public static void addToPlantedPlantsForQuest(String name)
    {
        PlantedPlantsForQuest.add(name);
    }

    public static void clearListOfPlantedPlantsForQuest()
    {
        PlantedPlantsForQuest = new ArrayList<>();
    }

    public static ArrayList<String> getPlantedPlantsForQuest()
    {
        return PlantedPlantsForQuest;
    }

    public static void changeOccurrenceOfQuest(int numberOfQuest)
    {
        firstOccurrenceOfQuest[numberOfQuest] = true;
        QuestDataManager.currentNumberOfQuest += 1;
    }

    public static int getCurrentNumberOfQuest()
    {
        return currentNumberOfQuest;
    }

    public static boolean[] getOccurrenceOfQuest()
    {
        return firstOccurrenceOfQuest;
    }

    public static int getQuestReward(int number)
    {
        return questReward[number];
    }

    public static HashMap <String, Integer> getQuestRequirements(int questNumber)
    {
        switch (questNumber)
        {
            case 0:
                return firstQuestRequirements;
            case 1:
                return secondQuestRequirements;
            case 2:
                return thirdQuestRequirements;
            case 3:
                return fourthQuestRequirements;
            case 4:
                return fifthQuestRequirements;
            case 5:
                return sixthQuestRequirements;
            case 6:
                return seventhQuestRequirements;
            case 7:
                return eightQuestRequirements;

        }
        return null;
    }

    public static String getQuestDescription()
    {
        switch (currentNumberOfQuest)
        {
            case 0:
                return firstQuest();
            case 1:
                return secondQuest();
            case 2:
                return thirdQuest();
            case 3:
                return fourthQuest();
            case 4:
                return fifthQuest();
            case 5:
                return sixthQuest();
            case 6:
                return seventhQuest();
            case 7:
                return eightQuest();
        }
        return "";
    }
}
