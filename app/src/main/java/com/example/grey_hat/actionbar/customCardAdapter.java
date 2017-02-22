package com.example.grey_hat.actionbar;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

;import java.util.ArrayList;

/**
 * Created by grey-hat on 24/12/16.
 */

public class customCardAdapter extends ArrayAdapter<String> {
    private Activity context;
    private ArrayList<Bitmap> img;
    private ArrayList<String> title;
    ArrayList<String> videoIDs;
    customCardAdapter(Activity context,ArrayList<Bitmap> img,ArrayList<String> title, ArrayList<String> videoIDs)
    {
        super(context, R.layout.frag_list, title);
        this.context=context;
        this.img=img;
        this.videoIDs=videoIDs;
        this.title=title;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater =context.getLayoutInflater();
        View rowView = layoutInflater.inflate(R.layout.card_view,null,true);
        TextView txtview = (TextView)rowView.findViewById(R.id.cardTitle);
       ImageView imgview = (ImageView)rowView.findViewById(R.id.crImage);
        imgview.setImageBitmap(img.get(position));
        txtview.setText(title.get(position));

        return rowView;
    }
}
