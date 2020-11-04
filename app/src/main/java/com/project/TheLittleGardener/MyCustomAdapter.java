package com.project.TheLittleGardener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**creates ListView, which has button and TextView*/
public class MyCustomAdapter extends BaseAdapter implements ListAdapter
{
    private Context context;
    private ArrayList<String> listOfAdditionalPlants;
    private Button[] buttons;


    public MyCustomAdapter(Context context)
    {
        this.context = context;
        listOfAdditionalPlants = new ArrayList<>();
        buttons = new Button[3];
        listOfAdditionalPlants.add("item1");
        listOfAdditionalPlants.add("item2");
    }

    @Override
    public int getCount()
    {
        return listOfAdditionalPlants.size();
    }

    @Override
    public Object getItem(int pos)
    {
        return listOfAdditionalPlants.get(pos);
    }

    @Override
    public long getItemId(int pos)
    {
        return listOfAdditionalPlants.get(pos).indexOf(pos);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View view = convertView;

        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_layout, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.first_list_item_string);
        listItemText.setText(listOfAdditionalPlants.get(position));
        buttons[0] = (Button) view.findViewById(R.id.first_list_button);
        buttons[0].setText("first");

        TextView listItemText2= (TextView)view.findViewById(R.id.second_list_item_string);
        listItemText2.setText(listOfAdditionalPlants.get(position));
        buttons[1] = (Button) view.findViewById(R.id.second_list_button);
        buttons[1].setText("second");


//TRY WITH IMAGE VIEW
/*
        firstButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                notifyDataSetChanged();
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                notifyDataSetChanged();
            }
        });*/

        return view;
    }
}