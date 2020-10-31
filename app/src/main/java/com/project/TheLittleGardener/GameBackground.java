package com.project.TheLittleGardener;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameBackground
{
    Bitmap background;
    int x=0, y=0;

    GameBackground(int screenX, int screenY, Resources res)
    {
        background = BitmapFactory.decodeResource(res, R.drawable.game_background);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false); //when moving map it must be changed;
    }
}
