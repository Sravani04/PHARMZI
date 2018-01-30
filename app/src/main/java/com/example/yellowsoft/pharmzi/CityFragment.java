package com.example.yellowsoft.pharmzi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 11/1/18.
 */

public class CityFragment extends Fragment {
    TextView select_area,delivery_btn,pickup_btn;
    ArrayList<Areas> areasfrom_api;
    String area_id;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.city_fragment_screen, container, false);
        Session.forceRTLIfSupported(getActivity());
        select_area = (TextView) view.findViewById(R.id.select_area);
        delivery_btn = (TextView) view.findViewById(R.id.delivery_btn);
        pickup_btn = (TextView) view.findViewById(R.id.pickup_btn);
        areasfrom_api = new ArrayList<>();
        select_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = onAreaChoice();
                dialog.show();
            }
        });

        select_area.setHint("Select Area");

        delivery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (select_area.getText().toString().equals("")){
                    Log.e("coming","inside");
                    Toast.makeText(getActivity(),"Please Enter Area",Toast.LENGTH_SHORT).show();
                    select_area.requestFocus();
                }else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("id", area_id);
                    intent.putExtra("act", "0");
                    intent.putExtra("delivery", "0");
                    startActivity(intent);
                }
//                Intent intent = new Intent();
//                intent.putExtra("id",area_id);
//                setResult(RESULT_OK,intent);
//                finish();
            }
        });

        pickup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (select_area.getText().toString().equals("")){
                    Log.e("coming","inside");
                    Toast.makeText(getActivity(),"Please Enter Area",Toast.LENGTH_SHORT).show();
                    select_area.requestFocus();
                }else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("id", area_id);
                    intent.putExtra("act", "0");
                    intent.putExtra("delivery", "0");
                    startActivity(intent);
                }
//                Intent intent = new Intent();
//                intent.putExtra("id",area_id);
//                setResult(RESULT_OK,intent);
//                finish();
            }
        });
        get_areas();
        return view;
    }

    public void get_areas(){
        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(this)
                .load(Session.SERVERURL+"areas.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            hud.dismiss();
                            Log.e("country",result.toString());

                            for (int i = 0; i < result.size(); i++) {
                                Areas area = new Areas(result.get(i).getAsJsonObject(),getActivity(),"0");
                                areasfrom_api.add(area);

                                for (int j = 0; j < result.get(i).getAsJsonObject().get("areas").getAsJsonArray().size(); j++) {

                                    Areas sub_category = new Areas(result.get(i).getAsJsonObject().get("areas").getAsJsonArray().get(j).getAsJsonObject(), getActivity(), "1");
                                    areasfrom_api.add(sub_category);

                                }
                            }

//                            JsonObject jsonObject = result.get(0).getAsJsonObject();
//                            JsonArray jsonArray = jsonObject.get("areas").getAsJsonArray();
//                            Log.e("jsonarray",jsonArray.toString());
//                            for (int i=0;i<jsonArray.size();i++){
//                                Areas area = new Areas(jsonArray.get(i).getAsJsonObject(),CityActivity.this);
//                                areasfrom_api.add(area);
//                            }


                        }catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                });

    }

    public Dialog onAreaChoice() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final CharSequence[] array = new CharSequence[areasfrom_api.size()];
        for(int i=0;i<areasfrom_api.size();i++){

            array[i] = areasfrom_api.get(i).title;
        }

        builder.setTitle("Select Area")
                .setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String selectedItem = array[i].toString();
                        Log.e("select",selectedItem);
                        select_area.setText(selectedItem);
                        area_id = areasfrom_api.get(i).id;
                        Log.e("area_id",area_id);
                        Session.SerAreaId(getActivity(),areasfrom_api.get(i).id);



                    }
                })

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }
}
