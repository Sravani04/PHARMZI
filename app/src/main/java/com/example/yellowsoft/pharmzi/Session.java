package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by yellowsoft on 8/1/18.
 */

public class Session {
    public static final String SERVERURL = "http://clients.mamacgroup.com/sadaleya/api/";
    public  static  final String mem_id="mem_id";
    public static final String cart_products = "cart_products";
    public static  final String area_id = "area_id";
    public static String  PAYMENT_URL = "http://clients.mamacgroup.com/sadaleya/api/Tap.php?";
    public  static  final String p_id="p_id";
    public  static  final String p_dc="p_dc";
    public  static  final String lang="lan";
    public  static  final String Words_en="en";
    public  static  final String Words_ar="ar";

    public  static void SetUserId(Context context, String id){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(mem_id,id);
        editor.commit();
    }

    public  static String GetUserId(Context context) {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(mem_id,"-1");
    }

    public  static void SerAreaId(Context context, String id){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(area_id,id);
        editor.commit();
    }

    public  static String GetAreaId(Context context) {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(area_id,"-1");
    }

    public static void SetCartProducts(Context context,Products cart_product){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray= GetCartProducts(context);
        Log.e("adding_pro",String.valueOf(jsonArray.size()));
        jsonArray.add(cart_product.getJson());
        Log.e("adding_pro",String.valueOf(jsonArray.size()));
        Log.e("array_info",jsonArray.toString());
        editor.putString(cart_products,jsonArray.toString());
        editor.apply();
    }

    public static JsonArray GetCartProducts(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = new JsonArray();
        try
        {
            jsonArray = (JsonArray) jsonParser.parse(sharedPreferences.getString(cart_products, "[]"));
            Log.e("dfd",jsonArray.toString());
        }catch (Exception rx){
            rx.printStackTrace();
        }

        return  jsonArray;
    }

    public static void deleteCart(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(cart_products,"[]");
        editor.putString(p_id,"-1");
        editor.apply();
    }

    public  static void SetPharmciId(Context context, String id,String dc){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(p_id,id);
        editor.putString(p_dc,dc);
        editor.commit();
    }

    public  static String GetPharmciId(Context context) {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(p_id,"-1");
    }
    public  static String GetPharmciDc(Context context) {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(p_dc,"0");
    }

    public static   void forceRTLIfSupported(Activity activity) {
        SharedPreferences sharedPref;
        sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
        Log.e("lan", sharedPref.getString(lang, "-1"));

        if (GetLang(activity).equals("en")) {
            Resources res = activity.getResources();
            // Change locale settings in the app.
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.locale = new Locale("en".toLowerCase());
            res.updateConfiguration(conf, dm);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
        } else if (GetLang(activity).equals("ar")) {
            Resources res = activity.getResources();
            // Change locale settings in the app.
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.locale = new Locale("ar".toLowerCase());
            res.updateConfiguration(conf, dm);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        } else {
            Resources res = activity.getResources();
            // Change locale settings in the app.
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.locale = new Locale("en".toLowerCase());
            res.updateConfiguration(conf, dm);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
        }
    }


    public  static void SetLang(Context context, String ar){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(lang,ar);
        editor.commit();
    }

    public  static String GetLang(Context context) {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(lang,"en");
    }

    public  static void SetEnWords(Context context, String en){
        Log.e("engres",en);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Words_en,en);
        editor.commit();
    }

    public  static String GetEnWords(Context context) {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(Words_en,"-1");
    }

    public  static void SetArWords(Context context, String ar){
        Log.e("arabicres",ar);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Words_ar,ar);
        editor.commit();
    }

    public  static String GetArWords(Context context) {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(Words_ar,"-1");
    }

    public static String GetWord(Context context,String word){
        if (Session.GetLang(context).equals("ar")){
            try {
                Log.e("ar_words",GetArWords(context));
                JSONObject jsonObject = new JSONObject(GetArWords(context));
                return jsonObject.getString(word);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            try {
                JSONObject jsonObject = new JSONObject(GetEnWords(context));
                return jsonObject.getString(word);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return word;
    }



}
