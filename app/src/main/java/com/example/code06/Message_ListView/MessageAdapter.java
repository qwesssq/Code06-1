package com.example.code06.Message_ListView;




import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.code06.Home_Recycleview.WaterfallAdapter;
import com.example.code06.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter{
    public  Context mContext;
    public List<MyMessage> mdata;


    public MessageAdapter(Context mContext, List<MyMessage> mdata){
        this.mContext=mContext;
        this.mdata=mdata;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview_message_item,null);
        return new MessageAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder holder2 = (MyViewHolder) holder;
        MyMessage myMessage = mdata.get(position);

        Uri uri = Uri.parse(myMessage.getImgUri());
        holder2.mImage.setImageURI(uri);
        //holder2.mName.setText(myMessage.name);
        holder2.mTitle.setText(myMessage.getTitle());
        holder2.mDetail.setText(myMessage.getDetail());
        holder2.mTime.setText(myMessage.getTime());

    }

    @Override
    public int getItemCount() {
        if(mdata!=null){
            return mdata.size();
        }
        return 0;
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImage;
        public TextView mTitle;
        public TextView mDetail;
        public TextView mTime;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.img_message);
            mTitle = (TextView) itemView.findViewById(R.id.tv_message_title);
            mDetail = (TextView) itemView.findViewById(R.id.tv_message_detail);
            mTime = (TextView) itemView.findViewById(R.id.tv_message_time);
        }
    }
}