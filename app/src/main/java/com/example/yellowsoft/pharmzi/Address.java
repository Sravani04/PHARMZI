package com.example.yellowsoft.pharmzi;

import android.content.Context;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by yellowsoft on 11/1/18.
 */

public class Address implements Serializable {
    public String id,type,phone,area_id,area_title,area_title_ar,block,street,building,flat,jaddah;
    public Address(JsonObject jsonObject, Context context){
        id = jsonObject.get("id").getAsString();
        type = jsonObject.get("type").getAsString();
        phone = jsonObject.get("phone").getAsString();
        area_id = jsonObject.get("area").getAsJsonObject().get("id").getAsString();
        area_title = jsonObject.get("area").getAsJsonObject().get("title") != JsonNull.INSTANCE?jsonObject.get("area").getAsJsonObject().get("title").getAsString() : null;
        area_title_ar = jsonObject.get("area").getAsJsonObject().get("title_ar")!= JsonNull.INSTANCE?jsonObject.get("area").getAsJsonObject().get("title_ar").getAsString() : null;
        block = jsonObject.get("block").getAsString();
        street = jsonObject.get("street").getAsString();
        building = jsonObject.get("building").getAsString();
        flat = jsonObject.get("flat").getAsString();
        jaddah = jsonObject.get("jaddah").getAsString();
    }
}
