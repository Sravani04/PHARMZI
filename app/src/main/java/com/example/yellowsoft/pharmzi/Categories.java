package com.example.yellowsoft.pharmzi;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yellowsoft on 9/1/18.
 */

public class Categories implements Serializable {
    public String id,title,title_ar,image,type;
    public Categories(JsonObject jsonObject, Context context){
        id = jsonObject.get("id").getAsString();
        title = jsonObject.get("title").getAsString();
        title_ar = jsonObject.get("title_ar").getAsString();
        image = jsonObject.get("image").getAsString();
        type="0";

    }

    public Categories(JsonObject jsonObject, Context context,String type){
        this.type = type;
        if(type.equals("0")){
            id = jsonObject.get("id").getAsString();
            title = jsonObject.get("title").getAsString();
            title_ar = jsonObject.get("title_ar").getAsString();
            image = jsonObject.get("image").getAsString();


        }else {
            id = jsonObject.get("id").getAsString();
            title = jsonObject.get("title").getAsString();
            title_ar = jsonObject.get("title_ar").getAsString();
            image = jsonObject.get("image").getAsString();

        }
    }
}
