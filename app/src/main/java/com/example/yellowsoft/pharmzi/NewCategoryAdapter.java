package com.example.yellowsoft.pharmzi;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by yellowsoft on 22/1/18.
 */

public class NewCategoryAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Categories> categories;


    public NewCategoryAdapter(Context context,ArrayList<Categories> categories){
        this.context = context;
        this.categories = categories;
        inflater = LayoutInflater.from(context);
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
    public View getView(final  int i, View view, ViewGroup viewGroup) {
        View item_view  = inflater.inflate(R.layout.new_category_items,null);
        TextView cat_title = (TextView) item_view.findViewById(R.id.cat_title);
        final LinearLayout category = (LinearLayout) item_view.findViewById(R.id.category);
        ImageView next_btn = (ImageView) item_view.findViewById(R.id.next_btn);
        cat_title.setText(categories.get(i).title);


        if (Session.GetLang(context).equals("en")) {
            cat_title.setText(categories.get(i).title);
        }else {
            cat_title.setText(categories.get(i).title_ar);

        }

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProductsListActivity.class);
                intent.putExtra("header","1");
                intent.putExtra("catid",categories.get(i).id);
                intent.putExtra("cat_title",categories.get(i).title);
                intent.putExtra("cat_title_ar",categories.get(i).title_ar);

                context.startActivity(intent);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProductsListActivity.class);
                intent.putExtra("header","1");
                intent.putExtra("catid",categories.get(i).id);
                intent.putExtra("cat_title",categories.get(i).title);
                context.startActivity(intent);
            }
        });
        return item_view;
    }
}
