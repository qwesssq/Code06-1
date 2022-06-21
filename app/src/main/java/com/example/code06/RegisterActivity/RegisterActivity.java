package com.example.code06.RegisterActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.code06.MainActivity;
import com.example.code06.R;

import com.example.code06.SQL.User;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;


import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import okhttp3.Call;
import okhttp3.Callback;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText registername;
    public EditText registerpassword;
    public EditText reconfirmpassword;
    public Button bt_register;
    public Button bt_cancel;
    public static int k = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        Bmob.initialize(this, "4b0df4932523d4c7c32a55ccc0c79c62");
        SQLiteStudioService.instance().start(this);
        Objects.requireNonNull(this.getSupportActionBar()).hide();//隐藏标题栏

        inintView();
    }

    public void inintView() {
        registername = findViewById(R.id.inputname);
        registerpassword = findViewById(R.id.inputpassword);
        reconfirmpassword = findViewById(R.id.reconfirmpassword);
        bt_register = findViewById(R.id.zhuche);
        bt_cancel = findViewById(R.id.quxiao);
        bt_register.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if(id==R.id.zhuche){
            String name = registername.getText().toString().trim();//trim去掉字符串两端的多余的空格
            String password = registerpassword.getText().toString().trim();
            String Rpassword = reconfirmpassword.getText().toString().trim();
            if (!TextUtils.isEmpty(name)//三者不为空
                    && !TextUtils.isEmpty(password)
                    && !TextUtils.isEmpty(Rpassword)) {
                if (password.equals(Rpassword)) {//判断两次密码是否相同

                    MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                    JSONObject json = new JSONObject();
                    try {
                        json.put("password", password);
                        json.put("username", name);
                    } catch (Exception E) {
                        Log.d("7777777", E.toString());
                    }
                    RequestBody requestBody1 = RequestBody.create(JSON, String.valueOf(json));  //59-71:封装json数据
                    String url = "http://47.107.52.7:88/member/photo/user/register";
                    OkHttpClient okHttpClient = new OkHttpClient();
                    final Request request = new Request.Builder()
                            .url(url)
                            .addHeader("appId", MainActivity.appId)
                            .addHeader("appSecret", MainActivity.appSecret)
                            .post(requestBody1)
                            .build();
                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
                            Log.d("123123000000000000", response.code() + "yess!!!!");
                        }
                    });


                    final User user = new User();
                    user.setUserId(k);
                    user.setName(name);
                    user.setPassword(password);
                    user.sethUri("content://media/external/images/media/4394459");
                    user.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                            k++;
                        }
                    });
                    Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }else if(id==R.id.quxiao){
            finish();
        }
//        switch (view.getId()) {
//            case R.id.zhuche:
//                /****************************云数据库操作**********************************/
//                String name = registername.getText().toString().trim();//trim去掉字符串两端的多余的空格
//                String password = registerpassword.getText().toString().trim();
//                String Rpassword = reconfirmpassword.getText().toString().trim();
//                if (!TextUtils.isEmpty(name)//三者不为空
//                        && !TextUtils.isEmpty(password)
//                        && !TextUtils.isEmpty(Rpassword)) {
//                    if (password.equals(Rpassword)) {//判断两次密码是否相同
//
//                        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
//                        JSONObject json = new JSONObject();
//                        try {
//                            json.put("password", password);
//                            json.put("username", name);
//                        } catch (Exception E) {
//                            Log.d("7777777", E.toString());
//                        }
//                        RequestBody requestBody1 = RequestBody.create(JSON, String.valueOf(json));  //59-71:封装json数据
//                        String url = "http://47.107.52.7:88/member/photo/user/register";
//                        OkHttpClient okHttpClient = new OkHttpClient();
//                        final Request request = new Request.Builder()
//                                .url(url)
//                                .addHeader("appId", MainActivity.appId)
//                                .addHeader("appSecret", MainActivity.appSecret)
//                                .post(requestBody1)
//                                .build();
//                        Call call = okHttpClient.newCall(request);
//                        call.enqueue(new Callback() {
//                            @Override
//                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                            }
//
//                            @Override
//                            public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
//                                if (true) {
//                                    Log.d("123123000000000000", response.code() + "yess!!!!");
//                                } else {
//                                    Log.d("000000000", "false!!!!!!!!!");
//                                }
//                            }
//                        });
//
//
//                        final User user = new User();
//                        user.setUserId(k);
//                        user.setName(name);
//                        user.setPassword(password);
//                        user.sethUri("content://media/external/images/media/4394459");
//                        user.save(new SaveListener<String>() {
//                            @Override
//                            public void done(String objectId, BmobException e) {
//                                k++;
//                            }
//                        });
//                        Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                }
//
//                /**************************************************************/
//
//                break;
//            case R.id.quxiao:
//                finish();
//                break;
//            default:
//                finish();
//                break;
//
//        }
    }
}