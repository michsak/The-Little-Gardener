package com.project.TheLittleGardener;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.content.Context;
import androidx.core.content.ContextCompat;


public class Player
{
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;

    public Player (Context context, double positionX, double positionY, double radius)
    {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;

        paint=new Paint();
        int color = ContextCompat.getColor(context, R.color.yellow);
        paint.setColor(color);
    }


    public void draw(Canvas canvas)  //to be changed
    {
        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);

    }

    public void update()
    {

    }

    public void setPosition(double positionX, double positionY)
    {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
