package com.project.TheLittleGardener;

import java.util.HashMap;

/**Final cost and reward for planting and collecting each plant*/
public class PlantsCostAndReward
{
    public final static int[] costOfPlants = new int [] {2, 3, 5, 7, 8, 10, 8, 8, 12, 15, 16, 17, 18, 20, 50};
    public final static int[] rewardOfPlants = new int [] {1, 2, 4, 5, 8, 12, 4, 6, 12, 12, 15, 15, 17, 22, 30};

    private static HashMap<Integer, GrownPlantsContainer> growingPlantsContainerWithIndexDict;
    private static HashMap<String, Integer> costOfEachPlant;
    private static HashMap<String, Integer> rewardForEachPlant;

    public static void initializePlantsIndexCostAndReward()
    {
        setIndexToPlants();
        setCostToEachPlant();
        setRewardForEachPlant();
    }

    private static void setIndexToPlants()
    {
        growingPlantsContainerWithIndexDict = new HashMap<>();
        growingPlantsContainerWithIndexDict.put(0, GrownPlantsContainer.TREE);
        growingPlantsContainerWithIndexDict.put(1, GrownPlantsContainer.CORN);
        growingPlantsContainerWithIndexDict.put(2, GrownPlantsContainer.BEAN);
        growingPlantsContainerWithIndexDict.put(3, GrownPlantsContainer.BUSH);
        growingPlantsContainerWithIndexDict.put(4, GrownPlantsContainer.DAISY);
        growingPlantsContainerWithIndexDict.put(5, GrownPlantsContainer.CLOVER);
        growingPlantsContainerWithIndexDict.put(6, GrownPlantsContainer.CACTUS);
        growingPlantsContainerWithIndexDict.put(7, GrownPlantsContainer.MUSHROOMS);
        growingPlantsContainerWithIndexDict.put(8, GrownPlantsContainer.SUNFLOWER);
        growingPlantsContainerWithIndexDict.put(9, GrownPlantsContainer.NETTLE);
        growingPlantsContainerWithIndexDict.put(10, GrownPlantsContainer.FERN);
        growingPlantsContainerWithIndexDict.put(11, GrownPlantsContainer.MOSS);
        growingPlantsContainerWithIndexDict.put(12, GrownPlantsContainer.CABBAGE);
        growingPlantsContainerWithIndexDict.put(13, GrownPlantsContainer.CATTAIL);
        growingPlantsContainerWithIndexDict.put(14, GrownPlantsContainer.DANDELION);
    }

    private static void setRewardForEachPlant()
    {
        rewardForEachPlant = new HashMap<>();
        rewardForEachPlant.put(DropDownListResources.TREE.name(), PlantsCostAndReward.rewardOfPlants[0]);
        rewardForEachPlant.put(DropDownListResources.CORN.name(), PlantsCostAndReward.rewardOfPlants[1]);
        rewardForEachPlant.put(DropDownListResources.BEAN.name(), PlantsCostAndReward.rewardOfPlants[2]);
        rewardForEachPlant.put(DropDownListResources.BUSH.name(), PlantsCostAndReward.rewardOfPlants[3]);
        rewardForEachPlant.put(DropDownListResources.DAISY.name(), PlantsCostAndReward.rewardOfPlants[4]);   //
        rewardForEachPlant.put(DropDownListResources.CLOVER.name(), PlantsCostAndReward.rewardOfPlants[5]);
        rewardForEachPlant.put(DropDownListResources.CACTUS.name(), PlantsCostAndReward.rewardOfPlants[6]);
        rewardForEachPlant.put(DropDownListResources.MUSHROOMS.name(), PlantsCostAndReward.rewardOfPlants[7]);
        rewardForEachPlant.put(DropDownListResources.SUNFLOWER.name(), PlantsCostAndReward.rewardOfPlants[8]);
        rewardForEachPlant.put(DropDownListResources.NETTLE.name(), PlantsCostAndReward.rewardOfPlants[9]);
        rewardForEachPlant.put(DropDownListResources.FERN.name(), PlantsCostAndReward.rewardOfPlants[10]);
        rewardForEachPlant.put(DropDownListResources.MOSS.name(), PlantsCostAndReward.rewardOfPlants[11]);
        rewardForEachPlant.put(DropDownListResources.CABBAGE.name(), PlantsCostAndReward.rewardOfPlants[12]);
        rewardForEachPlant.put(DropDownListResources.CATTAIL.name(), PlantsCostAndReward.rewardOfPlants[13]);
        rewardForEachPlant.put(DropDownListResources.DANDELION.name(), PlantsCostAndReward.rewardOfPlants[14]);
    }

    private static void setCostToEachPlant()
    {
        costOfEachPlant = new HashMap<>();
        costOfEachPlant.put(DropDownListResources.TREE.name(), PlantsCostAndReward.costOfPlants[0]);
        costOfEachPlant.put(DropDownListResources.CORN.name(), PlantsCostAndReward.costOfPlants[1]);
        costOfEachPlant.put(DropDownListResources.BEAN.name(), PlantsCostAndReward.costOfPlants[2]);
        costOfEachPlant.put(DropDownListResources.BUSH.name(), PlantsCostAndReward.costOfPlants[3]);
        costOfEachPlant.put(DropDownListResources.DAISY.name(), PlantsCostAndReward.costOfPlants[4]);
        costOfEachPlant.put(DropDownListResources.CLOVER.name(), PlantsCostAndReward.costOfPlants[5]);
        costOfEachPlant.put(DropDownListResources.CACTUS.name(), PlantsCostAndReward.costOfPlants[6]);
        costOfEachPlant.put(DropDownListResources.MUSHROOMS.name(), PlantsCostAndReward.costOfPlants[7]);
        costOfEachPlant.put(DropDownListResources.SUNFLOWER.name(), PlantsCostAndReward.costOfPlants[8]);
        costOfEachPlant.put(DropDownListResources.NETTLE.name(), PlantsCostAndReward.costOfPlants[9]);
        costOfEachPlant.put(DropDownListResources.FERN.name(), PlantsCostAndReward.costOfPlants[10]);
        costOfEachPlant.put(DropDownListResources.MOSS.name(), PlantsCostAndReward.costOfPlants[11]);
        costOfEachPlant.put(DropDownListResources.CABBAGE.name(), PlantsCostAndReward.costOfPlants[12]);
        costOfEachPlant.put(DropDownListResources.CATTAIL.name(), PlantsCostAndReward.costOfPlants[13]);
        costOfEachPlant.put(DropDownListResources.DANDELION.name(), PlantsCostAndReward.costOfPlants[14]);
    }

    public static HashMap<Integer, GrownPlantsContainer> getPlantsIndex()
    {
        return growingPlantsContainerWithIndexDict;
    }

    public static HashMap<String, Integer> getCostOfEachPlant()
    {
        return costOfEachPlant;
    }

    public static HashMap<String, Integer> getRewardForEachPlant()
    {
        return rewardForEachPlant;
    }

}
