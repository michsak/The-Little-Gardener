package com.project.TheLittleGardener;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicManagement
{
    private Object MainActivity;
    private MediaPlayer mediaPlayer;


    public void playMainMenuMusic()
    {
        mediaPlayer = MediaPlayer.create((Context) MainActivity, R.raw.menu);
        mediaPlayer.start();
    }

    public MediaPlayer getMainMenuMusic()
    {
        return mediaPlayer;
    }
}
