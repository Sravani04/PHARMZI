package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 24/1/18.
 */

public class MoreOrdersAdapter extends RecyclerView.Adapter<MoreOrdersAdapter.MyViewHolder> {
    Context context;
    ArrayList<Orders> orders;
    ArrayList<Products> products;
    MoreOrdersPage activity;




    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView order_image;
        TextView title,quantity,price,status,product_name,count;
        LinearLayout receipt_btn,cart_btn;
        public MyViewHolder(View view) {
            super(view);
            order_image = (ImageView) view.findViewById(R.id.order_image);
            title = (TextView) view.findViewById(R.id.title);
            quantity = (TextView) view.findViewById(R.id.quantity);
            price = (TextView) view.findViewById(R.id.price);
            status = (TextView) view.findViewById(R.id.status);
            receipt_btn = (LinearLayout) view.findViewById(R.id.receipt_btn);
            cart_btn = (LinearLayout) view.findViewById(R.id.cart_btn);
            product_name = (TextView) view.findViewById(R.id.product_name);
            count = (TextView) view.findViewById(R.id.count);


        }


    }


    public MoreOrdersAdapter(MoreOrdersPage activity, Context context, ArrayList<Orders> orders, ArrayList<Products> products) {
        this.context = context;
        this.orders = orders;
        this.products = products;
        this.activity = activity;


    }


    @Override
    public MoreOrdersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myorder_items, parent, false);
        return new MoreOrdersAdapter.MyViewHolder(itemView);
    }
    String temp="";
    @Override
    public void onBindViewHolder(final MoreOrdersAdapter.MyViewHolder holder, final int position) {
        Picasso.with(context).load(orders.get(position).pharmacy_image).into(holder.order_image);
        holder.title.setText(orders.get(position).pharmacy_title);
        holder.quantity.setText(orders.get(position).products.get(0).quantity);
        holder.status.setText(orders.get(position).delivery_status);
        holder.product_name.setText(orders.get(position).products.get(0).product_name);
        holder.price.setText(orders.get(position).total_price + "KD");
        holder.count.setText(String.valueOf(orders.get(position).products.size()));
        Log.e("count",String.valueOf(orders.get(position).products.size()));
        holder.cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=0;i<orders.get(position).products.size();i++) {

                    if(temp.equals("")){
                        temp=orders.get(position).products.get(i).product_id;
                    }else{
                        temp=temp+","+orders.get(position).products.get(i).product_id;
                    }
                }
                if (!Session.GetPharmciId(context).equals(orders.get(position).pharmacy_id)) {
                    Log.e("check1", String.valueOf(!Session.GetPharmciId(context).equals(orders.get(position).pharmacy_id)));
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!isFinishing()){
                                new AlertDialog.Builder(context)
                                        .setTitle("Alert")
                                        .setMessage("You are in Different Pharmacy, Selected cart products will be removed")
                                        .setCancelable(false)
                                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Session.deleteCart(context);
                                                activity.get_products(temp);
                                            }
                                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).show();
                            }
                        }

                        private boolean isFinishing() {
                            return false;
                        }
                    });

                }else {
                    Log.e("check", String.valueOf(Session.GetPharmciId(context).equals(orders.get(position).pharmacy_id)));
                    activity.get_products(temp);
                }

            }
        });
    }

    @Override
    public int getItemCount() {

        return orders.size();

    }

}

