package com.example.syl.myapplication_test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.syl.myapplication_test.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button btn_register;
    private Button mEmailSignInButton;
    private Button btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    attemptRegister();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        });
        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    attemptLogin();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //退出登录
    private void logout() {
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                Log.e("shen","success");

            }

            @Override
            public void onProgress(int progress, String status) {
                Log.e("shen","========");

            }

            @Override
            public void onError(int code, String message) {
                Log.e("shen","========"+message);

            }
        });
    }
    //登录
    private void attemptLogin() throws HyphenateException {
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();

        EMClient.getInstance().login(email, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("main", "登录聊天服务器成功！");
                startActivity(new Intent(LoginActivity.this, FriendActivity.class));
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登录聊天服务器失败！");
                Log.d("main", message);
            }
        });

    }

    //信息验证
    private void attemptRegister() throws HyphenateException {
        mEmailView.setError(null);
        mPasswordView.setError(null);
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    EMClient.getInstance().createAccount(email, password);//同步方法
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}

