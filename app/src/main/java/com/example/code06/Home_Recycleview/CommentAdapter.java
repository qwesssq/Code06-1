package com.example.code06.Home_Recycleview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.code06.Home_ItemActivity;
import com.example.code06.R;

import org.jetbrains.annotations.NotNull;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter {
    public Context context;
    public List<Mycomment.sdata.pcomment> mycommentList;

    public CommentAdapter(Context context, List<Mycomment.sdata.pcomment> mycommentList) {
        this.context = context;
        this.mycommentList = mycommentList;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycleview_home_item_comments,null);
        MyViewHolder holder = new MyViewHolder(view);
        return new CommentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        MyViewHolder holder1 = (CommentAdapter.MyViewHolder)holder;
        holder1.setIsRecyclable(false);
        Mycomment.sdata.pcomment mycomment = mycommentList.get(position);
        String url = "https://guet-lab.oss-cn-guangzhou.aliyuncs.com/api/2022/06/06/login.jpg";
        URL picUrl = null;
        try {
            picUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {

            HttpURLConnection conn = (HttpURLConnection) picUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            Bitmap pngBM = BitmapFactory.decodeStream(picUrl.openStream());
            Log.d("123123123123",picUrl.toString());
//            holder1.img_user.setImageBitmap(pngBM);
            //            放置图片

            //            分享者头像
        }catch (Exception R){
            Log.d("chucuuo",R.toString());
        }
        holder1.img_user.setImageResource(R.drawable.test02);
        holder1.tv_userId.setText(mycomment.getUsername());
        holder1.tv_usercomments.setText(mycomment.getContent());



    }

    @Override
    public int getItemCount() {
        if(mycommentList!=null){
            return mycommentList.size();
        }
        return 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView img_user;
        public TextView tv_userId;
        public TextView tv_usercomments;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            img_user = (ImageView) itemView.findViewById(R.id.comment_userimg);
            tv_userId = (TextView) itemView.findViewById(R.id.comment_username);
            tv_usercomments = (TextView) itemView.findViewById(R.id.comment_usercomment);
        }
    }
}
