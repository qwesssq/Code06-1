package com.example.code06.Person_RecycleView;




import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.code06.Home_ItemActivity;
import com.example.code06.MainActivity;
import com.example.code06.R;

import org.jetbrains.annotations.NotNull;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class  CollectionAdapter extends RecyclerView.Adapter{
    public  Context mContext;
    public List<MyCollection> mdata;


    public CollectionAdapter(Context mContext, List<MyCollection> mdata){
        this.mContext=mContext;
        this.mdata=mdata;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview_person_item,null);
        return new CollectionAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final MyViewHolder holder2 = (MyViewHolder) holder;
        MyCollection myCollection = mdata.get(position);




        final int[] count = {0};
        if(myCollection.gethUri()!=null&&myCollection.getImgUri()!=null) {
//        if (true){
            Log.d("000000000", "123123123");
            holder2.mImage.setImageURI(Uri.parse(myCollection.gethUri()));////
//            picUrl = new URL(getIntent().getExtras().getString("map_url"));
            URL picUrl = null;
            try {
                picUrl = new URL(myCollection.gethUri().toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                HttpURLConnection conn = (HttpURLConnection) picUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                Bitmap pngBM = BitmapFactory.decodeStream(picUrl.openStream());
                Log.d("123123123123",myCollection.gethUri().toString());
                holder2.sharerimgae.setImageResource(R.drawable.test02);
                holder2.mImage.setImageBitmap(pngBM);
            } catch (Exception R) {
                Log.d("123123123123", R.toString());
            }
        }







//        holder2.mImage.setImageURI(Uri.parse(myCollection.getImgUri()));
        holder2.mTitle.setText(myCollection.getTitle());
        holder2.mImage.getLayoutParams().height = myCollection.getImgheight();
//        holder2.sharerimgae.setImageURI(Uri.parse(myCollection.gethUri()));
        holder2.sharername.setText(myCollection.getSharerName());

        holder2.personcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,
                        Home_ItemActivity.class);
                Bundle bundle = new Bundle();

//                int userId = mdata.get(position).getUserId();
                //userid----sharename
//                String sharerName = mdata.get(position).getSharerName();
//                int mmsharerId = mdata.get(position).getSharerId();
//                String mmhUri = mdata.get(position).gethUri();
//                int itemId = mdata.get(position).getItemId();
//                String mmimghUri = mdata.get(position).getImgUri();
//                String mmTitle = holder2.mTitle.getText().toString();
//                String mmDetail = mdata.get(position).getDetail();
//                int mmHeight = mdata.get(position).getImgheight();
                int userId = mdata.get(position).getUserId();
                String sharerName =mdata.get(position).getSharerName();
                int mmsharerId = mdata.get(position).getUserId();
                String mmhUri = mdata.get(position).getImgUri();
                String itemId = ""+mdata.get(position).getItemId();
                String mmimghUri = mdata.get(position).getImgUri();
                String mmTitle = holder2.mTitle.getText().toString();
                String mmDetail = mdata.get(position).getDetail();
                int mmHeight = 0;
                Log.d("uuuuuuu",itemId);
                intent.putExtras(bundle);
                intent.putExtra("UserIdkey",userId);
                intent.putExtra("sharerNamekey",sharerName);
                intent.putExtra("sharerIdkey",itemId);
                intent.putExtra("hUrikey",mmhUri);
                intent.putExtra("itemidkey",itemId);
                intent.putExtra("imgUrikey",mmimghUri);
                intent.putExtra("titlekey",mmTitle);
                intent.putExtra("detailkey",mmDetail);
                intent.putExtra("imgHeight",mmHeight);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mdata!=null){
            return mdata.size();
        }
        return 0;
    }

    //  删除item
    public void removeData(int position) {
        mdata.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();

    }

    private static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImage;
        public TextView mTitle;
        public ImageView Heart;
        public TextView mcount;
        public CardView personcardView;
        public ImageView deletehomeitem;
        public ImageView sharerimgae;
        public TextView sharername;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.iv_person_shareimage);
            mTitle = (TextView) itemView.findViewById(R.id.collection_title);
            Heart = (ImageView) itemView.findViewById(R.id.person_love_selected);
            mcount = (TextView) itemView.findViewById(R.id.person_count);
            personcardView = (CardView) itemView.findViewById(R.id.cd_person_item);
            sharername = (TextView)itemView.findViewById(R.id.p_shareName);
            sharerimgae = (ImageView) itemView.findViewById(R.id.p_sharerhimg);
        }
    }
}