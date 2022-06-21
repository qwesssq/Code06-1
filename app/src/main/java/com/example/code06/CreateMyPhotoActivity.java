package com.example.code06;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.code06.Home_Recycleview.Mydynamic;
import com.example.code06.Home_Recycleview.WaterfallAdapter;
import com.example.code06.Person_RecycleView.MyCollection;
import com.example.code06.ui.home.HomeFragment;
import com.example.code06.ui.home.HomeViewModel;
import com.google.gson.Gson;

import androidx.lifecycle.ViewModelProviders;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreateMyPhotoActivity extends AppCompatActivity implements View.OnClickListener{
    public static ImageView addimg;
    public static EditText ettitle;
    public static EditText etdetail;
    public static Button btcancel;
    public static Button btdetermine;
    public static Mydynamic m;
    public static List<Mydynamic> list;
    public Uri uri;
    public static int k=0;
    public static String imagecode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_my_photo);
//        新建图片分享界面
        this.getSupportActionBar().hide();//隐藏标题栏
        initview();
    }
    private void initview(){
        addimg = findViewById(R.id.addmImage);
        ettitle = findViewById(R.id.et_gettitle);
        etdetail = findViewById(R.id.et_getdetail);
        btcancel = findViewById(R.id.bt_cancel);
        btdetermine = findViewById(R.id.bt_determine);

        uri = this.getIntent().getParcelableExtra("urikey");

        addimg.setImageURI(uri);

        btdetermine.setOnClickListener(this);
        btcancel.setOnClickListener(this);
    }


    private static final String TAG ="CreateMyPhotoActivity";
    void testOkhttp151515() throws IOException {
//        String uri1 = this.getIntent().getParcelableExtra("urikey");
        Intent intent = getIntent();
//        try {
//            imagecode = intent.getStringExtra().toString();
//        }catch (Exception E){
//
//        }
        imagecode = intent.getStringExtra("IMGCODE");
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        JSONObject json=new JSONObject();
        try {
            json.put("content",etdetail.getText().toString());
            json.put("id",k);
            json.put("imageCode",imagecode);
            json.put("pUserId",MainActivity.Zuseridcode);
            json.put("title",ettitle.getText().toString());
            Log.d("33333334433",String.valueOf(k));
        }catch (Exception E){
            Log.d("7777777",E.toString());
        }

        Log.d("1955151591",imagecode+"///12121");
//        UserId = String.valueOf(UserNameText.getText());
//        mpassword = String.valueOf(PasswordText.getText());
//        请求params
//        RequestBody requestBody1 = new FormBody.Builder()    //(post使用)
        RequestBody requestBody1 = RequestBody.create(JSON, String.valueOf(json));  //59
//        RequestBody requestBody = new FormBody.Builder()	//(post使用)
//                .add("content",etdetail.getText().toString())         //
//                .add("id","132")
//                .add("imageCode","1534353422415958016")
//                .add("pUserId","1534079468031840256")
//                .add("title",ettitle.getText().toString())
//                .build();
        //intent
        Log.d("1955151591",ettitle.getText().toString()+"///"+etdetail.getText().toString());
        String url = "http://47.107.52.7:88/member/photo/share/add"; //
        OkHttpClient okHttpClient = new OkHttpClient();         //
        final Request request = new Request.Builder()           //
                .url(url)
                .addHeader("Content-Type","application/json")
                .addHeader("appId",MainActivity.appId)
                .addHeader("appSecret",MainActivity.appSecret)
                .post(requestBody1)
//                .get()    //请求参数加在别的地方
                .build();           //
        Call call = okHttpClient.newCall(request);  //

        call.enqueue(new Callback() {       //异步请求
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("8888888888888","onfailure");    //在Logcat中查看日志
                Toast.makeText(CreateMyPhotoActivity.this,"fall",Toast.LENGTH_SHORT).show();
            }
            //请求失败执行
            @Override
            public void onResponse(Call call,final Response response) throws IOException {
//                Log.d(TAG,"onResponse:" + response.code());       //调试使用

//                if (true){
//                    Log.d(TAG, response.protocol() + " " +response.code() + " " + response.message());
////                        Toast.makeText(MainActivity.this,this.response.code(),Toast.LENGTH_SHORT).show();
////                        Log.i(TAG, "okHttpPost enqueue: \n onResponse:"+ response.toString() +"\n body:" +response.body().string());
////                        final String http = response.body().string();
////                        Gson gson = new Gson();
////                        String output = gson.toJson(http);
////                        int a = response.code();
////                        test.setText("1213");
////
                int a = response.code();
                final String b = response.body().string();      //完整的body
                Log.d("8484464864",Integer.toString(a));
                Log.d("8484464864",b);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Gson gson = new Gson();			//解析
//                        Collection userJson = gson.fromJson(b,Collection.class);
//                        int A = userJson.getCode();
//                        Log.d("00000000",Integer.toString(A));
//
//
//                    }
//                });
//                    Gson gson = new Gson();         //解析
//                    File userJson = gson.fromJson(b,File.class);    //写一个File类
//                    //进行gson解析
////                        test.setText(Integer.toString(a));
////                        List<Login.JsonArrayBean> list = userJson.getJsonArray();
//                    String test = userJson.getId1();         //获取对应的字段
//                    Login.JsonArrayBean array = userJson.getJsonArray();
////                        test.setText(array.getID());
////                        Intent intent = new Intent(MainActivity.this,ButtonNagivation.class);
////                        startActivity(intent);
//                }
//                else {
//                    Toast.makeText(MainActivity.this,"fall",Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_determine:
                if(ettitle.getText().toString().equals("")
                        ||etdetail.getText().toString().equals("")){
                    Toast.makeText(CreateMyPhotoActivity.this,"请完善标题或内容",Toast.LENGTH_SHORT).show();
                }
                else {

//                    m = new Mydynamic();
//
//                    m.setSharerId(Integer.valueOf(MainActivity.UserId));
//                    m.setSharerName(MainActivity.mname);
//                    m.sethUri(MainActivity.myh);
//                    m.setImgUri(String.valueOf(uri));
//                    m.setItemId(k);
//                    m.setMytitle(ettitle.getText().toString());
//                    m.setMydetail(etdetail.getText().toString());
//                    m.setImgheight(HomeFragment.randomnum + 350);
//                    m.setNumberOfLikes(0);
//
                    k++;

                    Log.d("1556165145","15454156156");
                    try {
                        testOkhttp151515();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("189151156",e.toString());
                    }
//                    m.save(new SaveListener<String>() {
//                        @Override
//                        public void done(String s, BmobException e) {
//                            HomeFragment.list.add(m);
//                            /****************************显示图片列表***********************************/
//                            HomeFragment.mAdapter = new WaterfallAdapter
//                                    (HomeFragment.mRecyclerView.getContext(), HomeFragment.list);
//                            HomeFragment.mRecyclerView.setAdapter(HomeFragment.mAdapter);
//                            /************************************************************************/
//                        }
//                    });

                    finish();
                }
                break;
            case R.id.bt_cancel:
                finish();
                break;
        }
    }
}