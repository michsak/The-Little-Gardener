package com.project.TheLittleGardener;

import java.util.Collections;

/**Checks if quest is completed*/
public class QuestCompletionChecker
{
    private static String[] keys = {"",""};
    private static int[] values = {0, 0};

    public static boolean checkIfQuestIsCompleted()
    {
        String[] key = {"", ""};
        int[] value = {0, 0};
        int i = 0;
        int currentNumberOfQuest = QuestDataManager.getCurrentNumberOfQuest();

        for (String name : QuestDataManager.getQuestRequirements(currentNumberOfQuest).keySet())
        {
            QuestCompletionChecker.keys[i] = name;
            key[i] = name;
            i++;
        }
        i = 0;
        for (int number : QuestDataManager.getQuestRequirements(currentNumberOfQuest).values())
        {
            value[i] = number;
            i++;
        }

        int firstKeyOccurrences = Collections.frequency(QuestDataManager.getPlantedPlantsForQuest(), key[0]);
        values[0] = firstKeyOccurrences;
        int secondKeyOccurrences = Collections.frequency(QuestDataManager.getPlantedPlantsForQuest(), key[1]);
        values[1] = secondKeyOccurrences;

        if (questRequirementsAreFulfilled(firstKeyOccurrences >= value[0], secondKeyOccurrences >= value[1]))
        {
            QuestDataManager.clearListOfPlantedPlantsForQuest();
            keys[0] = "";
            keys[1] = "";
            return true;
        }
        return false;
    }

    private static boolean questRequirementsAreFulfilled(boolean b, boolean b1)
    {
        return b && b1;
    }

    public static String[] getKeys()
    {
        return keys;
    }

    public static int[] getValues()
    {
        return values;
    }

    public static void clearValues()
    {
        for (int i=0; i<values.length; i++)
        {
            values[i] = 0;
        }
    }
}
