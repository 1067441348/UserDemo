package com.example.syl.myapplication_test.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.syl.myapplication_test.R;
import com.example.syl.myapplication_test.adapter.FriendAdapter;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

public class FriendActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_add, btn_refresh,btn_send,btn_get;
    private TextView tv_get;
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
                case 1:
                    String messages = msg.obj.toString();
                    tv_get.setText(messages+"");
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
        //getMessage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMessage();
        Log.e("shen","onResume");
    }

    private void getMessage() {
        EMMessageListener msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {
               // tv_get.setText(messages.get(0).toString());
                Log.e("shen",messages.get(0).getBody()+"");

                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = messages.get(0).getBody();
                handler.sendMessage(msg);
                //收到消息
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
            }
            @Override
            public void onMessageRecalled(List<EMMessage> messages) {
                //消息被撤回
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    private void initView() {
        list = (ListView) findViewById(R.id.list);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_refresh = (Button) findViewById(R.id.btn_refresh);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_get = (Button) findViewById(R.id.btn_get);
        tv_get = (TextView) findViewById(R.id.tv_get);
        btn_add.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btn_get.setOnClickListener(this);
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
        } else if (v == btn_send){

            EMMessage message = EMMessage.createTxtSendMessage("这是一条信息", "13405859633");
            //如果是群聊，设置chattype，默认是单聊
            /*if (chatType == CHATTYPE_GROUP)
                message.setChatType(ChatType.GroupChat);*/
            //发送消息
            EMClient.getInstance().chatManager().sendMessage(message);
        } else if (v == btn_get){
            getMessage();
        }
    }
}
