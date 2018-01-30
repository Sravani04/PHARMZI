package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 4/1/18.
 */

public class CityActivity extends FragmentActivity{
    TextView select_area,delivery_btn,pickup_btn,ordersmore_btn,offersmore_btn,area_textview;
    ArrayList<Areas> areasfrom_api;
    String area_id;
    RecyclerView recyclerView;
    MyOrdersAdapter adapter;
    ArrayList<Orders> ordersfrom_api;
    LinearLayout orders_layout,cart_btn;
    ImageView select;
    ArrayList<Offers> offersfrom_api;
    OffersAdapter offersAdapter;
    RecyclerView rv1;
    ArrayList<Products> productsfrom_api;
    String count;
    LinearLayout area_btn;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_screen);
        Session.forceRTLIfSupported(this);
        ordersfrom_api = new ArrayList<>();
        offersfrom_api = new ArrayList<>();
        productsfrom_api = new ArrayList<>();
        select_area = (TextView) findViewById(R.id.select_area);
        orders_layout = (LinearLayout) findViewById(R.id.orders_layout);
        delivery_btn = (TextView) findViewById(R.id.delivery_btn);
        pickup_btn = (TextView) findViewById(R.id.pickup_btn);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        rv1 = (RecyclerView) findViewById(R.id.rv1);
        select = (ImageView) findViewById(R.id.select);
        ordersmore_btn = (TextView) findViewById(R.id.ordersmore_btn);
        offersmore_btn = (TextView) findViewById(R.id.offersmore_btn);
        area_btn = (LinearLayout) findViewById(R.id.area_btn);


        adapter = new MyOrdersAdapter(this,this,ordersfrom_api,productsfrom_api);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        offersAdapter = new OffersAdapter(this,offersfrom_api);
        rv1.setAdapter(offersAdapter);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        select_area.setHint("Select Area");

        ordersmore_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CityActivity.this,MoreOrdersPage.class);
                intent.putExtra("orders",ordersfrom_api);
                intent.putExtra("products",productsfrom_api);
                startActivity(intent);
            }
        });

        offersmore_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CityActivity.this,OffersActivity.class);
                intent.putExtra("offers",offersfrom_api);
                startActivity(intent);
            }
        });




        areasfrom_api = new ArrayList<>();
        select_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = onAreaChoice();
                dialog.show();
            }
        });

        area_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = onAreaChoice();
                dialog.show();
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = onAreaChoice();
                dialog.show();
            }
        });

        if (Session.GetUserId(this).equals("-1")){
            orders_layout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        }else {
            orders_layout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }


            delivery_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (select_area.getText().toString().equals("")){
                        Log.e("coming","inside");
                        Toast.makeText(CityActivity.this,"Please Enter Area",Toast.LENGTH_SHORT).show();
                        select_area.requestFocus();
                    }else{
                        Intent intent = new Intent(CityActivity.this, MainActivity.class);
                        intent.putExtra("id", area_id);
                        intent.putExtra("act", "0");
                        intent.putExtra("delivery", "0");
                        startActivity(intent);
                    }
//                Intent intent = new Intent();
//                intent.putExtra("id",area_id);ci
//                setResult(RESULT_OK,intent);
//                finish();
                }
            });



            pickup_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (select_area.getText().toString().equals("")){
                        Log.e("coming","inside");
                        Toast.makeText(CityActivity.this,"Please Enter Area",Toast.LENGTH_SHORT).show();
                        select_area.requestFocus();
                    }else {
                        Intent intent = new Intent(CityActivity.this, MainActivity.class);
                        intent.putExtra("id", area_id);
                        intent.putExtra("act", "0");
                        intent.putExtra("delivery", "1");
                        startActivity(intent);
                    }
//                Intent intent = new Intent();
//                intent.putExtra("id",area_id);
//                setResult(RESULT_OK,intent);
//                finish();
                }
            });

        get_areas();
        get_orders();
        get_offers();
    }


    public void get_areas(){
        final KProgressHUD hud = KProgressHUD.create(CityActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(this)
                .load(Session.SERVERURL+"areas.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            hud.dismiss();
                            Log.e("country",result.toString());

                            for (int i = 0; i < result.size(); i++) {
                                Areas area = new Areas(result.get(i).getAsJsonObject(),CityActivity.this,"0");
                                areasfrom_api.add(area);

                                for (int j = 0; j < result.get(i).getAsJsonObject().get("areas").getAsJsonArray().size(); j++) {

                                    Areas sub_category = new Areas(result.get(i).getAsJsonObject().get("areas").getAsJsonArray().get(j).getAsJsonObject(), CityActivity.this, "1");
                                    areasfrom_api.add(sub_category);

                                }
                            }

//                            JsonObject jsonObject = result.get(0).getAsJsonObject();
//                            JsonArray jsonArray = jsonObject.get("areas").getAsJsonArray();
//                            Log.e("jsonarray",jsonArray.toString());
//                            for (int i=0;i<jsonArray.size();i++){
//                                Areas area = new Areas(jsonArray.get(i).getAsJsonObject(),CityActivity.this);
//                                areasfrom_api.add(area);
//                            }


                        }catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                });

    }

    public Dialog onAreaChoice() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final CharSequence[] array = new CharSequence[areasfrom_api.size()];
        for(int i=0;i<areasfrom_api.size();i++){

            array[i] = areasfrom_api.get(i).title;
        }

        builder.setTitle("Select Area")
                .setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String selectedItem = array[i].toString();
                        Log.e("select",selectedItem);
                        select_area.setText(selectedItem);
                        area_id = areasfrom_api.get(i).id;
                        Log.e("area_id",area_id);
                        Session.SerAreaId(CityActivity.this,area_id);



                    }
                })

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }



    public void get_orders(){
        final KProgressHUD hud = KProgressHUD.create(CityActivity.this)
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
                                Orders orders = new Orders(result.get(i).getAsJsonObject(),CityActivity.this);
                                ordersfrom_api.add(orders);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }

                    }
                });
    }


    public void get_offers(){
        final KProgressHUD hud = KProgressHUD.create(CityActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(this)
                .load(Session.SERVERURL+"offers.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try{
                            hud.dismiss();
                            Log.e("offers",result.toString());
                            for (int i=0;i<result.size();i++){
                                Offers offers = new Offers(result.get(i).getAsJsonObject(),CityActivity.this);
                                offersfrom_api.add(offers);
                            }
                            offersAdapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }

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
                                Products products = new Products(result.get(i).getAsJsonObject(),CityActivity.this);
                                productsfrom_api.add(products);
                                Session.SetCartProducts(CityActivity.this,productsfrom_api.get(i));
                            }

                            Toast.makeText(CityActivity.this, "Product is added to the Cart", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CityActivity.this, MainActivity.class);
                            intent.putExtra("act", "1");
                            intent.putExtra("id", Session.GetAreaId(CityActivity.this));
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
