package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.eminayar.panter.DialogType;
import com.eminayar.panter.PanterDialog;
import com.eminayar.panter.interfaces.OnSingleCallbackConfirmListener;
import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import static com.example.yellowsoft.pharmzi.R.id.ifRoom;
import static com.example.yellowsoft.pharmzi.R.id.info;
import static com.example.yellowsoft.pharmzi.R.id.price;

/**
 * Created by yellowsoft on 4/1/18.
 */

public class HomeFragment  extends Fragment{
    ListView listView;
    ViewFlipper viewFlipper;
    TextView pharma_btn;
    LinearLayout search_btn,search_pop;
    HomeAdapter adapter;
    MainActivity mainActivity;
    EditText search_edit;
    ArrayList<Pharmacies> pharmaciesfrom_api;
    ImageView imageView,close_btn,search_image,filter_image;
    TextView apply_btn,newest_option,title_option,distance_option,filter,search,search_text,phar_count;
    LinearLayout title_btn,newest_btn,distance_btn,filter_btn,filter_popup;
    String title,text;

    public static HomeFragment newInstance(int someInt) {
        HomeFragment myFragment = new HomeFragment();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen, container, false);
        Session.forceRTLIfSupported(getActivity());
        mainActivity = (MainActivity) getActivity();
        pharmaciesfrom_api = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.pharmacies_list);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.filter_frame);
        pharma_btn = (TextView) view.findViewById(R.id.pharma_btn);
        filter_btn = (LinearLayout) view.findViewById(R.id.filter_btn);
        search_btn = (LinearLayout) view.findViewById(R.id.search_btn);
        search_pop = (LinearLayout) view.findViewById(R.id.search_pop);
        search_edit = (EditText) view.findViewById(R.id.search_edit);
        search_text = (TextView) view.findViewById(R.id.search_text);
        imageView = (ImageView) view.findViewById(R.id.image);
        apply_btn = (TextView) view.findViewById(R.id.apply_btn);
        title_option = (TextView) view.findViewById(R.id.title_option);
        newest_option = (TextView) view.findViewById(R.id.newest_option);
        title_btn = (LinearLayout) view.findViewById(R.id.title_btn);
        newest_btn = (LinearLayout) view.findViewById(R.id.newest_btn);
        filter_popup = (LinearLayout) view.findViewById(R.id.filter_popup);
        close_btn = (ImageView) view.findViewById(R.id.close_btn);
        search_image = (ImageView) view.findViewById(R.id.search_image);
        filter_image = (ImageView) view.findViewById(R.id.filter_image);
        filter = (TextView) view.findViewById(R.id.filter);
        search = (TextView) view.findViewById(R.id.search);
        phar_count = (TextView) view.findViewById(R.id.phar_count);

        phar_count.setText("");

        adapter = new HomeAdapter(getActivity(),pharmaciesfrom_api);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),ProductsActivity.class);
                intent.putExtra("pharmcies",pharmaciesfrom_api.get(i));
                startActivity(intent);
            }
        });


        filter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.setDisplayedChild(3);
                search_pop.setVisibility(View.GONE);
                filter.setTextColor(getResources().getColor(R.color.languagecolor));
                filter_image.setColorFilter(getResources().getColor(R.color.languagecolor));
                pharma_btn.setTextColor(getResources().getColor(R.color.text_color));
                search.setTextColor(getResources().getColor(R.color.text_color));
                search_image.setColorFilter(getResources().getColor(R.color.text_color));
                if (filter_popup.isShown()) {
                    filter_popup.setVisibility(View.GONE);
                }else {
                    filter_popup.setVisibility(View.VISIBLE);
                }
            }
        });






        pharma_btn.setTextColor(getResources().getColor(R.color.languagecolor));
        search.setTextColor(getResources().getColor(R.color.text_color));
        viewFlipper.setDisplayedChild(1);

        pharma_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter.setTextColor(getResources().getColor(R.color.text_color));
                filter_image.setColorFilter(getResources().getColor(R.color.text_color));
                pharma_btn.setTextColor(getResources().getColor(R.color.languagecolor));
                search.setTextColor(getResources().getColor(R.color.text_color));
                search_image.setColorFilter(getResources().getColor(R.color.text_color));
                viewFlipper.setDisplayedChild(1);
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_edit.setText("");
                filter_popup.setVisibility(View.GONE);
                search.setTextColor(getResources().getColor(R.color.languagecolor));
                search_image.setColorFilter(getResources().getColor(R.color.languagecolor));
                pharma_btn.setTextColor(getResources().getColor(R.color.text_color));
                filter.setTextColor(getResources().getColor(R.color.text_color));
                filter_image.setColorFilter(getResources().getColor(R.color.text_color));

                if (search_pop.isShown()){
                    search_pop.setVisibility(View.GONE);
                }else {
                    search_pop.setVisibility(View.VISIBLE);
                }

                viewFlipper.setDisplayedChild(2);
            }
        });


        search_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text  = search_edit.getText().toString();
                Log.e("text",text);
                search_pop.setVisibility(View.GONE);
                pharmaciesfrom_api.clear();
                adapter.notifyDataSetChanged();
                get_pharmacies();
                Log.e("textt", search_edit.getText().toString());

            }
        });




        search_edit.setText("");

//        search_edit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                if(adapter!=null)
//                    adapter.getFilter().filter(charSequence);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter_popup.setVisibility(View.GONE);
            }
        });

        apply_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    title = title_option.getText().toString();
                    Log.e("title",title);
                    filter_popup.setVisibility(View.GONE);
                    pharmaciesfrom_api.clear();
                    adapter.notifyDataSetChanged();
                    get_pharmacies();
                    Log.e("titl", title_option.getText().toString());

                }
            });


        title_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_dialog();
            }
        });

        newest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newest_dialog();
            }
        });



        title_option.setText("");

        newest_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newest_dialog();
            }
        });

        title_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_dialog();
            }
        });




         if (mainActivity.delivery.equals("0")) {
             get_delivery_pharmacies();
         }else if (mainActivity.delivery.equals("1")){
             get_pharmacies();
         }



        return view;
    }


    public void title_dialog(){
        final String[] titles={"TASC","TDESC"};
        final PanterDialog panterDialog = new PanterDialog(getActivity());
        panterDialog.setHeaderBackground(R.color.languagecolor)
                .setTitle("Select Title range")
                .setNegative("Cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        panterDialog.dismiss();
                    }
                })
                .setPositive("Ok")
                .setDialogType(DialogType.SINGLECHOICE)
                .items(titles, new OnSingleCallbackConfirmListener() {
                    @Override
                    public void onSingleCallbackConfirmed(PanterDialog dialog, int pos, String text) {
                        if (titles[pos].equals("TASC")){
                            title_option.setText("TASC");
                        }else if (titles[pos].equals("TDESC")){
                            title_option.setText("TDESC");
                        }
                    }
                });
        panterDialog.isCancelable(false);
        panterDialog.show();
    }


    public void newest_dialog(){
        final String[] newests={"ASC","DESC"};
        final PanterDialog panterDialog = new PanterDialog(getActivity());
        panterDialog.setHeaderBackground(R.color.languagecolor)
                .setTitle("Select Newest Range")
                .setNegative("Cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        panterDialog.dismiss();
                    }
                })
                .setPositive("Ok")
                .setDialogType(DialogType.SINGLECHOICE)
                .items(newests, new OnSingleCallbackConfirmListener() {
                    @Override
                    public void onSingleCallbackConfirmed(PanterDialog dialog, int pos, String text) {
                        if (newests[pos].equals("ASC")){
                            newest_option.setText("ASC");
                        }else if (newests[pos].equals("DESC")){
                            newest_option.setText("DESC");
                        }
                    }
                });
        panterDialog.isCancelable(false);
        panterDialog.show();
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
                .setBodyParameter("search",text)
                .setBodyParameter("sorting",title)
                .setBodyParameter("newest",newest_option.getText().toString())
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            Log.e("pickuppharm",result.toString());
                            hud.dismiss();
                            for (int i=0;i<result.size();i++) {
                                Pharmacies pharmacies = new Pharmacies(result.get(i).getAsJsonObject(),getContext());
                                pharmaciesfrom_api.add(pharmacies);
                                phar_count.setText(String.valueOf(pharmaciesfrom_api.size() + " OPEN Pharmcies "));
                                Log.e("count",String.valueOf(pharmaciesfrom_api.size()));
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
                .setBodyParameter("search",search_edit.getText().toString())
                .setBodyParameter("sorting",title)
                .setBodyParameter("newest",newest_option.getText().toString())
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            Log.e("homepharmacies",result.toString());
                            hud.dismiss();
                            for (int i=0;i<result.size();i++) {
                                Pharmacies pharmacies = new Pharmacies(result.get(i).getAsJsonObject(),getContext());
                                pharmaciesfrom_api.add(pharmacies);
                                phar_count.setText(String.valueOf(pharmaciesfrom_api.size() + " OPEN Pharmcies "));
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }




}
