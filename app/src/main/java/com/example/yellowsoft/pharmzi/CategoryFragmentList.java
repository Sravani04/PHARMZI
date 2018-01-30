package com.example.yellowsoft.pharmzi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 19/1/18.
 */

public class CategoryFragmentList extends Fragment {
    CategoryAdapter adapter;
    GridView listView;
    ArrayList<Categories> subcategoriesfrom_api;
    ArrayList<Products> productsfrom_api;
    Categories categories;
    ArrayList<Pharmacies> pharmaciesfrom_api;
    MainActivity mainActivity;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.category_fragment_list,container,false);
        subcategoriesfrom_api = new ArrayList<>();
        productsfrom_api = new ArrayList<>();
        pharmaciesfrom_api = new ArrayList<>();
        mainActivity = (MainActivity) getActivity();
        listView = (GridView) view.findViewById(R.id.cat_list);
        adapter = new CategoryAdapter(getActivity(),subcategoriesfrom_api,this);
        listView.setAdapter(adapter);


        categories = (Categories) getArguments().getSerializable("categories");
        Log.e("caaa", String.valueOf(categories));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent  = new Intent(getContext(),PharmcyActivity.class);
                intent.putExtra("cat",categories);
                startActivity(intent);
            }
        });


      //  get_products();
        get_subcategories();
        return view;
    }

    public void get_subcategories(){
//        final KProgressHUD hud = KProgressHUD.create(getContext())
//                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("Please wait")
//                .setCancellable(true)
//                .setMaxProgress(100)
//                .show();
        Ion.with(getContext())
                .load(Session.SERVERURL+"sub-category.php")
                .setBodyParameter("category",categories.id)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        Log.e("category",result.toString());
                        try {
//                            hud.dismiss();
                            for (int i = 0; i < result.size(); i++) {
                                Categories categories = new Categories(result.get(i).getAsJsonObject(),getContext(),"0");
                                subcategoriesfrom_api.add(categories);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }







}
