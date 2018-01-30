package com.example.yellowsoft.pharmzi;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by yellowsoft on 23/1/18.
 */

public class Offers implements Serializable {
    public String id,title,title_ar,image,pharmacy_id,pharmacy_title,pharmacy_title_ar;
    public Offers(JsonObject jsonObject,Context context){
        id = jsonObject.get("id").getAsString();
        title = jsonObject.get("title").getAsString();
        title_ar = jsonObject.get("title_ar").getAsString();
        image = jsonObject.get("image").getAsString();
        pharmacy_id = jsonObject.get("pharmacy").getAsJsonObject().get("id").getAsString();
        pharmacy_title = jsonObject.get("pharmacy").getAsJsonObject().get("title").getAsString();
        pharmacy_title_ar = jsonObject.get("pharmacy").getAsJsonObject().get("title_ar").getAsString();
    }
}
