package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 23/1/18.
 */

public class OffersActivity extends Activity{
    RecyclerView recyclerView;
    ImageView back_btn;
    OffersAdapter adapter;
    ArrayList<Offers> offers;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offers_list);
        offers  = (ArrayList<Offers>) getIntent().getSerializableExtra("offers");
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        adapter = new OffersAdapter(this,offers);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
