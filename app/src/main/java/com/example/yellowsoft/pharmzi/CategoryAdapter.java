package com.example.yellowsoft.pharmzi;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 5/1/18.
 */

public class CategoryAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Categories> categories;
    CategoryFragmentList fragment;

    public CategoryAdapter(Context context, ArrayList<Categories> categories,CategoryFragmentList fragment){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.categories = categories;
        this.fragment = fragment;
    }
    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View item_view = inflater.inflate(R.layout.category_fragment_items,null);
        ImageView cat_image = (ImageView) item_view.findViewById(R.id.cat_image);
        final TextView cat_title = (TextView) item_view.findViewById(R.id.cat_title);
        Picasso.with(context).load(categories.get(i).image).into(cat_image);
        cat_title.setText(categories.get(i).title);
        return item_view;
    }
}
