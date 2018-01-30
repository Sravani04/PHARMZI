package com.example.yellowsoft.pharmzi;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

import static android.view.View.*;

/**
 * Created by yellowsoft on 5/1/18.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

//    public List<Category>  categories;

//    public HashMap<Integer,Category> dummyList;

    private Context context;
    CartFragment cartFragment;
    ArrayList<Products> products;
    public class MyViewHolder extends RecyclerView.ViewHolder implements OnLongClickListener,OnClickListener{
        public TextView title, price, qty,full_name,area,plus,minus,date_option,time_option;
        public ImageView img,close,date_cal,clock;
        public LinearLayout item_ll,date,time;
        public MyViewHolder(View view) {
            super(view);
            title=(TextView) view.findViewById(R.id.cart_product_title);
            price=(TextView) view.findViewById(R.id.cart_product_price);
            qty=(TextView) view.findViewById(R.id.cart_product_qty);
            title=(TextView) view.findViewById(R.id.cart_product_title);
            img=(ImageView) view.findViewById(R.id.cart_product_img);
            close=(ImageView) view.findViewById(R.id.cart_close_img);
            time = (LinearLayout) view.findViewById(R.id.time);
            time_option = (TextView) view.findViewById(R.id.time_option);
            clock = (ImageView) view.findViewById(R.id.clock);
            area = (TextView) view.findViewById(R.id.area);
            plus = (TextView) view.findViewById(R.id.plus);
            minus = (TextView) view.findViewById(R.id.minus);
            date_option = (TextView) view.findViewById(R.id.date_option);
            date_cal=(ImageView) view.findViewById(R.id.date_cal);

            date=(LinearLayout) view.findViewById(R.id.date);
            view.setOnClickListener(this);

        }

        @Override
        public boolean onLongClick(View view) {

            title.setBackgroundColor(Color.parseColor("black"));

            return false;
        }

        @Override
        public void onClick(View view) {

        }
    }


    public CartAdapter(Context context,ArrayList<Products> products,CartFragment cartFragment) {
        this.context = context;
        this.cartFragment = cartFragment;
        this.products = products;

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item2, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        try {

            if (Session.GetLang(context).equals("en")) {
                holder.title.setText(products.get(position).title);
            }else {
                holder.title.setText(products.get(position).title_ar);

            }
            holder.price.setText(products.get(position).price);
            Picasso.with(context).load(String.valueOf(products.get(position).images.get(0).image)).into(holder.img);
           // holder.area.setText(products.get(position).pharmacies.get(position).areas.get(0).title);
//            holder.qty.setText(products.get(position).cart_quantity);

            holder.close.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartFragment.remove_cart_data(position);

                }
            });

            holder.date_option.setHint("Select Date");
            holder.time_option.setHint("Select Time");

            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("plus", String.valueOf(products.get(position).cart_quantity));
                    if (products.get(position).cart_quantity < 10){
                        products.get(position).cart_quantity ++;
                        holder.qty.setText(String.valueOf(products.get(position).cart_quantity));
                        cartFragment.calculate_total_price();
                    }
                }
            });

            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("minus", String.valueOf(products.get(position).cart_quantity));
                    if (products.get(position).cart_quantity > 1){
                        products.get(position).cart_quantity--;
                        holder.qty.setText(String.valueOf(products.get(position).cart_quantity));
                        cartFragment.calculate_total_price();
                    }
                }
            });

            holder.date.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar mcurrentDate=Calendar.getInstance();
                    final int mYear = mcurrentDate.get(Calendar.YEAR);
                    final int mMonth = mcurrentDate.get(Calendar.MONTH);
                    final int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog mDatePicker=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                            holder.date_option.setText(selectedday +"-"+(selectedmonth+1) +"-"+selectedyear);
                            cartFragment.date = holder.date_option.getText().toString();
                        }
                    },mYear, mMonth, mDay);
                    mDatePicker.setTitle("Select date");
                    mDatePicker.show();  }
            });

            holder.date_cal.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar mcurrentDate=Calendar.getInstance();
                    final int mYear = mcurrentDate.get(Calendar.YEAR);
                    final int mMonth = mcurrentDate.get(Calendar.MONTH);
                    final int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog mDatePicker=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                            holder.date_option.setText(selectedday +"-"+(selectedmonth+1) +"-"+selectedyear);
                            cartFragment.date = holder.date_option.getText().toString();
                        }
                    },mYear, mMonth, mDay);
                    mDatePicker.setTitle("Select date");
                    mDatePicker.show();  }
            });


            holder.time.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            boolean isPM = (selectedHour >= 12);
                            holder.time_option.setText(String.format("%02d:%02d %s", (selectedHour == 12 || selectedHour == 0) ? 12 : selectedHour % 12, selectedMinute, isPM ? "PM" : "AM"));
                            cartFragment.time = holder.time_option.getText().toString();
                        }

                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();

                }
            });

            holder.clock.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            boolean isPM = (selectedHour >= 12);
                            holder.time_option.setText(String.format("%02d:%02d %s", (selectedHour == 12 || selectedHour == 0) ? 12 : selectedHour % 12, selectedMinute, isPM ? "PM" : "AM"));
                            cartFragment.time = holder.time_option.getText().toString();
                        }

                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();

                }
            });


            //cartFragment.dc.setText(products.get(position).pharmacies.get(position).delivery_charges + "KD");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public class POJO {
        String activityValue;
        String activityValueText;
        String activityEmptyText;
        boolean activityCardEmptyViewVisibility;
        boolean activityCardViewVisibility;
    }
    @Override
    public int getItemCount() {

        return products.size();

    }

}

