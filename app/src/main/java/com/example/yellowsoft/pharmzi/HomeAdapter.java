package com.example.yellowsoft.pharmzi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by yellowsoft on 5/1/18.
 */

public class HomeAdapter extends BaseAdapter implements Filterable{
    Context context;
    LayoutInflater inflater;
    PlanetFilter planetFilter;
    ArrayList<Pharmacies> pharmacies;
    ArrayList<Pharmacies> pharmacies_all;

    public HomeAdapter(Context context,ArrayList<Pharmacies> pharmacies){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.pharmacies = pharmacies;
        this.pharmacies_all = pharmacies;
    }
    @Override
    public int getCount() {
        return pharmacies.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public Filter getFilter() {
        if(planetFilter==null)
            planetFilter=new PlanetFilter();
        return planetFilter;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View item_view = inflater.inflate(R.layout.home_screen_items,null);
        ImageView phar_image = (ImageView) item_view.findViewById(R.id.phar_image);
        TextView title = (TextView) item_view.findViewById(R.id.title);
        TextView payment_title= (TextView) item_view.findViewById(R.id.payment_title);
        TextView time = (TextView) item_view.findViewById(R.id.time);
        TextView minimum = (TextView) item_view.findViewById(R.id.minimum);
        Picasso.with(context).load(pharmacies.get(position).image).into(phar_image);
        if (Session.GetLang(context).equals("en")) {
            title.setText(pharmacies.get(position).title);
        }else {
            title.setText(pharmacies.get(position).title_ar);

        }
        payment_title.setText(pharmacies.get(position).payments.get(0).title);
        time.setText(pharmacies.get(position).time);
        minimum.setText(pharmacies.get(position).minimum);
        return item_view;
    }


    private class PlanetFilter extends Filter {
        Boolean clear_all=false;
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
// We implement here the filter logic
            clear_all=false;
            if (constraint == null || constraint.length() == 0) {
                clear_all=true;
// No filter implemented we return all the list
                results.values = pharmacies;
                results.count = pharmacies.size();
            }
            else {
// We perform filtering operation
                List<Pharmacies> nPlanetList = new ArrayList<>();

                for (Pharmacies p : pharmacies_all) {

//Pattern.compile(Pattern.quote(s2), Pattern.CASE_INSENSITIVE).matcher(s1).find();

                    if (Pattern.compile(Pattern.quote(constraint.toString()), Pattern.CASE_INSENSITIVE).matcher(p.title).find())
                        nPlanetList.add(p);
                }

                results.values = nPlanetList;
                results.count = nPlanetList.size();

            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            if (results.count == 0) {
//                restaurants = (ArrayList<Restaurants>) results.values;
                notifyDataSetChanged();
            }
            else if(clear_all){

                pharmacies = pharmacies_all;
                notifyDataSetChanged();
            }


            else {
                pharmacies = (ArrayList<Pharmacies>) results.values;
                notifyDataSetChanged();
            }



        }

    }
}
