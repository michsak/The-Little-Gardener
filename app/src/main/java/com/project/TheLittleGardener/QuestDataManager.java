package com.project.TheLittleGardener;

import java.util.ArrayList;
import java.util.HashMap;

/**Contains all quests data*/
public class QuestDataManager extends QuestGeneralDescription
{
    private static ArrayList<String> PlantedPlantsForQuest = new ArrayList<>();
    private static boolean[] firstOccurrenceOfQuest = {false, false, false, false, false};
    private static int currentNumberOfQuest = 0;
    private static int[] questReward = {20, 50, 10, 15, 20};

    /*dictonaries with name of plant and number of those plants required to get quest points*/
    private static HashMap <String, Integer> firstQuestRequirements = new HashMap<String, Integer>(){
        {put(PlantContainer.TREE.name(), 5); put(PlantContainer.CORN.name(), 5);}};
    private static HashMap <String, Integer> secondQuestRequirements = new HashMap<String, Integer>(){
        {put(PlantContainer.BEAN.name(), 7); put(PlantContainer.BUSH.name(), 4);}};
    private static HashMap <String, Integer> thirdQuestRequirements = new HashMap<String, Integer>(){
        {put(PlantContainer.BUSH.name(), 3); put(PlantContainer.DAISY.name(), 5);}};
    private static HashMap <String, Integer> fourthQuestRequirements = new HashMap<String, Integer>(){
        {put(PlantContainer.CLOVER.name(), 5); put(PlantContainer.DANDELION.name(), 5);}};
    private static HashMap <String, Integer> fifthQuestRequirements = new HashMap<String, Integer>(){
        {put(PlantContainer.CABBAGE.name(), 3); put(PlantContainer.CATTAIL.name(), 3);}};


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
        }
        return "";
    }
}
