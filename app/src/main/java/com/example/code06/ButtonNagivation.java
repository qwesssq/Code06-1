package com.example.code06;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.code06.Home_Recycleview.Mydynamic;
import com.example.code06.SQL.DataDTO;
import com.example.code06.SQL.File1;
import com.example.code06.SQL.FileUtil;
import com.example.code06.SQL.Login;
import com.example.code06.ui.home.HomeFragment;
import com.example.code06.ui.person.PersonFragment;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Objects;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ButtonNagivation extends AppCompatActivity {
    private ImageView imageView;
    private Intent intent;
    public Array mypath;
    public static String mFilePath;
    public static BmobFile bmobFile;
    public static String imgurl = "1213";
    public static String usridcode1;
    protected static String useridcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_nagivation);
        usridcode1 = this.getIntent().getStringExtra("useridcode");
        Log.d("useridcode", usridcode1);
        Log.d("useridcode111", useridcode);
        Objects.requireNonNull(this.getSupportActionBar()).hide();//隐藏标题栏

        Fresco.initialize(this);
        //路由跳转
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dynamic, R.id.navigation_person)
                .build();
//      路由跳转绑定的按钮（bottom——nav——menv）
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_button_nagivation);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController((BottomNavigationView) findViewById(R.id.nav_view), navController);


        selectPic();
    }

    private void selectPic() {
        imageView = findViewById(R.id.creatPic);//按钮
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*********************选择图片**************************/
                if (ContextCompat.checkSelfPermission(ButtonNagivation.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    intent = new Intent();
                    intent.setType("image/*");
                    //action表示intent的类型，可以是查看、删除、发布或其他情况；
                    // 选择ACTION_GET_CONTENT，系统可以根据Type类型来调用系统程序选择Type
                    //由上，选择图片

                    intent.setAction(Intent.ACTION_PICK);
                    //设定请求码为1
                    startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(ButtonNagivation.this, "请授权手机储存权限！", Toast.LENGTH_LONG).show();
                    //跳转到应用设置界面
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);

                }

                /*****************************************************/
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                HomeFragment.randomnum = (int) (Math.random() * 200 + 100);
                HomeFragment.r = (int) (Math.random() * 1000);
                if (requestCode == 0x1 && resultCode == RESULT_OK) {
                    if (data != null) {
                        HomeFragment.imguri = data.getData();//返回图片路径
                        mFilePath = FileUtil.getPath(this, HomeFragment.imguri);
//                        mFilePath = Uri.decode(data.getDataString());
//                        mFilePath = mFilePath.substring(7, mFilePath.length());
                        try {
                            testOkhttp1();
                        } catch (Exception E) {

                        }

                    }
                }

                break;

        }

    }

    private static final String TAG = "ButtonNavigation";

    void testOkhttp1() throws IOException {
        //上传图片

//        UserId = String.valueOf(UserNameText.getText());
//        mpassword = String.valueOf(PasswordText.getText());
//        请求params
        Log.d("12132123123", String.valueOf(HomeFragment.imguri));

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        File hhh = new File(mFilePath);
        RequestBody fileBody = MultipartBody.create(MediaType.parse("image/jpg"), hhh);
        builder.addFormDataPart("fileList", hhh.getName(), fileBody);
//        RequestBody fileBody = RequestBody.create(new File(String.valueOf(HomeFragment.imguri)), MediaType.parse("imgae/jpg"));
        RequestBody body = builder.build();
//        RequestBody requestBody = new FormBody.Builder()	//(post使用)
//
////                .add("fileList","D:/admfafin/asdfasdf")			//
//                .add("fileList", String.valueOf(fileBody))
//                //
//                .build();					//

        String url = "http://47.107.52.7:88/member/photo/image/upload";    //
        OkHttpClient okHttpClient = new OkHttpClient();            //
        final Request request = new Request.Builder()

                //
                .url(url)
                .addHeader("appId", MainActivity.appId)
                .addHeader("appSecret", MainActivity.appSecret)
                .post(body)
//                .get()	//请求参数加在别的地方
                .build();            //
        Call call = okHttpClient.newCall(request);    //
//        Log.d("12213223",);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("1111111111", e.toString());    //在Logcat中查看日志
//                Toast.makeText(ButtonNagivation.this,"fall",Toast.LENGTH_SHORT).show();
            }

            //请求失败执行
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
//                Log.d(TAG,"onResponse:" + response.code());		//调试使用

                if (true) {
                    Log.d(TAG, response.protocol() + " " + response.code() + " " + response.message());
//                        Toast.makeText(MainActivity.this,this.response.code(),Toast.LENGTH_SHORT).show();
//                        Log.i(TAG, "okHttpPost enqueue: \n onResponse:"+ response.toString() +"\n body:" +response.body().string());
//                        final String http = response.body().string();
//                        Gson gson = new Gson();
//                        String output = gson.toJson(http);
//                        int a = response.code();
//                        test.setText("1213");
//
//                        int a = response.code();
                    final String b = response.body().string();        //完整的body
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();            //解析
                            File1 userJson = gson.fromJson(b, File1.class);    //写一个File类
                            DataDTO userJsonData = userJson.getData();
                            String A = userJson.getCode();

                            imgurl = userJsonData.getImageCode();
                            Log.d("21321", imgurl);
//                            Intent intent2 = new Intent(ButtonNagivation.this,CreateMyPhotoActivity.class);
                            Intent intent2 = new Intent(ButtonNagivation.this, CreateMyPhotoActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("urikey", HomeFragment.imguri);

                            Log.d("213212323", imgurl);
                            intent2.putExtras(bundle);
//                            bundle.putString("IMGCODE",imgurl);

                            intent2.putExtra("IMGCODE", imgurl);
                            startActivity(intent2);


                        }
                    });
                    Gson gson = new Gson();            //解析
                    File1 userJson = gson.fromJson(b, File1.class);    //File类
                    DataDTO userJsonData = userJson.getData();
                    String A = userJson.getCode();
                    String imagecode1 = userJsonData.getImageCode();
                    Log.d("12123132", "b");
                    Log.d("12123132", b);
//                    //进行gson解析
//                    DataDTO imagecode = userJson.getData();
//                    Log.d("55555555",imagecode.getImageCode());

//                        test.setText(Integer.toString(a));
//                        List<Login.JsonArrayBean> list = userJson.getJsonArray();
//                   String test = userJson.getId2();			//获取对应的字段
//                    Login.JsonArrayBean array = userJson.getJsonArray();
//                        test.setText(array.getID());
//                        Intent intent = new Intent(MainActivity.this,ButtonNagivation.class);
//                        startActivity(intent);
                } else {
//                    Toast.makeText(ButtonNagivation.this,"fall",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getImagePath(Uri uri, String seletion) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, seletion, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

}