package com.project.TheLittleGardener;

import android.media.MediaPlayer;

public class MusicManagement
{
    public MediaPlayer mp;

    MusicManagement(MediaPlayer mp)
    {
        this.mp = mp;
    }


    public void playMusic()
    {
        this.mp.start();
    }

    public void stopMusic()
    {
        this.mp.stop();
    }

    public MediaPlayer getMusic()
    {
        return this.mp;
    }
}
