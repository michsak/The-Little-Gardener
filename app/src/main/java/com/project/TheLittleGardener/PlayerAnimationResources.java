package com.project.TheLittleGardener;

/**Contains resources of player sprites*/
public class PlayerAnimationResources
{
    private int numberOfCurrentPicture = 0;
    private int speedOfAnimation = 27;
    private int durationOfAnimation = 200;
    private int[] playerMovingUpResources = {R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4};
    private int[] playerMovingBackResources = {R.drawable.f1, R.drawable.f2, R.drawable.f3, R.drawable.f4};
    private int[] playerMovingRightResources = {R.drawable.r1, R.drawable.r2, R.drawable.r3, R.drawable.r4};
    private int[] playerMovingLeftResources = {R.drawable.l1, R.drawable.l2, R.drawable.l3, R.drawable.l4};
    public enum AnimDirection
    {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public int[] getPlayerMovingBackResources()
    {
        return playerMovingBackResources;
    }

    public int[] getPlayerMovingUpResources()
    {
        return playerMovingUpResources;
    }

    public int[] getPlayerMovingRightResources()
    {
        return playerMovingRightResources;
    }

    public int[] getPlayerMovingLeftResources()
    {
        return playerMovingLeftResources;
    }

    public int getAndSetNumberOfCurrentImage()
    {
        numberOfCurrentPicture += 1;

        if (numberOfCurrentPicture % 4 == 0)
        {
            numberOfCurrentPicture = 0;
        }
        return numberOfCurrentPicture;
    }

    public int getSpeedOfAnimation()
    {
        return speedOfAnimation;
    }

    public int getDurationOfAnimation()
    {
        return durationOfAnimation;
    }
}
