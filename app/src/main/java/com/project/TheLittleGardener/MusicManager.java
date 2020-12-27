package com.project.TheLittleGardener;

import android.media.MediaPlayer;

/**Set, play and stop music*/
public class MusicManager
{
    public MediaPlayer mp;

    MusicManager(MediaPlayer mp)
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
}
