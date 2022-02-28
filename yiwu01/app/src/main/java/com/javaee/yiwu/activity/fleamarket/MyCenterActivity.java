package com.javaee.yiwu.activity.fleamarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.javaee.yiwu.R;
import com.javaee.yiwu.until.HttpUtils;
import com.javaee.yiwu.until.ImageDeal;
import com.javaee.yiwu.until.RealPathFromUriUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.bmob.v3.util.The;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyCenterActivity extends AppCompatActivity implements View.OnClickListener {

    Button bianji, quxiao, chongzhi, tixian;
    RadioButton man, woman;
    ImageView touxiang;
    TextView student_number, balance, vip;
    EditText nicheng, tel, dizhi;
    LinearLayout touxiang_layout;

    private Bitmap headImage = null;


    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final int SELECT_PIC = 0;
    private Uri imageUri; //图片路径
    private String filename; //图片名称
    private CharSequence[] its = {"拍照", "从相册选择"};

    //提取本地值
    private String nickname;
    private String name;
    private String image;
    private String address;
    private float Balance;
    private int Vip;
    private int sex;
    private int Uid;
    private String pwd;
    private String Tel;

    String s = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_center);

        s = getIntent().getStringExtra("ss");
        initView();
        student_number.setText(s);

    }

    private void initView() {
        touxiang_layout = findViewById(R.id.touxiang_layout);
        touxiang_layout.setOnClickListener(this);

        student_number = findViewById(R.id.student_number);
        balance = findViewById(R.id.balance);
        vip = findViewById(R.id.vip);
        nicheng = findViewById(R.id.nicheng);
        tel = findViewById(R.id.tel);
        dizhi = findViewById(R.id.dizhi);

        bianji = findViewById(R.id.bianji);
        quxiao = findViewById(R.id.quxiao);
        chongzhi = findViewById(R.id.chongzhi);
        tixian = findViewById(R.id.tixian);
        bianji.setOnClickListener(this);
        quxiao.setOnClickListener(this);
        chongzhi.setOnClickListener(this);
        tixian.setOnClickListener(this);

        man = findViewById(R.id.male_rb);
        woman = findViewById(R.id.famale_rb);

        if (sex == 0) man.setChecked(true);
        else woman.setChecked(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String username = null;
                    String nickname = null;
                    String sex = null;
                    String tel1 = null;
                    String address = null;
                    final String[] name = {null};
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder requestBuild = new FormBody.Builder();
                    Request request = new Request.Builder()
                            .url("http://" + getString(R.string.ip) + "/findAllUser") // url
                            .build();
                    Call call = client.newCall(request);
                    Response response = call.execute();
                    String result = response.body().string();
                    Log.i("123", result);
                    JSONObject result_json = new JSONObject(result);
                    String resultCode = result_json.getString("code");
                    if (resultCode.equals("0")) {
                        //请求成功，获取JSON中的data
                        JSONArray resultJsonArray = result_json.getJSONArray("data");
                        Log.i("123", String.valueOf(resultJsonArray));
                        for (int i = 0; i < resultJsonArray.length(); i++) {
                            username = resultJsonArray.getJSONObject(i).getString("username");
                            if (username.equals(s)) {
                                nickname = resultJsonArray.getJSONObject(i).getString("nickname");
                                tel1 = resultJsonArray.getJSONObject(i).getString("tel");
                                Log.i("123",tel1);
                                address = resultJsonArray.getJSONObject(i).getString("address");
                            }
                        }
                        Handler handler = new Handler(Looper.getMainLooper());
                        String finalusername = username;
                        String finaladdress = address;
                        String finaltel = tel1;
                        String finalnickname = nickname;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                nicheng.setText(finalnickname);
                                tel.setText(finaltel);
                                dizhi.setText(finaladdress);
                            }
                        });
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bianji://编辑
                if (bianji.getText().equals("编辑")) bianji();
                else baocun();
                break;
            case R.id.touxiang_layout://头像
                alertWay();
                break;
            case R.id.quxiao:      //取消
                quxiao();
                break;
            case R.id.chongzhi:      //充值
                chongzhi();
                break;
            case R.id.tixian:      //提现
                tixian();
                break;
            default:
                break;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

        }
    };

    private void alertWay() {
        new AlertDialog.Builder(MyCenterActivity.this)
                .setTitle("更换头像")
                .setItems(its, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0://拍照
                                //图片名称 时间命名
                                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                                Date date = new Date(System.currentTimeMillis());
                                filename = format.format(date);
                                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                                File outputImage = new File(path, filename + ".jpg");
                                try {
                                    if (outputImage.exists()) {
                                        outputImage.delete();
                                    }
                                    outputImage.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //将File对象转换为Uri并启动照相程序
                                imageUri = Uri.fromFile(outputImage);
                                Intent tTntent = new Intent("android.media.action.IMAGE_CAPTURE"); //照相
                                tTntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //指定图片输出地址
                                startActivityForResult(tTntent, TAKE_PHOTO); //启动照相
                                break;
                            case 1://从相册上传
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                startActivityForResult(intent, SELECT_PIC);
                                break;
                        }
                    }
                })
                .create()
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != RESULT_OK)
            return;
        switch (requestCode) {
            case SELECT_PIC://从相册中选
                String path = RealPathFromUriUtils.getRealPathFromUri(this, data.getData());
                File file = new File(path);
                imageUri = Uri.fromFile(file);
                Intent intent1 = new Intent("com.android.camera.action.CROP");
                intent1.setDataAndType(imageUri, "image/*");
                intent1.putExtra("crop", "true");
                intent1.putExtra("aspectX", 1);
                intent1.putExtra("aspectY", 1);
                intent1.putExtra("outputX", 450);
                intent1.putExtra("outputY", 450);
                intent1.putExtra("return-data", false);
                intent1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                intent1.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                intent1.putExtra("noFaceDetection", true);
                startActivityForResult(intent1, CROP_PHOTO);
                break;
            case TAKE_PHOTO://相机
                try {
                    Log.i("123", "123");
                    Intent intent = new Intent("com.android.camera.action.CROP"); //剪裁
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    //设置宽高比例
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    //设置裁剪图片宽高
                    intent.putExtra("outputX", 450);
                    intent.putExtra("outputY", 450);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    Toast.makeText(MyCenterActivity.this, "剪裁图片", Toast.LENGTH_SHORT).show();
                    //广播刷新相册
                    Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intentBc.setData(imageUri);
                    this.sendBroadcast(intentBc);
                    startActivityForResult(intent, CROP_PHOTO); //设置裁剪参数显示图片至ImageView
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case CROP_PHOTO:
                try {
                    //图片解析成Bitmap对象
                    Bitmap bitmap = BitmapFactory.decodeStream(
                            getContentResolver().openInputStream(imageUri));
                    headImage = ImageDeal.toRoundBitmap(bitmap);
                    System.out.println("图片大小为" + headImage.getByteCount() / 1024 +
                            "KB宽度为" + headImage.getHeight() + "高度为：" + headImage.getWidth());
                    updatePicture();
                    touxiang.setImageBitmap(headImage); //将剪裁后照片显示出来
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public void updatePicture()//上传图片
    {
        new Thread() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                Map<String, String> params = new HashMap<String, String>();
                params.put("uid", Integer.toString(Uid));
                params.put("image", ImageDeal.Bitmap2String(headImage));
                String strUrlpath = getResources().getString(R.string.burl) + "SignupAndLoginAction_updateimage.action";
                String Result = HttpUtils.submitPostData(strUrlpath, params, "utf-8");
                SharedPreferences.Editor editor = pref.edit();
                if (!Result.trim().isEmpty() && !Result.trim().equals("-1")) {
                    editor.putString("image", Result.trim());
                    editor.commit();
                }
                Message message = new Message();
                message.what = 0;
                message.obj = Result;
                handler.sendMessage(message);
            }
        }.start();
    }

    private void bianji() {
        bianji.setText("保存");
        quxiao.setVisibility(View.VISIBLE);
        nicheng.setEnabled(true);
        tel.setEnabled(true);
        dizhi.setEnabled(true);
        man.setEnabled(true);
        woman.setEnabled(true);
    }

    public void quxiao()//取消按钮响应事件
    {
        bianji.setText("编辑");
        quxiao.setVisibility(View.INVISIBLE);
        nicheng.setEnabled(false);
        tel.setEnabled(false);
        dizhi.setEnabled(false);
        man.setEnabled(false);
        woman.setEnabled(false);
    }

    private void baocun() {
        if (nicheng.getText().toString().trim().isEmpty()) {
            Toast.makeText(MyCenterActivity.this, "昵称不能为空呀", Toast.LENGTH_SHORT).show();
        } else if (tel.getText().toString().trim().isEmpty()) {
            Toast.makeText(MyCenterActivity.this, "电话号码不对哟", Toast.LENGTH_SHORT).show();
        } else if (dizhi.getText().toString().trim().isEmpty()) {
            Toast.makeText(MyCenterActivity.this, "地址不能隐身哦", Toast.LENGTH_SHORT).show();
        } else {
            if (woman.isChecked()) {
                sex = 1;
            } else {
                sex = 0;
            }
            //添加数据，网络请求
            my_info_save(nicheng.getText().toString(), tel.getText().toString(), dizhi.getText().toString());
            bianji.setText("编辑");
            quxiao.setVisibility(View.INVISIBLE);
            nicheng.setEnabled(false);
            tel.setEnabled(false);
            dizhi.setEnabled(false);
            man.setEnabled(false);
            woman.setEnabled(false);
        }

    }

    private void my_info_save(String nickname, String tel, String address) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("nickname",nickname);
                    jsonObject.put("tel", tel);
                    jsonObject.put("address", address);
                    jsonObject.put("username", s);
                    Log.i("123", nickname + " " + tel + " " + address + " " + s);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), String.valueOf(jsonObject));
                    Request request = new Request.Builder()
                            .url("http://" + getString(R.string.ip) + "/updateUser")
                            .post(requestBody)
                            .build();
                    Call call = client.newCall(request);
                    Response response = call.execute();
                    String result = response.body().string();
                    Log.i("123", result);

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

    //    充值按钮响应事件
    private void chongzhi() {
        final LinearLayout chongzhi_layout = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_chongzhi, null);
        new AlertDialog.Builder(this)
                .setTitle("充值")
                .setView(chongzhi_layout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {//设置确定按钮
                    @Override//处理取消按钮点击事件
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();

    }

    //    体现按钮响应事件
    private void tixian() {

        final LinearLayout chongzhi_layout = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_chongzhi, null);
        new AlertDialog.Builder(this)
                .setTitle("提现")
                .setView(chongzhi_layout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {//设置确定按钮
                    @Override//处理取消按钮点击事件
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();

    }

}