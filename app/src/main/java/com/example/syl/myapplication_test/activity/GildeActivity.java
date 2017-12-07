package com.example.syl.myapplication_test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.syl.myapplication_test.R;
import com.squareup.picasso.Picasso;

public class GildeActivity extends AppCompatActivity {
    private ImageView img_gilde,img_picasso;
    private String url = "http://wx2.sinaimg.cn/mw690/ac38503ely1fesz8m0ov6j20qo140dix.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gilde);
        initView();
        getGlide();
        getPicasso();
    }

    private void getPicasso() {
        Picasso.with(GildeActivity.this).
                load(url)
                .placeholder(R.mipmap.ic_launcher)
                .resize(500,500)
                .into(img_picasso);
    }

    private void getGlide() {
        Glide.with(GildeActivity.this)
                .load(url)
                .override(500,500)//尺寸
                .placeholder(R.mipmap.ic_launcher)//加载中
                .error(R.mipmap.ic_launcher)//错误
                .into(img_gilde);

    }

    private void initView() {
        img_gilde = (ImageView) findViewById(R.id.img_gilde);
        img_picasso = (ImageView) findViewById(R.id.img_picasso);
        
    }
    
}
