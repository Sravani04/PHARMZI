package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 6/1/18.
 */

public class ProductDetail extends Activity {
    ImageView back_btn,share_btn,cart_btn;
    ViewPager viewPager;
    ProductImageSlider productImageSlider;
    Products products;
    Runnable Update;
    TextView product_name,product_price,title,product_about,product_description,product_instructions,minus,add,quantity,cart_items;
    LinearLayout share_pop_ll,buy_now_ll;
    MainActivity mainActivity;
    ArrayList<Products> pro;
    String area_id;
    float total_cart_price = 0.0f;
    ImageView close_pop_share;
    String pharid;
    ImageView fb_btn,twitter_btn,insta_btn;
    long back_pressed;
    RelativeLayout cart_ll;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_screen);
        Session.forceRTLIfSupported(this);
        pro=new ArrayList<>();
        back_btn = (ImageView) findViewById(R.id.back_img_pd);
        product_name = (TextView) findViewById(R.id.product_name_pd_tv);
        product_price = (TextView) findViewById(R.id.product_price_pd_tv);
        title = (TextView) findViewById(R.id.title);
        product_about = (TextView) findViewById(R.id.product_about);
        product_description = (TextView) findViewById(R.id.product_description);
        product_instructions = (TextView) findViewById(R.id.product_instructions);
        share_btn = (ImageView) findViewById(R.id.share_btn);
        share_pop_ll = (LinearLayout) findViewById(R.id.share_pop_ll);
        buy_now_ll = (LinearLayout) findViewById(R.id.buy_now_ll);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        minus = (TextView) findViewById(R.id.minus);
        add = (TextView) findViewById(R.id.add);
        quantity = (TextView) findViewById(R.id.quantity);
        cart_btn = (ImageView) findViewById(R.id.cart_btn);
        close_pop_share = (ImageView) findViewById(R.id.close_pop_share);
        cart_items = (TextView) findViewById(R.id.cart_items);
        cart_ll = (RelativeLayout) findViewById(R.id.cart_ll);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDetail.this.onBackPressed();
            }
        });

        if (getIntent()!=null && getIntent().hasExtra("products")){
            products = (Products) getIntent().getSerializableExtra("products");
        }

        cart_items.setText(String.valueOf(Session.GetCartProducts(this).size()));
        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetail.this, MainActivity.class);
                intent.putExtra("act", "1");
                intent.putExtra("id", Session.GetAreaId(ProductDetail.this));
                intent.putExtra("delivery", "0");
                intent.putExtra("pickup", "1");
                startActivity(intent);
            }
        });

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_pop_ll.setVisibility(View.VISIBLE);
            }
        });

        close_pop_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_pop_ll.setVisibility(View.GONE);
            }
        });


        if (Session.GetLang(this).equals("en")) {
            product_name.setText(products.title);
            title.setText(products.title);
            product_about.setText(Html.fromHtml(products.about));
            product_description.setText(Html.fromHtml(products.description));
            product_instructions.setText(Html.fromHtml(products.instructions));
        }else {
            product_name.setText(products.title_ar);
            title.setText(products.title_ar);
            product_about.setText(Html.fromHtml(products.about_ar));
            product_description.setText(Html.fromHtml(products.description_ar));
            product_instructions.setText(Html.fromHtml(products.instructions_ar));

        }


        product_price.setText(products.price + " KD ");
        productImageSlider = new ProductImageSlider(this,products);
        viewPager.setAdapter(productImageSlider);

        final Handler handler  = new Handler();
        Update = new Runnable(){
            public void run(){
                if (viewPager.getCurrentItem()<products.images.size()-1)
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                else
                    viewPager.setCurrentItem(0);

                handler.postDelayed(Update,2000);
            }
        };
        Update.run();

        for (int i =0;i<products.pharmacies.size();i++){
             pharid = products.pharmacies.get(i).id;
        }

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_pop_ll.setVisibility(View.VISIBLE);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (products.cart_quantity < 10){
                    products.cart_quantity ++;
                    quantity.setText(String.valueOf(products.cart_quantity));
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (products.cart_quantity > 1){
                    products.cart_quantity--;
                    quantity.setText(String.valueOf(products.cart_quantity));
                }
            }
        });

            buy_now_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!Session.GetPharmciId(ProductDetail.this).equals(products.pharmacies.get(0).id) && !Session.GetPharmciId(ProductDetail.this).equals("-1")) {
                        Log.e("check1", String.valueOf(Session.GetPharmciId(ProductDetail.this)+products.pharmacies.get(0).id));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!isFinishing()){
                                    new AlertDialog.Builder(ProductDetail.this)
                                            .setTitle("Alert")
                                            .setMessage("You are in Different Pharmacy, Selected cart products will be removed")
                                            .setCancelable(false)
                                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                       Session.deleteCart(ProductDetail.this);
                                                    Session.SetCartProducts(ProductDetail.this, products);
                                                    Intent intent = new Intent(ProductDetail.this, MainActivity.class);
                                                    intent.putExtra("act", "1");
                                                    intent.putExtra("id", Session.GetAreaId(ProductDetail.this));
                                                    intent.putExtra("delivery", "0");
                                                    intent.putExtra("pickup", "1");
                                                    startActivity(intent);
                                                }
                                            }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    }).show();
                                }
                            }
                        });

                    }else {
                        Log.e("check", String.valueOf(Session.GetPharmciId(ProductDetail.this).equals(products.pharmacies.get(0).id)));
                        Session.SetCartProducts(ProductDetail.this, products);
                        Log.e("cart_products_size", String.valueOf(Session.GetCartProducts(ProductDetail.this).size()));
                        Toast.makeText(ProductDetail.this, "Product is added to the Cart", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProductDetail.this, MainActivity.class);
                        intent.putExtra("act", "1");
                        intent.putExtra("id", Session.GetAreaId(ProductDetail.this));
                        intent.putExtra("delivery", "0");
                        intent.putExtra("pickup", "1");
                        startActivity(intent);
                    }
//                if(Session.GetUserId(ProductDetail.this).equals("-1")){
//                    Intent intent = new Intent(ProductDetail.this, LoginActivity.class);
//                    startActivity(intent);
//                }else {
//                    if (Session.GetCartProducts(ProductDetail.this).equals("-1") || Session.GetCartProducts(ProductDetail.this).equals("[]")) {
//                        Session.SetCartProducts(ProductDetail.this, products);
//                        Intent intent = new Intent(ProductDetail.this, MainActivity.class);
//                        intent.putExtra("act", "1");
//                        intent.putExtra("id","5");
//                        startActivity(intent);
//                    } else {
//                        ArrayList<String> trmp = new ArrayList<>();
//                        pro.clear();
//                        JsonArray jsonArray =Session.GetCartProducts(ProductDetail.this);
//                        for (int i = 0; i < jsonArray.size(); i++) {
//
//                            pro.add(new Products(jsonArray.get(i).getAsJsonObject(),ProductDetail.this));
//                            trmp.add(pro.get(i).id);
//                            Log.e("cartdata1", jsonArray.toString());
//
//                            if (products.id.equals(pro.get(i).id)) {
//                                pro.get(i).quantity = String.valueOf(Integer.parseInt(pro.get(i).quantity) + 1);
//                                Log.e("cartdata1  " + products.id, pro.get(i).quantity);
//                            }
////                            pro.get(i).start_date=date;
////                            pro.get(i).end_date=date1;
//                            Log.e("cartdata1_size1", String.valueOf(pro.size()));
//                        }
//                        if (!trmp.contains(products.id)) {
//                            pro.add(products);
//                        }
//
//                        Log.e("cartdata1_size2", String.valueOf(pro.size()));
//                        Session.deleteCart(ProductDetail.this);
//                        for (int i = 0; i < pro.size(); i++) {
//                            Log.e("cartdata1_size3", String.valueOf(pro.size()));
//                            Session.SetCartProducts(ProductDetail.this, pro.get(i));
//
//
                }
            });


    }





}
