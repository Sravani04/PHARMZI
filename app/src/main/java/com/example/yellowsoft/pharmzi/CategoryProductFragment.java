//package com.example.yellowsoft.pharmzi;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ExpandableListView;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.google.gson.JsonArray;
//import com.kaopiz.kprogresshud.KProgressHUD;
//import com.koushikdutta.async.future.FutureCallback;
//import com.koushikdutta.ion.Ion;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * Created by yellowsoft on 12/1/18.
// */
//
//public class CategoryProductFragment extends Fragment {
//    ProductsAdapter expandableListAdapter;
//    ImageView back_btn,my_profile;
//    private static final String ARG_PAGE_NUMBER = "page_number";
//    int position;
//    ExpandableListView expandableListView;
//
//    List<String> expandableListTitle;
//    List<String> expandableListDescription;
//    HashMap<Products, List<Products>> expandableListDetail;
//    HashMap<String, List<String>> expandableListDetail1;
//    HashMap<String,List<String>> expandableListDetail2;
//
//    public ArrayList<Products> productsfrom_api;
//    Categories categories;
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        final View view = inflater.inflate(R.layout.products_fragment_list,container,false);
//        if (getArguments()!=null && getArguments().containsKey("categories")){
//            categories   = (Categories) getArguments().getSerializable("categories");
//            Log.e("phar_",categories.toString());
//        }
//        productsfrom_api = new ArrayList<>();
//        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
//        expandableListAdapter = new ProductsAdapter(getContext(), productsfrom_api,expandableListDetail);
//        expandableListView.setAdapter(expandableListAdapter);
//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getActivity(),
//                        productsfrom_api.get(groupPosition) + " List Expanded.",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        (new Handler()).post(new Runnable() {
//
//            @Override
//            public void run() {
//                expandableListView.setIndicatorBounds(expandableListView.getRight()- 40, expandableListView.getWidth());
//            }
//        });
//
//        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getActivity(),
//                        productsfrom_api.get(groupPosition) + " List Collapsed.",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v,
//                                        int groupPosition, int childPosition, long id) {
//                Toast.makeText(
//                        getActivity(),
//                        productsfrom_api.get(groupPosition)
//                                + " -> "
//                                + expandableListDetail.get(
//                                productsfrom_api.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT
//                ).show();
//                return false;
//            }
//        });
//
//        get_products();
//
//        return view;
//    }
//
//
//
//    public static class ExpandableListDataPump {
//        public static HashMap<String, List<String>> getData() {
//            HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
//
//            List<String> selling = new ArrayList<String>();
//            selling.add("Rocket Salad");
//            selling.add("Caesar Salad");
//
//            List<String> vitamins = new ArrayList<String>();
//            vitamins.add("Rocket Salad");
//            vitamins.add("Caesar Salad");
//
//            List<String> beauty = new ArrayList<String>();
//            beauty.add("Rocket Salad");
//            beauty.add("Caesar Salad");
//
//
//
//
//            expandableListDetail.put("Most Selling", selling);
//            expandableListDetail.put("Vitamins and Supplements", vitamins);
//            expandableListDetail.put("Personal Care and Beauty", beauty);
//
//            return expandableListDetail;
//        }
//
//        public static HashMap<String, List<String>> getDataDescription() {
//            HashMap<String, List<String>> expandableListDetail1 = new HashMap<String, List<String>>();
//
//            List<String> selling = new ArrayList<String>();
//            selling.add("Parmesan Cheese Fresh Mushroom, Sun Dried Tomato, Pine Nut, Balsamic..");
//            selling.add("Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum");
//
//            List<String> vitamins = new ArrayList<String>();
//            vitamins.add("Parmesan Cheese Fresh Mushroom, Sun Dried Tomato, Pine Nut, Balsamic..");
//            vitamins.add("Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum");
//
//            List<String> beauty = new ArrayList<String>();
//            beauty.add("Parmesan Cheese Fresh Mushroom, Sun Dried Tomato, Pine Nut, Balsamic..");
//            beauty.add("Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum");
//
//
//
//            expandableListDetail1.put("Most Selling", selling);
//            expandableListDetail1.put("Vitamins and Supplements", vitamins);
//            expandableListDetail1.put("Personal Care and Beauty", beauty);
//
//            return expandableListDetail1;
//        }
//
//
//
//
//    }
//
//
//    public void get_products(){
//        final KProgressHUD hud = KProgressHUD.create(getContext())
//                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("Please wait")
//                .setCancellable(true)
//                .setMaxProgress(100)
//                .show();
//        Ion.with(getContext())
//                .load(Session.SERVERURL+"products.php")
//                .setBodyParameter("category",categories.id)
//                .asJsonArray()
//                .setCallback(new FutureCallback<JsonArray>() {
//                    @Override
//                    public void onCompleted(Exception e, JsonArray result) {
//                        try {
//                            hud.dismiss();
//                            Log.e("productsres",result.toString());
//                            for (int i=0;i<result.size();i++) {
//                                Products products = new Products(result.get(i).getAsJsonObject(),getContext());
//                                productsfrom_api.add(products);
//
//                            }
//                            expandableListAdapter.notifyDataSetChanged();
//                        }catch (Exception e1){
//                            e1.printStackTrace();
//                        }
//
//                    }
//                });
//    }
//
//
//
//}
//
