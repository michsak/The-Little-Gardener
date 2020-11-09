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


/**custom Adapter, which has ImageView and TextView*/
public class MyCustomAdapter extends BaseAdapter implements ListAdapter
{
    private Context context;
    private View parentView;
    private ImageView playerView;
    private ListView lView;

    String[] result;
    int [] imageId;
    private static LayoutInflater inflater=null;

    public MyCustomAdapter(Context context, String[] ddListText, int[] ddListImages, View parentView, ImageView playerView, ListView listView)
    {
        result = ddListText;
        imageId = ddListImages;
        this.context = context;
        this.parentView = parentView;
        this.playerView = playerView;
        this.lView = listView;
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

    //in case of adding components set invisible every parent view
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent)
    {
        final CustomAdapterContentHolder customAdapterContentHolder =new CustomAdapterContentHolder();
        final View adapterView;

        parentView.setVisibility(View.INVISIBLE);
        playerView.setImageResource(R.drawable.empty_player);   //change to invisible drawable element
        lView.setVisibility(View.VISIBLE);

        adapterView = inflater.inflate(R.layout.custom_layout, null);
        customAdapterContentHolder.contentTextView = adapterView.findViewById(R.id.list_TextView);
        customAdapterContentHolder.contentImageView = adapterView.findViewById(R.id.list_imageView);
        customAdapterContentHolder.contentTextView.setText(result[position]);
        customAdapterContentHolder.contentImageView.setImageResource(imageId[position]);

        adapterView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                parentView.setVisibility(View.VISIBLE);
                playerView.setImageResource(R.drawable.player);
                lView.setVisibility(View.INVISIBLE);

                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
            }
        });
        return adapterView;
    }

}