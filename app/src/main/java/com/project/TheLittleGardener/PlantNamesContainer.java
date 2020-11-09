package com.project.TheLittleGardener;

public enum PlantNamesContainer
{
    TREE (R.drawable.grown_tree),
    CORN (R.drawable.corn),
    BEAN (R.drawable.corn),  //to be changed all below included bean
    BUSH (R.drawable.corn),
    DAISY (R.drawable.corn),
    CLOVER (R.drawable.corn),
    MAIZE (R.drawable.corn),
    MUSHROOMS (R.drawable.corn),
    SUNFLOWER (R.drawable.corn),
    NETTLE (R.drawable.corn),
    FERN (R.drawable.corn),
    MOSS (R.drawable.corn),
    CABBAGE (R.drawable.corn),
    CATTAIL (R.drawable.corn),
    DANDELION (R.drawable.corn);

    private int pictureNumber;

    PlantNamesContainer(int pictureNumber)
    {
        this.pictureNumber = pictureNumber;
    }

    public int getValue()
    {
        return pictureNumber;
    }
}
