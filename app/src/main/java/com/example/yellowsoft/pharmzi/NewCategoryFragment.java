package com.example.yellowsoft.pharmzi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by yellowsoft on 22/1/18.
 */

public class NewCategoryFragment extends Fragment  {
    ListView listView;
    NewCategoryAdapter adapter;
    ArrayList<Categories> categoriesfrom_api;
    ArrayList<Products> productsfrom_api;
    LinkedHashSet<Products> lhs ;


    String phar_id,phar_dc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.new_category_list,container,false);
        Session.forceRTLIfSupported(getActivity());
        if (getArguments()!=null && getArguments().containsKey("id")){
            phar_id   = getArguments().getString("id");
            phar_dc = getArguments().getString("dc");
            Session.SetPharmciId(getActivity(),phar_id,phar_dc);
            Log.e("phar_",phar_dc);
        }
        categoriesfrom_api = new ArrayList<>();
        productsfrom_api = new ArrayList<>();
        lhs = new LinkedHashSet<Products>();

        listView = (ListView)view.findViewById(R.id.cat_list);
        adapter = new NewCategoryAdapter(getActivity(),categoriesfrom_api);
        listView.setAdapter(adapter);


        get_categories();
        //get_products();
        return view;
    }

    public void get_categories(){
        final KProgressHUD hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(getContext())
                .load(Session.SERVERURL+"category.php")
                .setBodyParameter("pharmacy",phar_id)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            Log.e("cat",result.toString());
                            hud.dismiss();
                            for (int i=0;i<result.size();i++){
                                Categories categories = new Categories(result.get(i).getAsJsonObject(),getContext());
                                categoriesfrom_api.add(categories);

                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }


    public void get_products(){
        final KProgressHUD hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(getContext())
                .load(Session.SERVERURL+"products.php")
                .setBodyParameter("pharmacy",phar_id)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        try {
                            Log.e("products",result.toString());
                            hud.dismiss();

                            for (int i=0;i<result.size();i++){
                                Products products = new Products(result.get(i).getAsJsonObject(),getContext());
                                productsfrom_api.add(products);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }
}
