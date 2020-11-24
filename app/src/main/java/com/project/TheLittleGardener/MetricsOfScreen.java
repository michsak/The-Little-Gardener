package com.project.TheLittleGardener;

import android.content.Context;
import android.util.DisplayMetrics;

/**Get screen width and height to enable player move*/
public class MetricsOfScreen
{
    public int height;
    public int width;

    MetricsOfScreen(Context context)
    {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.height = displayMetrics.heightPixels;
        this.width = displayMetrics.widthPixels;
    }
}
