package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 22/1/18.
 */

public class MoreOrdersPage extends Activity {
    ImageView back_btn;
    MoreOrdersAdapter adapter;
    ArrayList<Orders> orders;
    RecyclerView recyclerView;
    ArrayList<Products> productsfrom_api;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_orders_screen);
        orders  = (ArrayList<Orders>) getIntent().getSerializableExtra("orders");
        productsfrom_api = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        adapter = new MoreOrdersAdapter(this, MoreOrdersPage.this,orders,productsfrom_api);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoreOrdersPage.this.onBackPressed();
            }
        });

    }

    public void get_products(String pro_id){
        final KProgressHUD hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(this)
                .load(Session.SERVERURL+"products.php")
                .setBodyParameter("product_id",pro_id)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            hud.dismiss();
                            Log.e("productsres",result.toString());

                            for (int i=0;i<result.size();i++) {
                                Products products = new Products(result.get(i).getAsJsonObject(),MoreOrdersPage.this);
                                productsfrom_api.add(products);
                                Session.SetCartProducts(MoreOrdersPage.this,productsfrom_api.get(i));
                            }

                            Toast.makeText(MoreOrdersPage.this, "Product is added to the Cart", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MoreOrdersPage.this, MainActivity.class);
                            intent.putExtra("act", "1");
                            intent.putExtra("id", Session.GetAreaId(MoreOrdersPage.this));
                            intent.putExtra("delivery", "0");
                            intent.putExtra("pickup", "1");
                            startActivity(intent);



                        }catch (Exception e1){
                            e1.printStackTrace();
                        }

                    }
                });
    }
}
