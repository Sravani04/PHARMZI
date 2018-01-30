package com.example.yellowsoft.pharmzi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 12/1/18.
 */

public class AddressAdapter extends BaseAdapter {
    int price;
    String[] result;
    Context context;
    int [] imageId;
    ArrayList<String> name;
    //    ArrayList<CompanyDetails> companyDetailses;
    private static LayoutInflater inflater=null;
    public AddressAdapter(Context mainActivity, ArrayList<String> name) {
        // TODO Auto-generated constructor stub
        //  result=prgmNameList;
        context=mainActivity;
        this.name=name;
        //  imageId=prgmImages;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return name.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView pick_com_name,Pick_com_items;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.my_address_item, null);
        holder.pick_com_name=(TextView) rowView.findViewById(R.id.myadress_item_title);
        holder.pick_com_name.setText(name.get(position));
//        holder.tv1.setText(companyDetailses.get(position).current_status);
//        price=Integer.parseInt(companyDetailses.get(position).price_pickup)+Integer.parseInt(companyDetailses.get(position).price_drop_off);
//        holder.tv2.setText(String.valueOf(price));
//        // holder.img.setImageResource(imageId[position]);
//        Picasso.with(context).load(companyDetailses.get(position).logo).into(holder.img);

        return rowView;
    }

}

