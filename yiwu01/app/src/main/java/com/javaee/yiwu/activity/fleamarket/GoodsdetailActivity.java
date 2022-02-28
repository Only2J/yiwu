package com.javaee.yiwu.activity.fleamarket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.javaee.yiwu.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GoodsdetailActivity extends AppCompatActivity {

    TextView tv_name, tv_price, tv_service, tv_type, tv_describle;
    ImageView imageView;
    Button bt_pinglun, bt_buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetail);
        initview();

        bt_pinglun = findViewById(R.id.bt_pinglun);
        bt_pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("123","pinglum");
                Toast.makeText(getApplicationContext(),"你点击了评论", Toast.LENGTH_SHORT).show();
            }
        });

        bt_buy = findViewById(R.id.bt_buy);
        bt_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("123","goumai");
                Toast.makeText(getApplicationContext(),"你点击了购买", Toast.LENGTH_SHORT).show();
                AlertDialog alertDialog = new AlertDialog.Builder(GoodsdetailActivity.this)
                        .setTitle("确定要购买吗？")
                        .setMessage("支付"+ getIntent().getStringExtra("productprice") + "元")
                        .setPositiveButton("确定购买", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"已支付完成", Toast.LENGTH_SHORT).show();
//                                向数据库中插入数据   卖方账号、图片url、价格、买方账号
                                adddata();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create();
                alertDialog.show();
            }
        });

    }

    private void adddata() {//向数据库中插入数据  卖方账号、商品名称、图片url、描述、价格、买方账号
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    JSONObject jsonObject =new JSONObject();
                    jsonObject.put("productname", getIntent().getStringExtra("productname"));
                    jsonObject.put("productImagesrc", getIntent().getStringExtra("productImagesrc"));
                    jsonObject.put("productDescribe", getIntent().getStringExtra("productDescribe"));
                    jsonObject.put("productprice", getIntent().getStringExtra("productprice"));
                    jsonObject.put("username", getIntent().getStringExtra("username"));

                    Log.i("data", getIntent().getStringExtra("productname"));
                    Log.i("data", getIntent().getStringExtra("productDescribe"));
//                    Log.i("data", getIntent().getStringExtra("ss"));
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), String.valueOf(jsonObject));
//                    Request request = new Request.Builder()
//                            .url()
//                            .build();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    private void initview() {
        tv_name = findViewById(R.id.tv_name);
        tv_price = findViewById(R.id.tv_price);
        tv_service = findViewById(R.id.tv_service);
        tv_type = findViewById(R.id.tv_type);
        tv_describle = findViewById(R.id.tv_describle);
        imageView = findViewById(R.id.imageView);
        tv_name.setText(getIntent().getStringExtra("productname"));
        tv_price.setText("￥" + getIntent().getStringExtra("productprice") + "元");
        tv_service.setText(getIntent().getStringExtra("service"));
        tv_type.setText(getIntent().getStringExtra("productType"));
        tv_describle.setText(getIntent().getStringExtra("productDescribe"));
        Glide.with(findViewById(R.id.imageView)).load(getIntent().getStringExtra("productImagesrc")).into(imageView);
    }
}