package com.example.syl.myapplication_test.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.syl.myapplication_test.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_retrofit, btn_hxim, btn_eventbus, btn_gilde, btn_redpacket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_retrofit = (Button) findViewById(R.id.btn_retrofit);
        btn_hxim = (Button) findViewById(R.id.btn_hxim);
        btn_eventbus = (Button) findViewById(R.id.btn_eventbus);
        btn_gilde = (Button) findViewById(R.id.btn_gilde);
        btn_redpacket = (Button) findViewById(R.id.btn_redpacket);
        btn_retrofit.setOnClickListener(this);
        btn_hxim.setOnClickListener(this);
        btn_eventbus.setOnClickListener(this);
        btn_gilde.setOnClickListener(this);
        btn_redpacket.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_retrofit:
                startActivity(new Intent(MainActivity.this, RetrofitActivity.class));
                break;
            case R.id.btn_hxim:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.btn_eventbus:
                startActivity(new Intent(MainActivity.this, EventBusActivity.class));
                break;
            case R.id.btn_gilde:
                startActivity(new Intent(MainActivity.this, GildeActivity.class));
                break;
            case R.id.btn_redpacket:
                startActivity(new Intent(MainActivity.this, PacketActivity.class));
                break;
        }
    }
}
