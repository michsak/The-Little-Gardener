package com.project.TheLittleGardener;

/**Holder of growing plants resources*/
public enum GrownPlantsContainer
{
    PLANT (R.drawable.small_plant_bg),
    TREE (R.drawable.grown_tree_bg),
    CORN (R.drawable.grown_corn_bg),
    BEAN (R.drawable.grown_bean_bg),
    BUSH (R.drawable.grown_bush_bg),
    DAISY (R.drawable.grown_daisy_bg),
    CLOVER (R.drawable.grown_clover_2_bg),
    CACTUS (R.drawable.grown_cactus_bg),
    MUSHROOMS (R.drawable.grown_mushrooms_bg),
    SUNFLOWER (R.drawable.grown_sunflower_bg),
    NETTLE (R.drawable.grown_nettle_bg),
    FERN (R.drawable.grown_fern_bg),
    MOSS (R.drawable.grown_moss),
    CABBAGE (R.drawable.grown_cabbage_bg),
    CATTAIL (R.drawable.grown_cattail_bg),
    DANDELION (R.drawable.grown_dandelion_bg);

    private int pictureNumber;

    GrownPlantsContainer(int pictureNumber)
    {
        this.pictureNumber = pictureNumber;
    }

    public int getValue()
    {
        return pictureNumber;
    }
}
