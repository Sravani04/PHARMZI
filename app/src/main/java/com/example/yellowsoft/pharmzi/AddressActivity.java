package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.*;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yellowsoft on 11/1/18.
 */

public class AddressActivity extends Activity {
    ImageView back_btn;
    RecyclerView recyclerView;
    LinearLayout add_new_address_btn;
    AddressListAdapter adapter;
    ArrayList<Address> addressfrom_api;
    LinearLayout no_address_page,main_page,add_address_btn;
    String add_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addresses_screen);
        addressfrom_api = new ArrayList<>();
        back_btn = (ImageView) findViewById(R.id.back_btn);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        add_new_address_btn = (LinearLayout) findViewById(R.id.add_new_address_btn);
        no_address_page = (LinearLayout) findViewById(R.id.no_address_page);
        main_page = (LinearLayout) findViewById(R.id.main_page);
        add_address_btn = (LinearLayout) findViewById(R.id.add_address_btn);
        adapter = new AddressListAdapter(this,addressfrom_api,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        add_new_address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddressActivity.this, AddNewAddress.class);
                startActivity(intent);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressActivity.this.onBackPressed();
                finish();
            }
        });

        get_address();

    }

    public void get_address(){
        final KProgressHUD hud = KProgressHUD.create(AddressActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(this)
                .load(Session.SERVERURL+"address-list.php")
                .setBodyParameter("member_id",Session.GetUserId(this))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            Log.e("addr",result.toString());
                            hud.dismiss();
                            for (int i=0;i<result.size();i++){
                                Address address = new Address(result.get(i).getAsJsonObject(),null);
                                addressfrom_api.add(address);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }

                        if (addressfrom_api.size() == 0){
                            no_address_page.setVisibility(View.VISIBLE);
                            add_address_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(AddressActivity.this, AddNewAddress.class);
                                    startActivity(intent);
                                }
                            });
                        }else {
                            no_address_page.setVisibility(View.GONE);
                        }

                    }
                });
    }

    public void delete_address(){
        final KProgressHUD hud = KProgressHUD.create(AddressActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(this)
                .load(Session.SERVERURL+"del-address.php")
                .setBodyParameter("member_id",Session.GetUserId(this))
                .setBodyParameter("address_id",add_id)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        try {
                            hud.dismiss();
                            if (result.get("status").getAsString().equals("Success")){
                                Toast.makeText(AddressActivity.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                                get_address();
                                if (addressfrom_api.size() == 0){
                                    no_address_page.setVisibility(View.VISIBLE);
                                    add_address_btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(AddressActivity.this, AddNewAddress.class);
                                            startActivity(intent);
                                        }
                                    });
                                }else {
                                    no_address_page.setVisibility(View.GONE);
                                }
                            }else {
                                Toast.makeText(AddressActivity.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }


                    }
                });
    }
}

