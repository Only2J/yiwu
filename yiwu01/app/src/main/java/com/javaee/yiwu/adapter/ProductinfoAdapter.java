package com.javaee.yiwu.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.javaee.yiwu.R;
import com.javaee.yiwu.activity.fleamarket.GoodsdetailActivity;
import com.javaee.yiwu.activity.fleamarket.GoodslistActivity;
import com.javaee.yiwu.entity.Productinfo;

import java.util.List;

public class ProductinfoAdapter extends RecyclerView.Adapter<ProductinfoAdapter.ProductinfoHolder> {

    private List<Productinfo.DataDTO> dataDTOList;
    private Context context;
    private LayoutInflater layoutInflater = null;

    public ProductinfoAdapter(Context context , List<Productinfo.DataDTO> dataDTOList) {
        this.dataDTOList = dataDTOList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public class ProductinfoHolder extends RecyclerView.ViewHolder {
        View productonfoView;
        ImageView product_image;
        TextView product_name;
        TextView product_price;

        public ProductinfoHolder(@NonNull View itemView) {
            super(itemView);
            productonfoView = itemView;
            product_image = itemView.findViewById(R.id.product_image);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
        }
    }

    @NonNull
    @Override
    public ProductinfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =layoutInflater.inflate(R.layout.productinfo_item,parent,false);
        final ProductinfoHolder holder = new ProductinfoHolder(view);
        holder.productonfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Productinfo.DataDTO productinfo = dataDTOList.get(position);
                Toast.makeText(v.getContext(),"你点击了"+productinfo.productname,Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(context, GoodsdetailActivity.class);
                intent.putExtra("productname", productinfo.productname);
                intent.putExtra("productprice", productinfo.productprice);
                intent.putExtra("productImagesrc", productinfo.productImagesrc);
                intent.putExtra("productDescribe", productinfo.productDescribe);
                intent.putExtra("productUploadTime", productinfo.productUploadTime);
                intent.putExtra("urgent", productinfo.urgent);
                intent.putExtra("service", productinfo.service);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductinfoHolder holder, int position) {
        Productinfo.DataDTO dataDTO = dataDTOList.get(position);
        Glide.with(holder.itemView).load(dataDTO.productImagesrc).into(holder.product_image);
        holder.product_name.setText(dataDTO.productname);
        holder.product_price.setText(dataDTO.productprice);
    }

    @Override
    public int getItemCount() {
        Log.i("456", String.valueOf(dataDTOList.size()));
        return dataDTOList.size();
    }
}
