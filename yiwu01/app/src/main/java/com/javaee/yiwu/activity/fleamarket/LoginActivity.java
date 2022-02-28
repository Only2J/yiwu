package com.javaee.yiwu.activity.fleamarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.javaee.yiwu.R;
import com.javaee.yiwu.entity.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_username, edit_password;
    private TextView tv_telephone, tv_retrieve, tv_register;
    private Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //第一：默认初始化
//        Bmob.initialize(this, "Your Application ID");
        Bmob.initialize(this, "2d88ebc6a434d6a18843452bc694e0e0");

        edit_username = findViewById(R.id.edit_username);
        edit_password = findViewById(R.id.edit_password);
        tv_telephone = findViewById(R.id.tv_telephone);
        tv_telephone.setOnClickListener(this);
        tv_retrieve = findViewById(R.id.tv_retrieve);
        tv_retrieve.setOnClickListener(this);
        tv_register = findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
        bt_login = findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_telephone:
                Intent intent_tel = new Intent(LoginActivity.this, TeleLoginActivity.class);
                startActivity(intent_tel);
                break;
            case R.id.tv_register:
                Intent intent_register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent_register);
                finish();
                break;
            case R.id.bt_login:
                login();
                break;
            case R.id.tv_retrieve:
                Intent intent_retrieve = new Intent(LoginActivity.this, RetrieveActivity.class);
                startActivity(intent_retrieve);
        }
    }

    private void login() {
        User user = new User();
        String username = edit_username.getText().toString().trim();
        String password = edit_password.getText().toString().trim();
        user.setUsername(username);
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    User user1 = User.getCurrentUser(User.class);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish();
                } else if(edit_username.getText().toString().trim().equals("")||edit_password.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "用户名或密码没有输入", Toast.LENGTH_LONG).show();
                }else {
                    Log.i("LoginActivity", e.getMessage());
                    Toast.makeText(getApplicationContext(), "用户名或密码输入有误", Toast.LENGTH_LONG).show();
                }
            }
        });

//        Intent intent = new Intent();
//
//        intent.setClass(LoginActivity.this,MainActivity.class);
//        Log.i("123", String.valueOf(intent));
//        startActivity(intent);

    }
}