package com.example.yellowsoft.pharmzi;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 5/1/18.
 */

public class ListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<String> titles;
    CategoryFragment fragment;
    ArrayList<Categories> categories;

    public ListAdapter(Context context, int list_items, ArrayList<Categories> categories, CategoryFragment fragment){
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View item_view = inflater.inflate(R.layout.list_items,null);
        final TextView title = (TextView) item_view.findViewById(R.id.titles);
        title.setText(categories.get(i).title);
        fragment.cat_id = categories.get(i).id;
        return item_view;
    }
}
