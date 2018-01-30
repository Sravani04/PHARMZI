package com.example.yellowsoft.pharmzi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 4/1/18.
 */

public class PharmaciesFragment  extends Fragment {
    ListView listView;
    PharmaciesAdapter adapter;
    ArrayList<Pharmacies> pharmaciesfrom_api;
    MainActivity mainActivity;
    private static long back_pressed;

    public static PharmaciesFragment newInstance(int someInt) {
        PharmaciesFragment myFragment = new PharmaciesFragment();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.pharmacies_fragment, container, false);
        pharmaciesfrom_api = new ArrayList<>();
        mainActivity = (MainActivity) getActivity();
        listView = (ListView) view.findViewById(R.id.pharmacies);
        adapter  = new PharmaciesAdapter(getContext(),pharmaciesfrom_api);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),ProductsActivity.class);
                intent.putExtra("pharmcies",pharmaciesfrom_api.get(i));
                startActivity(intent);
            }
        });
        if (mainActivity.delivery.equals("0")) {
            get_delivery_pharmacies();
        }else if (mainActivity.delivery.equals("1")){
            get_pharmacies();
        }

        return view;
    }



    public void get_pharmacies(){
        final KProgressHUD hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(getContext())
                .load(Session.SERVERURL+"pharmacies.php")
                .setBodyParameter("pickup",mainActivity.area_id)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            Log.e("pharmacies",result.toString());
                            hud.dismiss();
                            for (int i=0;i<result.size();i++) {
                                Pharmacies pharmacies = new Pharmacies(result.get(i).getAsJsonObject(),getContext());
                                pharmaciesfrom_api.add(pharmacies);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }

    public void get_delivery_pharmacies(){
        final KProgressHUD hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(getContext())
                .load(Session.SERVERURL+"pharmacies.php")
                .setBodyParameter("delivery",mainActivity.area_id)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            Log.e("pharmacies",result.toString());
                            hud.dismiss();
                            for (int i=0;i<result.size();i++) {
                                Pharmacies pharmacies = new Pharmacies(result.get(i).getAsJsonObject(),getContext());
                                pharmaciesfrom_api.add(pharmacies);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }

    public void  getData(String id){
        //get_pharmacies(id);
    }



}
