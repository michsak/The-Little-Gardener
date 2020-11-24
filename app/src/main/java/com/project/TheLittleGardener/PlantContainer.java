package com.project.TheLittleGardener;

/**Holder of drop down list resources*/
public enum PlantContainer
{
    TREE (R.drawable.grown_tree),
    CORN (R.drawable.corn),
    BEAN (R.drawable.bean),
    BUSH (R.drawable.bush),
    DAISY (R.drawable.daisy),
    CLOVER (R.drawable.clover2),
    CACTUS (R.drawable.cactus),
    MUSHROOMS (R.drawable.mushrooms),
    SUNFLOWER (R.drawable.sunflower),
    NETTLE (R.drawable.corn),
    FERN (R.drawable.corn),
    MOSS (R.drawable.corn),
    CABBAGE (R.drawable.corn),
    CATTAIL (R.drawable.corn),
    DANDELION (R.drawable.corn);

    private int pictureNumber;

    PlantContainer(int pictureNumber)
    {
        this.pictureNumber = pictureNumber;
    }

    public int getValue()
    {
        return pictureNumber;
    }
}
