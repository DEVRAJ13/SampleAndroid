package com.example.xornor_pc.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xornor_pc.myapplication.R;
import com.example.xornor_pc.myapplication.model.Hero;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Hero> {

    //the hero list that will be displayed
    private List<Hero> heroList;

    //the context object
    private Context mCtx;

    //here we are getting the herolist and context
    //so while creating the object of this adapter class we need to give herolist and context
    public ListViewAdapter(List<Hero> heroList, Context mCtx) {
        super(mCtx, R.layout.list_items, heroList);
        this.heroList = heroList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        //getting text views
        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        //getting image views
        ImageView img =  listViewItem.findViewById(R.id.textViewImageUrl);

        //Getting the hero for the specified position
        Hero hero = heroList.get(position);

        //setting hero values to textviews
        textViewName.setText(hero.getName());
        //setting hero images to ImageView
        Picasso.with(getContext()).load(hero.getImageUrl()).into(img);

        //returning the listitem
        return listViewItem;
    }
}