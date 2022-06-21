package com.example.code06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.code06.Home_Recycleview.Mycomment;
import com.example.code06.RegisterActivity.RegisterActivity;
import com.example.code06.SQL.ALL;
import com.example.code06.SQL.Login;
import com.example.code06.SQL.Picture;
import com.example.code06.SQL.Share;
import com.example.code06.SQL.User;
import com.example.code06.ui.home.HomeFragment;
import com.google.gson.Gson;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;
//import rxhttp.RxHttp;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    public final static String appId = "ad9ad59c8f8842fb91feae1b84a86ead";
    public final static String appId = "26679907f2e146a3a15caf84c77932a1";
    public final static String appSecret = "85719a27a2945592940248c9bdb562ba3d124";
//    public final static String appSecret = "19286d59a813a8c544f5b8c4db327d4813be5";
    boolean flag = true;
    public ImageView PasswordVisibleImage;
    public TextView test;
    public Button LoginButton;
    public static String useridcode;
    public EditText UserNameText;
    public EditText PasswordText;
    public static String UserId;
    public static String mname;
    public static String mpassword;
    public String passwordfromBomb;
    private boolean mflag;
    private User user;
    private ArrayList<User> data;
    public static String obj;
    public static String myh;
    public static String Zuseridcode;
    public static String Zusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//应该在mainActivity中获取所有表的数据,不至于用缓冲
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();//隐藏标题栏
        SQLiteStudioService.instance().start(this);
        Bmob.initialize(this, "4b0df4932523d4c7c32a55ccc0c79c62");

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        initView();

    }

    public void initView() {
        PasswordVisibleImage = findViewById(R.id.PasswordVisibleImage);

        LoginButton = findViewById(R.id.LoginButton);
        UserNameText = findViewById(R.id.UserNameText);
        PasswordText = findViewById(R.id.PasswordText);
//        test = findViewById(R.id.test);

        UserNameText.setHintTextColor(Color.WHITE);
        PasswordText.setHintTextColor(Color.WHITE);

        LoginButton.setOnClickListener(this);


    }

    private static final String TAG = "MainActivity";

    void testOkhttp() throws IOException {

        UserId = String.valueOf(UserNameText.getText());
        Home_ItemActivity.sharename = UserId;
        MainActivity.Zusername = UserId;

        Log.d("MAIN", MainActivity.Zusername + UserId);
        mpassword = String.valueOf(PasswordText.getText());

//        请求params
        RequestBody requestBody = new FormBody.Builder()
                .add("password", mpassword)
                .add("username", UserId)
                .build();
        String url = "http://47.107.52.7:88/member/photo/user/login";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("appId", MainActivity.appId)
                .addHeader("appSecret", MainActivity.appSecret)
                .post(requestBody)
//                .get()
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onfailure");
//                Toast.makeText(MainActivity.this,"fall",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
//                Log.d(TAG,"onResponse:" + response.code());

                Log.d("123123", response.protocol() + " " + response.code() + " " + response.message());
//                        Toast.makeText(MainActivity.this,this.response.code(),Toast.LENGTH_SHORT).show();
//                        Log.i(TAG, "okHttpPost enqueue: \n onResponse:"+ response.toString() +"\n body:" +response.body().string());
//                        final String http = response.body().string();
//                        Gson gson = new Gson();
//                        String output = gson.toJson(http);
//                        int a = response.code();
//                        test.setText("1213");
//
                int a = response.code();
                final String b = Objects.requireNonNull(response.body()).string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Login userJson = gson.fromJson(b, Login.class);
                        //进行gson解析
//                        test.setText(Integer.toString(a));
//                        List<Login.JsonArrayBean> list = userJson.getJsonArray();
                        Login.JsonArrayBean array = userJson.getJsonArray();
//                                test.setText(array.getID());
                        Intent intent = new Intent(MainActivity.this, ButtonNagivation.class);
                        ButtonNagivation.useridcode = array.getID();
                        MainActivity.Zuseridcode = array.getID();
                        Log.d("useidcode", MainActivity.Zuseridcode);
                        intent.putExtra("useridcode", array.getID());
                        startActivity(intent);
                    }
                });

            }
        });
    }

    public void getUserLoginMessage() {
        UserId = String.valueOf(UserNameText.getText());
        mpassword = String.valueOf(PasswordText.getText());

//        if (!TextUtils.isEmpty(UserId) && !TextUtils.isEmpty(mpassword)) {
//            BmobQuery<User> UQuery = new BmobQuery<User>();
//            UQuery.findObjects(new FindListener<User>() {
//                @Override
//                public void done(List<User> list, BmobException e) {
//                    RegisterActivity.k += list.size();
//                    for (int i = 0; i < list.size(); i++) {
//                        User u = list.get(i);
//
//                        if (UserId.equals(String.valueOf(u.getUserId())) &&
//                                u.getPassword().equals(mpassword)
//                        ) {
//                            mflag = true;
//                            mname = u.getName();
//                            myh = u.gethUri();
//                            obj = u.getObjectId();
//                            break;
//                        } else mflag = false;
//                    }
//                    if (mflag) {
//                        Intent intent = new Intent(MainActivity.this, ButtonNagivation.class);
//                        startActivity(intent);
//                        Toast.makeText(MainActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
//                        PasswordText.setText("");
//                    } else Toast.makeText(MainActivity.this, "登录失败！！", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }


    }

    public void onClick(View view) {
//        RxHttp.get()
//                .addHeader()
//                .

        switch (view.getId()) {
            case R.id.LoginButton://登录操作
//                    getUserLoginMessage();
                UserId = String.valueOf(UserNameText.getText());
                mpassword = String.valueOf(PasswordText.getText());
//                Toast.makeText(MainActivity.this,UserId,Toast.LENGTH_SHORT).show();
                try {
                    testOkhttp();
//                        pictuer();
                    Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
//                        Toast.makeText(MainActivity.this,"登录失败！！",Toast.LENGTH_SHORT).show();
                }
//                    Intent intent = new Intent(MainActivity.this,ButtonNagivation.class);
//                    startActivity(intent);
                break;
            case R.id.PasswordVisibleImage://密码是否可见
                flag = !flag;
                if (flag) {
                    PasswordVisibleImage.setImageResource(R.drawable.showpassword);
                    PasswordText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    PasswordVisibleImage.setImageResource(R.drawable.donotdisplaypassword);
                    PasswordText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD |
                            InputType.TYPE_CLASS_TEXT);
                    PasswordText.setTypeface(Typeface.DEFAULT);//使得hint提示不会因为改变文本类型而移动
                }
                break;
            case R.id.register://注册
                Intent registerintent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerintent);
                break;
            case R.id.forgetpassword:
                break;

        }
    }
//    void pictrue(){
//        RxHttp.get("http://47.107.52.7:88/member/demo/login?password=123456&username=fangshicai")
//                .addHeader("appId","46351c5d3fdd44a084167c7186772a2e")
//                .addHeader("appSecret","21671c3772dd71d3340f3ae77355421aa335f")
//                .asString()
//                .subscribe(s->{
//                    Log.d("123456",s);
////                    s.indexOf(1)
//
//                },throwable -> {
//                    Log.d("123456","error");
//                });
//    }

    void pictuer() {
        RequestBody requestBody = new FormBody.Builder()

                .add("current", "0")
                .add("size", "10")
                .build();

        String url1 = "http://47.107.52.7:88/member/photo/share?current=0&size=10&userId=1532321653437108224";
        //         url = url+"?current="+"0"+"&size="+"10"+"&userId="+"1532321653437108224";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url1)
                .addHeader("appId", MainActivity.appId)
                .addHeader("appSecret", MainActivity.appSecret)

                .get()
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onfailure");

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {


                if (true) {

                    int a = response.code();
                    final String b;
                    b = response.body().string();
                    Log.d("11111111", b);

                    Gson gson = new Gson();
                    Share userJson = gson.fromJson(b, Share.class);
                    String code = userJson.getCode();
                    String data = userJson.getData().getTotal();
                    Log.d("11111111", code);
                    Log.d("11111111", data);
                    List<Share.data.record> records = userJson.getData().getRecord();
                    Share.data.record record1 = records.get(0);
                    String id = record1.getId();

                    Log.d("11111111", id);
                    Log.d("11111111", record1.getImgurl()[0]);


                } else {
//                     Toast.makeText(MainActivity.this,"fall",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
