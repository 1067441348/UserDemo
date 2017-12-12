package com.example.syl.myapplication_test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.syl.myapplication_test.R;
import com.example.syl.myapplication_test.adapter.FriendAdapter;
import com.example.syl.myapplication_test.bean.Chat;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

public class FriendActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_add, btn_refresh;
    private ListView list;
    private List<String> userList;
    private FriendAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    adapter = new FriendAdapter(FriendActivity.this, userList);
                    list.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        initView();
        getFridend();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("shen", "onResume");
    }


    private void initView() {
        list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FriendActivity.this, ChatActivity.class);
                intent.putExtra("user",userList.get(position).toString());
                startActivity(intent);
            }
        });
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_refresh = (Button) findViewById(R.id.btn_refresh);
        btn_add.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);
    }

    //获取列表
    private void getFridend() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    userList = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    Message msg = Message.obtain();
                    msg.what = 0;
                    handler.sendMessage(msg);

                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.e("shen", e.toString());
                }
            }
        }.start();

    }

    @Override
    public void onClick(View v) {
        if (v == btn_add) {
            try {
                EMClient.getInstance().contactManager().acceptInvitation("13405859633");
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        } else if (v == btn_refresh) {
            getFridend();
        }
    }
}
