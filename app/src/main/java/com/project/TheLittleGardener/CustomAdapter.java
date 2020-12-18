package com.project.TheLittleGardener;

import android.content.Context;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


/**Creates custom List View with ImageView and TextView*/
public class CustomAdapter extends BaseAdapter implements ListAdapter
{
    private static LayoutInflater inflater=null;
    private View parentView;
    private ImageView playerView;
    private ListView lView;
    private TextView questTextView;
    private TextView plantInfoTextView;
    private String[] result;
    private int [] imageId;

    public CustomAdapter(Context context, String[] ddListText, int[] ddListImages, View parentView,
                         ImageView playerView, ListView listView, TextView questTextView, TextView plantInfoTextView)
    {
        result = ddListText;
        imageId = ddListImages;
        this.parentView = parentView;
        this.playerView = playerView;
        this.questTextView = questTextView;
        this.lView = listView;
        this.plantInfoTextView = plantInfoTextView;

        /*instantiate the contents of layout XML files into their corresponding View objects*/
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return result.length;
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent)
    {
        final CustomAdapterContentHolder customAdapterContentHolder = new CustomAdapterContentHolder();
        final View adapterView;

        adapterView = inflater.inflate(R.layout.custom_layout, null);
        changeVisibilityToDropDownListRequirements();
        findCustomAdapterViews(customAdapterContentHolder, adapterView);
        setCustomAdapterViewsParams(position, customAdapterContentHolder);

        adapterView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                changeVisibilityToPlayScreenRequirements();
                CurrentPlantAndNumberOfSeeds.setCurrentPlant(position);
                PlayGameActivity.resizePlantTextViewInfoOnBottomButtonClick(result[position], plantInfoTextView);
            }
        });
        return adapterView;
    }

    private void setCustomAdapterViewsParams(int position, CustomAdapterContentHolder customAdapterContentHolder) {
        customAdapterContentHolder.contentTextView.setText(result[position]);
        customAdapterContentHolder.contentImageView.setImageResource(imageId[position]);
        int positionOfCost = CostAndRewardForPlants.costOfPlants[position];
        customAdapterContentHolder.seedTextView.setText(Integer.toString(positionOfCost));
    }

    private void findCustomAdapterViews(CustomAdapterContentHolder customAdapterContentHolder, View adapterView) {
        customAdapterContentHolder.contentTextView = adapterView.findViewById(R.id.list_TextView);
        customAdapterContentHolder.contentImageView = adapterView.findViewById(R.id.list_imageView);
        customAdapterContentHolder.seedTextView = adapterView.findViewById(R.id.numberOfSeedsTextView);
    }

    private void changeVisibilityToDropDownListRequirements()
    {
        parentView.setVisibility(View.INVISIBLE);
        questTextView.setVisibility(View.INVISIBLE);
        plantInfoTextView.setVisibility(View.INVISIBLE);
        playerView.setImageResource(R.drawable.empty_player);
        lView.setVisibility(View.VISIBLE);
    }

    private void changeVisibilityToPlayScreenRequirements()
    {
        parentView.setVisibility(View.VISIBLE);
        playerView.setImageResource(R.drawable.gardener_front);
        questTextView.setVisibility(View.VISIBLE);
        plantInfoTextView.setVisibility(View.VISIBLE);
        lView.setVisibility(View.INVISIBLE);
    }
}
