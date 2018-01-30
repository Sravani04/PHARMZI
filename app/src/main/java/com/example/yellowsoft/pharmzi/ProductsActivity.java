package com.example.yellowsoft.pharmzi;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.squareup.picasso.Picasso;

/**
 * Created by yellowsoft on 5/1/18.
 */

public class ProductsActivity extends FragmentActivity {
    ImageView back_btn,my_profile,phar_image;
   // ViewPager viewPager;
   // PagerSlidingTabAdapter tabsAdapter;
   // PagerSlidingTabStrip tabs;
   // ViewPager.OnPageChangeListener mPageChangeListener;
    Pharmacies pharmcies;
    TextView title,payment_title,time,minimum,products_btn,location_btn,info_btn,phar_title;
    FrameLayout frame;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_list);
        Session.forceRTLIfSupported(this);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        phar_image = (ImageView) findViewById(R.id.phar_image);
        title = (TextView) findViewById(R.id.title);
        payment_title = (TextView) findViewById(R.id.payment_title);
        time = (TextView) findViewById(R.id.time);
        minimum = (TextView) findViewById(R.id.minimum);
        products_btn = (TextView) findViewById(R.id.products_btn);
        location_btn = (TextView) findViewById(R.id.location_btn);
        info_btn = (TextView) findViewById(R.id.info_btn);
        frame = (FrameLayout) findViewById(R.id.frame);
        phar_title = (TextView) findViewById(R.id.phar_title);

        if (getIntent()!=null && getIntent().hasExtra("pharmcies")){
            pharmcies = (Pharmacies) getIntent().getSerializableExtra("pharmcies");
        }
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductsActivity.this.onBackPressed();
            }
        });

        if (Session.GetLang(this).equals("en")) {
            title.setText(pharmcies.title);
            phar_title.setText(pharmcies.title);
        }else {
            title.setText(pharmcies.title_ar);
            phar_title.setText(pharmcies.title_ar);

        }

        Picasso.with(this).load(pharmcies.image).into(phar_image);

        payment_title.setText(pharmcies.payments.get(0).title);
        time.setText(pharmcies.time);
        minimum.setText(pharmcies.minimum);


        products_btn.setBackground(getResources().getDrawable(R.drawable.underline));
        products_btn.setTextColor(getResources().getColor(R.color.languagecolor));
        products_btn.setTypeface(Typeface.DEFAULT_BOLD);
        location_btn.setTextColor(getResources().getColor(R.color.text_color));
        location_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        info_btn.setTextColor(getResources().getColor(R.color.text_color));
        info_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        NewCategoryFragment newCategoryFragment = new NewCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",pharmcies.id);
        bundle.putString("dc",pharmcies.delivery_charges);
        Log.e("phar", pharmcies.delivery_charges);
        newCategoryFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,newCategoryFragment).commit();

        products_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                products_btn.setBackground(getResources().getDrawable(R.drawable.underline));
                products_btn.setTextColor(getResources().getColor(R.color.languagecolor));
                products_btn.setTypeface(Typeface.DEFAULT_BOLD);
                location_btn.setBackground(getResources().getDrawable(R.drawable.underlinenormal));
                info_btn.setBackground(getResources().getDrawable(R.drawable.underlinenormal));
                location_btn.setTextColor(getResources().getColor(R.color.text_color));
                location_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                info_btn.setTextColor(getResources().getColor(R.color.text_color));
                info_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                NewCategoryFragment newCategoryFragment = new NewCategoryFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id",pharmcies.id);
                bundle.putString("dc",pharmcies.delivery_charges);
                Log.e("phar", pharmcies.delivery_charges);
                newCategoryFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,newCategoryFragment).commit();
            }
        });

        location_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location_btn.setBackground(getResources().getDrawable(R.drawable.underline));
                location_btn.setTextColor(getResources().getColor(R.color.languagecolor));
                location_btn.setTypeface(Typeface.DEFAULT_BOLD);
                products_btn.setBackground(getResources().getDrawable(R.drawable.underlinenormal));
                products_btn.setTextColor(getResources().getColor(R.color.text_color));
                products_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                info_btn.setBackground(getResources().getDrawable(R.drawable.underlinenormal));
                info_btn.setTextColor(getResources().getColor(R.color.text_color));
                info_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                CityFragment cityFragment = new CityFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,cityFragment).commit();


            }
        });

        info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info_btn.setBackground(getResources().getDrawable(R.drawable.underline));
                info_btn.setTextColor(getResources().getColor(R.color.languagecolor));
                info_btn.setTypeface(Typeface.DEFAULT_BOLD);
                products_btn.setBackground(getResources().getDrawable(R.drawable.underlinenormal));
                products_btn.setTextColor(getResources().getColor(R.color.text_color));
                products_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                location_btn.setBackground(getResources().getDrawable(R.drawable.underlinenormal));
                location_btn.setTextColor(getResources().getColor(R.color.text_color));
                location_btn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                InfoFragment infoFragment = new InfoFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("pharmcies",pharmcies);
                infoFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,infoFragment).commit();

            }
        });

//        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
//        viewPager = (ViewPager) findViewById(R.id.view_pager);
//        tabsAdapter = new PagerSlidingTabAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(tabsAdapter);
//        tabsAdapter.notifyDataSetChanged();
//        tabs.setViewPager(viewPager);
//        tabs.setOnPageChangeListener(mPageChangeListener);




//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                try {
//
//                    if (position == 0)
//                       viewPager.setCurrentItem(position,true);
//
//
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//
//            }
//
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });












    }






}

