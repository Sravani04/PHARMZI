//package com.example.yellowsoft.pharmzi;
//
//import android.graphics.Typeface;
//import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.squareup.picasso.Picasso;
//
///**
// * Created by yellowsoft on 12/1/18.
// */
//
//public class CategoryProductsActivity extends FragmentActivity {
//    ImageView back_btn,my_profile,phar_image;
//    Categories categories;
//    TextView title,payment_title,time,minimum,products_btn,location_btn,info_btn,phar_title;
//    FrameLayout frame;
//    @Override
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.products_list);
//        back_btn = (ImageView) findViewById(R.id.back_btn);
//        phar_image = (ImageView) findViewById(R.id.phar_image);
//        title = (TextView) findViewById(R.id.title);
//        payment_title = (TextView) findViewById(R.id.payment_title);
//        time = (TextView) findViewById(R.id.time);
//        minimum = (TextView) findViewById(R.id.minimum);
//        products_btn = (TextView) findViewById(R.id.products_btn);
//        location_btn = (TextView) findViewById(R.id.location_btn);
//        info_btn = (TextView) findViewById(R.id.info_btn);
//        frame = (FrameLayout) findViewById(R.id.frame);
//        phar_title = (TextView) findViewById(R.id.phar_title);
//
//        if (getIntent()!=null && getIntent().hasExtra("category")){
//            categories = (Categories) getIntent().getSerializableExtra("category");
//        }
//        back_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CategoryProductsActivity.this.onBackPressed();
//            }
//        });
//
//        Picasso.with(this).load(categories.image).into(phar_image);
//        title.setText(categories.title);
//
//        products_btn.setTextColor(getResources().getColor(R.color.languagecolor));
//        products_btn.setTypeface(Typeface.DEFAULT_BOLD);
//        location_btn.setTextColor(getResources().getColor(R.color.text_color));
//        location_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
//        info_btn.setTextColor(getResources().getColor(R.color.text_color));
//        info_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
//        ProductsFragment productsFragment = new ProductsFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("categories",categories.id);
//        Log.e("phar", String.valueOf(categories));
//        productsFragment.setArguments(bundle);
//        getSupportFragmentManager().beginTransaction().replace(R.id.frame,productsFragment).commit();
//
//        products_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                products_btn.setTextColor(getResources().getColor(R.color.languagecolor));
//                products_btn.setTypeface(Typeface.DEFAULT_BOLD);
//                location_btn.setTextColor(getResources().getColor(R.color.text_color));
//                location_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
//                info_btn.setTextColor(getResources().getColor(R.color.text_color));
//                info_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
//                ProductsFragment productsFragment = new ProductsFragment();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("categories",categories.id);
//                Log.e("phar", String.valueOf(categories));
//                productsFragment.setArguments(bundle);
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame,productsFragment).commit();
//            }
//        });
//
//        location_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                location_btn.setTextColor(getResources().getColor(R.color.languagecolor));
//                location_btn.setTypeface(Typeface.DEFAULT_BOLD);
//                products_btn.setTextColor(getResources().getColor(R.color.text_color));
//                products_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
//                info_btn.setTextColor(getResources().getColor(R.color.text_color));
//                info_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
//                CityFragment cityFragment = new CityFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame,cityFragment).commit();
//
//
//            }
//        });
//
//        info_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                info_btn.setTextColor(getResources().getColor(R.color.languagecolor));
////                info_btn.setTypeface(Typeface.DEFAULT_BOLD);
////                products_btn.setTextColor(getResources().getColor(R.color.text_color));
////                products_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
////                location_btn.setTextColor(getResources().getColor(R.color.text_color));
////                location_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
////                InfoFragment infoFragment = new InfoFragment();
////                Bundle bundle = new Bundle();
////                bundle.putSerializable("pharmcies",pharmcies);
////                infoFragment.setArguments(bundle);
////                getSupportFragmentManager().beginTransaction().replace(R.id.frame,infoFragment).commit();
//
//            }
//        });
//
//
//    }
//
//}
//
