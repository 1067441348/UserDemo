package com.example.syl.myapplication_test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.syl.myapplication_test.R;

public class GildeActivity extends AppCompatActivity {
    private ImageView img_gilde;
    private String url = "http://wx2.sinaimg.cn/mw690/ac38503ely1fesz8m0ov6j20qo140dix.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gilde);
        initView();
        getPic();
    }

    private void getPic() {
        Glide.with(GildeActivity.this).load(url).into(img_gilde);

    }

    private void initView() {
        img_gilde = (ImageView) findViewById(R.id.img_gilde);
        
    }
    
}
