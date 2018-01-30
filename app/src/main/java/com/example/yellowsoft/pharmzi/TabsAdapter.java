package com.example.yellowsoft.pharmzi;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by yellowsoft on 4/1/18.
 */

public class TabsAdapter  extends FragmentStatePagerAdapter {
    HomeFragment homeFragment;
    PharmaciesFragment pharmaciesFragment;
    CategoryFragment categoryFragment;
    AccountFragment accountFragment;
    CartFragment cartFragment;
    Context context;

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Log.e("pos", String.valueOf(position));

        if (position == 0) {
            return HomeFragment.newInstance(position);

        } else if (position == 1) {
            pharmaciesFragment = PharmaciesFragment.newInstance(position);
            return pharmaciesFragment;


        }else if(position == 2){
            categoryFragment = CategoryFragment.newInstance(position);
            return categoryFragment;

        }else if(position == 3) {
            accountFragment = AccountFragment.newInstance(position);
            return accountFragment;
        }
        else if(position == 4) {
            cartFragment = CartFragment.newInstance("Cart");
            return cartFragment;
        } else
            return DemoFragment.newInstance(position);

    }

    @Override
    public int getCount() {
        return 6;
    }


}

