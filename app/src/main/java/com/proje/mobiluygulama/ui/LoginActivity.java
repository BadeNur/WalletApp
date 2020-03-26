package com.proje.mobiluygulama.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.proje.mobiluygulama.R;

public class LoginActivity extends Activity {

    public TextView tvYeniHesap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvYeniHesap = findViewById(R.id.tvYeniHesap);
        tvYeniHesap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegActivity.class));
                finish();
            }
        });
    }
}
