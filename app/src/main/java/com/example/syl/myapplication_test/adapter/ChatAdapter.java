package com.example.syl.myapplication_test.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.syl.myapplication_test.R;
import com.example.syl.myapplication_test.bean.Chat;

import java.util.List;

/**
 * Created by SYL on 2017/12/12.
 */

public class ChatAdapter extends BaseQuickAdapter<Chat,BaseViewHolder>{
    public ChatAdapter(int layoutResId, @Nullable List<Chat> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Chat item) {
        helper.setText(R.id.tv_send,item.getContent())
        .setText(R.id.tv_get,item.getContent());
        if (item.getType() == 0){
            helper.setVisible(R.id.tv_get, false);
        }else if (item.getType() == 1){
            helper.setVisible(R.id.tv_send, false);
        }
    }
}
