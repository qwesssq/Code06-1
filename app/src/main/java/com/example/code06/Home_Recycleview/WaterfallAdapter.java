 package com.example.code06.Home_Recycleview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.code06.Home_ItemActivity;
import com.example.code06.MainActivity;
import com.example.code06.R;
import com.example.code06.SQL.Login;
import com.example.code06.SQL.Picture;
import com.example.code06.SQL.Share;
import com.google.gson.Gson;

import android.content.Intent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

 public class WaterfallAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public  Context mContext;
    public List<Share.data.record> mdata;

    public WaterfallAdapter(Context mContext, List<Share.data.record> mdata){
        this.mContext=mContext;
        this.mdata=mdata;///所有图片信息
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview_home_item,null);
//        界面的adapter绑定的页面分布
        final MyViewHolder holder = new MyViewHolder(view);
        Log.d("000000000","123123123");
        return new MyViewHolder(view);


    }

    public void test(){

         String a = mdata.get(0).getId();
        Log.d("qqqqqqqqq",a);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //绑定数据
        final  MyViewHolder holder2 = (MyViewHolder) holder;
        holder2.setIsRecyclable(false);
        final Share.data.record mydynamic = mdata.get(position);
        //获取单个图片

        final boolean[] flag = {false};


        final int[] count = {0};
        if(Uri.parse(mydynamic.getImgurl()[0])!=null&&mydynamic.getImgurl()[0]!=null){
//        if (true){

            Log.d("000000000","123123123");

            holder2.mImage.setImageURI(Uri.parse(mydynamic.getImgurl()[0]));////
//            picUrl = new URL(getIntent().getExtras().getString("map_url"));
            URL picUrl = null;
            try {
                picUrl = new URL(mydynamic.getImgurl()[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {

                HttpURLConnection conn = (HttpURLConnection) picUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                Bitmap pngBM = BitmapFactory.decodeStream(picUrl.openStream());//网络加载图片
                Log.d("123123123123",picUrl.toString());
                holder2.mImage.setImageBitmap(pngBM);
                //            放置图片
//                holder2.sharerh.setImageBitmap(pngBM);
                //            分享者头像
            }catch (Exception R){
                Log.d("123123123123",R.toString());
            }
            holder2.sharerh.setImageResource(R.drawable.test02);//头像写死
//            Bitmap pngBM = BitmapFactory.decodeStream();
            Log.d("555555",Uri.parse(mydynamic.getImgurl()[0]).toString());
//            holder2.testtext.setText("2121231");
            holder2.mImage.getLayoutParams().height = 800;
//          设置每个图片的高度
            holder2.mTitle.setText(mydynamic.getTitle());
//            标题
            int num = 0;
            if (mydynamic.getLikenum()==null){
                num = 0;
            }else {
                num = Integer.valueOf(mydynamic.getLikenum());
            }
            holder2.mcount.setText(Integer.toString(num));  //获取点赞的数目


            holder2.sharername.setText(mydynamic.getUsername());
//            分享者名字
            //点赞需要在该类设置点赞状态
//            BmobQuery<Mydynamic> bm = new BmobQuery<>();
//            bm.getObject(mydynamic.getObjectId(), new QueryListener<Mydynamic>() {
//                @Override
//                public void done(Mydynamic myc, BmobException e) {
//                    count[0] = myc.getNumberOfLikes();
//                    holder2.mcount.setText(String.valueOf(count[0]));
//                }
//            });
//            BmobQuery<LikeInformation> bll = new BmobQuery<>();
//            bll.findObjects(new FindListener<LikeInformation>() {
//                @Override
//                public void done(List<LikeInformation> list, BmobException e) {
//                    for(int i=0;i< list.size();i++){
//                        if(list.get(i).getItemId()==mydynamic.getItemId()&&
//                                String.valueOf(list.get(i).getUserId()).equals(MainActivity.UserId)
//                        ){
//                            holder2.Heart.setImageResource(R.drawable.love1);
//                            flag[0] = true;
//                            break;
//                        }
//                        else{
//                            holder2.Heart.setImageResource(R.drawable.love2);
//                            flag[0] = false;
//                        }

//                    }
//                }
//            });
        }


/******************设置点赞的点击事件*******************/


        holder2.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"点击图片",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mContext,
//                        Home_ItemActivity.class);
//                mContext.startActivity(intent);
            }
        });
//        holder2.Heart.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
////                if(flag[0]){
////
////                    holder2.Heart.setImageResource(R.drawable.love2);
////                    count[0]--;
////                    mydynamic.setNumberOfLikes(count[0]);
////                    mydynamic.update(mydynamic.getObjectId(), new UpdateListener() {
////                        @Override
////                        public void done(BmobException e) {
////
////
////
////                            holder2.mcount.setText(Integer.toString(count[0]));
////
////                        }
////                    });
////
////                    BmobQuery<LikeInformation> bl = new BmobQuery<>();
////                    bl.addWhereEqualTo("userId",Integer.valueOf(MainActivity.UserId));
////                    bl.findObjects(new FindListener<LikeInformation>() {
////                        @Override
////                        public void done(List<LikeInformation> list, BmobException e) {
////                            for(int i=0;i< list.size();i++){
////                                if(list.get(i).getItemId()==mydynamic.getItemId()){
////                                    list.get(i).delete(new UpdateListener() {
////                                        @Override
////                                        public void done(BmobException e) {
////
////                                        }
////                                    });
////                                }
////                            }
////                        }
////                    });
////
////                }
////                else {
////                    holder2.Heart.setImageResource(R.drawable.love1);
////                    count[0]++;
////                    mydynamic.setNumberOfLikes(count[0]);
////                    mydynamic.update(mydynamic.getObjectId(), new UpdateListener() {
////                        @Override
////                        public void done(BmobException e) {
////
////
////
////                            holder2.mcount.setText(Integer.toString(count[0]));
////
////                        }
////                    });
////
////                    LikeInformation likeInformation = new LikeInformation();
////                    likeInformation.setUserId(Integer.valueOf(MainActivity.UserId));
////                    likeInformation.setItemId(mydynamic.getItemId());
////                    likeInformation.save(new SaveListener<String>() {
////                        @Override
////                        public void done(String s, BmobException e) {
////
////                        }
////                    });
////
////                }
//                flag[0] =!flag[0];
//            }
//        });
        /************************设置点赞的点击事件***************************/
        /**************设置卡片布局的点击事件**************/
        //跳转界面并完成数据传输
        holder2.homecardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,
                        Home_ItemActivity.class);

                String mmObjectId = "123123";
                String mmhUri = "null";
                String mmImgUri = mdata.get(position).getImgurl()[0];
                String mmsharerId = mdata.get(position).getId();
                String mmsharerName = mdata.get(position).getUsername();
                int itemId = 0;
                String mmTitle = mdata.get(position).getTitle();
                String mmDetail = mdata.get(position).getContent();
                int mmHeight = 800;


                intent.putExtra("ObjectIdkey",mmObjectId);
                intent.putExtra("sharerIdkey",mmsharerId);
                intent.putExtra("shareNamekey",mmsharerName);
                intent.putExtra("hUrikey",mmhUri);
                intent.putExtra("imgUrikey",mmImgUri);
                intent.putExtra("itemidkey",itemId);
                intent.putExtra("titlekey",mmTitle);
                intent.putExtra("detailkey",mmDetail);
                intent.putExtra("imgHeight",mmHeight);

                mContext.startActivity(intent);
            }
        });
        /**************设置卡片布局的点击事件**************/
        /**********************删除一个Item***********************/
        holder2.deletehomeitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData(position);
//                主界面删除一个图片adapter的
                Toast.makeText(mContext,"删除成功！",Toast.LENGTH_SHORT).show();
            }
        });
        /**********************删除一个Item***********************/

    }

    
    @Override
    public int getItemCount() {
        if(mdata!=null){
//            Log.d("999999999",Integer.toString(mdata.size()));
            return mdata.size();
//            return 0;
        }
//        Log.d("1111111111",Integer.toString(mdata.size()));
        return 0;

    }
//      删除item
    public void removeData(int position) {
        mdata.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{//初始化控件
        public ImageView mImage;
        public TextView mTitle;
        public ImageView Heart;
//        public TextView testtext;
        public TextView mcount;
        public CardView homecardView;
        public ImageView deletehomeitem;
        public ImageView sharerh;
        public TextView sharername;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
//            testtext = (TextView) itemView.findViewById(R.id.testtext1);
            mImage = (ImageView) itemView.findViewById(R.id.iv_shareimage);
            mTitle = (TextView) itemView.findViewById(R.id.mytitle);
            Heart = (ImageView) itemView.findViewById(R.id.love_selected);
            mcount = (TextView) itemView.findViewById(R.id.count);
            homecardView = (CardView)itemView.findViewById(R.id.cd_home_item);
            deletehomeitem = (ImageView)itemView.findViewById(R.id.ig_home_item_delete);
            sharerh = (ImageView)itemView.findViewById(R.id.h_item_sharerig);
            sharername = (TextView)itemView.findViewById(R.id.h_item_sharername);
        }
    }
     void pictuer(){
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
                 Log.d("11111111", "onfailure");

             }

             @Override
             public void onResponse(Call call, final Response response) throws IOException {


                 if (true) {

                     int a = response.code();
                     final String b;
                     b = response.body().string();
                     Log.d("aaaaaaaa",b);

                     Gson gson = new Gson();
                     Share userJson = gson.fromJson(b, Share.class);
                     String code = userJson.getCode();
                     String data = userJson.getData().getTotal();
                     Log.d("aaaaaaaa",code);
                     Log.d("aaaaaaaa",data);
                     List<Share.data.record> records = userJson.getData().getRecord();
                     Share.data.record record1 = records.get(0);
                     String id = record1.getId();

                     Log.d("aaaaaaaa",id);
                     Log.d("aaaaaaaa",record1.getImgurl()[0]);
//                    CreateMyPhotoActivity.k+=records.size();
////                    /****************************显示图片列表***********************************/

//                    mRecyclerView.setAdapter(mAdapter);


                 } else {
//                     Toast.makeText(MainActivity.this,"fall",Toast.LENGTH_SHORT).show();
                 }
             }
         });

     }




}