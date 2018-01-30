package com.example.yellowsoft.pharmzi;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 4/1/18.
 */

public class CategoryFragment  extends Fragment{
    GridView gridView1,gridView2,gridView3,gridView4,gridView5,gridView6,gridView7,gridView8,gridView9;
    CategoryAdapter adapter;
    TextView vitamine_btn;
    FrameLayout frame;
    ListView listView;
    ListAdapter listAdapter;
    ArrayList<String> titles;
    ArrayList<Categories> categoriesfrom_api;
    String cat_id;
    TextView tv;
    public static CategoryFragment newInstance(int someInt) {
        CategoryFragment myFragment = new CategoryFragment();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment, container, false);
        Session.forceRTLIfSupported(getActivity());
        frame = (FrameLayout) view.findViewById(R.id.frame);
        listView = (ListView) view.findViewById(R.id.list_items);
        categoriesfrom_api = new ArrayList<>();

        titles = new ArrayList<>();
        titles.add("New");
        titles.add("Vitamins and Supplements");
        titles.add("Trends");
        titles.add("Mum and Baby");
        titles.add("Medicines");
        titles.add("Personal Care and Beauty");
        titles.add("Perfumes and Gifts");
        titles.add("Sale");
        titles.add("Natural and Organic");

        listAdapter = new ListAdapter(getContext(),R.layout.list_items,categoriesfrom_api,this){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                tv = (TextView) view.findViewById(R.id.titles);

                // Set the text color of TextView (ListView Item)


                // Generate ListView Item using TextView
                return view;
            }
        };

        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Log.e("clicke","clicked");

                for (int i = 0; i < listView.getChildCount(); i++) {
                    if(position == i ){
                        listView.getChildAt(i).setBackground(getResources().getDrawable(R.drawable.underlinelistview));
                    }else{
                        listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);

                    }
                }
               // view.setSelected(true);
                CategoryFragmentList categoryFragmentList = new CategoryFragmentList();
                Bundle bundle = new Bundle();
                bundle.putSerializable("categories",categoriesfrom_api.get(position));
                categoryFragmentList.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frame,categoryFragmentList).commit();

            }
        });



        get_categories();



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
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        Log.e("cat",result.toString());
                        try {
                            hud.dismiss();
                            for (int i=0;i<result.size();i++){
                                Categories categories = new Categories(result.get(i).getAsJsonObject(),getContext());
                                categoriesfrom_api.add(categories);
                            }
                            listAdapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }














}
