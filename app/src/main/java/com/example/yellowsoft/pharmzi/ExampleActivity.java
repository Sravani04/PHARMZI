package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * Created by yellowsoft on 8/1/18.
 */

public class ExampleActivity extends Activity {
    ViewFlipper viewFlipper;
    TextView login_btn,signin_btn;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example);
        viewFlipper=(ViewFlipper)findViewById(R.id.viewFlipper);
        login_btn=(TextView) findViewById(R.id.login_btn);
        signin_btn=(TextView)findViewById(R.id.signin_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_btn.setTextColor(getResources().getColor(R.color.languagecolor));
                signin_btn.setTextColor(Color.parseColor("#aa000000"));
                viewFlipper.setDisplayedChild(0);
            }
        });

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin_btn.setTextColor(getResources().getColor(R.color.languagecolor));
                login_btn.setTextColor(Color.parseColor("#aa000000"));
                viewFlipper.setDisplayedChild(1);
            }
        });
    }

    public void onBackPressed() {
        if(viewFlipper.getDisplayedChild()==1){
            viewFlipper.setDisplayedChild(0);
        }else{
            finish();
        }
    }
}
