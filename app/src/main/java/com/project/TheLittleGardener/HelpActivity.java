package com.project.TheLittleGardener;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**description of game*/
public class HelpActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //checks if a MenuInflater object exists in memory, used to delete double bar icons
        getMenuInflater();

        TextView descriptionTextView = findViewById(R.id.gameDescriptionTextView);
        descriptionTextView.setText(getTextInEng());
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
}