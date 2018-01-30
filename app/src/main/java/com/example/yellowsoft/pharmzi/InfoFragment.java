package com.example.yellowsoft.pharmzi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by yellowsoft on 12/1/18.
 */

public class InfoFragment extends Fragment {
    Pharmacies pharmacies;
    TextView area,status,category,hours,order,minimum_order,dc,payment_type;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.info_fragment,container,false);
        Session.forceRTLIfSupported(getActivity());
        if (getArguments()!=null && getArguments().containsKey("pharmcies")){
            pharmacies = (Pharmacies) getArguments().getSerializable("pharmcies");
        }
        area = (TextView) view.findViewById(R.id.area);
        status = (TextView) view.findViewById(R.id.status);
        category = (TextView) view.findViewById(R.id.category);
        hours = (TextView) view.findViewById(R.id.hours);
        order = (TextView) view.findViewById(R.id.order);
        minimum_order = (TextView) view.findViewById(R.id.minimum_order);
        dc = (TextView) view.findViewById(R.id.dc);
        payment_type = (TextView) view.findViewById(R.id.payment_type);

        for (int i=0;i<pharmacies.areas.size();i++){
            area.setText(pharmacies.areas.get(i).title);
        }


        status.setText(pharmacies.current_status);
        hours.setText(pharmacies.hours);
        minimum_order.setText(pharmacies.minimum);
        dc.setText(pharmacies.delivery_charges);
        payment_type.setText(pharmacies.payments.get(0).title);
        return view;
    }
}
