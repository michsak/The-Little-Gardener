package com.project.TheLittleGardener;

import java.util.Collections;

/**Checks if quest is completed*/
public class QuestCompletionChecker
{
    public static boolean checkIfQuestIsCompleted()
    {
        String[] key = {"", ""};
        int[] value = {0, 0};
        int i = 0;
        int currentNumberOfQuest = QuestDataManager.getCurrentNumberOfQuest();

        for (String name : QuestDataManager.getQuestRequirements(currentNumberOfQuest).keySet())
        {
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
        int secondKeyOccurrences = Collections.frequency(QuestDataManager.getPlantedPlantsForQuest(), key[1]);

        if (firstKeyOccurrences >= value[0] && secondKeyOccurrences >= value[1])
        {
            QuestDataManager.clearListOfPlantedPlantsForQuest();
            return true;
        }
        return false;
    }
}
