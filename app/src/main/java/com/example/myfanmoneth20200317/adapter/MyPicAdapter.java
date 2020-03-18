package com.example.myfanmoneth20200317.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfanmoneth20200317.R;
import com.example.myfanmoneth20200317.activity.MainActivity;
import com.example.myfanmoneth20200317.bean.MySearchMusicBean;

import java.util.List;

public class MyPicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MySearchMusicBean.ContentBean> content;
    public MyPicAdapter(Context context, List<MySearchMusicBean.ContentBean> content) {
        this.context=context;
        this.content=content;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_itema, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(content.get(position).getPic_big()).error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher).into(((ViewHolder)holder).itemImg);
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView itemImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.item_img);
        }
    }
}
