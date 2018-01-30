package com.example.yellowsoft.pharmzi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 23/1/18.
 */

public class OffersAdapter  extends RecyclerView.Adapter<OffersAdapter.MyViewHolder> {
    Context context;
    ArrayList<Offers> offers;
    LayoutInflater inflater;


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView offer_image;
        public MyViewHolder(View view) {
            super(view);
            offer_image = (ImageView) view.findViewById(R.id.offer_image);

        }


    }


    public OffersAdapter(Context context,ArrayList<Offers> offers) {
        this.context = context;
        this.offers = offers;
        inflater = LayoutInflater.from(context);



    }

    @Override
    public OffersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offer_items, parent, false);
        return new OffersAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final OffersAdapter.MyViewHolder holder, final int position) {
        Picasso.with(context).load(offers.get(position).image).into(holder.offer_image);
        holder.offer_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {

        return offers.size();

    }

}

