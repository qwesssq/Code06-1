package com.example.code06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.code06.Home_Recycleview.CommentAdapter;
import com.example.code06.Home_Recycleview.Mycomment;
import com.example.code06.Home_Recycleview.Mydynamic;
import com.example.code06.Home_Recycleview.WaterfallAdapter;
import com.example.code06.Person_RecycleView.CollectionAdapter;
import com.example.code06.Person_RecycleView.MyCollection;
import com.example.code06.SQL.Iscollect;
import com.example.code06.SQL.Share;
import com.example.code06.ui.person.PersonFragment;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.w3c.dom.Comment;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Home_ItemActivity extends AppCompatActivity implements View.OnClickListener {
    public static ImageView igdetail;
    public TextView tv_item_title;
    public TextView tv_item_detail;
    public TextView tv_item_count;
    public EditText et_mcomment;
    public ImageView ig_collect;
    public ImageView igBack;
    public Button bt_comment;
    public String iscollect1;
    public String ObjectId;
    public String sharerId;
    public String sharerName;
    public static String itemUri;
    public int itemId;
    public String itemtitle;
    public String itemdetail;
    public int itemHeight;
    public Uri hhuri;
    public static boolean flag = true;
    public int j=0;
    public String collectid;
    public String imgid;
    public static List<Mycomment.sdata.pcomment> commentList;
    public static List<Mycomment> ALLcommentList;
    public List<Mydynamic> mlist;
    protected static String sharename;
    public MyCollection myCollection;
    public RecyclerView CRecyclerview;
    public RecyclerView.LayoutManager ClayoutManager;
    public CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_home_item_click_layout);

        initview();
        getitemmessage();

        showcollect();//显示是否收藏
        getcomment();  //获取评论

        this.getSupportActionBar().hide();//隐藏标题栏

//        Showcomments();
//        JudgeCollectionStatus();
//        showcollect();      //显示是否收藏了
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back_home:
                finish();
                break;
            case R.id.Comment:
                /**************************获取评论信息，储存在Mycomment类中***************************/
//                String mcomment = et_mcomment.getText().toString();
//                if (!TextUtils.isEmpty(mcomment)) {
                if(true){
//                    Mycomment c = new Mycomment
//                            (itemId,
//                                    Integer.valueOf(MainActivity.UserId),
//                                    MainActivity.mname,
//                                    mcomment,
//                                    MainActivity.myh);
//                    c.save(new SaveListener<String>() {
//                        @Override
//                        public void done(String s, BmobException e) {
//
//                        }
//                    });
//                    getcomment();
                    putcomment();
                    /************************************************************************************/

                    et_mcomment.setText("");//评论完成后重置输入框
                    /*********************************显示评论***********************************/
//                    commentList.add(c);
//                    commentAdapter = new CommentAdapter(Home_ItemActivity.this, commentList);
//                    CRecyclerview.setAdapter(commentAdapter);
//                    j++;
//                    tv_item_count.setText("共有" + j + "条评论");
                    /*********************************************************************/
                    getcomment();           //重新获取评论
                } else Toast.makeText(Home_ItemActivity.this, "评论不能为空!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ig_home_item_collect:


                if (flag) {//取消收藏
                    ig_collect.setImageResource(R.drawable.shouchang);
                    collect_delete();
//                    BmobQuery<MyCollection> mmm = new BmobQuery<>();
//                    mmm.findObjects(new FindListener<MyCollection>() {
//                        @Override
//                        public void done(List<MyCollection> list, BmobException e) {
//                            for(int i=0;i<list.size();i++){
//                                if(list.get(i).getItemId()==itemId){
//                                    ObjectId = list.get(i).getObjectId();
//                                    break;
//                                }
//                            }
//                            MyCollection m1 = new MyCollection();
//                            m1.setObjectId(ObjectId);
//                            m1.delete(new UpdateListener() {
//                                @Override
//                                public void done(BmobException e) {
//
//                                }
//                            });
//                        }
//                    });


                } else {//收藏
                    ig_collect.setImageResource(R.drawable.dynamic_is_selected);
                    collect_chose();
//                    myCollection = new MyCollection();
//                    myCollection.setUserId(Integer.valueOf(MainActivity.UserId));
//                    myCollection.setSharerId(sharerId);
//                    myCollection.setImgUri(String.valueOf(itemUri));
//
//                    myCollection.setItemId(itemId);
//                    myCollection.setSharerName(sharerName);
//                    myCollection.sethUri(String.valueOf(hhuri));
//                    myCollection.setTitle(itemtitle);
//                    myCollection.setDetail(itemdetail);
//                    myCollection.setImgheight(itemHeight);
//
//
//                    myCollection.save(new SaveListener<String>() {
//                        @Override
//                        public void done(String s, BmobException e) {
//
//                        }
//                    });

                    break;
                }
        }
    }

    private void getcomment(){
        String url1 = "http://47.107.52.7:88/member/photo/comment/first?current=0&shareId="+sharerId+"&size=10";      //chuangzhi
        //         url = url+"?current="+"0"+"&size="+"10"+"&userId="+"1532321653437108224";
        Log.d("shareid",sharerId);
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url1)
                .addHeader("appId", MainActivity.appId)
                .addHeader("appSecret", MainActivity.appSecret)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        List<Share.data.record> records111 = null;
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("11111111", "onfailure");
//                List<Share.data.record> records111 = null;
//                return records111;
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {


                if (true) {
                    String b = response.body().string();
                    Log.d("12312313",b);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            Mycomment json = gson.fromJson(b,Mycomment.class);
//                            Log.d("2131321",json.toString());
                            List<Mycomment.sdata.pcomment> pcomments = json.getData().getRecords();
//                            Log.d("2131321",pcomments.get(0).getContent());
                            commentList = pcomments;
                            commentAdapter = new CommentAdapter(Home_ItemActivity.this, commentList);
                            CRecyclerview.setAdapter(commentAdapter);
                            j = Integer.valueOf(json.getData().getTotal());
                            tv_item_count.setText("共有"+j+"条评论");
//                            imgid = commentList.get(0).getShareId();
                            //图片表示数字
                        }
                    });

                        }
                    }

        });
    }
    private void putcomment(){
        //进行图片评论的上传
        String url = "http://47.107.52.7:88/member/photo/comment/first";
        RequestBody requestBody = new FormBody.Builder()

                .add("content",et_mcomment.getText().toString())
                .add("shareId",sharerId)
                .add("userId",ButtonNagivation.useridcode)
                .add("userName",sharename)
                .build();
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        JSONObject json=new JSONObject();
        try {
            json.put("content",et_mcomment.getText().toString());
            json.put("shareId",sharerId);
            json.put("userId",ButtonNagivation.useridcode);
            json.put("userName",sharename);

            Log.d("33333334433",et_mcomment.getText().toString());
        }catch (Exception E){
            Log.d("7777777",E.toString());
        }
        RequestBody requestBody1 = RequestBody.create(JSON, String.valueOf(json));
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("appId", MainActivity.appId)
                .addHeader("appSecret", MainActivity.appSecret)
                .post(requestBody1)
//                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String B = response.body().string();
                Log.d("21231321",B);
            }
        });
    }
    private void showcollect(){
        //获取是否收藏

        String url = "http://47.107.52.7:88/member/photo/share/detail?shareId="+sharerId+"&userId="+MainActivity.Zuseridcode;
        OkHttpClient okHttpClient = new OkHttpClient();
        Log.d("BIAOQIAN",sharerId+"?"+MainActivity.Zusername);
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("appId", MainActivity.appId)
                .addHeader("appSecret", MainActivity.appSecret)
//                .post(requestBody)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String B = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Iscollect iscollect =gson.fromJson(B,Iscollect.class);
                        iscollect1= iscollect.getData().getHascollect();
                        collectid = iscollect.getData().getCollect();
                        if (iscollect1 == "true"){
                            Log.d("123123123","........");
                            flag = true;
                            ig_collect.setImageResource(R.drawable.dynamic_is_selected);

                        }
                        else {
                            Log.d("123123123","0000000");
                            ig_collect.setImageResource(R.drawable.shouchang);
                            flag = false;
                        }

                    }
                });

            }
        });


    }
    private void collect_chose(){
        Log.d("SHOUCHANG","SHOUCHANG");
        String url1 = "http://47.107.52.7:88/member/photo/collect";
        //         url = url+"?current="+"0"+"&size="+"10"+"&userId="+"1532321653437108224";
        RequestBody requestBody = new FormBody.Builder()

                .add("shareId", sharerId)
                .add("userId",MainActivity.Zuseridcode)
                .build();
        Log.d("shareID",sharerId+"...");
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url1)
                .addHeader("appId", MainActivity.appId)
                .addHeader("appSecret", MainActivity.appSecret)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        List<Share.data.record> records111 = null;
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("11111111", "onfailure");
//                List<Share.data.record> records111 = null;
//                return records111;
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {


                if (true) {
                    String b = response.body().string();
                    Log.d("pppppppb",b);


                }
            }

        });
    }
    private void collect_delete(){
        Log.d("NOSHOUCHANG","NOSHOUCHANG");
        String url1 = "http://47.107.52.7:88/member/photo/collect/cancel";
        //         url = url+"?current="+"0"+"&size="+"10"+"&userId="+"1532321653437108224";
        Log.d("collectid",collectid+"?");
        RequestBody requestBody = new FormBody.Builder()

                .add("collectId",collectid)  //要进行传值

                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url1)
                .addHeader("appId", MainActivity.appId)
                .addHeader("appSecret", MainActivity.appSecret)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        List<Share.data.record> records111 = null;
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("11111111", "onfailure");
//                List<Share.data.record> records111 = null;
//                return records111;
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {


                if (true) {
                    String b = response.body().string();
                    Log.d("ppppppp",b);


                }
            }

        });
    }
    private  void initview(){
        /***********************初始化控件**************************/
        igBack = findViewById(R.id.img_back_home);
        igdetail = findViewById(R.id.ig_myshareimage);

        tv_item_title = findViewById(R.id.tv_home_item_title);
        tv_item_detail = findViewById(R.id.tv_home_item_detail);
        tv_item_count = findViewById(R.id.home_item_commentscount);
        ig_collect = findViewById(R.id.ig_home_item_collect);
        bt_comment = findViewById(R.id.Comment);
        CRecyclerview = findViewById(R.id.rv_home_item_commentlist);
        et_mcomment = findViewById(R.id.home_item_Entercomments);
        //输入评论内容
        /***********************初始化控件**************************/
        /***********************初始化评论列**************************/

        commentList = new ArrayList<>();
        ALLcommentList = new ArrayList<>();

        CRecyclerview.setHasFixedSize(true);
        ClayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        CRecyclerview.setLayoutManager(ClayoutManager);
        /*********************************************************/

    }

    private void getitemmessage(){
        /***********************获取item的信息**************************/
        sharerId = this.getIntent().getStringExtra("sharerIdkey");
        sharerName = this.getIntent().getStringExtra("shareNamekey");
        hhuri = Uri.parse(this.getIntent().getStringExtra("hUrikey"));
        itemId = this.getIntent().getIntExtra("itemidkey",0);
        itemUri=this.getIntent().getStringExtra("imgUrikey");
        itemtitle = this.getIntent().getStringExtra("titlekey");
        itemdetail = this.getIntent().getStringExtra("detailkey");
        itemHeight = this.getIntent().getIntExtra("imgHeight",0);


//        igdetail.setImageURI(itemUri);
        //放置图片
        URL picUrl = null;
        try {
            picUrl = new URL(itemUri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {

            HttpURLConnection conn = (HttpURLConnection) picUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            Bitmap pngBM = BitmapFactory.decodeStream(picUrl.openStream());
            Log.d("123123123123",picUrl.toString());
            igdetail.setImageBitmap(pngBM);
            //            放置图片
//            holder2.sharerh.setImageBitmap(pngBM);
            //            分享者头像
        }catch (Exception R){
            Log.d("123123123123",R.toString());
        }

        tv_item_title.setText(itemtitle);
        tv_item_detail.setText(itemdetail);
        /***********************初始化控件**************************/
    }
    public void JudgeCollectionStatus(){
        BmobQuery<MyCollection> collectionBmobQuery = new BmobQuery<>();
        collectionBmobQuery.findObjects(new FindListener<MyCollection>() {
            @Override
            public void done(List<MyCollection> list, BmobException e) {
                if (list!=null){
                    for(int i=0;i<list.size();i++){
                        if(MainActivity.UserId.equals(String.valueOf(list.get(i).getUserId()))&&
                                itemId==list.get(i).getItemId()
                        ){
                            ig_collect.setImageResource(R.drawable.dynamic_is_selected);
                            break;
                        }
                        else ig_collect.setImageResource(R.drawable.shouchang);
                    }
                }

            }
        });
    }
    public void Showcomments(){

        /***********************从云数据库中根据item号取出评论信息******************************/
//        BmobQuery<Mycomment> commentBmobQuery = new BmobQuery<>();
//        commentBmobQuery.findObjects(new FindListener<Mycomment>() {
//            @Override
//            public void done(List<Mycomment> list, BmobException e) {
//                if(list!=null){
//                    for(int i=0;i<list.size();i++){
//                        if(list.get(i).shareId==itemId){
//                            commentList.add(list.get(i));
//                            j++;
//                        }
//                    }
//                    commentAdapter = new CommentAdapter(Home_ItemActivity.this,commentList);
                    CRecyclerview.setAdapter(commentAdapter);
                    tv_item_count.setText("共有"+j+"条评论");
//                }

//            }
//        });


        /*******************************************************************************/
    }

}