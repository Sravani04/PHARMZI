//package com.example.yellowsoft.pharmzi;
//
//import android.content.Context;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;
//
///**
// * Created by yellowsoft on 5/1/18.
// */
//
//public class PagerSlidingTabAdapter  extends FragmentStatePagerAdapter {
//    private final String[] TITLES= {"Products","Location","Info"};
//    Context context;
//    ProductsFragment fragment;
//
//
//
//    public PagerSlidingTabAdapter(FragmentManager fm) {
//        super(fm);
//
//    }
//
//
//    @Override
//    public Fragment getItem(int position) {
//
//        return ProductsFragment.newInstance(position);
//
////        Log.e("pos", String.valueOf(position));
////
////        if (position == 0) {
////
////               fragment =  ServicesFragment.newInstance();
////                return fragment;
////
////        }else {
////            return DemoFragment.newInstance(position);
////        }
//
//
//
//
//    }
//
//    @Override
//    public int getCount() {
//        return TITLES.length;
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//
//        return TITLES[position];
//    }
//
////    @Override
////    public int getPageIconResId(int position) {
////       return categories.get(position).image;
////    }
//
//
//
////    @Override
////    public int getPageIconResId(int position) {
////        return Integer.parseInt(categories.get(position).image);
////    }
//}
//
