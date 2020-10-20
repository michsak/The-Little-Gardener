package com.project.TheLittleGardener;

import android.media.MediaPlayer;

public class MusicManagement
{
    public MediaPlayer mp;

    MusicManagement(MediaPlayer mp)
    {
        this.mp = mp;
    }


    public void playMainMenuMusic()
    {
        this.mp.start();
    }

    public void stopMainMenuMusic()
    {
        this.mp.stop();
    }

    public MediaPlayer getMainMenuMusic()
    {
        return this.mp;
    }
}
