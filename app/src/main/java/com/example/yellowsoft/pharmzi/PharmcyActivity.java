package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 23/1/18.
 */

public class PharmcyActivity extends Activity {
    LinearLayout header;
    ListView listView;
    PharmcyAdapter adapter;
    ArrayList<Pharmacies> pharmaciesfrom_api;
    Categories categories;
    ImageView back_btn;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacies_fragment);
        Session.forceRTLIfSupported(this);
        header = (LinearLayout) findViewById(R.id.header);
        header.setVisibility(View.VISIBLE);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        listView = (ListView) findViewById(R.id.pharmacies);
        pharmaciesfrom_api = new ArrayList<>();
        categories = (Categories) getIntent().getSerializableExtra("cat");
        adapter = new PharmcyAdapter(PharmcyActivity.this,pharmaciesfrom_api);
        listView.setAdapter(adapter);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PharmcyActivity.this.onBackPressed();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent  intent = new Intent(PharmcyActivity.this,ProductsListActivity.class);
                intent.putExtra("header","0");
                intent.putExtra("catid",categories.id);
                intent.putExtra("cat_title",categories.title);
                intent.putExtra("cat_title_ar",categories.title_ar);
                startActivity(intent);
            }
        });
        get_pharmacies();

    }





    public void get_pharmacies(){
        final KProgressHUD hud = KProgressHUD.create(PharmcyActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(PharmcyActivity.this)
                .load(Session.SERVERURL+"pharmacies.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            Log.e("pickuppharm",result.toString());
                            hud.dismiss();
                            for (int i=0;i<result.size();i++) {
                                Pharmacies pharmacies = new Pharmacies(result.get(i).getAsJsonObject(),PharmcyActivity.this);
                                pharmaciesfrom_api.add(pharmacies);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }
}
