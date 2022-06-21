package com.example.code06.ui.person;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.code06.MainActivity;
import com.example.code06.Mine_Recycleview.Mine;
import com.example.code06.Person_RecycleView.CollectionAdapter;
import com.example.code06.Person_RecycleView.MyCollection;
import com.example.code06.R;
import com.example.code06.SQL.Collection;
import com.example.code06.SQL.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PersonFragment extends Fragment  implements View.OnClickListener {

    private PersonViewModel personViewModel;
    private int RESULT_OK = -1;
    private View view;
    public static ImageView imageView;
    public TextView username;
    public TextView userid;
    private Intent intent;
    public static Uri himguri;
    public static int uid;
    public static String name;
    public List<User> list;
    public static List<MyCollection> ALLlistcollect;
    public static List<MyCollection> listcollect;
    public static RecyclerView pRecyclerView;
    public static RecyclerView.LayoutManager playoutmanager;
    public static CollectionAdapter pAdapter;

    public MyCollection myCollection;
    public User user;
    public String huri;
    public int myaccountnumber;
    public User user1;
    private Intent intent1;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        personViewModel =
                new ViewModelProvider(this).get(PersonViewModel.class);
        view = inflater.inflate(R.layout.fragment_person, container, false);
        initview();
        showCollcetion();
        return view;
    }
    public void selectheadportrait(){
        try {
            /*********************选择图片**************************/
            intent1=new Intent();
            intent1.setType("image/*");
            //action表示intent的类型，可以是查看、删除、发布或其他情况；
            // 选择ACTION_GET_CONTENT，系统可以根据Type类型来调用系统程序选择Type
            //由上，选择图片
            intent1.setAction(Intent.ACTION_PICK);
            //如果第二个参数大于或等于0，那么当用户操作完成后会返回到本程序的onActivityResult方法
            startActivityForResult(intent1,2);
            /*****************************************************/
        }catch (SecurityException e){
            e.printStackTrace();
        }

    }
    public void initview(){
        imageView = view.findViewById(R.id.person_headportrait);
        username = view.findViewById(R.id.person_name);
        userid = view.findViewById(R.id.person_id);


        imageView.setOnClickListener(this);

        listcollect = new ArrayList<>();
        ALLlistcollect = new ArrayList<>();

        pRecyclerView = view.findViewById(R.id.Rv_person);
//      绑定收藏recle分栏界面
        pRecyclerView.setHasFixedSize(true);
//      保留图片adapter的高度宽度
        playoutmanager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        pRecyclerView.setLayoutManager(playoutmanager);

        username.setText(MainActivity.mname);
        userid.setText(MainActivity.UserId);

        if(MainActivity.myh!=null){
            imageView.setImageResource(R.drawable.test02);
        }
        else imageView.setImageResource(R.drawable.test02);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.person_headportrait:
                selectheadportrait();
                break;

            case R.id.person_name:

                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0x2 && resultCode == RESULT_OK) {
            if (data != null) {
                himguri = data.getData();
            }

        }
        imageView.setImageResource(R.drawable.test02);
        user1 = new User();
        user1.sethUri(String.valueOf(himguri));
        user1.update(MainActivity.obj,new UpdateListener() {
            @Override
            public void done(BmobException e) {

            }
        });
    }







    public void showCollcetion(){
//        用来放置adapter的图片（CollectionAdapter）
//        BmobQuery<MyCollection> myCollectionBmobQuery = new BmobQuery<>();
//        myCollectionBmobQuery.addWhereEqualTo("userId",Integer.valueOf(MainActivity.UserId));
//        myCollectionBmobQuery.findObjects(new FindListener<MyCollection>() {
//            @Override
//            public void done(List<MyCollection> list, BmobException e) {
//                if(list!=null){
//                    listcollect = list;
//                    pAdapter = new CollectionAdapter(getActivity(),listcollect);
//                    pRecyclerView.setAdapter(pAdapter);
//                }
//
//            }
//        });

        final String TAG ="MainActivity";
        RequestBody requestBody = new FormBody.Builder()	//(post使用)
                .add("fileList","D:/admfafin/asdfasdf")
                .build();
        String url = "http://47.107.52.7:88/member/photo/collect?current=1&size=99&userId="+MainActivity.Zuseridcode;
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
                    Log.d("JIEGOU",response.message());
//                    if (response.message()==null){
//
//                    }else {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Gson gson = new Gson();            //解析
                                Collection userJson = gson.fromJson(b, Collection.class);
                                Log.d("JIEGOU","1"+userJson.getMsg());
                                if (userJson.getData()==null){}
                                else {
                                    int A = userJson.getCode();
                                    Log.d("00000000", Integer.toString(A));

                                    for (int i = 0; i < userJson.getData().getRecords().size(); i++) {
                                        myCollection = new MyCollection(
                                                1,        //username---userid
                                                Integer.parseInt(userJson.getData().getRecords().get(i).getId()),                          //id-----shareid
//                                        userJson.getData().getRecords().get(i).getPUserId(),
                                                userJson.getData().getRecords().get(i).getId(),     //userid----sharename
                                                userJson.getData().getRecords().get(i).getImageUrlList().get(0),  //图片做头像
                                                Integer.parseInt(userJson.getData().getRecords().get(i).getId()),    //id---iteamid
                                                userJson.getData().getRecords().get(i).getImageUrlList().get(0),  //图片
                                                userJson.getData().getRecords().get(i).getTitle(), //标题
                                                userJson.getData().getRecords().get(i).getContent(), //内容
                                                500
                                        );
                                        listcollect.add(myCollection);
                                    }
                                    pAdapter = new CollectionAdapter(getActivity(), listcollect);
                                    pRecyclerView.setAdapter(pAdapter);
                                }

                            }
                        });


                }

            }
        });
    }
}