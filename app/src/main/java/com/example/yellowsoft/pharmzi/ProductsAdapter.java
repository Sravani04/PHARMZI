package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yellowsoft on 5/1/18.
 */

public class ProductsAdapter extends BaseExpandableListAdapter {

    private Context context;
    LayoutInflater inflater;
    private List<String> expandableListTitle;
    private HashMap<Products, List<Products>> expandableListDetail;
    private HashMap<String, List<String>> expandableListDetail1;
    private List<String> expandableListDescription;
    ArrayList<Products> products;


//    public ProductsAdapter(Context context, List<String> expandableListTitle,
//                                 HashMap<String, List<String>> expandableListDetail,List<String> expandableListDescription,HashMap<String, List<String>> expandableListDetail1) {
//        this.context = context;
//        this.expandableListTitle = expandableListTitle;
//        this.expandableListDetail = expandableListDetail;
//        this.expandableListDescription = expandableListDescription;
//        this.expandableListDetail1 = expandableListDetail1;
//    }

    public ProductsAdapter(Context context,ArrayList<Products> products, HashMap<Products, List<Products>> expandableListDetail ) {
        this.context = context;
        this.products = products;
        this.expandableListDetail = expandableListDetail;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.products.get(listPosition))
                .get(expandedListPosition);

    }


    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(final int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
//        final Products expandedListText = (Products) getChild(listPosition, expandedListPosition);
        //final String expandedListDescription = (String) getChild1(listPosition,expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.products_fragment_items, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        ImageView product_image = (ImageView) convertView.findViewById(R.id.product_image);
        ImageView addto_cart = (ImageView) convertView.findViewById(R.id.addto_cart);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        description.setText(Html.fromHtml(products.get(listPosition).about));
        expandedListTextView.setText(products.get(listPosition).title);
        price.setText(products.get(listPosition).price);
        Picasso.with(context).load(products.get(listPosition).images.get(0).image).into(product_image);
        Log.e("title",products.get(listPosition).title);
        LinearLayout product = (LinearLayout) convertView.findViewById(R.id.product);
        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProductDetail.class);
                intent.putExtra("products",products.get(listPosition));
                context.startActivity(intent);
            }
        });

        addto_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Session.GetPharmciId(context).equals(products.get(listPosition).pharmacies.get(0).id)) {
                    Log.e("check", String.valueOf(Session.GetPharmciId(context).equals(products.get(listPosition).pharmacies.get(0).id)));
                    Session.SetCartProducts(context, products.get(listPosition));
                    Log.e("cart_products_size", String.valueOf(Session.GetCartProducts(context).size()));
                    Toast.makeText(context, "Product is added to the Cart", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("act", "1");
                    intent.putExtra("id", Session.GetAreaId(context));
                    intent.putExtra("delivery", "0");
                    intent.putExtra("pickup", "1");
                    context.startActivity(intent);
                }else {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if (!isFinishing()){
                                new AlertDialog.Builder(context)
                                        .setTitle("Your Alert")
                                        .setMessage("You are in Different Pharmacy, Selected cart products will be removed")
                                        .setCancelable(false)
                                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Session.deleteCart(context);
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
                            return true;
                        }
                    });
                }
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
//       return this.expandableListDetail.get(products.get(listPosition)).size();
        return 1;
     //  return products.size();
    }


    @Override
    public Object getGroup(int listPosition) {
        return products.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return products.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
//        String listTitle = (String) getGroup(listPosition);
        Products listTitle = (Products) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(products.get(listPosition).title);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

}

