package com.example.syl.myapplication_test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.syl.myapplication_test.R;

import java.util.List;

/**
 * Created by SYL on 2017/11/9.
 */

public class FriendAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<String> name;

    public FriendAdapter(Context context, List<String> name) {
        this.mInflater = LayoutInflater.from(context);
        this.name = name;
    }

    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int position) {
        return name.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder = null;
        if (convertView == null) {
            holder = new viewHolder();
            convertView = mInflater.inflate(R.layout.item_friend, null);
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.name.setText(name.get(position).toString());
        return convertView;
    }

    public static class viewHolder {
        public TextView name;
    }
}
