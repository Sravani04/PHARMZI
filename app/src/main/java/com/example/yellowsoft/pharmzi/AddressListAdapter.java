package com.example.yellowsoft.pharmzi;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 11/1/18.
 */

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.MyViewHolder> {
    private Context context;
    ArrayList<Address> address;
    AddressActivity activity;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView type, area_title,delete_address;
        public MyViewHolder(View view) {
            super(view);
            type=(TextView) view.findViewById(R.id.type);
            area_title=(TextView) view.findViewById(R.id.area_title);
            delete_address = (TextView) view.findViewById(R.id.delete_address);

        }

    }


    public AddressListAdapter(Context context,ArrayList<Address> address,AddressActivity activity) {
        this.context = context;
        this.address = address;
        this.activity = activity;

    }



    @Override
    public AddressListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.addresses_screen_items, parent, false);
        return new AddressListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AddressListAdapter.MyViewHolder holder, final int position) {
        try {

            holder.type.setText(address.get(position).type);
            holder.area_title.setText((address.get(position).area_title));
            activity.add_id = address.get(position).id;
            holder.delete_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.delete_address();
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {

        return address.size();

    }

}

