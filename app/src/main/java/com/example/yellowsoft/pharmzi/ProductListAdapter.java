package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 22/1/18.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.SimpleViewHolder> {
    Context mContext;
    LayoutInflater inflater;
    private static final int LIST_ITEM = 0;
    private static final int GRID_ITEM = 1;
    boolean isViewWithCatalog = true;
    ArrayList<Products> products;
    ProductsListActivity activity;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        ImageView pro_image;
        TextView pro_price,pro_title;
        LinearLayout list;
        public SimpleViewHolder(View view){
            super(view);
            pro_image = (ImageView) view.findViewById(R.id.pro_image);
            pro_price = (TextView) view.findViewById(R.id.pro_price);
            pro_title = (TextView) view.findViewById(R.id.pro_title);
            list = (LinearLayout) view.findViewById(R.id.list);

        }
    }

    public ProductListAdapter(Context context,ArrayList<Products> products,ProductsListActivity activity){
        mContext = context;
        inflater = LayoutInflater.from(context);
        this.products = products;
        this.activity = activity;
    }

    @Override
    public ProductListAdapter.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == LIST_ITEM){
            itemView = LayoutInflater.from(mContext).inflate( R.layout.product_row_layout_list, null);
        }else{
            itemView = LayoutInflater.from(mContext).inflate(R.layout.product_row_layout_grid, null);
        }
        return new SimpleViewHolder(itemView);
//        final View view = LayoutInflater.from(mContext).inflate(isViewWithCatalog ? R.layout.product_row_layout_list : R.layout.product_row_layout_grid,null);
//        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final  SimpleViewHolder holder, final int position) {
        if (Session.GetLang(mContext).equals("en")) {
            holder.pro_title.setText(products.get(position).title);
        } else {
            holder.pro_title.setText(products.get(position).title_ar);
        }

        holder.pro_price.setText(products.get(position).price + "KD");
        activity.phar_title = products.get(position).pharmacies.get(0).title;
        activity.phar_title_ar = products.get(position).pharmacies.get(0).title_ar;
        Picasso.with(mContext).load(products.get(position).images.get(0).image).into(holder.pro_image);

        holder.list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ProductDetail.class);
                intent.putExtra("products", products.get(position));
                mContext.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemViewType (int position) {
        if (isViewWithCatalog){
            return LIST_ITEM;
        }else{
            return GRID_ITEM;
        }
    }


    @Override
    public int getItemCount() {
        return products.size();
    }
}

