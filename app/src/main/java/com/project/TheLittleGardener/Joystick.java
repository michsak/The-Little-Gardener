package com.project.TheLittleGardener;

import android.util.Log;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.zerokol.views.joystickView.JoystickView;


public class Joystick
{
    private JoystickView joystick;
    private ImageView player;

    private float xPositon = 0f;
    private float yPosition = -10f;
    private float downPlayerMargin = 12f;
    private float edgePlayerMarin = 95f;
    private float topPlayerMargin = 420f;
    private int durationOfAnim = 100;
    private int height;
    private int width;

    private final float speed = 20f;


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
                //enable player to move
                //right is actually left, and left is actually right
                switch (direction)
                {
                    case JoystickView.FRONT:
                        moveUp();
                        break;
                    case JoystickView.FRONT_RIGHT:
                        moveUpLeft();
                        break;
                    case JoystickView.RIGHT:
                        moveLeft();
                        break;
                    case JoystickView.RIGHT_BOTTOM:
                        moveDownLeft();
                        break;
                    case JoystickView.BOTTOM:
                        moveDown();
                        break;
                    case JoystickView.BOTTOM_LEFT:
                        moveDownRight();
                        break;
                    case JoystickView.LEFT:
                        moveRight();
                        break;
                    case JoystickView.LEFT_FRONT:
                        moveUpRight();
                        break;
                    default:
                }
            }
        }, JoystickView.DEFAULT_LOOP_INTERVAL);
    }


    private void moveUp()
    {
        if (yPosition >= (-height + topPlayerMargin))
        {
            TranslateAnimation animation = new TranslateAnimation(xPositon, xPositon, yPosition, yPosition - speed);
            animation.setDuration(durationOfAnim);
            animation.setFillAfter(true);
            player.startAnimation(animation);
            yPosition = yPosition - speed;
        }
    }

    private void moveUpRight()
    {
        if (xPositon <= width/2 - 100f && yPosition >= (-height + topPlayerMargin))
        {
            TranslateAnimation animation = new TranslateAnimation(xPositon, xPositon + speed, yPosition, yPosition - speed);
            animation.setDuration(durationOfAnim);
            animation.setFillAfter(true);
            player.startAnimation(animation);
            yPosition = yPosition - speed;
            xPositon = xPositon + speed;
        }
    }

    private void moveUpLeft()
    {
        if (xPositon >= (-width/2 + 100f) && yPosition >= (-height + topPlayerMargin))
        {
            TranslateAnimation animation = new TranslateAnimation(xPositon, xPositon - speed, yPosition, yPosition - speed);
            animation.setDuration(durationOfAnim);
            animation.setFillAfter(true);
            player.startAnimation(animation);
            yPosition = yPosition - speed;
            xPositon = xPositon - speed;
        }
    }

    private void moveDown()
    {
        if (yPosition <= -downPlayerMargin)
        {
            TranslateAnimation animation = new TranslateAnimation(xPositon, xPositon, yPosition, yPosition + speed);
            animation.setDuration(durationOfAnim);
            animation.setFillAfter(true);
            player.startAnimation(animation);
            yPosition = yPosition + speed;
        }
    }

    private void moveDownLeft()
    {
        if (yPosition <= -downPlayerMargin && xPositon >= (-width/2 + 100f))
        {
            TranslateAnimation animation = new TranslateAnimation(xPositon, xPositon - speed, yPosition, yPosition + speed);
            animation.setDuration(durationOfAnim);
            animation.setFillAfter(true);
            player.startAnimation(animation);
            yPosition = yPosition + speed;
            xPositon = xPositon - speed;
        }
    }

    private void moveDownRight()
    {
        if (yPosition <= -downPlayerMargin && xPositon <= (width/2 - 100f))
        {
            TranslateAnimation animation = new TranslateAnimation(xPositon, xPositon + speed, yPosition, yPosition + speed);
            animation.setDuration(durationOfAnim);
            animation.setFillAfter(true);
            player.startAnimation(animation);
            yPosition = yPosition + speed;
            xPositon = xPositon + speed;
        }
    }

    private void moveRight()
    {
        if (xPositon <= width/2 - edgePlayerMarin)
        {
            TranslateAnimation animation = new TranslateAnimation(xPositon, xPositon + speed, yPosition, yPosition);
            animation.setDuration(durationOfAnim);
            animation.setFillAfter(true);
            player.startAnimation(animation);
            xPositon = xPositon + speed;
        }
    }

    private void moveLeft()
    {
        if (xPositon > (-width/2 + edgePlayerMarin))
        {
            TranslateAnimation animation = new TranslateAnimation(xPositon, xPositon - speed, yPosition, yPosition);
            animation.setDuration(durationOfAnim);
            animation.setFillAfter(true);
            player.startAnimation(animation);
            xPositon = xPositon - speed;
        }
    }
}
