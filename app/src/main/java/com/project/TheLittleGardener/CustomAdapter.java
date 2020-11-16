package com.project.TheLittleGardener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**creates custom List View with ImageView and TextView*/
public class CustomAdapter extends BaseAdapter implements ListAdapter
{
    private Context context;
    private View parentView;
    private ImageView playerView;
    private ListView lView;
    private String[] result;
    private int [] imageId;
    private static LayoutInflater inflater=null;

    public CustomAdapter(Context context, String[] ddListText, int[] ddListImages, View parentView, ImageView playerView, ListView listView)
    {
        result = ddListText;
        imageId = ddListImages;
        this.context = context;
        this.parentView = parentView;
        this.playerView = playerView;
        this.lView = listView;
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

                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
            }
        });
        return adapterView;
    }

    private void setCustomAdapterViewsParams(int position, CustomAdapterContentHolder customAdapterContentHolder) {
        customAdapterContentHolder.contentTextView.setText(result[position]);
        customAdapterContentHolder.contentImageView.setImageResource(imageId[position]);
    }

    private void findCustomAdapterViews(CustomAdapterContentHolder customAdapterContentHolder, View adapterView) {
        customAdapterContentHolder.contentTextView = adapterView.findViewById(R.id.list_TextView);
        customAdapterContentHolder.contentImageView = adapterView.findViewById(R.id.list_imageView);
    }

    private void changeVisibilityToDropDownListRequirements()
    {
        parentView.setVisibility(View.INVISIBLE);
        playerView.setImageResource(R.drawable.empty_player);
        lView.setVisibility(View.VISIBLE);
    }

    private void changeVisibilityToPlayScreenRequirements()
    {
        parentView.setVisibility(View.VISIBLE);
        playerView.setImageResource(R.drawable.player);
        lView.setVisibility(View.INVISIBLE);
    }
}
