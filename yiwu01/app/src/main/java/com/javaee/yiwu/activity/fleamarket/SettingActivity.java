package com.javaee.yiwu.activity.fleamarket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.javaee.yiwu.R;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout aboutus, version, changepwd;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initview();
    }

    private void initview() {
        aboutus = findViewById(R.id.aboutus);
        version = findViewById(R.id.version);
        changepwd = findViewById(R.id.changepwd);
        logout = findViewById(R.id.logout);
        aboutus.setOnClickListener(this);
        version.setOnClickListener(this);
        changepwd.setOnClickListener(this);
        logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changepwd:      //改密码
                setChangepwd();
                break;
            case R.id.aboutus:      //关于我们
                setAboutus();
                break;
            case R.id.version:      //版本信息
                setVersion();
                break;
            case R.id.logout:      //注销
                setLogout();
                break;
        }

    }


    private void setChangepwd() {
        final LinearLayout changepwd_layout = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_changepwd, null);
        new AlertDialog.Builder(this).
                setTitle("修改密码").
                setView(changepwd_layout).
                setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText oldpwd = changepwd_layout.findViewById(R.id.oldpwd);
                        EditText newpwd = changepwd_layout.findViewById(R.id.newpwd);
                        EditText renewpwd = changepwd_layout.findViewById(R.id.renewpwd);
                        if (oldpwd.getText().toString().trim().isEmpty()||newpwd.getText().toString().trim().isEmpty() || renewpwd.getText().toString().trim().isEmpty()) {
                            Toast.makeText(SettingActivity.this, "有空值，重新输入", Toast.LENGTH_SHORT).show();
                        }else if (!(renewpwd.getText().toString().trim().equals(newpwd.getText().toString().trim()))) {
                            Toast.makeText(SettingActivity.this, "新密码和旧密码不一致", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();

    }

    private void setAboutus() {
        AlertDialog alertDialog = new AlertDialog.Builder(SettingActivity.this).setTitle("关于我们")
                .setMessage("本项目采用前后端分离技术，其中：\n" +
                        "\n" +
                        "app端使用Android Studio 2020.3.1\n" +
                        "后端使用IntelliJ IDEA 2021.2.2\n" +
                        "使用MySQL8.0.12进行数据存储")
                .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();
        alertDialog.show();
    }

    private void setVersion() {
        AlertDialog alert = new AlertDialog.Builder(SettingActivity.this).setTitle("版本信息")
                .setMessage("version：1.0.0\n" +
                        "\n" +
                        "永不言弃队")
                .setPositiveButton("关闭", new DialogInterface.OnClickListener() {//设置确定按钮
                    @Override//处理确定按钮点击事件
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();//对话框关闭。
                    }
                }).create();
        alert.show();
    }

    private void setLogout() {
        AlertDialog alert = new AlertDialog.Builder(SettingActivity.this).setTitle("提示")
                .setMessage("确定要注销么？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//设置确定按钮
                    @Override//处理确定按钮点击事件
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(SettingActivity.this, LoginActivity.class);
                        startActivity(i);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {//设置取消按钮
                    @Override//处理确定按钮点击事件
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();//对话框关闭。
                    }
                }).create();
        alert.show();
    }

}