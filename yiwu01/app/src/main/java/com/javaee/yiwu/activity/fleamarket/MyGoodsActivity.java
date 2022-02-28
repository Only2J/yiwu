package com.javaee.yiwu.activity.fleamarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.javaee.yiwu.Fragment.GoodsFragment1;
import com.javaee.yiwu.Fragment.GoodsFragment2;
import com.javaee.yiwu.Fragment.GoodsFragment3;
import com.javaee.yiwu.Fragment.GoodsFragment4;
import com.javaee.yiwu.Fragment.GoodsFragment5;
import com.javaee.yiwu.R;

public class MyGoodsActivity extends AppCompatActivity {
    GoodsFragment1 goodsFragment1 = new GoodsFragment1();
    GoodsFragment2 goodsFragment2 = new GoodsFragment2();
    GoodsFragment3 goodsFragment3 = new GoodsFragment3();
    GoodsFragment4 goodsFragment4 = new GoodsFragment4();
    GoodsFragment5 goodsFragment5 = new GoodsFragment5();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_goods);

        LinearLayout check = findViewById(R.id.check);
        LinearLayout sale = findViewById(R.id.sale);
        LinearLayout trade = findViewById(R.id.trade);
        LinearLayout success = findViewById(R.id.success);
        LinearLayout failure = findViewById(R.id.failure);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.setSelected(true);
                sale.setSelected(false);
                trade.setSelected(false);
                success.setSelected(false);
                failure.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.goods_frame,goodsFragment1).commit();
            }
        });
        sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.setSelected(false);
                sale.setSelected(true);
                trade.setSelected(false);
                success.setSelected(false);
                failure.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.goods_frame,goodsFragment2).commit();
            }
        });

        trade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.setSelected(false);
                sale.setSelected(false);
                trade.setSelected(true);
                success.setSelected(false);
                failure.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.goods_frame,goodsFragment3).commit();
            }
        });

        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.setSelected(false);
                sale.setSelected(false);
                trade.setSelected(false);
                success.setSelected(true);
                failure.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.goods_frame,goodsFragment4).commit();
            }
        });

        failure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.setSelected(false);
                sale.setSelected(false);
                trade.setSelected(false);
                success.setSelected(false);
                failure.setSelected(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.goods_frame,goodsFragment5).commit();
            }
        });




    }
}