package com.javaee.yiwu.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.javaee.yiwu.R;
import com.javaee.yiwu.activity.fleamarket.GoodsdetailActivity;
import com.javaee.yiwu.activity.fleamarket.GoodslistActivity;
import com.javaee.yiwu.activity.fleamarket.GoodsmoreActivity;
import com.javaee.yiwu.activity.fleamarket.GoodspartActivity;
import com.javaee.yiwu.activity.fleamarket.GoodssearchActivity;
import com.javaee.yiwu.activity.fleamarket.GoodstimeActivity;
import com.javaee.yiwu.adapter.ProductinfoAdapter;
import com.javaee.yiwu.entity.Productinfo;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment implements View.OnClickListener {

    Button search;
    EditText searchcontext;
    LinearLayout button1, button2, button3, button4, button5, button6, button7, button8;

    LinearLayout jilayout1, jilayout2, jilayout3, jimore;
    TextView text11, text12, text21, text22, text31, text32;

    private Banner banner;
    private ArrayList<String> list_path;

    List<Productinfo.DataDTO> dataDTOList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getdata();
        initview();
        getdata2();
        RecyclerView recyclerView = getActivity().findViewById(R.id.recycler_view_home);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ProductinfoAdapter adapter = new ProductinfoAdapter(getActivity(), dataDTOList);
        recyclerView.setAdapter(adapter);


    }

    private void initview() {
        search = getActivity().findViewById(R.id.search);
        search.setOnClickListener(this);
        searchcontext = getActivity().findViewById(R.id.searchcontext);

        banner = getActivity().findViewById(R.id.banner);

        button1 = getActivity().findViewById(R.id.button1);
        button2 = getActivity().findViewById(R.id.button2);
        button3 = getActivity().findViewById(R.id.button3);
        button4 = getActivity().findViewById(R.id.button4);
        button5 = getActivity().findViewById(R.id.button5);
        button6 = getActivity().findViewById(R.id.button6);
        button7 = getActivity().findViewById(R.id.button7);
        button8 = getActivity().findViewById(R.id.button8);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);

        jilayout1 = getActivity().findViewById(R.id.jilayout1);
        jilayout2 = getActivity().findViewById(R.id.jilayout2);
        jilayout3 = getActivity().findViewById(R.id.jilayout3);
        jilayout1.setOnClickListener(this);
        jilayout2.setOnClickListener(this);
        jilayout3.setOnClickListener(this);
        jimore = getActivity().findViewById(R.id.jimore);
        jimore.setOnClickListener(this);

        text11 = getActivity().findViewById(R.id.text11);
        text12 = getActivity().findViewById(R.id.text12);
        text21 = getActivity().findViewById(R.id.text21);
        text22 = getActivity().findViewById(R.id.text22);
        text31 = getActivity().findViewById(R.id.text31);
        text32 = getActivity().findViewById(R.id.text32);


        list_path = new ArrayList<>();
        list_path.add("http://r47qmngct.hn-bkt.clouddn.com/banner01.jpeg");
        list_path.add("http://r47qmngct.hn-bkt.clouddn.com/banner02.jpeg");
        list_path.add("http://r47qmngct.hn-bkt.clouddn.com/banner03.jpeg");
        list_path.add("http://r47qmngct.hn-bkt.clouddn.com/banner04.jpeg");
        banner.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new MyLoader());
        banner.setImages(list_path);
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER)
                .start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                if (searchcontext.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getActivity(), "内容不能为空哟", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), GoodssearchActivity.class);
                    intent.putExtra("name", searchcontext.getText().toString().trim());
                    searchcontext.setText("");
                    startActivity(intent);
                }
                break;
            case R.id.button1://全部
                Intent b1intent = new Intent(getActivity(), GoodslistActivity.class);
                b1intent.putExtra("name", "");
                startActivity(b1intent);
                break;
            case R.id.button2://最新发布
                Intent b2intent = new Intent(getActivity(), GoodstimeActivity.class);
                b2intent.putExtra("name", "");
                startActivity(b2intent);
                break;
            case R.id.button3://书籍
                Intent b3intent = new Intent(getActivity(), GoodspartActivity.class);
                b3intent.putExtra("name", "书籍");
                startActivity(b3intent);

                break;
            case R.id.button4://生活百货
                Intent b4intent = new Intent(getActivity(), GoodspartActivity.class);
                b4intent.putExtra("name", "生活百货");
                startActivity(b4intent);
                break;
            case R.id.button5://运动户外
                Intent b5intent = new Intent(getActivity(), GoodspartActivity.class);
                b5intent.putExtra("name", "运动户外");
                startActivity(b5intent);
                break;
            case R.id.button6://手机数码
                Intent b6intent = new Intent(getActivity(), GoodspartActivity.class);
                b6intent.putExtra("name", "手机数码");
                startActivity(b6intent);
                break;
            case R.id.button7://服装首饰
                Intent b7intent = new Intent(getActivity(), GoodspartActivity.class);
                b7intent.putExtra("name", "服装首饰");
                startActivity(b7intent);
                break;
            case R.id.button8://其他
                Intent b8intent = new Intent(getActivity(), GoodspartActivity.class);
                b8intent.putExtra("name", "其他");
                startActivity(b8intent);
                break;
            case R.id.jilayout1://急售1
                Intent intent1 = new Intent(getActivity(), GoodsdetailActivity.class);
                intent1.putExtra("productname",dataDTOList.get(0).productname);
                intent1.putExtra("productprice",dataDTOList.get(0).productprice);
                intent1.putExtra("productImagesrc",dataDTOList.get(0).productImagesrc);
                intent1.putExtra("productType",dataDTOList.get(0).productType);
                intent1.putExtra("productDescribe",dataDTOList.get(0).productDescribe);
                intent1.putExtra("productUploadTime",dataDTOList.get(0).productUploadTime);
                intent1.putExtra("urgent",dataDTOList.get(0).urgent);
                intent1.putExtra("service",dataDTOList.get(0).service);
                startActivity(intent1);
                break;
            case R.id.jilayout2://急售2
                Intent intent2 = new Intent(getActivity(), GoodsdetailActivity.class);
                intent2.putExtra("productname",dataDTOList.get(1).productname);
                intent2.putExtra("productprice",dataDTOList.get(1).productprice);
                intent2.putExtra("productImagesrc",dataDTOList.get(1).productImagesrc);
                intent2.putExtra("productType",dataDTOList.get(1).productType);
                intent2.putExtra("productDescribe",dataDTOList.get(1).productDescribe);
                intent2.putExtra("productUploadTime",dataDTOList.get(1).productUploadTime);
                intent2.putExtra("urgent",dataDTOList.get(1).urgent);
                intent2.putExtra("service",dataDTOList.get(1).service);
                startActivity(intent2);
                break;
            case R.id.jilayout3://急售3
                Intent intent3 = new Intent(getActivity(), GoodsdetailActivity.class);
                intent3.putExtra("productname",dataDTOList.get(2).productname);
                intent3.putExtra("productprice",dataDTOList.get(2).productprice);
                intent3.putExtra("productImagesrc",dataDTOList.get(2).productImagesrc);
                intent3.putExtra("productType",dataDTOList.get(2).productType);
                intent3.putExtra("productDescribe",dataDTOList.get(2).productDescribe);
                intent3.putExtra("productUploadTime",dataDTOList.get(2).productUploadTime);
                intent3.putExtra("urgent",dataDTOList.get(2).urgent);
                intent3.putExtra("service",dataDTOList.get(2).service);
                startActivity(intent3);
                break;
            case R.id.jimore:
                Intent intent_more = new Intent(getActivity(), GoodsmoreActivity.class);
                startActivity(intent_more);
                break;
            default:
                break;

        }
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }

    }

    //    急售
    public void getdata2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://" + getString(R.string.ip) + "/findUrgentproductInfo/true")
                        .build();
                Call call = client.newCall(request);
                try {
                    String result = call.execute().body().string();
                    Gson gson = new Gson();
                    Productinfo productinfo = gson.fromJson(result, Productinfo.class);
                    dataDTOList = productinfo.data;
                    text11.setText("【" + dataDTOList.get(0).productType + "】");
                    text21.setText("【" + dataDTOList.get(1).productType + "】");
                    text31.setText("【" + dataDTOList.get(2).productType + "】");
                    text12.setText(dataDTOList.get(0).productDescribe);
                    text22.setText(dataDTOList.get(1).productDescribe);
                    text32.setText(dataDTOList.get(2).productDescribe);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //    猜你喜欢
    public void getdata() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                //请求数据
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder requestBuild = new FormBody.Builder();
                    Request request = new Request.Builder()
                            .url("http://" + getString(R.string.ip) + "/findAllproductInfo")
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

    }



}