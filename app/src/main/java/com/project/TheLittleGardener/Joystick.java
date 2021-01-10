package com.project.TheLittleGardener;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**Contains all methods which enable user to move in the specific direction*/
public class Joystick extends MetricsOfScreen
{
    private JoystickView joystick;
    private ImageView player;
    private MarginsOfScreen marginsOfScreen;
    private PlayerAnimationResources playerAnimation;
    private float xPosition = 0f;
    private float yPosition = -10f;
    private final float playerMovingSpeed = 16f;


    Joystick(JoystickView joystick, ImageView player, Context context)
    {
        super(context);
        this.joystick = joystick;
        this.player = player;
        marginsOfScreen = new MarginsOfScreen();
        checkIfProperScreensMargins();
        playerAnimation = new PlayerAnimationResources();
    }

    private void checkIfProperScreensMargins()
    {
        if (getScreenWidth() > 1200)
        {
            marginsOfScreen.topPlayerMargin = 650f;
        }
        if (getScreenHeight() > 2200)
        {
            marginsOfScreen.edgePlayerMargin = 100f;
        }
    }

    /**Creates joystick and switches the direction of player movement*/
    public void createJoysticks()
    {
        joystick.setOnJoystickMoveListener(new JoystickView.OnJoystickMoveListener() {

            @Override
            public void onValueChanged(int angle, int power, int direction)
            {
                switch (direction)
                {
                    case JoystickView.FRONT:
                        moveUp();
                        break;
                    case JoystickView.FRONT_RIGHT:
                        moveUpRight();
                        break;
                    case JoystickView.RIGHT:
                        moveRight();
                        break;
                    case JoystickView.RIGHT_BOTTOM:
                        moveDownRight();
                        break;
                    case JoystickView.BOTTOM:
                        moveDown();
                        break;
                    case JoystickView.BOTTOM_LEFT:
                        moveDownLeft();
                        break;
                    case JoystickView.LEFT:
                        moveLeft();
                        break;
                    case JoystickView.LEFT_FRONT:
                        moveUpLeft();
                        break;
                    default:
                }
            }
        }, JoystickView.DEFAULT_LOOP_INTERVAL);
    }

    private void createAndStartAnimation(float xPosition, float xFinalPosition, float yPosition, float yFinalPosition)
    {
        TranslateAnimation animation = new TranslateAnimation(xPosition, xFinalPosition, yPosition, yFinalPosition);
        animation.setDuration(playerAnimation.getDurationOfAnimation());
        animation.setFillAfter(true);
        player.startAnimation(animation);
        this.xPosition = xFinalPosition;
        this.yPosition = yFinalPosition;
    }

    private void moveUp()
    {
        if (yPosition >= (-height + marginsOfScreen.topPlayerMargin))
        {
            createAndStartAnimation(xPosition, xPosition, yPosition, yPosition - playerMovingSpeed);
            moveInSpecifiedDirection(PlayerAnimationResources.AnimDirection.UP);
        }
    }

    private void moveUpRight()
    {
        if (xPosition <= width/2 - 100f && yPosition >= (-height + marginsOfScreen.topPlayerMargin))
        {
            createAndStartAnimation(xPosition, xPosition+ playerMovingSpeed, yPosition, yPosition- playerMovingSpeed);
            moveInSpecifiedDirection(PlayerAnimationResources.AnimDirection.RIGHT);
        }
    }

    private void moveUpLeft()
    {
        if (xPosition >= (-width/2 + 100f) && yPosition >= (-height + marginsOfScreen.topPlayerMargin))
        {
            createAndStartAnimation(xPosition, xPosition- playerMovingSpeed, yPosition, yPosition- playerMovingSpeed);
            moveInSpecifiedDirection(PlayerAnimationResources.AnimDirection.LEFT);
        }
    }

    private void moveDown()
    {
        if (yPosition <= -marginsOfScreen.downPlayerMargin)
        {
            createAndStartAnimation(xPosition, xPosition, yPosition, yPosition+ playerMovingSpeed);
            moveInSpecifiedDirection(PlayerAnimationResources.AnimDirection.DOWN);
        }
    }

    private void moveDownLeft()
    {
        if (yPosition <= -marginsOfScreen.downPlayerMargin && xPosition >= (-width/2 + 100f))
        {
            createAndStartAnimation(xPosition, xPosition- playerMovingSpeed, yPosition, yPosition+ playerMovingSpeed);
            moveInSpecifiedDirection(PlayerAnimationResources.AnimDirection.LEFT);
        }
    }

    private void moveDownRight()
    {
        if (yPosition <= -marginsOfScreen.downPlayerMargin && xPosition <= (width/2 - 100f))
        {
            createAndStartAnimation(xPosition, xPosition+ playerMovingSpeed, yPosition, yPosition+ playerMovingSpeed);
            moveInSpecifiedDirection(PlayerAnimationResources.AnimDirection.RIGHT);
        }
    }

    private void moveRight()
    {
        if (xPosition <= width/2 - marginsOfScreen.edgePlayerMargin)
        {
            createAndStartAnimation(xPosition, xPosition+ playerMovingSpeed, yPosition, yPosition);
            moveInSpecifiedDirection(PlayerAnimationResources.AnimDirection.RIGHT);
        }
    }

    private void moveLeft()
    {
        if (xPosition > (-width/2 + marginsOfScreen.edgePlayerMargin))
        {
            createAndStartAnimation(xPosition, xPosition- playerMovingSpeed, yPosition, yPosition);
            moveInSpecifiedDirection(PlayerAnimationResources.AnimDirection.LEFT);
        }
    }

    private void moveInSpecifiedDirection(PlayerAnimationResources.AnimDirection specificDirection)
    {
        int[] resource = checkDirection(specificDirection);
        int currentImageNumber = playerAnimation.getAndSetNumberOfCurrentImage();
        try
        {
            Thread.sleep(playerAnimation.getSpeedOfAnimation());
            player.setImageResource(resource[currentImageNumber]);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    /**This method checks direction and sets proper animation resources
     * @param specificDirection specific player movement direction
     */
    private int[] checkDirection(PlayerAnimationResources.AnimDirection specificDirection)
    {
        int[] currentResource;
        if (specificDirection == PlayerAnimationResources.AnimDirection.UP)
        {
            currentResource = playerAnimation.getPlayerMovingUpResources();
        }
        else if(specificDirection == PlayerAnimationResources.AnimDirection.DOWN)
        {
            currentResource = playerAnimation.getPlayerMovingBackResources();
        }
        else if(specificDirection == PlayerAnimationResources.AnimDirection.LEFT)
        {
            currentResource = playerAnimation.getPlayerMovingLeftResources();
        }
        else //specificDirection == RIGHT
        {
            currentResource = playerAnimation.getPlayerMovingRightResources();
        }
        return currentResource;
    }

    public static int getScreenWidth()
    {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight()
    {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public float getXPosition()      // adding due to change of layout
    {
        if (getScreenHeight() > 2000)
        {
            Log.i("position", Float.toString(xPosition + getScreenHeight()/2 -200f) + " real x pos");
            PlayGameActivity.setDistanceFromButton(400f);
            return xPosition + getScreenHeight()/2 -200f;
        }
        else
            return xPosition + 850f;
    }

    public float getYPosition()     // adding due to change of layout
    {
        if (getScreenWidth() > 1200)
        {
            return yPosition*0.9f + getScreenWidth()/2;
        }
        else
            return yPosition*0.9f + 600f;
    }
}
