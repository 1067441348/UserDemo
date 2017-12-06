package com.example.syl.myapplication_test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.syl.myapplication_test.R;
import com.example.syl.myapplication_test.TestApplication;
import com.example.syl.myapplication_test.bean.Translation;
import com.example.syl.myapplication_test.bean.Translation1;
import com.example.syl.myapplication_test.http.GetRequest_Interface;
import com.example.syl.myapplication_test.http.PostRequest_Interface;
import com.squareup.leakcanary.RefWatcher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SYL on 2017/11/7.
 */

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

      // requestGet();
        requestPost();
    }

    private void requestPost() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        PostRequest_Interface request = retrofit.create(PostRequest_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<Translation1> call = request.getCall("I love you");

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation1>() {

            //请求成功时回调
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                Log.e("shen","response="+response.body().getType());
                Log.e("shen","response="+response.body().getElapsedTime()+"");
                Log.e("shen","response="+response.body().getErrorCode()+"");
                Log.e("shen","response="+response.body().getTranslateResult().get(0).get(0).getSrc());
                Log.e("shen","response="+response.body().getTranslateResult().get(0).get(0).getTgt());


            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation1> call, Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });
    }

    private void requestGet() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<Translation> call = request.getCall();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                // 步骤7：处理返回的数据结果
               Log.e("shen","response="+response.body().getContent().getErrNo());
                Log.e("shen","response="+response.body().getContent().getFrom());
                Log.e("shen","response="+response.body().getContent().getTo());
                Log.e("shen","response="+response.body().getContent().getOut());
                Log.e("shen","response="+response.body().getContent().getVendor());
                Log.e("shen","response="+response.body().getStatus());



            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //监控内存泄漏情况，如果这个 Activity 没有被销毁，logcat 就会打印出如下信息告诉你内存泄漏发生了。
        RefWatcher refWatcher = TestApplication.getRefWatcher(RetrofitActivity.this);
        refWatcher.watch(RetrofitActivity.this);
    }
}

