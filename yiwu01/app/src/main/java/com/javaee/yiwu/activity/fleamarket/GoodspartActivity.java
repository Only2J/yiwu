package com.javaee.yiwu.activity.fleamarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.javaee.yiwu.R;
import com.javaee.yiwu.adapter.ProductinfoAdapter;
import com.javaee.yiwu.entity.Productinfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GoodspartActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout moren, renqi, shijian, jiagedi, jiagegao;
    TextView moren_text, renqi_text, shijian_text, jiagedi_text, jiagegao_text;
    //定义颜色值
    private int Black = 0xFF000000;
    private int Red = 0xFFFF0000;

    LinearLayout backlayout;

    RecyclerView recyclerView;

    List<Productinfo.DataDTO> dataDTOList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodspart);
        backlayout = findViewById(R.id.back);
        getdata();
        initview();
    }


    private void initview() {
        moren = findViewById(R.id.moren);
        renqi = findViewById(R.id.renqi);
        shijian = findViewById(R.id.shijian);
        jiagedi = findViewById(R.id.jiagedi);
        jiagegao = findViewById(R.id.jiagegao);
        moren.setOnClickListener(this);
        renqi.setOnClickListener(this);
        shijian.setOnClickListener(this);
        jiagedi.setOnClickListener(this);
        jiagegao.setOnClickListener(this);

        moren_text = findViewById(R.id.moren_text);
        renqi_text = findViewById(R.id.renqi_text);
        shijian_text = findViewById(R.id.shijian_text);
        jiagedi_text = findViewById(R.id.jiagedi_text);
        jiagegao_text = findViewById(R.id.jiagegao_text);

        initstate();

    }

    public void initstate() {
        moren_text.setTextColor(Red);
        renqi_text.setTextColor(Black);
        shijian_text.setTextColor(Black);
        jiagegao_text.setTextColor(Black);
        jiagedi_text.setTextColor(Black);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.moren:
                clearChioce();
                moren_text.setTextColor(Red);
                getdata();
                break;
            case R.id.renqi:
                clearChioce();
                renqi_text.setTextColor(Red);
                getdata();
                break;
            case R.id.shijian:
                clearChioce();
                shijian_text.setTextColor(Red);
                getdata();
                break;
            case R.id.jiagedi:
                clearChioce();
                jiagedi_text.setTextColor(Red);
                getdata();
                break;
            case R.id.jiagegao:
                clearChioce();
                jiagegao_text.setTextColor(Red);
                getdata();
                break;
            default:
                break;
        }
    }

    //建立一个清空选中状态的方法
    public void clearChioce() {
        moren_text.setTextColor(Black);
        renqi_text.setTextColor(Black);
        shijian_text.setTextColor(Black);
        jiagegao_text.setTextColor(Black);
        jiagedi_text.setTextColor(Black);
    }

    private void getdata() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    String word = getIntent().getStringExtra("name");
                    Log.i("123",word);
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://" + getString(R.string.ip) + "/findPartproductInfo/" + word)
                            .build();

                    Call call = client.newCall(request);
                    Response response = call.execute();
                    String result = response.body().string();
                    Log.i("123", result);
                    Gson gson = new Gson();
                    Productinfo productinfo = gson.fromJson(result, Productinfo.class);
                    dataDTOList = productinfo.data;
                    Log.i("123", String.valueOf(dataDTOList));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (dataDTOList.size() == 0){
            backlayout.setVisibility(View.VISIBLE);
        } else {
            recyclerView = findViewById(R.id.recycler_view_part);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            ProductinfoAdapter adapter = new ProductinfoAdapter(this, dataDTOList);
            recyclerView.setAdapter(adapter);
        }

    }


}