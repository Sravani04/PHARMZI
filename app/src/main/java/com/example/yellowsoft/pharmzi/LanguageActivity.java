package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yellowsoft on 4/1/18.
 */

public class LanguageActivity extends Activity {
    TextView english_btn,arabic_btn;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language);
        english_btn = (TextView) findViewById(R.id.english_btn);
        arabic_btn = (TextView) findViewById(R.id.arabic_btn);

        english_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Session.SetLang(LanguageActivity.this,"en");
                Intent intent = new Intent(LanguageActivity.this,CityActivity.class);
                startActivity(intent);
                finish();
            }
        });

        arabic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Session.SetLang(LanguageActivity.this,"ar");
                Intent intent = new Intent(LanguageActivity.this,CityActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
