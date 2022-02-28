package com.javaee.yiwu.Fragment;

import static cn.bmob.v3.Bmob.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.javaee.yiwu.R;
import com.javaee.yiwu.activity.fleamarket.CharitydetailActivity;

public class LunTanFragment extends Fragment implements View.OnClickListener{

    LinearLayout gaoping,ji,gongyi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lun_tan, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gongyi = getActivity().findViewById(R.id.gongyi);
        gaoping = getActivity().findViewById(R.id.gaoping);
        ji = getActivity().findViewById(R.id.ji);
        gongyi.setOnClickListener(this);
        gaoping.setOnClickListener(this);
        ji.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gongyi:
                Intent intent = new Intent(getActivity(), CharitydetailActivity.class);
                startActivity(intent);
                break;
            case R.id.gaoping:
                Toast.makeText(getActivity(),"还在开发中……", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.ji:
                Toast.makeText(getActivity(),"还在开发中", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }
}