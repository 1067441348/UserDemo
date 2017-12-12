package com.example.syl.myapplication_test.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.example.syl.myapplication_test.R;
import com.example.syl.myapplication_test.adapter.ChatAdapter;
import com.example.syl.myapplication_test.bean.Chat;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
    PullToRefreshScrollView pullToRefreshScrollView;
    EditText et_send;
    Button btn_send;
    ChatAdapter adapter;
    RecyclerView recyclerview;
    List<Chat> list;
    List<String> list1;
    Chat chat;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    List<EMMessage> messages = (List<EMMessage>) msg.obj;
                    List<Chat> tlist = new ArrayList<Chat>();
                    chat = new Chat();
                    chat.setContent(messages.get(0).getBody() + "");
                    chat.setType(0);
                    tlist.add(chat);
                    list.addAll(tlist);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initView();
    }

    private void initView() {
        list = new ArrayList<Chat>();
        list1 = new ArrayList<>();
        pullToRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.pulltoscroll_scrollview);
        pullToRefreshScrollView.getLoadingLayoutProxy(true, false).setLastUpdatedLabel("下拉刷新");
        pullToRefreshScrollView.getLoadingLayoutProxy(true, false).setPullLabel("");
        pullToRefreshScrollView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在刷新");
        pullToRefreshScrollView.getLoadingLayoutProxy(true, false).setReleaseLabel("放开以刷新");
        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                new GetDataTask().execute();
            }
        });
        et_send = (EditText) findViewById(R.id.et_send);
        recyclerview = (RecyclerView) findViewById(R.id.list);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
        adapter = new ChatAdapter(R.layout.item_chat, list);
        recyclerview.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMessage();
    }

    private void getMessage() {
        EMMessageListener msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                Message msg = Message.obtain();
                msg.what = 0;
                msg.obj = messages;
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

    @Override
    public void onClick(View v) {
        if (v == btn_send){
            //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
            EMMessage message = EMMessage.createTxtSendMessage(et_send.getText().toString(), getIntent().getStringExtra("user"));
            //发送消息
            EMClient.getInstance().chatManager().sendMessage(message);
            List<Chat> tlist = new ArrayList<Chat>();
            chat = new Chat();
            chat.setContent(et_send.getText().toString());
            chat.setType(1);
            chat.setUser(getIntent().getStringExtra("user"));
            tlist.add(chat);
            list.addAll(tlist);
            adapter.notifyDataSetChanged();
        }
    }
    private class GetDataTask extends AsyncTask<Void, Void, Object> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Object doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Object();
        }

        @Override
        protected void onPostExecute(Object result) {
            pullToRefreshScrollView.onRefreshComplete();
            super.onPostExecute(result);
        }
    }
}
