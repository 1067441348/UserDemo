package com.example.syl.myapplication_test.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syl.myapplication_test.R;
import com.example.syl.myapplication_test.eventbus.FirstEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class EventBusActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_next;
    private TextView tv_get;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_event);
        //注册
        EventBus.getDefault().register(this);
        btn_next = (Button) findViewById(R.id.btn_next);
        tv_get = (TextView) findViewById(R.id.tv_get);
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_next) {
            startActivity(new Intent(EventBusActivity.this, EventBusNextActivity.class));
        }
    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        String msg = "onEventMainThread收到了消息：" + event.getMsg();
        tv_get.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
