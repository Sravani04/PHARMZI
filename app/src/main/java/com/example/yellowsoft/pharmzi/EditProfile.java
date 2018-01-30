package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
 * Created by yellowsoft on 8/1/18.
 */

public class EditProfile extends Activity {
    EditText fname,lname,phone,block,street,house,flat_no,floor_no;
    LinearLayout select_area,save_btn;
    ImageView area_dropdown,back_btn;
    String area_id;
    TextView area_option,st_profile;
    String fname_text,lname_text,phone_text,email,area_title,block_text,street_text,house_text,flat,floor;
    ArrayList<Areas> areasfrom_api;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile_screen);
        Session.forceRTLIfSupported(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        areasfrom_api = new ArrayList<>();
//        if (getIntent()!=null && getIntent().hasExtra("areas")){
//            country = (Country) getIntent().getSerializableExtra("areas");
//            Log.e("areas",country.toString());
//        }

        if (getIntent()!=null && getIntent().hasExtra("fname")){
            fname_text =  getIntent().getStringExtra("fname");
            lname_text =  getIntent().getStringExtra("lname");
            phone_text =  getIntent().getStringExtra("phone");
            block_text =  getIntent().getStringExtra("block");
            street_text =  getIntent().getStringExtra("street");
            house_text =  getIntent().getStringExtra("house");
            flat =  getIntent().getStringExtra("flat");
            floor =  getIntent().getStringExtra("jaddah");

        }

        select_area = (LinearLayout) findViewById(R.id.select_area);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        phone = (EditText) findViewById(R.id.phone);
        block = (EditText) findViewById(R.id.block);
        street = (EditText) findViewById(R.id.street);
        house = (EditText) findViewById(R.id.house);
        flat_no = (EditText) findViewById(R.id.flat_no);
        floor_no = (EditText) findViewById(R.id.floor_no);
        save_btn = (LinearLayout) findViewById(R.id.save_btn);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        area_option = (TextView) findViewById(R.id.area_option);
        area_dropdown = (ImageView) findViewById(R.id.area_dropdown);
        st_profile = (TextView) findViewById(R.id.st_profile);

        fname.setText(fname_text);
        lname.setText(lname_text);
        phone.setText(phone_text);
        block.setText(block_text);
        street.setText(street_text);
        house.setText(house_text);
        flat_no.setText(flat);
        floor_no.setText(floor);

        select_area.setOnClickListener(new View.OnClickListener() {
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


        area_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = onAreaChoice();
                dialog.show();
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              edit_profile();
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditProfile.this.onBackPressed();
            }
        });

        get_areas();


    }

    public void get_areas(){
        final KProgressHUD hud = KProgressHUD.create(EditProfile.this)
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
                                Areas area = new Areas(result.get(i).getAsJsonObject(),EditProfile.this,"0");
                                areasfrom_api.add(area);

                                for (int j = 0; j < result.get(i).getAsJsonObject().get("areas").getAsJsonArray().size(); j++) {

                                    Areas sub_category = new Areas(result.get(i).getAsJsonObject().get("areas").getAsJsonArray().get(j).getAsJsonObject(), EditProfile.this, "1");
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
                        Session.SerAreaId(EditProfile.this,areasfrom_api.get(i).id);



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


    public void edit_profile(){
        String  fname_string = fname.getText().toString();
        String lname_string = lname.getText().toString();
        String phone_string  =phone.getText().toString();
        String area_string = area_id;
        String block_string = block.getText().toString();
        String street_string = street.getText().toString();
        String house_string = house.getText().toString();
        String floor_string = floor_no.getText().toString();
        String flat_string = flat_no.getText().toString();
        if (fname_string.equals("")){
            Toast.makeText(EditProfile.this,"Please Enter First Name",Toast.LENGTH_SHORT).show();
            fname.requestFocus();
        }else if (lname_string.equals("")){
            Toast.makeText(EditProfile.this,"Please Enter Last Name",Toast.LENGTH_SHORT).show();
            lname.requestFocus();
        }else if (phone_string.equals("")){
            Toast.makeText(EditProfile.this,"Please Enter Phone",Toast.LENGTH_SHORT).show();
            phone.requestFocus();
        }else if (area_string == ""){
            Toast.makeText(EditProfile.this,"Please Enter Area",Toast.LENGTH_SHORT).show();
            area_option.requestFocus();
        }else if (block_string.equals("")){
            Toast.makeText(EditProfile.this,"Please Enter Block Name / No",Toast.LENGTH_SHORT).show();
            block.requestFocus();
        }else  if (street_string.equals("")){
            Toast.makeText(EditProfile.this,"Please Enter Street Name",Toast.LENGTH_SHORT).show();
            street.requestFocus();
        }else if (house_string.equals("")){
            Toast.makeText(EditProfile.this,"Please Enter House No",Toast.LENGTH_SHORT).show();
            house.requestFocus();
        }else {
            final KProgressHUD hud = KProgressHUD.create(EditProfile.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setCancellable(true)
                    .setMaxProgress(100)
                    .show();
            Ion.with(this)
                    .load(Session.SERVERURL+"edit-member.php")
                    .setBodyParameter("member_id",Session.GetUserId(this))
                    .setBodyParameter("fname",fname_string)
                    .setBodyParameter("lname",lname_string)
                    .setBodyParameter("phone",phone_string)
                    .setBodyParameter("area",area_string)
                    .setBodyParameter("block",block_string)
                    .setBodyParameter("street",street_string)
                    .setBodyParameter("house",house_string)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {

                            try {
                                Log.e("editmember",result.toString());
                                hud.dismiss();
                                if (result.get("status").getAsString().equals("Success")){
                                    Toast.makeText(EditProfile.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                                    EditProfile.this.onBackPressed();
                                }else {
                                    Toast.makeText(EditProfile.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e1){
                                e1.printStackTrace();
                            }
                        }
                    });


        }
    }



}
