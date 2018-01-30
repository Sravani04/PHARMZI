package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 17/1/18.
 */

public class OrdersScreen extends Activity {
    ImageView back_btn;
    ListView listView;
    OrdersAdapter adapter;
    ArrayList<Orders> ordersfrom_api;
    LinearLayout no_orders_page;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_screen);
        Session.forceRTLIfSupported(this);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        listView = (ListView) findViewById(R.id.orders_list);
        no_orders_page = (LinearLayout) findViewById(R.id.no_orders);
        ordersfrom_api = new ArrayList<>();
        adapter = new OrdersAdapter(this,ordersfrom_api);
        listView.setAdapter(adapter);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrdersScreen.this.onBackPressed();
            }
        });

        get_orders();



    }

    public void get_orders(){
        final KProgressHUD hud = KProgressHUD.create(OrdersScreen.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(this)
                .load(Session.SERVERURL+"order-history.php")
                .setBodyParameter("member_id",Session.GetUserId(this))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try{
                            hud.dismiss();
                            Log.e("orders",result.toString());
                            for (int i=0;i<result.size();i++){
                                Orders orders = new Orders(result.get(i).getAsJsonObject(),OrdersScreen.this);
                                ordersfrom_api.add(orders);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }

                        if (ordersfrom_api.size() == 0)
                        {
                            no_orders_page.setVisibility(View.VISIBLE);
                        }else {
                            no_orders_page.setVisibility(View.GONE);
                        }
                    }
                });
    }
}

