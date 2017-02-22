package com.example.grey_hat.actionbar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;;
/**
 * Created by grey-hat on 24/12/16.
 */

public class customAdapter extends ArrayAdapter<String> {
    private Activity context;
    private  String [] characters;
    private Integer[] resourceIds;
    customAdapter(Activity context,String[] characters,Integer[] resourceIds)
    {
        super(context, R.layout.frag_list, characters);
        this.context=context;
        this.characters=characters;
        this.resourceIds=resourceIds;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater =context.getLayoutInflater();
        View rowView = layoutInflater.inflate(R.layout.frag_list,null,true);
        TextView txtview = (TextView)rowView.findViewById(R.id.listTxt);
        ImageView imgview = (ImageView)rowView.findViewById(R.id.imgList);
        txtview.setText(characters[position]);
        imgview.setBackgroundResource(resourceIds[position]);
        return rowView;
    }
}
