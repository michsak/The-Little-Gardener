package com.project.TheLittleGardener;

/**Holder of drop down list resources*/
public enum DropDownListResources
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
    NETTLE (R.drawable.nettle),
    FERN (R.drawable.fern2),
    MOSS (R.drawable.moss),
    CABBAGE (R.drawable.cabbage),
    CATTAIL (R.drawable.cattail),
    DANDELION (R.drawable.dandelion);

    private int pictureNumber;

    DropDownListResources(int pictureNumber)
    {
        this.pictureNumber = pictureNumber;
    }

    public int getValue()
    {
        return pictureNumber;
    }
}
