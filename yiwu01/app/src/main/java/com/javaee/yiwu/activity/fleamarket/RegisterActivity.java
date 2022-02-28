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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_re_username, edit_re_password, edit_re_password2, edit_re_qq;
    private TextView tv_bank_to_login;
    private Button bt_re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Bmob.initialize(this, "2d88ebc6a434d6a18843452bc694e0e0");
        edit_re_username = findViewById(R.id.edit_re_username);
        edit_re_password = findViewById(R.id.edit_re_password);
        edit_re_password2 = findViewById(R.id.edit_re_password2);
        edit_re_qq = findViewById(R.id.edit_re_qq);
        tv_bank_to_login = findViewById(R.id.tv_bank_to_login);
        tv_bank_to_login.setOnClickListener(this);
        bt_re = findViewById(R.id.bt_re);
        bt_re.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_re:
                signUp();
                break;
            case R.id.tv_bank_to_login:
                Intent intent_bank_login = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent_bank_login);
                finish();
                break;
        }
    }

    private void signUp() {
        final User user = new User();
        user.setUsername(edit_re_username.getText().toString().trim());
        user.setPassword(edit_re_password.getText().toString().trim());
        user.setQq(edit_re_qq.getText().toString().trim());
        if (edit_re_username.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "用户名为空，请输入用户名", Toast.LENGTH_LONG).show();
        } else if (edit_re_password.getText().toString().trim().equals("") || edit_re_password2.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "密码为空，请输入密码", Toast.LENGTH_LONG).show();
        } else if (!(edit_re_password.getText().toString().trim().equals(edit_re_password2.getText().toString().trim()))) {
            Toast.makeText(getApplicationContext(), "两次输入密码不一样，请重新输入", Toast.LENGTH_LONG).show();
            Log.i("RegisterActivity", "if");
        } else {
            user.signUp(new SaveListener<User>() {
                @Override
                public void done(User user, BmobException e) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            });
            finish();
        }
    }
}