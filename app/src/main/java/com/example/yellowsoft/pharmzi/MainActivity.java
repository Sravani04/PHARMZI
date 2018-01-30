package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

public class MainActivity extends AppCompatActivity  implements BottomNavigation.OnMenuItemSelectionListener{

    TextView page_title;
    ImageView back_btn,search,change_city,lan,cart;
    private ActionBarDrawerToggle mDrawerToggle;
    private AHBottomNavigation mBottomNavigation;
    public ViewPager mViewPager;
    private TabsAdapter tabsAdapter;
    private DrawerLayout mDrawerLayout;
    int previous_postion=0;
    boolean previous_page;
    String pre="0";
    String area_id;
    String act,cart_;
    String delivery;
    private static long back_pressed;

    private void setupActionBar() {
//set action bar
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));


        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);

        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.action_bar, null);

        page_title=(TextView)v.findViewById(R.id.page_title);
        back_btn=(ImageView)v.findViewById(R.id.page_back);
        search=(ImageView)v.findViewById(R.id.search);
        lan=(ImageView)v.findViewById(R.id.lan);
        change_city = (ImageView) v.findViewById(R.id.change_city);
        cart = (ImageView) v.findViewById(R.id.cart);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);
            }
        });
        lan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LanguageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        change_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CityActivity.class);
                startActivityForResult(intent,1);
                finish();
            }
        });

        getSupportActionBar().setCustomView(v, layoutParams);
        Toolbar parent = (Toolbar) v.getParent();
        parent.setContentInsetsAbsolute(0, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mBottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        if (getIntent()!=null && getIntent().hasExtra("id")){
            area_id = getIntent().getStringExtra("id");

        }

        act=getIntent().getStringExtra("act");
        cart_ = getIntent().getStringExtra("cart");
        delivery = getIntent().getStringExtra("delivery");


        mBottomNavigation.addItem(new AHBottomNavigationItem("Home", R.drawable.home));
        mBottomNavigation.addItem(new AHBottomNavigationItem("Pharmacies", R.drawable.shop_gray));
        mBottomNavigation.addItem(new AHBottomNavigationItem("Categories", R.drawable.category_gray));
        mBottomNavigation.addItem(new AHBottomNavigationItem("Account", R.drawable.user_gray));
        mBottomNavigation.addItem(new AHBottomNavigationItem("Cart", R.drawable.cart_gray));
        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        mBottomNavigation.setDefaultBackgroundColor(Color.parseColor("#f6f5f0"));
        mBottomNavigation.setAccentColor(Color.parseColor("#00bbb2"));
        mBottomNavigation.setInactiveColor(Color.parseColor("#616161"));
        mBottomNavigation.setForceTint(true);
        mBottomNavigation.setCurrentItem(0);
        mBottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
        mBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...
//                if(position==3||position==4) {
//                    if (Session.GetUserId(MainActivity.this).equals("-1")) {
//                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                        startActivity(intent);
//
//                        pre = "1";
//                        finish();
//                    } else {
//                        pre = "0";
//                        if (act.equals("1")) {
//                            mViewPager.setCurrentItem(position);
//                        }else if (act.equals("0")){
//                            mViewPager.setCurrentItem(position);
//                        }
//                    }
//                }
//                }else if (position==1){
//                    if (Session.GetAreaId(MainActivity.this).equals("-1")){
//                        Intent intent = new Intent(MainActivity.this,CityActivity.class);
//                        startActivityForResult(intent,1);
//                        pre = "1";
//                    }else {
//                        pre = "0";
//                        mViewPager.setCurrentItem(position);
//                    }
//                }
//                else{
                    mViewPager.setCurrentItem(position);
//                }
                //mViewPager.setCurrentItem(position);
                return true;
            }
        });
        mBottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override public void onPositionChange(int y) {
                // Manage the new y position
            }
        });
       // mBottomNavigation.setOnMenuItemClickListener(this);
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(tabsAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {
                try {

                    if (position == 0)
                        mBottomNavigation.setCurrentItem(position, false);
                    else if (position == 1)
                        mBottomNavigation.setCurrentItem(position, false);
                    else if (position == 2)
                        mBottomNavigation.setCurrentItem(position, false);
                    else if (position == 3)
                        mBottomNavigation.setCurrentItem(position, false);
                    else if (position == 4)
                        mBottomNavigation.setCurrentItem(position, false);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                setHeader(position);
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        setupActionBar();
        setHeader(0);


            if(act.equals("1")){
                mViewPager.setCurrentItem(4);
            }else if (act.equals("0")){
                mViewPager.setCurrentItem(0);
            }




        RelativeLayout mainView = (RelativeLayout) findViewById(R.id.mainView);


    }


//    public void onActivityResult(int requestCode,int resultCode, Intent imageReturnedIntent){
//        super.onActivityResult(requestCode,resultCode,imageReturnedIntent);
//        if (requestCode == 1){
//            if (resultCode == Activity.RESULT_OK) {
//
//                // do something with the result
//                if (imageReturnedIntent!=null && imageReturnedIntent.hasExtra("id")){
//                    area_id = imageReturnedIntent.getExtras().getString("id");
//                    Log.e("areaid",area_id);
//                    tabsAdapter.pharmaciesFragment.getData(area_id);
//
//                }
//
//            } else if (resultCode == Activity.RESULT_CANCELED) {
//                // some stuff that will happen if there's no result
//            }
//        }
//    }


        @Override
        public void onBackPressed() {
            Log.e("pp_pp_back",String.valueOf(previous_postion));
            if(mViewPager.getCurrentItem()!=0){
                if(mViewPager.getCurrentItem()==1) {
                    mViewPager.setCurrentItem(previous_postion, false);
                }else if(mViewPager.getCurrentItem()==2) {
                    mViewPager.setCurrentItem(previous_postion, false);
                }else if(mViewPager.getCurrentItem()==3) {
                    mViewPager.setCurrentItem(previous_postion, false);
                }else if(mViewPager.getCurrentItem()==4) {
                    mViewPager.setCurrentItem(previous_postion, true);
                }else
                    mViewPager.setCurrentItem(0, false);
            }

            else
                finish();

        }


    @Override
    public void onMenuItemSelect(@IdRes int i, int i1, boolean b) {

//        Log.e("i", String.valueOf(i1));
//        // previous_page = mViewPager.getCurrentItem();
//        if (i1 == 4)
//            mViewPager.setCurrentItem(5);
//        else
//            mViewPager.setCurrentItem(i1);


    }

    @Override
    public void onMenuItemReselect(@IdRes int i, int i1, boolean b) {
        //   Log.e("previous",String.valueOf(previous_page));
        if (i1 == 2 && !previous_page && mViewPager.getCurrentItem() != 3) {

            try {
                //tabsAdapter.newsFragment.get_news("0");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        previous_page = false;


    }


        private void setHeader(int pos) {
            page_title.setVisibility(View.GONE);

            switch (pos) {

                case 0:
                    back_btn.setVisibility(View.GONE);
                    search.setVisibility(View.GONE);
                    cart.setVisibility(View.GONE);
                    lan.setVisibility(View.VISIBLE);
                    change_city.setVisibility(View.VISIBLE);
                    page_title.setVisibility(View.VISIBLE);
                    page_title.setText("Salwa");

                    break;

                case 1:
                    lan.setVisibility(View.GONE);
                    change_city.setVisibility(View.GONE);
                    search.setVisibility(View.VISIBLE);
                    back_btn.setVisibility(View.VISIBLE);
                    page_title.setVisibility(View.VISIBLE);
                    page_title.setText("Pharmacies");


                    break;


                case 2:
                    lan.setVisibility(View.GONE);
                    change_city.setVisibility(View.GONE);
                    cart.setVisibility(View.GONE);
                    search.setVisibility(View.GONE);
                    back_btn.setVisibility(View.GONE);
                    page_title.setVisibility(View.VISIBLE);
                    page_title.setText("Categories");

                    break;


                case 3:

                    lan.setVisibility(View.GONE);
                    change_city.setVisibility(View.GONE);
                    cart.setVisibility(View.GONE);
                    search.setVisibility(View.GONE);
                    back_btn.setVisibility(View.GONE);
                    page_title.setVisibility(View.VISIBLE);
                    page_title.setText("Account");

                    break;
                case 4:
                    lan.setVisibility(View.GONE);
                    change_city.setVisibility(View.GONE);
                    cart.setVisibility(View.GONE);
                    search.setVisibility(View.GONE);
                    back_btn.setVisibility(View.GONE);
                    page_title.setVisibility(View.VISIBLE);
                    page_title.setText("Cart");
                    break;
                default:

                    break;
            }
        }


        @Override
        public void onResume() {
            super.onResume();
            try {
                if(pre.equals("1")){
                    mBottomNavigation.setCurrentItem(0);
                }
//            tabsAdapter.profileFragment.get_members();
            }catch (Exception e){
                e.printStackTrace();
            }


        }




}

