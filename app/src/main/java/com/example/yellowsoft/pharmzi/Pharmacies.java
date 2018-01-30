package com.example.yellowsoft.pharmzi;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yellowsoft on 9/1/18.
 */

public class Pharmacies implements Serializable {
    public String id,title,title_ar,current_status,hours,time,minimum,image,banner,description,
    description_ar,small_description,small_description_ar,delivery_charges;
    ArrayList<Areas> areas;
    ArrayList<Payment> payments;
    ArrayList<PickArea> pickareas;
    ArrayList<DropArea> dropAreas;
    public Pharmacies(JsonObject jsonObject, Context context){
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
            Areas area = new Areas(jsonObject.get("area").getAsJsonArray().get(i).getAsJsonObject(),context);
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
        public String id,title,title_ar;
        public Areas(JsonObject jsonObject,Context context){
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
