package com.example.syl.myapplication_test.eventbus;

/**
 * Created by SYL on 2017/11/10.
 */

public class FirstEvent {
    private String mMsg;
    public FirstEvent(String msg) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
