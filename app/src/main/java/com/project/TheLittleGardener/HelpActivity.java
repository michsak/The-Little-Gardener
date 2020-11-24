package com.project.TheLittleGardener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**Description of game*/
public class HelpActivity extends AppCompatActivity
{
    private MediaPlayer mediaPlayer;
    private MusicManagement helpMenuMusicManagement;
    private TextView descriptionTextView;
    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        playMusic();

        //checks if a MenuInflater object exists in memory, used to delete double bar icons
        getMenuInflater();

        descriptionTextView = findViewById(R.id.gameDescriptionTextView);
        descriptionTextView.setText(getTextInEng());
        titleTextView = findViewById(R.id.howToPlayTextView);
    }

    private String getTextInEng()
    {
        String text = "The main goal of the game is complete all quests.\n You can win by completing 'em all.\n\n" +
                "Once you complete quest you get seeds, needed for planting plants. You can also get seeds by collecting grown plants\n \n"+
                "To boost speed of growing plants water them by clicking on them. It's possible to water each plant once during the whole process of growing.\n\n"+
                "You are able to move using joystick.\n\n"+
                "To remind yourself a quest click parchment in the upper left corner. \n\n"+
                "To show more plants click an arrow pointing up \n \n"+
                "You don't need more hints to play. Wish you a lot of luck!";

        return text;
    }

    private String getTextInPl()
    {
        String text = "Celem gry, a także drogą do wygranej jest wykonanie wszystkich misji.\n\n "+
                "Po wykonaniu misji gracz dostaje nasionka, które niezbędne są do sadzenia roślin. Nasiona można uzyskać także poprzez zebranie wyrośniętych roślin.\n\n" +
                "Aby przyspieszyć proces wzrostu roślin kliknij na daną roslinę. Gracz może tylko jednokrotnie podlać każdą roślinę.\n\n"+
                "Poruszanie następuje za pomocą joysticka zlokalizowanego w lewym dolnym rogu ekranu.\n\n"+
                "Aby wyświetlić aktualną misję kliknij w ikonkę pergaminu.\n\n" +
                "Aby zobaczyć więcej roślin kliknij w przycisk ze strzałką skierowaną do góry\n\n"+
                "Powodzenia!";

        return text;
    }

    private String getTitleInPl()
    {
        String title = "Opis gry";
        return title;
    }

    private String getTitleInEng()
    {
        String title = "How to play the game?";
        return title;
    }

    public void textInPolish(View view)
    {
        descriptionTextView.setText(getTextInPl());
        titleTextView.setText(getTitleInPl());
    }

    public void textInEnglish(View view)
    {
        descriptionTextView.setText(getTextInEng());
        titleTextView.setText(getTitleInEng());
    }

    public void playGame(View view)
    {
        startActivity(new Intent(HelpActivity.this, PlayGameActivity.class));
        helpMenuMusicManagement.stopMusic();
    }

    public void exitGame (View view)
    {
        finishAffinity();
    }

    private void playMusic()
    {
        helpMenuMusicManagement = new MusicManagement(mediaPlayer);
        helpMenuMusicManagement.mp = MediaPlayer.create(this, R.raw.menu);
        helpMenuMusicManagement.playMusic();
    }
}
