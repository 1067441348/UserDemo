package com.example.syl.myapplication_test.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.syl.myapplication_test.R;
import com.example.syl.myapplication_test.bean.RedPacket;
import com.example.syl.myapplication_test.ui.RedPacketView;

public class PacketActivity extends AppCompatActivity {
    private RedPacketView view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packet);
        view = (RedPacketView) findViewById(R.id.red_packets_view1);
        view.startRain();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.stopRainNow();
            }
        },3000);

    }
}
