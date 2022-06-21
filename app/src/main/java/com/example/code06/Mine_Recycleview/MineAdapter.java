package com.example.code06.Mine_Recycleview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
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

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MineAdapter extends RecyclerView.Adapter {
    public Context mContext;
    public List<Mine> mdata;

    public MineAdapter(Context mContext, List<Mine> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;


    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview_mine_item,null);
        final WaterfallAdapter.MyViewHolder holder = new WaterfallAdapter.MyViewHolder(view);

        return new MineAdapter.MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        Mine mine = mdata.get(position);


        final int[] count = {0};
        if(mine.gethUri()!=null&&mine.gethUri()!=null) {
//        if (true){
            Log.d("000000000", "123123123");
            holder1.m_himage.setImageURI(mine.gethUri());////
//            picUrl = new URL(getIntent().getExtras().getString("map_url"));
            URL picUrl = null;
            try {
                picUrl = new URL(mine.gethUri().toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                HttpURLConnection conn = (HttpURLConnection) picUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                Bitmap pngBM = BitmapFactory.decodeStream(picUrl.openStream());
                Log.d("123123123123", mine.gethUri().toString());

                holder1.m_image.setImageBitmap(pngBM);
            } catch (Exception R) {
                Log.d("123123123123", R.toString());
            }
        }
        holder1.m_himage.setImageResource(R.drawable.test02);
//        holder1.m_himage.setImageURI(mine.hUri);
        holder1.m_Name.setText(mine.getName());
        holder1.m_Title.setText(mine.getTitle());
//        holder1.m_image.setImageURI(mine.shareUri);

    }

    @Override
    public int getItemCount() {
        if(mdata!=null){
            return mdata.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView m_himage;
        ImageView m_image;
        TextView m_Title;
        TextView m_Name;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            m_himage = (ImageView)itemView.findViewById(R.id.m_hImg);
            m_image = (ImageView)itemView.findViewById(R.id.m_Pic);
            m_Title = (TextView)itemView.findViewById(R.id.m_Title);
            m_Name = (TextView)itemView.findViewById(R.id.m_Name);
        }
    }
}
