package com.project.TheLittleGardener;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.zerokol.views.joystickView.JoystickView;

/**Holds player, parent, joystick, plant info and listview views*/
public class ViewsHolder
{
    public ImageView player;
    public View parentView;
    public JoystickView joystickView;
    public TextView plantInfoTextView;
    public ListView listView;
    public ImageView backgroundViewUnderPerch;
    public ImageView backgroundViewUnderScore;

    ViewsHolder(View player, View parentView, View joystickView, View plantInfoTextView, View listView,
                View backgroundViewUnderPerch, View backgroundViewUnderScore)
    {
        this.player = (ImageView) player;
        this.parentView = (View) parentView;
        this.joystickView = (JoystickView) joystickView;
        this.plantInfoTextView = (TextView) plantInfoTextView;
        this.listView = (ListView) listView;
        this.backgroundViewUnderPerch = (ImageView) backgroundViewUnderPerch;
        this.backgroundViewUnderScore = (ImageView) backgroundViewUnderScore;
    }
}
