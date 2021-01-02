package com.project.TheLittleGardener;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

/**Main game menu*/
public class MainActivity extends AppCompatActivity
{
    private MusicManager menuMusicManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        playMusic();

        //checks if a MenuInflater object exists in memory, used to delete double bar icons
        getMenuInflater();
    }

    public void playAction(View view)
    {
        startActivity(new Intent(MainActivity.this, PlayGameActivity.class));
        menuMusicManager.stopMusic();
    }

    public void helpAction(View view)
    {
        startActivity(new Intent(MainActivity.this, HelpActivity.class));
        menuMusicManager.stopMusic();
    }

    public void exitGame (View view)
    {
        finish();
    }

    private void playMusic()
    {
        menuMusicManager = new MusicManager(new MediaPlayer());
        menuMusicManager.mp = MediaPlayer.create(this, R.raw.menu);
        menuMusicManager.playMusic();
    }
}
