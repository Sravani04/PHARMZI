//package com.example.yellowsoft.pharmzi;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.ViewFlipper;
//
//import com.google.gson.JsonObject;
//import com.kaopiz.kprogresshud.KProgressHUD;
//import com.koushikdutta.async.future.FutureCallback;
//import com.koushikdutta.ion.Ion;
//
///**
// * Created by yellowsoft on 5/1/18.
// */
//
//public class LoginActivity extends Activity {
//    ViewFlipper viewFlipper;
//    TextView login_btn,signin_btn,fp_btn;
//    EditText email,password,fname,lname,email_text,password_text,phone;
//    LinearLayout login_ll,create_acc_ll;
//    String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//    String write;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.login_screen);
//        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
//        login_btn = (TextView) findViewById(R.id.login_btn);
//        signin_btn = (TextView) findViewById(R.id.signin_btn);
//        email = (EditText) findViewById(R.id.email);
//        password = (EditText) findViewById(R.id.password);
//        login_ll = (LinearLayout) findViewById(R.id.login_ll);
//        create_acc_ll = (LinearLayout) findViewById(R.id.create_acc_ll);
//        fp_btn = (TextView) findViewById(R.id.fp_btn);
//        fname = (EditText) findViewById(R.id.fname);
//        lname = (EditText) findViewById(R.id.lname);
//        email_text = (EditText) findViewById(R.id.email_text);
//        password_text = (EditText) findViewById(R.id.password_text);
//        phone = (EditText) findViewById(R.id.phone);
//
//        login_btn.setTextColor(getResources().getColor(R.color.languagecolor));
//        signin_btn.setTextColor(Color.parseColor("#aa000000"));
//        viewFlipper.setDisplayedChild(0);
//
//        login_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                login_btn.setTextColor(getResources().getColor(R.color.languagecolor));
//                signin_btn.setTextColor(Color.parseColor("#aa000000"));
//                viewFlipper.setDisplayedChild(0);
//            }
//        });
//
//        signin_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signin_btn.setTextColor(getResources().getColor(R.color.languagecolor));
//                login_btn.setTextColor(Color.parseColor("#aa000000"));
//                viewFlipper.setDisplayedChild(1);
//            }
//        });
//
//        login_ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                login();
//
//            }
//        });
//
//        create_acc_ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                register();
//            }
//        });
//
//        fp_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                 fp_dialog();
//            }
//        });
//    }
//
//
//
//
//    public void login(){
//        String email_string = email.getText().toString();
//        String password_string = password.getText().toString();
//        if (email_string.equals("")){
//            Toast.makeText(LoginActivity.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
//            email.requestFocus();
//        }else if (password_string.equals("")){
//            Toast.makeText(LoginActivity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
//            password.requestFocus();
//        }else {
//            final KProgressHUD hud = KProgressHUD.create(LoginActivity.this)
//                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                    .setLabel("Please wait")
//                    .setCancellable(true)
//                    .setMaxProgress(100)
//                    .show();
//            Ion.with(this)
//                    .load(Session.SERVERURL + "login.php")
//                    .setBodyParameter("email", email_string)
//                    .setBodyParameter("password", password_string)
//                    .asJsonObject()
//                    .setCallback(new FutureCallback<JsonObject>() {
//                        @Override
//                        public void onCompleted(Exception e, JsonObject result) {
//                            try {
//                                hud.dismiss();
//                                Log.e("loginresult", result.toString());
//                                if (result.get("status").getAsString().equals("Success")) {
//                                    Session.SetUserId(LoginActivity.this, result.get("member_id").getAsString());
//                                    Log.e("member_id", result.get("member_id").toString());
//                                    //Toast.makeText(LoginActivity.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                    intent.putExtra("id",Session.GetAreaId(LoginActivity.this));
//                                    intent.putExtra("act", "0");
//                                    intent.putExtra("delivery","1");
//                                    Log.e("area",Session.GetAreaId(LoginActivity.this));
//                                    startActivity(intent);
//                                } else {
//                                    Toast.makeText(LoginActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (Exception e1) {
//                                e1.printStackTrace();
//                            }
//
//                        }
//                    });
//        }
//    }
//
//
//    public void register(){
//
//        String fname_string = fname.getText().toString();
//        String lname_string = lname.getText().toString();
//        String email_string = email.getText().toString();
//        String password_string = password.getText().toString();
//        String phone_string = phone.getText().toString();
//        if (fname_string.equals("")){
//            Toast.makeText(LoginActivity.this,"Please Enter Your First Name",Toast.LENGTH_SHORT).show();
//            fname.requestFocus();
//        }else if (lname_string.equals("")){
//            Toast.makeText(LoginActivity.this,"Please Enter Your Last Name",Toast.LENGTH_SHORT).show();
//            lname.requestFocus();
//        }else if (email_string.equals("")){
//            Toast.makeText(LoginActivity.this,"Please Enter Your Email",Toast.LENGTH_SHORT).show();
//            email_text.requestFocus();
//        }else if (password_string.equals("")){
//            Toast.makeText(LoginActivity.this,"Please Enter Your Password",Toast.LENGTH_SHORT).show();
//            password_text.requestFocus();
//        }else if (phone_string.equals("")){
//            Toast.makeText(LoginActivity.this,"Please Enter Your Phone",Toast.LENGTH_SHORT).show();
//            phone.requestFocus();
//        }else {
//            final KProgressHUD hud = KProgressHUD.create(LoginActivity.this)
//                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                    .setLabel("Please wait")
//                    .setCancellable(true)
//                    .setMaxProgress(100)
//                    .show();
//            Ion.with(this)
//                    .load(Session.SERVERURL + "member.php")
//                    .setBodyParameter("fname", fname_string)
//                    .setBodyParameter("lname", lname_string)
//                    .setBodyParameter("email", email_string)
//                    .setBodyParameter("password", password_string)
//                    .setBodyParameter("phone", phone_string)
//                    .asJsonObject()
//                    .setCallback(new FutureCallback<JsonObject>() {
//                        @Override
//                        public void onCompleted(Exception e, JsonObject result) {
//                            try {
//                                hud.dismiss();
//                                Log.e("loginresult", result.toString());
//                                if (result.get("status").getAsString().equals("Success")) {
//                                    Toast.makeText(LoginActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
//                                    viewFlipper.setDisplayedChild(0);
//                                } else {
//                                    Toast.makeText(LoginActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (Exception e1) {
//                                e1.printStackTrace();
//                            }
//
//                        }
//                    });
//        }
//    }
//
//
//    public void fp_dialog(){
//       final AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
//        alert.setTitle("Forgot Password");
//        LinearLayout layout = new LinearLayout(LoginActivity.this);
//        final EditText input = new EditText(LoginActivity.this);
//        input.setSingleLine();
//        input.setHint("Enter Email Address");
//        input.setVerticalScrollBarEnabled(true);
//        input.setBackgroundResource(R.drawable.blue_rounded_corners);
//        input.setPadding(15, 15, 15, 15);
//        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        buttonLayoutParams.setMargins(25, 35, 25, 35);
//        input.setLayoutParams(buttonLayoutParams);
//        layout.setOrientation(LinearLayout.VERTICAL);
//        layout.addView(input);
//
//        alert.setView(layout);
//        alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                write = input.getText().toString();
//                if (input.getText().toString().equals(""))
//                    Toast.makeText(LoginActivity.this, "Please Enter email", Toast.LENGTH_SHORT).show();
//                else if (!input.getText().toString().matches(emailPattern))
//                    Toast.makeText(LoginActivity.this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
//                else
//                    forgot_pass();
//            }
//        });
//        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        AlertDialog dialog = alert.create();
//        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        dialog.show();
//    }
//
//
//    public void forgot_pass(){
//        final KProgressHUD hud = KProgressHUD.create(LoginActivity.this)
//                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("Please wait")
//                .setCancellable(true)
//                .setMaxProgress(100)
//                .show();
//        Ion.with(this)
//                .load(Session.SERVERURL+"forget-password.php")
//                .setBodyParameter("email",write)
//                .asJsonObject()
//                .setCallback(new FutureCallback<JsonObject>() {
//                    @Override
//                    public void onCompleted(Exception e, JsonObject result) {
//                        try {
//                            hud.dismiss();
//                            if (result.get("status").getAsString().equals("Success")){
//                                Toast.makeText(LoginActivity.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
//                                finish();
//                            }else {
//                                Toast.makeText(LoginActivity.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
//                            }
//                        }catch (Exception e1){
//                            e1.printStackTrace();
//                        }
//                    }
//                });
//    }
//
//
//
//
//
//}
