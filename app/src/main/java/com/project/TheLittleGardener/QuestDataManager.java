package com.project.TheLittleGardener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class QuestDataManager
{
    private static ArrayList<String> PlantedPlantsForQuest = new ArrayList<>();
    private static boolean[] firstOccurrenceOfQuest = {false, false, false, false, false};
    private static int currentNumberOfQuest = 0;
    private static int[] questReward = {5, 7, 10, 15, 20};

    /*dictonaries with name of plant and number of those plants required to get quest points*/
    private static HashMap <String, Integer> firstQuestRequirements = new HashMap<String, Integer>(){
        {put(PlantContainer.TREE.name(), 5);}};
    private static HashMap <String, Integer> secondQuestRequirements = new HashMap<String, Integer>(){
        {put(PlantContainer.CORN.name(), 2); put(PlantContainer.BEAN.name(), 3);}};
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

    private static HashMap <String, Integer> getQuestRequirements(int questNumber)
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


    //TODO
    //THIS METHOD SHOULD BE PLACED IN ANOTHER CLASS MAYBE IN QUEST SYSTEM
    public static boolean checkIfQuestIsCompleted()
    {
        String[] key = {"", ""};
        int[] value = {0, 0};
        int i = 0;

        for (String name : getQuestRequirements(currentNumberOfQuest).keySet())
        {
            key[i] = name;
            i++;
        }
        i = 0;
        for (int number : getQuestRequirements(currentNumberOfQuest).values())
        {
            value[i] = number;
            i++;
        }

        int firstKeyOccurrences = Collections.frequency(QuestDataManager.getPlantedPlantsForQuest(), key[0]);
        int secondKeyOccurrences = Collections.frequency(QuestDataManager.getPlantedPlantsForQuest(), key[1]);

        if (firstKeyOccurrences >= value[0] && secondKeyOccurrences >= value[1])
        {
            clearListOfPlantedPlantsForQuest();
            return true;
        }
        return false;
    }
}
