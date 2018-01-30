package com.example.yellowsoft.pharmzi;

import android.content.Context;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yellowsoft on 17/1/18.
 */

public class Orders implements Serializable {
    public String id,firstname,lastname,area_id,area_title,area_title_ar,block,street,building,floor,flat,jaddah,phone,coupon_code,discount_amount,
            price,delivery_charges,total_price,payment_method,pharmacy_id,pharmacy_title,pharmacy_title_ar,pharmacy_image,delivery_date,delivery_time,payment_status,delivery_status,
            date,product_id,product_name,product_name_ar,quantity,product_price,history_id,message,time,pro_id;
    public ArrayList<Product> products;
    public Orders(JsonObject jsonObject, Context context){
        id = jsonObject.get("id").getAsString();
        firstname = jsonObject.get("firstname").getAsString();
        lastname = jsonObject.get("lastname").getAsString();
        area_id = jsonObject.get("area").getAsJsonObject().get("id").getAsString();
        area_title = jsonObject.get("area").getAsJsonObject().get("title") != JsonNull.INSTANCE?jsonObject.get("area").getAsJsonObject().get("title").getAsString() : null;
        area_title_ar = jsonObject.get("area").getAsJsonObject().get("title_ar")!= JsonNull.INSTANCE?jsonObject.get("area").getAsJsonObject().get("title_ar").getAsString() : null;
        block = jsonObject.get("block").getAsString();
        street = jsonObject.get("street").getAsString();
        building = jsonObject.get("building").getAsString();
        floor = jsonObject.get("floor").getAsString();
        flat = jsonObject.get("flat").getAsString();
        jaddah = jsonObject.get("jaddah").getAsString();
        phone = jsonObject.get("phone").getAsString();
        coupon_code = jsonObject.get("coupon_code").getAsString();
        discount_amount = jsonObject.get("discount_amount").getAsString();
        price = jsonObject.get("price").getAsString();
        delivery_charges = jsonObject.get("delivery_charges").getAsString();
        total_price = jsonObject.get("total_price").getAsString();
        payment_method = jsonObject.get("payment_method").getAsString();
        pharmacy_id = jsonObject.get("pharmacy").getAsJsonObject().get("id").getAsString();
        pharmacy_title = jsonObject.get("pharmacy").getAsJsonObject().get("title")!= JsonNull.INSTANCE?jsonObject.get("pharmacy").getAsJsonObject().get("title").getAsString() : null;
        pharmacy_title_ar = jsonObject.get("pharmacy").getAsJsonObject().get("title_ar")!= JsonNull.INSTANCE?jsonObject.get("pharmacy").getAsJsonObject().get("title_ar").getAsString() : null;
        pharmacy_image = jsonObject.get("pharmacy").getAsJsonObject().get("image").getAsString();
        delivery_date = jsonObject.get("delivery_date").getAsString();
        delivery_time = jsonObject.get("delivery_time").getAsString();
        payment_status = jsonObject.get("payment_status").getAsString();
        delivery_status = jsonObject.get("delivery_status").getAsString();
        date = jsonObject.get("date").getAsString();
        products = new ArrayList<>();
        for (int i=0;i<jsonObject.get("products").getAsJsonArray().size();i++){
               Product product=new Product(jsonObject.get("products").getAsJsonArray().get(i).getAsJsonObject(),context);
                products.add(product);

        }

        for (int i=0;i<jsonObject.get("history").getAsJsonArray().size();i++){
            history_id = jsonObject.get("history").getAsJsonArray().get(i).getAsJsonObject().get("id").getAsString();
            message = jsonObject.get("history").getAsJsonArray().get(i).getAsJsonObject().get("message").getAsString();
            time = jsonObject.get("history").getAsJsonArray().get(i).getAsJsonObject().get("time").getAsString();
        }




    }

    public class Product implements Serializable{
        public String pro_id,product_id,product_name,product_name_ar,quantity,product_price;
        public Product(JsonObject jsonObject,Context context){
                pro_id = jsonObject.get("id").getAsString();
                product_id = jsonObject.get("product_id").getAsString();
                product_name = jsonObject.get("product_name")!= JsonNull.INSTANCE?jsonObject.get("product_name").getAsString() : null;
                product_name_ar = jsonObject.get("product_name_ar")!= JsonNull.INSTANCE?jsonObject.get("product_name_ar").getAsString() : null;;
                quantity = jsonObject.get("quantity").getAsString();
                product_price = jsonObject.get("price").getAsString();
        }
    }
}
