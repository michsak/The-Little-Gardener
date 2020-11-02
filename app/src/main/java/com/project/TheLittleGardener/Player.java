package com.project.TheLittleGardener;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player
{
    int x;
    int y;
    int width;
    int height;

    Bitmap gardener;

    Player(int screenX, int screenY, Resources res)
    {
        this.x = screenX;
        this.y = screenY;

        gardener = BitmapFactory.decodeResource(res, R.drawable.player);

        width = gardener.getWidth()/2;
        height = gardener.getHeight()/2;

        gardener = Bitmap.createScaledBitmap(gardener, width, height, false);
        x = screenX/2 ; //to be centered
    }

    public Bitmap getGardener()
    {
        return gardener;
    }

}
