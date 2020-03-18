package com.example.myfanmoneth20200317.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfanmoneth20200317.R;
import com.example.myfanmoneth20200317.bean.MySearchMusicBean;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MySearchMusicBean.ContentBean> content;


    public MyRecyclerAdapter(Context context, List<MySearchMusicBean.ContentBean> content) {
        this.context = context;
        this.content = content;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_item1, null);
        ViewHodler holder = new ViewHodler(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        //Glide加载异步图片
        Glide.with(context).load(content.get(position).getPic_big()).error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher).into(((ViewHodler)holder).imageImg);
        //加载文字
        ((ViewHodler)holder).itemTitle.setText(content.get(position).getTitle());
        ((ViewHodler)holder).itemAuthor.setText(content.get(position).getAuthor());

        //点击事件
        ((ViewHodler)holder).itemTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchClickener!=null){
                    searchClickener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

   class ViewHodler extends RecyclerView.ViewHolder{

        private final ImageView imageImg;
        private final TextView itemTitle;
        private final TextView itemAuthor;


        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            imageImg = itemView.findViewById(R.id.item_img);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemAuthor = itemView.findViewById(R.id.item_author);
        }
    }

    private onSearchClickener searchClickener;

    public void setSearchClickener(onSearchClickener searchClickener) {
        this.searchClickener = searchClickener;
    }

    public interface onSearchClickener{
        void onItemClick(int position);
    }
}

