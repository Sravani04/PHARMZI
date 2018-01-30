package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yellowsoft on 17/1/18.
 */

public class OrdersDetailPage extends Activity {
    ImageView back_btn;
    TextView order_id,date,payment_status,payment_type,delivery_status,fname,area,phone,block,title,quantity,price,delivery_charges,coupon,discount,grand_total,
    dt,street,building,floor,flat;
    Orders orders;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);
        if (getIntent()!=null && getIntent().hasExtra("orders")){
            orders = (Orders) getIntent().getSerializableExtra("orders");
            Log.e("orderprint",orders.toString());
        }
        order_id = (TextView) findViewById(R.id.order_id);
        date = (TextView) findViewById(R.id.date);
        payment_status = (TextView) findViewById(R.id.payment_status);
        payment_type = (TextView) findViewById(R.id.payment_tyoe);
        delivery_status = (TextView) findViewById(R.id.delivery_status);
        fname = (TextView) findViewById(R.id.fname);
        area = (TextView) findViewById(R.id.area);
        phone = (TextView) findViewById(R.id.phone);
        block = (TextView) findViewById(R.id.block);
        dt = (TextView) findViewById(R.id.dt);
        street = (TextView) findViewById(R.id.street);
        building = (TextView) findViewById(R.id.building);
        floor = (TextView) findViewById(R.id.floor);
        flat = (TextView) findViewById(R.id.flat);
        title = (TextView) findViewById(R.id.title);
        quantity = (TextView) findViewById(R.id.quantity);
        price = (TextView) findViewById(R.id.price);
        delivery_charges = (TextView) findViewById(R.id.delivery_charges);
        coupon = (TextView) findViewById(R.id.coupon);
        discount = (TextView) findViewById(R.id.discount);
        grand_total = (TextView) findViewById(R.id.grand_total);
        back_btn = (ImageView) findViewById(R.id.back_btn);

        order_id.setText(orders.id);
        date.setText(orders.date);
        payment_status.setText(orders.payment_status);
        payment_type.setText(orders.payment_method);
        delivery_status.setText(orders.delivery_status);
        fname.setText(orders.firstname);
        area.setText(orders.area_title);
        phone.setText(orders.phone);
        block.setText(orders.block);
        dt.setText(orders.delivery_time);
        street.setText(orders.street);
        building.setText(orders.building);
        floor.setText(orders.floor);
        flat.setText(orders.flat);
        title.setText(orders.product_name);
        quantity.setText(orders.quantity);
        price.setText(orders.product_price);
        delivery_charges.setText(orders.delivery_charges + "KD");
        discount.setText(orders.discount_amount);
        coupon.setText(orders.coupon_code);
        grand_total.setText(orders.price + "KD");

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrdersDetailPage.this.onBackPressed();
            }
        });
    }
}

