package com.project.TheLittleGardener;

import android.content.Context;
import android.sax.RootElement;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**creates ListView, which has button and TextView*/
public class MyCustomAdapter extends BaseAdapter implements ListAdapter
{
    private Context context;
    String[] result;
    int [] imageId;
    boolean[] mVisisbilityList = { false, false, false, false };
    private static LayoutInflater inflater=null;

    public MyCustomAdapter(Context context, String[] ddListText, int[] ddListImages)
    {
        result = ddListText;
        imageId = ddListImages;
        this.context = context;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        final ContentHolder contentHolder =new ContentHolder();
        final View adapterView;
        adapterView = inflater.inflate(R.layout.custom_layout, null);
        contentHolder.tv = adapterView.findViewById(R.id.list_TextView);
        contentHolder.img = adapterView.findViewById(R.id.list_imageView);
        contentHolder.tv.setText(result[position]);
        contentHolder.img.setImageResource(imageId[position]);
        adapterView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();

                for (int i = 0; i < mVisisbilityList.length; i++)
                {

                }
            }
        });
        return adapterView;
    }

}
