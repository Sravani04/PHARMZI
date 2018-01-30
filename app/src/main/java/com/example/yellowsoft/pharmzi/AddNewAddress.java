package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
 * Created by yellowsoft on 11/1/18.
 */

public class AddNewAddress extends Activity {
    ImageView back_btn;
    EditText fname,lname,phone,block,street,house,flat,juddah;
    TextView area_option,type_option;
    ImageView area_dropdown,type_dropdown;
    LinearLayout select_area,select_type;
    LinearLayout save_btn;
    ArrayList<Areas> areasfrom_api;
    String area_id;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_address_screen);
        Session.forceRTLIfSupported(this);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        areasfrom_api = new ArrayList<>();
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        phone = (EditText) findViewById(R.id.phone);
        block = (EditText) findViewById(R.id.block);
        street = (EditText) findViewById(R.id.street);
        house = (EditText) findViewById(R.id.street);
        flat = (EditText) findViewById(R.id.flat);
        juddah = (EditText) findViewById(R.id.juddah);
        area_option = (TextView) findViewById(R.id.area_option);
        area_dropdown = (ImageView) findViewById(R.id.area_dropdown);
        type_dropdown = (ImageView) findViewById(R.id.type_dropdown);
        type_option = (TextView) findViewById(R.id.type_option);
        select_area = (LinearLayout) findViewById(R.id.select_area);
        select_type = (LinearLayout) findViewById(R.id.select_type);
        save_btn = (LinearLayout) findViewById(R.id.save_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewAddress.this.onBackPressed();
            }
        });


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               add_address();
            }
        });

        select_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = onAreaChoice();
                dialog.show();
            }
        });

        area_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = onAreaChoice();
                dialog.show();
            }
        });

        area_dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = onAreaChoice();
                dialog.show();
            }
        });

        select_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = onTypeChoice();
                dialog.show();
            }
        });

        type_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = onTypeChoice();
                dialog.show();
            }
        });

        type_dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = onTypeChoice();
                dialog.show();
            }
        });

        get_areas();
        get_members();

    }

    public void get_members(){
        final KProgressHUD hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();

        Ion.with(this)
                .load(Session.SERVERURL+"members.php")
                .setBodyParameter("member_id",Session.GetUserId(this))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            hud.dismiss();
                            JsonObject jsonObject = result.get(0).getAsJsonObject();
                            fname.setText(jsonObject.get("fname").getAsString());
                            lname.setText(jsonObject.get("lname").getAsString());

                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }

    public void add_address(){

        String fname_string = fname.getText().toString();
        String lname_string = lname.getText().toString();
        String phone_string = phone.getText().toString();
        String area_string = area_id;
        String type_string = type_option.getText().toString();
        String block_string = block.getText().toString();
        String street_string = street.getText().toString();
        String house_string = house.getText().toString();
        String flat_string = flat.getText().toString();
        String juddah_string = juddah.getText().toString();
        if (fname_string.equals("")){
            Toast.makeText(AddNewAddress.this,"Please Enter First Name",Toast.LENGTH_SHORT).show();
            fname.requestFocus();
        }else if (lname_string.equals("")){
            Toast.makeText(AddNewAddress.this,"Please Enter Last Name",Toast.LENGTH_SHORT).show();
            lname.requestFocus();
        }else if (phone_string.equals("")){
            Toast.makeText(AddNewAddress.this,"Please Enter Phone",Toast.LENGTH_SHORT).show();
            phone.requestFocus();
        }else if (area_string==""){
            Toast.makeText(AddNewAddress.this,"Please Enter Area",Toast.LENGTH_SHORT).show();
            area_option.requestFocus();
        }else if (type_string.equals("")){
            Toast.makeText(AddNewAddress.this,"Please Enter Type",Toast.LENGTH_SHORT).show();
            type_option.requestFocus();
        }else if (block_string.equals("")){
            Toast.makeText(AddNewAddress.this,"Please Enter Block",Toast.LENGTH_SHORT).show();
            block.requestFocus();
        }else if (street_string.equals("")){
            Toast.makeText(AddNewAddress.this,"Please Enter Street",Toast.LENGTH_SHORT).show();
            street.requestFocus();
        }else if (house_string.equals("")){
            Toast.makeText(AddNewAddress.this,"Please Enter House",Toast.LENGTH_SHORT).show();
            house.requestFocus();
        }else if (flat_string.equals("")){
            Toast.makeText(AddNewAddress.this,"Please Enter Flat",Toast.LENGTH_SHORT).show();
            flat.requestFocus();
        }else if (juddah_string.equals("")){
            Toast.makeText(AddNewAddress.this,"Please Enter Juddah",Toast.LENGTH_SHORT).show();
            juddah.requestFocus();
        }else {
            final KProgressHUD hud = KProgressHUD.create(AddNewAddress.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setCancellable(true)
                    .setMaxProgress(100)
                    .show();


            Ion.with(this)
                    .load(Session.SERVERURL + "add-address.php")
                    .setBodyParameter("member_id",Session.GetUserId(this))
                    .setBodyParameter("phone",phone_string)
                    .setBodyParameter("area",area_string)
                    .setBodyParameter("type",type_string)
                    .setBodyParameter("block",block_string)
                    .setBodyParameter("street",street_string)
                    .setBodyParameter("house",house_string)
                    .setBodyParameter("flat",flat_string)
                    .setBodyParameter("juddah",juddah_string)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                              try {
                                  Log.e("addre",result.toString());
                                  hud.dismiss();
                                  if (result.get("status").getAsString().equals("Success")){
                                      Toast.makeText(AddNewAddress.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                                      AddNewAddress.this.onBackPressed();
                                  }else {
                                      Toast.makeText(AddNewAddress.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                                  }

                              }catch (Exception e1){
                                  e1.printStackTrace();
                              }
                        }
                    });
        }

    }


    public void get_areas(){
        final KProgressHUD hud = KProgressHUD.create(AddNewAddress.this)
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
                                Areas area = new Areas(result.get(i).getAsJsonObject(),AddNewAddress.this,"0");
                                areasfrom_api.add(area);

                                for (int j = 0; j < result.get(i).getAsJsonObject().get("areas").getAsJsonArray().size(); j++) {

                                    Areas sub_category = new Areas(result.get(i).getAsJsonObject().get("areas").getAsJsonArray().get(j).getAsJsonObject(), AddNewAddress.this, "1");
                                    areasfrom_api.add(sub_category);

                                }
                            }


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

        builder.setTitle("Select Country")
                .setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String selectedItem = array[i].toString();
                        Log.e("select",selectedItem);
                        area_option.setText(selectedItem);
                        area_id = areasfrom_api.get(i).id;
                        Log.e("area_id",area_id);
                        Session.SerAreaId(AddNewAddress.this,areasfrom_api.get(i).id);



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

    public Dialog onTypeChoice() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final CharSequence[] array = {"House","Apartment","Office","Hospital","Other"};


        builder.setTitle("Select Time")
                .setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedItem = array[which].toString();
                        Log.e("select",selectedItem);
                        type_option.setText(selectedItem);

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


}
