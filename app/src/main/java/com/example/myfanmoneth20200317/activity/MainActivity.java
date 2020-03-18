package com.example.myfanmoneth20200317.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myfanmoneth20200317.R;
import com.example.myfanmoneth20200317.adapter.MyPicAdapter;
import com.example.myfanmoneth20200317.adapter.MyRecyclerAdapter;
import com.example.myfanmoneth20200317.base.BaseActivity;
import com.example.myfanmoneth20200317.base.BasePresenter;
import com.example.myfanmoneth20200317.bean.MySearchMusicBean;
import com.example.myfanmoneth20200317.contract.IHomePagerContract;
import com.example.myfanmoneth20200317.contract.IHomeSearchContract;
import com.example.myfanmoneth20200317.custom.CustomViewGroup;
import com.example.myfanmoneth20200317.presenter.IHomePagerPresenter;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends BaseActivity implements IHomePagerContract.onPagerContractViewInterface {


    private CustomViewGroup customViewGroup;
    private RecyclerView rView1;
    private RecyclerView rv1;
    private View view;

    @Override
    protected BasePresenter initPresenter() {
        return new IHomePagerPresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        view = findViewById(R.id.view);
        customViewGroup = findViewById(R.id.custom_viewgroup);
        rv1 = findViewById(R.id.recycler_v1);
        rView1 = findViewById(R.id.recycler_view1);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                startActivity(intent);
            }
        });

        customViewGroup.setSearchClickListener(new CustomViewGroup.OnSearchClickListener() {
            @Override
            public void onSearchClick(String str) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                //传数据
                intent.putExtra("searchKey",str);
                //启动跳转
                startActivity(intent);
            }
        });


    }

    @Override
    protected void getData() {

        BasePresenter basePresenter = getmPresenter();
        if (basePresenter instanceof IHomePagerPresenter){
            ((IHomePagerPresenter) basePresenter).onPagerPresenter("http://blog.zhaoliang5156.cn/baweiapi/searchmusic?kword=欧美金曲榜"
                    );
        }
    }

    @Override
    public void onPagerViewSuccess(String json) {
        Log.i("xxx",json);
        /*//Gson解析
        Gson gson = new Gson();
        MySearchMusicBean mySearchMusicBean = gson.fromJson(json, MySearchMusicBean.class);
        final List<MySearchMusicBean.ContentBean> content = mySearchMusicBean.getContent();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false);
        rv1.setLayoutManager(linearLayoutManager);

        MyPicAdapter adapter1 = new MyPicAdapter(MainActivity.this,content);
        rv1.setAdapter(adapter1);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        rView1.setLayoutManager(gridLayoutManager);

        //创建适配器
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(MainActivity.this, content);
        //设置适配器
        rView1.setAdapter(adapter);

        //点击事件
        adapter.setSearchClickener(new MyRecyclerAdapter.onSearchClickener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, content.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                //传值
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("pic_big",content.get(position).getPic_big());
                intent.putExtra("title",content.get(position).getTitle());
                intent.putExtra("author",content.get(position).getAuthor());
                startActivity(intent);
            }
        });*/
    }

    @Override
    public void onPagerViewError(String msg) {
        //打印不出来数据
        Log.i("xxx",msg);
    }
}
