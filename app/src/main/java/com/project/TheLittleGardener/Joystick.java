package com.project.TheLittleGardener;

import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import com.zerokol.views.joystickView.JoystickView;

/**creates joystick and enables user move in the wanted direction*/
public class Joystick implements MarginsOfScreen
{
    private JoystickView joystick;
    private ImageView player;
    private float xPosition = 0f;
    private float yPosition = -10f;
    private final int durationOfAnim = 100;
    private final float speed = 15f;
    private int height;
    private int width;


    Joystick( JoystickView joystick, ImageView player, int height, int width)
    {
        this.joystick = joystick;
        this.player = player;
        this.height = height;
        this.width = width;
    }

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
        animation.setDuration(durationOfAnim);
        animation.setFillAfter(true);
        player.startAnimation(animation);
        this.xPosition = xFinalPosition;
        this.yPosition = yFinalPosition;
    }

    private void moveUp()
    {
        if (yPosition >= (-height + topPlayerMargin))
        {
            createAndStartAnimation(xPosition, xPosition, yPosition, yPosition - speed);
        }
    }

    private void moveUpRight()
    {
        if (xPosition <= width/2 - 100f && yPosition >= (-height + topPlayerMargin))
        {
            createAndStartAnimation(xPosition, xPosition+speed, yPosition, yPosition-speed);
        }
    }

    private void moveUpLeft()
    {
        if (xPosition >= (-width/2 + 100f) && yPosition >= (-height + topPlayerMargin))
        {
            createAndStartAnimation(xPosition, xPosition-speed, yPosition, yPosition-speed);
        }
    }

    private void moveDown()
    {
        if (yPosition <= -downPlayerMargin)
        {
            createAndStartAnimation(xPosition, xPosition, yPosition, yPosition+speed);
        }
    }

    private void moveDownLeft()
    {
        if (yPosition <= -downPlayerMargin && xPosition >= (-width/2 + 100f))
        {
            createAndStartAnimation(xPosition, xPosition-speed, yPosition, yPosition+speed);
        }
    }

    private void moveDownRight()
    {
        if (yPosition <= -downPlayerMargin && xPosition <= (width/2 - 100f))
        {
            createAndStartAnimation(xPosition, xPosition+speed, yPosition, yPosition+speed);
        }
    }

    private void moveRight()
    {
        if (xPosition <= width/2 - edgePlayerMarin)
        {
            createAndStartAnimation(xPosition, xPosition+speed, yPosition, yPosition);
        }
    }

    private void moveLeft()
    {
        if (xPosition > (-width/2 + edgePlayerMarin))
        {
            createAndStartAnimation(xPosition, xPosition-speed, yPosition, yPosition);
        }
    }

    public float getXPosition()
    {
        return xPosition + 850f; // adding due to change of layout
    }

    public float getYPosition()
    {
        return yPosition*0.9f + 600f; // adding due to change of layout
    }
}
