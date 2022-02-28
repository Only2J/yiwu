package com.javaee.yiwu.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.javaee.yiwu.R;
import com.javaee.yiwu.activity.fleamarket.LoginActivity;
import com.javaee.yiwu.activity.fleamarket.MyCenterActivity;
import com.javaee.yiwu.activity.fleamarket.MyGoodsActivity;
import com.javaee.yiwu.activity.fleamarket.MyOrderActivity;
import com.javaee.yiwu.activity.fleamarket.SettingActivity;

public class MyFragment extends Fragment implements View.OnClickListener {

    LinearLayout geren, order, mygoods, setting, mycenter_login_layout;
    TextView nicheng;
    String ss = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ss = getActivity().getIntent().getStringExtra("username");
        Initview();
        nicheng.setText(ss);
    }

    private void Initview() {
        geren = getActivity().findViewById(R.id.geren);
        order = getActivity().findViewById(R.id.order);
        mygoods = getActivity().findViewById(R.id.mygoods);
        setting = getActivity().findViewById(R.id.setting);
        mycenter_login_layout = getActivity().findViewById(R.id.mycenter_login_layout);
        nicheng = getActivity().findViewById(R.id.nicheng);
        geren.setOnClickListener(this);
        order.setOnClickListener(this);
        mygoods.setOnClickListener(this);
        setting.setOnClickListener(this);
        mycenter_login_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.geren:
                Intent intent_geren = new Intent(getActivity(), MyCenterActivity.class);
                intent_geren.putExtra("ss", ss);
                startActivity(intent_geren);
                break;
            case R.id.order:
                Intent intent_order = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(intent_order);
                break;
            case R.id.mygoods:
                Intent intent_goods = new Intent(getActivity(), MyGoodsActivity.class);
                startActivity(intent_goods);
                break;
            case R.id.setting:
                Intent intent_setting = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent_setting);
                break;
            case R.id.mycenter_login_layout:
                Intent intent_login = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent_login);
                break;
            default:
                break;
        }
    }
}