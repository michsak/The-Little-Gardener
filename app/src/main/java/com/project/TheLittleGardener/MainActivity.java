package com.project.TheLittleGardener;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

/**main game menu*/
public class MainActivity extends AppCompatActivity
{
    private MediaPlayer mediaPlayer;
    private MusicManagement menuMusicManagement;

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
        menuMusicManagement.stopMusic();
    }

    public void helpAction(View view)
    {
        startActivity(new Intent(MainActivity.this, HelpActivity.class));
    }

    public void exitGame (View view)
    {
        finish();
    }

    private void playMusic()
    {
        menuMusicManagement = new MusicManagement(mediaPlayer);
        menuMusicManagement.mp = MediaPlayer.create(this, R.raw.menu);
        menuMusicManagement.playMusic();
    }
}