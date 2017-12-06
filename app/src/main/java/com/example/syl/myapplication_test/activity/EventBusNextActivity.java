package com.example.syl.myapplication_test.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syl.myapplication_test.R;
import com.example.syl.myapplication_test.eventbus.FirstEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.example.syl.myapplication_test.R.id.tv_get;

public class EventBusNextActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_post;
    private TextView tv_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_event_next);
        btn_post = (Button) findViewById(R.id.btn_post);
        tv_get = (TextView) findViewById(R.id.tv_get);
        btn_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_post) {
            //传递事件
            EventBus.getDefault().post(new FirstEvent("FirstEvent btn clicked"));
            finish();
        }
    }
}
