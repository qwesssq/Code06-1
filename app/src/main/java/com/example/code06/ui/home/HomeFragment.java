package com.example.code06.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import com.example.code06.ButtonNagivation;
import com.example.code06.CreateMyPhotoActivity;
import com.example.code06.MainActivity;
import com.example.code06.R;
import com.example.code06.Home_Recycleview.Mydynamic;
import com.example.code06.Home_Recycleview.WaterfallAdapter;
import com.example.code06.SQL.Share;
import com.example.code06.ui.person.PersonFragment;
import com.google.gson.Gson;

import android.content.Intent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class HomeFragment extends Fragment {
    private static String b;
    private static String QQQ;
    private static int RESULT_OK = -1;
    public static RecyclerView.LayoutManager mlayoutmanager;
    public static WaterfallAdapter mAdapter;
    public static View view = null;
    private ImageView imageView,imgdetail;
    public static Uri imguri;
    private Intent intent;
    public  List<Share.data.record> list;
    public static List<Mydynamic> list1;
    public static RecyclerView mRecyclerView;
    public static int randomnum;
    public static int r;
    public static Share sharelist;
    public static List<Share.data.record> records111;
    public static List<Share.data.record> list111;

    private HomeViewModel homeViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView();//部件选择器

//        BmobQuery<Mydynamic> bmobQuery = new BmobQuery<>();
//        bmobQuery.findObjects(new FindListener<Mydynamic>() {
//            @Override
//            public void done(List<Mydynamic> llist, BmobException e) {
//                if(llist!=null){
//                    list = llist;
//                    CreateMyPhotoActivity.k+=llist.size();
//                    /****************************显示图片列表***********************************/
//                    mAdapter = new WaterfallAdapter
//                            (getActivity(), list);
//                    mRecyclerView.setAdapter(mAdapter);
//                    /************************************************************************/
//                }
//
//            }
//        });
//        CreateMyPhotoActivity.k+=records.size();
//        pictuer();

//        change(records111);
//        list = records;
//        if (sharelist == null) {
//            Log.d("222222222", "11111");
//        }else {
//            Log.d("222222222","000000");
//        }

        pictuer();//加载图片
//        mAdapter = new WaterfallAdapter
//                (getActivity(), list);
//        mAdapter.test();
//        mRecyclerView.setAdapter(mAdapter);


//        Thread thread = new Thread("hh");
//                    thread.start();
//        new Thread(thread).start();

        return view;
    }
    void change(List<Share.data.record> list222){
        Log.d("222222222", list222.get(0).getId());
        mAdapter = new WaterfallAdapter
                (getActivity(), list222);
        mAdapter.test();
        mRecyclerView.setVisibility(View.VISIBLE);
        try {
            mRecyclerView.setAdapter(mAdapter);
        }catch (Exception E){
            Log.d("888888888",E.toString());
        }

//        return list111;
    }
    void pictuer(){
        Log.d("useridcode","21231");
//        RequestBody requestBody = new FormBody.Builder()
//
//                .add("current", "0")
//                .add("size", "10")
//                .build();

        String url1 = "http://47.107.52.7:88/member/photo/share?current=0&size=10&userId="+MainActivity.Zuseridcode;
        //         url = url+"?current="+"0"+"&size="+"10"+"&userId="+"1532321653437108224";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url1)
                .addHeader("appId", MainActivity.appId)
                .addHeader("appSecret", MainActivity.appSecret)

                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        List<Share.data.record> records111 = null;          //根据解析的内容写
        call.enqueue(new Callback() {           //异步请求
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("11111111", "onfailure");
//                List<Share.data.record> records111 = null;
//                return records111;
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {


                if (true) {

                    int a = response.code();

                    b = response.body().string();
//                    Log.d("11111111",b);
                    getActivity().runOnUiThread(new Runnable() {        //重新写一个进程
                        @Override
                        public void run() {
                            Log.d("7777777","12123132132");
                            Gson gson = new Gson();
                            Share userJson = gson.fromJson(b, Share.class);//开始解析
                            if (Integer.valueOf(userJson.getData().getTotal())==0){

                            }else {
                                Log.d("KONG",userJson.getData().getTotal());//
                                list = userJson.getData().getRecord();

                                mAdapter = new WaterfallAdapter(getActivity(), list);//调用构造器
                                mAdapter.test();
//                            if (mAdapter == null){
//                                Log.d("22222222","33333333");
//                            }else {
//
//                            }
                                mRecyclerView.setAdapter(mAdapter);//加载waterfalladapter
//                            QQQ = userJson.getCode();
//                            Log.d("7777777",QQQ);
                            }
                        }
                    });

//                    CreateMyPhotoActivity.k+=records.size();
////                    /****************************显示图片列表***********************************/


//                    return records111;

                } else {
//                     Toast.makeText(MainActivity.this,"fall",Toast.LENGTH_SHORT).show();
//                    List<Share.data.record> records111 = null;
//                    return records111;
                }
            }
        });




    }
    private void initView(){
        /*************************控件初始化*****************************/

        imgdetail = view.findViewById(R.id.cd_home_item);

        /*************************************************************/
//        list = new ArrayList<>();
        /**************************设置回收视图、瀑布流布局***************************/
        mRecyclerView = view.findViewById(R.id.Rv_home);
        mRecyclerView.setHasFixedSize(true);
        mlayoutmanager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        页面布局为2列，纵向分布
        mRecyclerView.setLayoutManager(mlayoutmanager);
        /********************************************************************/

    }


//    @Override
//    public void run() {
//        try {
////            pictuer();
//            RequestBody requestBody = new FormBody.Builder()
//
//                    .add("current", "0")
//                    .add("size", "10")
//                    .build();
//
//            String url1 = "http://47.107.52.7:88/member/photo/share?current=0&size=5&userId=1532000707601895424";
//            //         url = url+"?current="+"0"+"&size="+"10"+"&userId="+"1532321653437108224";
//            OkHttpClient okHttpClient = new OkHttpClient();
//            final Request request = new Request.Builder()
//                    .url(url1)
//                    .addHeader("appId", "058253e239dc4a5bb3e09e9b02344f5e")
//                    .addHeader("appSecret", "88912bad6830c207c479abc0e7be9ebe51434")
//
//                    .get()
//                    .build();
//            Call call = okHttpClient.newCall(request);
//            Response response = call.execute();
//            final String c = response.body().string();
//            Log.d("2222211111","9999999999");
//            Gson gson = new Gson();
//            Share userJson = gson.fromJson(c, Share.class);
//            sharelist = userJson;
//            if (b == null){
//                Log.d("2222211111","9999999999");
//            }
//            String code = userJson.getCode();
//////                    String data = userJson.getData().getTotal();
//            Log.d("111111110000000",code);
////                    Log.d("11111111",data);
//            records111 = userJson.getData().getRecord();
//            final List<Share.data.record> s = records111;
//
////                    change(records111);
////            Share.data.record record1 = records111.get(0);
////            String id = records111.get(0).getId();
////
////            Log.d("33333333",id);
////            Log.d("11111111",record1.getImgurl()[0]);
//
//        }catch(Exception E){
//            Log.d("2222211111",E.toString());
//        }
//
//    }
}


