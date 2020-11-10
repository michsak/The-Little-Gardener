package com.project.TheLittleGardener;

public enum PlantContainer
{
    TREE (R.drawable.grown_tree),
    CORN (R.drawable.corn),
    BEAN (R.drawable.bean),
    BUSH (R.drawable.daisy),
    DAISY (R.drawable.clover),
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

    PlantContainer(int pictureNumber)
    {
        this.pictureNumber = pictureNumber;
    }

    public int getValue()
    {
        return pictureNumber;
    }
}
