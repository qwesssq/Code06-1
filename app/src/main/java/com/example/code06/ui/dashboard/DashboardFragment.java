package com.example.code06.ui.dashboard;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.code06.MainActivity;
import com.example.code06.Mine_Recycleview.Mine;
import com.example.code06.Mine_Recycleview.MineAdapter;
import com.example.code06.R;
import com.example.code06.Home_Recycleview.Mydynamic;
import com.example.code06.SQL.Collection;
import com.example.code06.SQL.Login;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
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

public class DashboardFragment extends Fragment {
    public RecyclerView m_recyclerView;
    public MineAdapter m_Adapter;
    public RecyclerView.LayoutManager m_layputManger;
    public View view;
    public List<Mydynamic> mydynamicList;
    public List<Mine> m_List;
    public Mine mine;
    public static String a;

    private Handler handler = new Handler();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initView();

        try {
            get_M_dynamic();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return view;
    }
    private void initView(){
        m_recyclerView = view.findViewById(R.id.Rv_mine);
        m_layputManger = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        m_recyclerView.setHasFixedSize(true);
        m_recyclerView.setLayoutManager(m_layputManger);
        m_List = new ArrayList<>();
    }


    private  void get_M_dynamic () throws IOException{

        final String TAG ="MainActivity";
        RequestBody requestBody = new FormBody.Builder()	//(post使用)
                .add("fileList","D:/admfafin/asdfasdf")
                .build();
        String url = "http://47.107.52.7:88/member/photo/share/myself?current=1&size=99&userId="+MainActivity.Zuseridcode;
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("appId",MainActivity.appId)
                .addHeader("appSecret",MainActivity.appSecret)
//                    .post(requestBody)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("1111111111","onfailure");	//在Logcat中查看日志
            }
            //请求失败执行
            @Override
            public void onResponse(Call call,final Response response) throws IOException {
                if (true){
                    Log.d(TAG, response.protocol() + " " +response.code() + " " + response.message());
                    final String b = response.body().string();		//完整的body
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();			//解析
                            Collection userJson = gson.fromJson(b,Collection.class);
                            int A = userJson.getCode();
                            Log.d("00000000",Integer.toString(A));
                            for(int i=0;i<userJson.getData().getRecords().size();i++){
                                mine = new Mine(
                                        Uri.parse(userJson.getData().getRecords().get(i).getImageUrlList().get(0)),
                                        userJson.getData().getRecords().get(i).getTitle(),
                                        userJson.getData().getRecords().get(i).getUsername(),
                                        Uri.parse(userJson.getData().getRecords().get(i).getImageUrlList().get(0))
                                );
                                m_List.add(mine);
                            }
                            show_M_dynamic();
                        }
                    });

                }

            }
        });





    }


//    Runnable   runnableUi=new  Runnable(){
//        @Override
//        public void run() {
//            //在这里写更新UI的操作
//            Log.d("6",a);
//            m_Adapter = new MineAdapter(getActivity(),m_List);
//            m_recyclerView.setAdapter(m_Adapter);
//        }
//
//    };


    private  void show_M_dynamic(){




        m_Adapter = new MineAdapter(getActivity(),m_List);
        m_recyclerView.setAdapter(m_Adapter);
    }




}
