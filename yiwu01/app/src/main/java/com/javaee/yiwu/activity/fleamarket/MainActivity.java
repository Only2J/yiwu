package com.javaee.yiwu.activity.fleamarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.javaee.yiwu.Fragment.FaBuFragment;
import com.javaee.yiwu.Fragment.HomeFragment;
import com.javaee.yiwu.Fragment.LunTanFragment;
import com.javaee.yiwu.Fragment.MessageFragment;
import com.javaee.yiwu.Fragment.MyFragment;
import com.javaee.yiwu.R;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    LunTanFragment lunTanFragment = new LunTanFragment();
    FaBuFragment faBuFragment = new FaBuFragment();
    MessageFragment messageFragment = new MessageFragment();
    MyFragment myFragment = new MyFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout home_layout = findViewById(R.id.home_layout);
        LinearLayout luntan_layout = findViewById(R.id.luntan_layout);
        LinearLayout fabu_layout = findViewById(R.id.fabu_layout);
        LinearLayout message_layout = findViewById(R.id.message_layout);
        LinearLayout my_layout = findViewById(R.id.my_layout);
        home_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_layout.setSelected(true);
                luntan_layout.setSelected(false);
                fabu_layout.setSelected(false);
                message_layout.setSelected(false);
                my_layout.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, homeFragment).commit();
            }
        });

        luntan_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_layout.setSelected(false);
                luntan_layout.setSelected(true);
                fabu_layout.setSelected(false);
                message_layout.setSelected(false);
                my_layout.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, lunTanFragment).commit();
            }
        });

        fabu_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_layout.setSelected(false);
                luntan_layout.setSelected(false);
                fabu_layout.setSelected(true);
                message_layout.setSelected(false);
                my_layout.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, faBuFragment).commit();
            }
        });

        message_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_layout.setSelected(false);
                luntan_layout.setSelected(false);
                fabu_layout.setSelected(false);
                message_layout.setSelected(true);
                my_layout.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, messageFragment).commit();
            }
        });

        my_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_layout.setSelected(false);
                luntan_layout.setSelected(false);
                fabu_layout.setSelected(false);
                message_layout.setSelected(false);
                my_layout.setSelected(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).commit();
            }
        });

        home_layout.setSelected(true);
        luntan_layout.setSelected(false);
        fabu_layout.setSelected(false);
        message_layout.setSelected(false);
        my_layout.setSelected(false);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, homeFragment).commit();
    }

}