package com.project.TheLittleGardener;

public class QuestSystem implements QuestsDescription
{

    QuestSystem()
    {

    }

    @Override
    public void firstQuest()
    {
        String task = "Plant and collect 5 trees. Reward: 5 seeds";
    }

    @Override
    public void secondQuest()
    {
        String task = "Plant 2 corn and 3 beans. Reward: 7 seeds";
    }

    @Override
    public void thirdQuest()  //to be changed
    {
        String task = "Plant 3 bushes and 5 daisy. Reward: 7 seeds";
    }

    @Override
    public void fourthQuest()  //to be changed
    {
        String task = "Plant 5 clovers and 5 dandelion. Reward: 10 seeds";
    }

    @Override
    public void fifthQuest() //to be changed
    {
        String task = "Plant 3 cabbages and 3 cattails. Reward: 7 seeds";
    }
}
