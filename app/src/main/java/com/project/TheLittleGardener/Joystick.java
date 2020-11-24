package com.project.TheLittleGardener;

import android.content.Context;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import com.zerokol.views.joystickView.JoystickView;

/**Creates joystick and enables user move in the wanted direction*/
public class Joystick extends MetricsOfScreen
{
    private JoystickView joystick;
    private ImageView player;
    private MarginsOfScreen marginsOfScreen;
    private float xPosition = 0f;
    private float yPosition = -10f;
    private final int durationOfAnim = 100;
    private final float playerMovingSpeed = 15f;

    Joystick(JoystickView joystick, ImageView player, Context context)
    {
        super(context);
        this.joystick = joystick;
        this.player = player;
        marginsOfScreen = new MarginsOfScreen();
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
        if (yPosition >= (-height + marginsOfScreen.topPlayerMargin))
        {
            createAndStartAnimation(xPosition, xPosition, yPosition, yPosition - playerMovingSpeed);
            player.setImageResource(R.drawable.gardener_back);
        }
    }

    private void moveUpRight()
    {
        if (xPosition <= width/2 - 100f && yPosition >= (-height + marginsOfScreen.topPlayerMargin))
        {
            createAndStartAnimation(xPosition, xPosition+ playerMovingSpeed, yPosition, yPosition- playerMovingSpeed);
            player.setImageResource(R.drawable.gardener_back);
        }
    }

    private void moveUpLeft()
    {
        if (xPosition >= (-width/2 + 100f) && yPosition >= (-height + marginsOfScreen.topPlayerMargin))
        {
            createAndStartAnimation(xPosition, xPosition- playerMovingSpeed, yPosition, yPosition- playerMovingSpeed);
            player.setImageResource(R.drawable.gardener_back);
        }
    }

    private void moveDown()
    {
        if (yPosition <= -marginsOfScreen.downPlayerMargin)
        {
            createAndStartAnimation(xPosition, xPosition, yPosition, yPosition+ playerMovingSpeed);
            player.setImageResource(R.drawable.gardener_front);
        }
    }

    private void moveDownLeft()
    {
        if (yPosition <= -marginsOfScreen.downPlayerMargin && xPosition >= (-width/2 + 100f))
        {
            createAndStartAnimation(xPosition, xPosition- playerMovingSpeed, yPosition, yPosition+ playerMovingSpeed);
            player.setImageResource(R.drawable.gardener_left);
        }
    }

    private void moveDownRight()
    {
        if (yPosition <= -marginsOfScreen.downPlayerMargin && xPosition <= (width/2 - 100f))
        {
            createAndStartAnimation(xPosition, xPosition+ playerMovingSpeed, yPosition, yPosition+ playerMovingSpeed);
            player.setImageResource(R.drawable.gardener_right);
        }
    }

    private void moveRight()
    {
        if (xPosition <= width/2 - marginsOfScreen.edgePlayerMarin)
        {
            createAndStartAnimation(xPosition, xPosition+ playerMovingSpeed, yPosition, yPosition);
            player.setImageResource(R.drawable.gardener_right);
        }
    }

    private void moveLeft()
    {
        if (xPosition > (-width/2 + marginsOfScreen.edgePlayerMarin))
        {
            createAndStartAnimation(xPosition, xPosition- playerMovingSpeed, yPosition, yPosition);
            player.setImageResource(R.drawable.gardener_left);
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
