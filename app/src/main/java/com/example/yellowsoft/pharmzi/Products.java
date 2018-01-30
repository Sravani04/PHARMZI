package com.example.yellowsoft.pharmzi;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yellowsoft on 10/1/18.
 */

public class Products implements Serializable {
    public String id,title,title_ar,stock,price,quantity,about,about_ar,description,description_ar,
            instructions,instructions_ar,cat_id,cat_title,cat_title_ar,brand_id,brand_title,brand_title_ar;
    int cart_quantity;
    String shipping_price;
    public ArrayList<Images> images;
    public ArrayList<Pharmacies> pharmacies;
    public ArrayList<Pharmacies> pharmacies_all;
    public Products(JsonObject jsonObject, Context context){
        id = jsonObject.get("id").getAsString();
        title = jsonObject.get("title").getAsString();
        title_ar = jsonObject.get("title_ar").getAsString();
        stock = jsonObject.get("stock").getAsString();
        price = jsonObject.get("price").getAsString();
        quantity = jsonObject.get("quantity").getAsString();
        try {
            cat_id = jsonObject.get("category").getAsJsonObject().get("id").getAsString();
            cat_title = jsonObject.get("category").getAsJsonObject().get("title") != JsonNull.INSTANCE?jsonObject.get("category").getAsJsonObject().get("title").getAsString() : null;
            cat_title_ar = jsonObject.get("category").getAsJsonObject().get("title_ar").getAsString();
        }catch (Exception e){
            e.printStackTrace();
        }

        brand_id = jsonObject.get("brand").getAsJsonObject().get("id").getAsString();
        brand_title = jsonObject.get("brand").getAsJsonObject().get("title").getAsString();
        brand_title_ar = jsonObject.get("brand").getAsJsonObject().get("title_ar").getAsString();
        about = jsonObject.get("about").getAsString();
        about_ar = jsonObject.get("about_ar").getAsString();
        description = jsonObject.get("description").getAsString();
        description_ar = jsonObject.get("description_ar").getAsString();
        instructions = jsonObject.get("instructions").getAsString();
        instructions_ar = jsonObject.get("instructions_ar").getAsString();
        cart_quantity = 1;
        images = new ArrayList<>();
        if (jsonObject.has("images")) {
            for (int i = 0; i < jsonObject.get("images").getAsJsonArray().size(); i++) {
                Images image = new Images(jsonObject.get("images").getAsJsonArray().get(i).getAsJsonObject(), context);
                images.add(image);
            }
        }else {

        }

        pharmacies = new ArrayList<>();

        if (jsonObject.has("pharmacy")) {
            for (int j = 0; j < jsonObject.get("pharmacy").getAsJsonArray().size(); j++) {
                Pharmacies pharmaciess = new Pharmacies(jsonObject.get("pharmacy").getAsJsonArray().get(j).getAsJsonObject(), context);
                pharmacies.add(pharmaciess);
            }
        }else {

        }

    }



    public class Images implements Serializable{
        public String image;
        public Images(JsonObject jsonObject,Context context){
            image = jsonObject.get("image").getAsString();
        }
    }

    public class Pharmacies implements Serializable{
        public String id,title,title_ar,current_status,hours,time,minimum,image,banner,description,
                description_ar,small_description,small_description_ar,delivery_charges;
        ArrayList<Areas> areas;
        ArrayList<Payment> payments;
        ArrayList<PickArea> pickareas;
        ArrayList<DropArea> dropAreas;
        public Pharmacies(JsonObject jsonObject, Context context) {
            id = jsonObject.get("id").getAsString();
            title = jsonObject.get("title").getAsString();
            title_ar = jsonObject.get("title_ar").getAsString();
            current_status = jsonObject.get("current_status").getAsString();
            hours = jsonObject.get("hours").getAsString();
            time = jsonObject.get("time").getAsString();
            minimum = jsonObject.get("minimum").getAsString();
            image = jsonObject.get("image").getAsString();
            banner = jsonObject.get("banner").getAsString();
            description = jsonObject.get("description").getAsString();
            description_ar = jsonObject.get("description_ar").getAsString();
            small_description = jsonObject.get("small_description").getAsString();
            small_description_ar = jsonObject.get("small_description_ar").getAsString();
            delivery_charges = jsonObject.get("delivery_charges").getAsString();

            areas = new ArrayList<>();
            for (int i=0;i<jsonObject.get("area").getAsJsonArray().size();i++){
                Areas area = new Areas(jsonObject.get("area").getAsJsonArray().get(i).getAsJsonObject(),context,"0");
                areas.add(area);
            }

            payments = new ArrayList<>();
            for (int i=0;i<jsonObject.get("payment").getAsJsonArray().size();i++){
                Payment payment = new Payment(jsonObject.get("payment").getAsJsonArray().get(i).getAsJsonObject(),context);
                payments.add(payment);
            }

            pickareas = new ArrayList<>();
            for (int i=0;i<jsonObject.get("pick_areas").getAsJsonArray().size();i++){
                PickArea pareas = new PickArea(jsonObject.get("pick_areas").getAsJsonArray().get(i).getAsJsonObject(),context);
                pickareas.add(pareas);
            }

            dropAreas = new ArrayList<>();
            for (int i=0;i<jsonObject.get("drop_areas").getAsJsonArray().size();i++){
                DropArea dareas = new DropArea(jsonObject.get("drop_areas").getAsJsonArray().get(i).getAsJsonObject(),context);
                dropAreas.add(dareas);
            }
        }


        public class Areas implements Serializable{
            public String id,title,title_ar,type;
            public Areas(JsonObject jsonObject,Context context,String type){
                this.type = type;
                id = jsonObject.get("id").getAsString();
                title = jsonObject.get("title").getAsString();
                title_ar = jsonObject.get("title_ar").getAsString();
            }
        }

        public class Payment implements Serializable{
            public String id,title,title_ar,image;
            public Payment(JsonObject jsonObject,Context context){
                id = jsonObject.get("id").getAsString();
                title = jsonObject.get("title").getAsString();
                title_ar = jsonObject.get("title_ar").getAsString();
                image = jsonObject.get("image").getAsString();
            }
        }

        public class PickArea implements Serializable{
            public String id,title,title_ar,image;
            public PickArea(JsonObject jsonObject,Context context){
                id = jsonObject.get("id").getAsString();
                title = jsonObject.get("title").getAsString();
                title_ar = jsonObject.get("title_ar").getAsString();
            }
        }

        public class DropArea implements Serializable{
            public String id,title,title_ar,image;
            public DropArea(JsonObject jsonObject,Context context){
                id = jsonObject.get("id").getAsString();
                title = jsonObject.get("title").getAsString();
                title_ar = jsonObject.get("title_ar").getAsString();
            }
        }
    }


    public JsonObject getJson(){

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",id);
        jsonObject.addProperty("title",title);
        jsonObject.addProperty("title_ar",title_ar);
        jsonObject.addProperty("price",price);
        jsonObject.addProperty("stock",stock);
        jsonObject.addProperty("quantity",quantity);
        jsonObject.addProperty("about",about);
        jsonObject.addProperty("about_ar",about_ar);
        jsonObject.addProperty("description",description);
        jsonObject.addProperty("description_ar",description_ar);
        jsonObject.addProperty("instructions",instructions);
        jsonObject.addProperty("instructions_ar",instructions_ar);
        jsonObject.addProperty("cart_quantity",cart_quantity);


        JsonObject category = new JsonObject();
        category.addProperty("id",cat_id);
        category.addProperty("title",cat_title);
        category.addProperty("title_ar",cat_title_ar);

        JsonObject brand = new JsonObject();
        brand.addProperty("id",brand_id);
        brand.addProperty("title",brand_title);
        brand.addProperty("title_ar",brand_title_ar);


        JsonArray imagess = new JsonArray();
        for (int i=0;i<this.images.size();i++){
            JsonObject image = new JsonObject();
            image.addProperty("image",this.images.get(i).image);
            imagess.add(image);

        }

        JsonArray ph = new JsonArray();
        for (int i=0;i<this.pharmacies.size();i++){
            JsonObject pharmcy = new JsonObject();
            pharmcy.addProperty("id",this.pharmacies.get(i).id);
            pharmcy.addProperty("title",this.pharmacies.get(i).title);
            pharmcy.addProperty("title_ar",this.pharmacies.get(i).title_ar);
            pharmcy.addProperty("current_status",this.pharmacies.get(i).current_status);
            pharmcy.addProperty("hours",this.pharmacies.get(i).hours);
            pharmcy.addProperty("time",this.pharmacies.get(i).time);
            pharmcy.addProperty("minimum",this.pharmacies.get(i).minimum);
            pharmcy.addProperty("image",this.pharmacies.get(i).image);
            pharmcy.addProperty("banner",this.pharmacies.get(i).banner);
            pharmcy.addProperty("description",this.pharmacies.get(i).description);
            pharmcy.addProperty("description_ar",this.pharmacies.get(i).description_ar);
            pharmcy.addProperty("small_description",this.pharmacies.get(i).small_description);
            pharmcy.addProperty("small_description_ar",this.pharmacies.get(i).small_description_ar);
            pharmcy.addProperty("delivery_charges",this.pharmacies.get(i).delivery_charges);

            JsonArray areass = new JsonArray();
            for (i=0;i<this.pharmacies.get(0).areas.size();i++){
                JsonObject area = new JsonObject();
                area.addProperty("id",this.pharmacies.get(0).areas.get(i).id);
                area.addProperty("title",this.pharmacies.get(0).areas.get(i).title);
                area.addProperty("title_ar",this.pharmacies.get(0).areas.get(i).title_ar);
                areass.add(area);
            }

            pharmcy.add("area",areass);




            JsonArray paymentss = new JsonArray();
            for (i=0;i<this.pharmacies.get(0).payments.size();i++){
                JsonObject payment = new JsonObject();
                payment.addProperty("id",this.pharmacies.get(0).payments.get(i).id);
                payment.addProperty("title",this.pharmacies.get(0).payments.get(i).title);
                payment.addProperty("title_ar",this.pharmacies.get(0).payments.get(i).title_ar);
                payment.addProperty("image",this.pharmacies.get(0).payments.get(i).image);
                paymentss.add(payment);

            }

            pharmcy.add("payment",paymentss);

            JsonArray pick_areass = new JsonArray();
            for (i=0;i<this.pharmacies.get(0).pickareas.size();i++){
                JsonObject pick_area = new JsonObject();
                pick_area.addProperty("id",this.pharmacies.get(0).pickareas.get(i).id);
                pick_area.addProperty("title",this.pharmacies.get(0).pickareas.get(i).title);
                pick_area.addProperty("title_ar",this.pharmacies.get(0).pickareas.get(i).title_ar);
                pick_area.addProperty("image",this.pharmacies.get(0).pickareas.get(i).image);
                pick_areass.add(pick_area);

            }

            pharmcy.add("pick_areas",pick_areass);



            JsonArray drop_areass = new JsonArray();
            for (i=0;i<this.pharmacies.get(0).dropAreas.size();i++){
                JsonObject drop_area = new JsonObject();
                drop_area.addProperty("id",this.pharmacies.get(0).dropAreas.get(i).id);
                drop_area.addProperty("title",this.pharmacies.get(0).dropAreas.get(i).title);
                drop_area.addProperty("title_ar",this.pharmacies.get(0).dropAreas.get(i).title_ar);
                drop_area.addProperty("image",this.pharmacies.get(0).dropAreas.get(i).image);
                drop_areass.add(drop_area);

            }

            pharmcy.add("drop_areas",drop_areass);

            ph.add(pharmcy);
        }


        jsonObject.add("category",category);
        jsonObject.add("brand",brand);
        jsonObject.add("images",imagess);
        jsonObject.add("pharmacy",ph);

        return jsonObject;
    }
}
