package com.example.yellowsoft.pharmzi;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by yellowsoft on 10/1/18.
 */

public class ProductImageSlider extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    Products products;

    public ProductImageSlider(Context context,Products products){
        this.context = context;
        this.products = products;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return products.images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);    }



    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View itemView = inflater.inflate(R.layout.product_image_slider, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.product_image);
        Picasso.with(context).load(products.images.get(position).image).into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
