package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 5/1/18.
 */

public class CheckoutActivity extends FragmentActivity {
    ArrayList<Products> products;
    EditText fname,lname,phone,block,street,house,flat,juddah,email;
    TextView subtotal,dc,total,area_option;
    LinearLayout select_area,place_order,address_pop_up,tap_btn,cod_btn,saved_address_btn,new_address_btn;
    ImageView area_dropdown;
    ArrayList<Areas> areasfrom_api;
    String area_id;
    String total_price,coupon,discount_value,shipping_price,message;
    Pharmacies pharmacies;
    ArrayList<Address> address_list;
    ListView address_lv;
    AddressAdapter addressAdapter;
    ArrayList<String> address_title;
    TextView email_sta_tv,fname_sta_tv,lname_sta_tv,mobile_sta_tv,alias_sta_tv,area_sta_tv,block_sta_tv,
            street_sta_tv,building_sta_tv,flat_sta_tv,fname_tv,lname_tv,mobile_tv,alias_tv,area_tv,block_tv,street_tv,building_tv,flat_tv,
            new_address_tv,place_order_tv,saved_address_tv,choose_address_tv,sta_order_summery,sta_pa_methode;
    String a_id="-1";
    ArrayList<String> address_id;
    ImageView tap_img,cod_img;
    LinearLayout cod_ll,tap_ll;
    String pay_met="";
    ImageView final_pop_close;
    String date,time;
    ImageView back_btn;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example1);
        Session.forceRTLIfSupported(this);
        areasfrom_api = new ArrayList<>();
        address_list = new ArrayList<>();
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        tap_img=(ImageView) findViewById(R.id.tap_img);
        cod_img=(ImageView) findViewById(R.id.cod_img);
        back_btn=(ImageView) findViewById(R.id.back_btn);
        tap_ll = (LinearLayout) findViewById(R.id.tap_ll);
        cod_ll=(LinearLayout)findViewById(R.id.cod_ll);
        address_title=new ArrayList<>();
        address_id= new ArrayList<>();
        area_option = (TextView) findViewById(R.id.area_option);
        area_dropdown = (ImageView) findViewById(R.id.area_dropdown);
        block = (EditText) findViewById(R.id.block);
        street = (EditText) findViewById(R.id.street);
        house = (EditText) findViewById(R.id.house);
        flat = (EditText) findViewById(R.id.flat);
        juddah = (EditText) findViewById(R.id.juddah);
        subtotal = (TextView) findViewById(R.id.subtotal);
        dc = (TextView) findViewById(R.id.dc);
        total = (TextView) findViewById(R.id.total);
        area_option = (TextView) findViewById(R.id.area_option);
        select_area = (LinearLayout) findViewById(R.id.select_area);
        place_order = (LinearLayout) findViewById(R.id.place_order);
        address_pop_up = (LinearLayout) findViewById(R.id.address_pop_up);
        tap_btn = (LinearLayout) findViewById(R.id.tap_btn);
        cod_btn = (LinearLayout) findViewById(R.id.cod_btn);
        area_dropdown = (ImageView) findViewById(R.id.area_dropdown);
        saved_address_btn = (LinearLayout) findViewById(R.id.saved_address_btn);
        new_address_btn = (LinearLayout) findViewById(R.id.new_address_btn);
        fname_tv = (TextView) findViewById(R.id.fname_add_tv);
        lname_tv = (TextView) findViewById(R.id.lname_add_tv);
        mobile_tv = (TextView) findViewById(R.id.mobile_add_tv);
        alias_tv = (TextView) findViewById(R.id.email_add_tvv);
        block_tv = (TextView) findViewById(R.id.block_add_tv);
        street_tv = (TextView) findViewById(R.id.street_add_tv);
        building_tv = (TextView) findViewById(R.id.buillding_add_tv);
        flat_tv = (TextView) findViewById(R.id.flat_add_tv);
        address_lv=(ListView)findViewById(R.id.address_list_final);
        final_pop_close=(ImageView) findViewById(R.id.final_pop_close);
        addressAdapter=new AddressAdapter(this,address_title);
        address_lv.setAdapter(addressAdapter);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckoutActivity.this.onBackPressed();
            }
        });


        if (getIntent()!=null && getIntent().hasExtra("total")){
            total_price = getIntent().getStringExtra("total");
            coupon = getIntent().getStringExtra("text");
            discount_value = getIntent().getStringExtra("dv");
            shipping_price=getIntent().getStringExtra("dc");
            pharmacies = (Pharmacies) getIntent().getSerializableExtra("pharmcies");
            date = getIntent().getStringExtra("date");
            time = getIntent().getStringExtra("time");
//            Log.e("date",date);
  //          Log.e("time",time);
//            gallerypath = getIntent().getStringExtra("lpath");
        }

        subtotal.setText(total_price + "KD");
        total.setText(total_price+"KD");
        Log.e("final_cost",total.getText().toString());


        select_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = onAreaChoice();
                dialog.show();
            }
        });

        final_pop_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address_pop_up.setVisibility(View.GONE);
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

        new_address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new_address_btn.setBackgroundResource(R.drawable.grey_square_corners_with_full);
                saved_address_btn.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        });

        saved_address_btn.setBackgroundResource(R.drawable.grey_square_corners_with_full);
        new_address_btn.setBackgroundColor(Color.parseColor("#ffffff"));

        saved_address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (address_list.size() == 0) {
                    Toast.makeText(CheckoutActivity.this,  "No saved Address", Toast.LENGTH_SHORT).show();
                } else {
                    address_pop_up.setVisibility(View.VISIBLE);
                }
                saved_address_btn.setBackgroundResource(R.drawable.grey_square_corners_with_full);
                new_address_btn.setBackgroundColor(Color.parseColor("#ffffff"));

                fname.setVisibility(View.VISIBLE);
                lname.setVisibility(View.VISIBLE);
                phone.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);
                area_option.setVisibility(View.VISIBLE);
                block.setVisibility(View.VISIBLE);
                street.setVisibility(View.VISIBLE);
                house.setVisibility(View.VISIBLE);
                flat.setVisibility(View.VISIBLE);
            }
        });

        address_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int which, long id) {
                a_id=address_id.get(which);
                area_id=address_list.get(which).area_id;

                phone.setText(address_list.get(which).phone);
                area_option.setText(address_list.get(which).area_title);
                block.setText(address_list.get(which).block);
                street.setText(address_list.get(which).street);
                house.setText(address_list.get(which).building);
                flat.setText(address_list.get(which).flat);
                juddah.setText(address_list.get(which).jaddah);
                address_pop_up.setVisibility(View.GONE);

            }
        });

        tap_img.setImageResource(R.drawable.radio_off);
        cod_img.setImageResource(R.drawable.radio_off);
        cod_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tap_img.setImageResource(R.drawable.radio_off);
                cod_img.setImageResource(R.drawable.radio_on);
                pay_met = "cash";
            }
        });
        tap_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tap_img.setImageResource(R.drawable.radio_on);
                cod_img.setImageResource(R.drawable.radio_off);
                pay_met = "tap";
            }
        });

        get_address();

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname_string =fname.getText().toString();
                String lname_string = lname.getText().toString();
                String email_string = email.getText().toString();
                String phone_string = phone.getText().toString();
                String area_string = area_id;
                String block_string = block.getText().toString();
                String street_string = street.getText().toString();
                String house_string = house.getText().toString();
                String flat_string = flat.getText().toString();
                String juddah_string = juddah.getText().toString();
                if (email_string.equals("")) {
                    Toast.makeText(CheckoutActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                }else if (fname_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter First Name",Toast.LENGTH_SHORT).show();
                    fname.requestFocus();
                }else if (lname_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Last Name",Toast.LENGTH_SHORT).show();
                    lname.requestFocus();
                }else if (phone_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Phone",Toast.LENGTH_SHORT).show();
                    phone.requestFocus();
                }else if (area_string==""){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Area",Toast.LENGTH_SHORT).show();
                    area_option.requestFocus();
                }else if (block_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Block",Toast.LENGTH_SHORT).show();
                    block.requestFocus();
                }else if (street_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Street",Toast.LENGTH_SHORT).show();
                    street.requestFocus();
                }else if (house_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter House",Toast.LENGTH_SHORT).show();
                    house.requestFocus();
                }else if (flat_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Flat",Toast.LENGTH_SHORT).show();
                    flat.requestFocus();
                }else if (juddah_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Juddah",Toast.LENGTH_SHORT).show();
                    juddah.requestFocus();
                }else {
                    Intent intent = new Intent(CheckoutActivity.this, PaymentPage.class);
                    intent.putExtra("amount", total_price);
                    startActivityForResult(intent, 1);
                }
            }
        });

        get_areas();
        get_members();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if (resultCode == Activity.RESULT_OK) {

                // do something with the result
                if (data != null && data.hasExtra("message")) {
                    message = data.getExtras().getString("message");
                    Log.e("toast", message);
                    if (message.equals("success")){
                        place_order();
                    }else if (message.equals("failure")){
                        Toast.makeText(CheckoutActivity.this,"Please Try Again",Toast.LENGTH_SHORT).show();
                    }
                }





            } else if (resultCode == Activity.RESULT_CANCELED) {
                // some stuff that will happen if there's no result
            }
        }
    }


    public void get_areas(){
        final KProgressHUD hud = KProgressHUD.create(CheckoutActivity.this)
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
                                Areas area = new Areas(result.get(i).getAsJsonObject(),CheckoutActivity.this,"0");
                                areasfrom_api.add(area);

                                for (int j = 0; j < result.get(i).getAsJsonObject().get("areas").getAsJsonArray().size(); j++) {

                                    Areas sub_category = new Areas(result.get(i).getAsJsonObject().get("areas").getAsJsonArray().get(j).getAsJsonObject(), CheckoutActivity.this, "1");
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
                        Session.SerAreaId(CheckoutActivity.this,areasfrom_api.get(i).id);



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


    public void get_members(){
        final KProgressHUD hud = KProgressHUD.create(CheckoutActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(this)
                .load(Session.SERVERURL+"members.php")
                .setBodyParameter("member_id",Session.GetUserId(CheckoutActivity.this))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            hud.dismiss();
                            JsonObject jsonObject = result.get(0).getAsJsonObject();
                            fname.setText(jsonObject.get("fname").getAsString());
                            lname.setText(jsonObject.get("lname").getAsString());
                            email.setText(jsonObject.get("email").getAsString());
                            block.setText(jsonObject.get("block").getAsString());
                            street.setText(jsonObject.get("street").getAsString());
                            house.setText(jsonObject.get("house").getAsString());
                            flat.setText(jsonObject.get("flat").getAsString());
                            juddah.setText(jsonObject.get("juddah").getAsString());
                            phone.setText(jsonObject.get("phone").getAsString());

                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }

    public void get_address(){
        final KProgressHUD hud = KProgressHUD.create(CheckoutActivity.this)
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
//                                Address address = new Address(result.get(i).getAsJsonObject(),null);
//                                address_list.add(address);
                                JsonObject sub = result.get(i).getAsJsonObject();
                                String ar_id = sub.get("id").getAsString();
                                String ar_name = sub.get("type").getAsString();
                                address_list.add(new Address(result.get(i).getAsJsonObject(),CheckoutActivity.this));
                                address_id.add(ar_id);
                                address_title.add(ar_name);
                            }

                            addressAdapter.notifyDataSetChanged();
                            addressAdapter=new AddressAdapter(CheckoutActivity.this,address_title);
                            address_lv.setAdapter(addressAdapter);

                        }catch (Exception e1){
                            e1.printStackTrace();
                        }

//                        if (addressfrom_api.size() == 0){
//                            no_address_page.setVisibility(View.VISIBLE);
//                        }else {
//                            no_address_page.setVisibility(View.GONE);
//                        }

                    }
                });
    }

    public void place_order(){

        final KProgressHUD hud = KProgressHUD.create(CheckoutActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("member_id",Session.GetUserId(CheckoutActivity.this));
        JsonParser jsonParser = new JsonParser();

        JsonArray jsonArray = Session.GetCartProducts(this);

        JsonArray jsonArray1 = new JsonArray();
        Log.e("products_cart",jsonArray.toString());
        for (int i=0;i<jsonArray.size();i++){
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("price",jsonArray.get(i).getAsJsonObject().get("price").getAsString());
            jsonObject2.addProperty("product_id",jsonArray.get(i).getAsJsonObject().get("id").getAsString());
            jsonObject2.addProperty("quantity",jsonArray.get(i).getAsJsonObject().get("cart_quantity").getAsString());
            jsonArray1.add(jsonObject2);
            Log.e("productsresponse",jsonArray1.toString());

        }
        jsonObject.add("products",jsonArray1);



        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("phone",phone.getText().toString());
        jsonObject1.addProperty("area",area_id);
        jsonObject1.addProperty("street",street.getText().toString());
        jsonObject1.addProperty("block",block.getText().toString());
        jsonObject1.addProperty("lastname",lname.getText().toString());
        jsonObject1.addProperty("firstname",fname.getText().toString());
        jsonObject1.addProperty("house",house.getText().toString());
        jsonObject1.addProperty("flat",flat.getText().toString());
        jsonObject1.addProperty("email",email.getText().toString());
        jsonObject1.addProperty("jaddah",juddah.getText().toString());
        jsonObject.add("address",jsonObject1);

        jsonObject.addProperty("coupon_code", coupon);
        jsonObject.addProperty("discount_amount",discount_value);
        jsonObject.addProperty("total_price", total_price);
        Log.e("dc",Session.GetPharmciId(this));
        jsonObject.addProperty("delivery_charges",Session.GetPharmciDc(this));
        jsonObject.addProperty("payment_method","Tap");
        jsonObject.addProperty("deliveryTime",time);
        jsonObject.addProperty("deliveryDate",date);
        jsonObject.addProperty("pharmacy",Session.GetPharmciId(this));
        jsonObject.addProperty("payment","0");
        Log.e("reeeee",jsonObject.toString());

        Ion.with(this)
                .load(Session.SERVERURL + "place-order.php")
                .setBodyParameter("content",jsonObject.toString())
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        hud.dismiss();
                        try {
                            if (result.get("status").getAsString().equals("Success")) {
                                Log.e("result",result.toString());
                                Log.e("invoice_id",result.get("invoice_id").getAsString());
                                Log.e("result", result.get("message").getAsString());
                                Toast.makeText(CheckoutActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                Session.deleteCart(CheckoutActivity.this);
                                Intent intent = new Intent(CheckoutActivity.this, ThankyouActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(CheckoutActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                });
    }
}
