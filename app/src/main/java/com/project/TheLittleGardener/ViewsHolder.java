package com.project.TheLittleGardener;


import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.zerokol.views.joystickView.JoystickView;

/**Holds necessary views*/
public class ViewsHolder
{
    public ImageView player;
    public View parentView;
    public JoystickView joystickView;
    public TextView plantInfoTextView;
    public ListView listView;

    ViewsHolder(View player, View parentView, View joystickView, View plantInfoTextView, View listView)
    {
        this.player = (ImageView) player;
        this.parentView = (View) parentView;
        this.joystickView = (JoystickView) joystickView;
        this.plantInfoTextView = (TextView) plantInfoTextView;
        this.listView = (ListView) listView;
    }
}
