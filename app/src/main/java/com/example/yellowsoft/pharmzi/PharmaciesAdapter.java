package com.example.yellowsoft.pharmzi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 9/1/18.
 */

public class PharmaciesAdapter  extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Pharmacies> pharmacies;

    public PharmaciesAdapter(Context context,ArrayList<Pharmacies> pharmacies){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.pharmacies = pharmacies;
    }
    @Override
    public int getCount() {
        return pharmacies.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        View item_view = inflater.inflate(R.layout.home_screen_items,null);
        ImageView phar_image = (ImageView) item_view.findViewById(R.id.phar_image);
        TextView title = (TextView) item_view.findViewById(R.id.title);
        TextView payment_title= (TextView) item_view.findViewById(R.id.payment_title);
        TextView time = (TextView) item_view.findViewById(R.id.time);
        TextView minimum = (TextView) item_view.findViewById(R.id.minimum);
        Picasso.with(context).load(pharmacies.get(position).image).into(phar_image);
        title.setText(pharmacies.get(position).title);
        payment_title.setText(pharmacies.get(position).payments.get(0).title);
        time.setText(pharmacies.get(position).time);
        minimum.setText(pharmacies.get(position).minimum);
        return item_view;
    }
}

