package com.project.TheLittleGardener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
    }
}