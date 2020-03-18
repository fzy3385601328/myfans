package com.example.myfanmoneth20200317.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfanmoneth20200317.R;
import com.example.myfanmoneth20200317.adapter.MyRecyclerAdapter;
import com.example.myfanmoneth20200317.base.BaseActivity;
import com.example.myfanmoneth20200317.base.BasePresenter;
import com.example.myfanmoneth20200317.bean.MySearchMusicBean;
import com.example.myfanmoneth20200317.contract.IHomeSearchContract;
import com.example.myfanmoneth20200317.custom.CustomViewGroup;
import com.example.myfanmoneth20200317.custom.FlowLayout;
import com.example.myfanmoneth20200317.presenter.IHomeSearchPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements IHomeSearchContract.onSearchContractViewInterface {


    private CustomViewGroup customVp;
    private FlowLayout flowLayout;

    //创建集合
    private List<String> mList = new ArrayList<>();

    @Override
    protected BasePresenter initPresenter() {
        return new IHomeSearchPresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        customVp = findViewById(R.id.custom_vp);
        flowLayout = findViewById(R.id.flow_layout);

        customVp.setSearchClickListener(new CustomViewGroup.OnSearchClickListener() {
            @Override
            public void onSearchClick(String str) {
                mList.add(str);
                flowLayout.removeAllViews();

                for (String string:mList){
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                            (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    //设置布局的外边距
                    layoutParams.setMargins(10,10,10,10);
                    //实例化TextView对象
                    TextView textView = new TextView(SearchActivity.this);
                    textView.setPadding(5,5,5,5);
                    textView.setText(string);
                    textView.setLayoutParams(layoutParams);
                    flowLayout.addView(textView,layoutParams);
                }
            }
        });
    }

    @Override
    protected void getData() {
        customVp.setData(getIntent().getStringExtra("searchKey"));

        /*BasePresenter basePresenter = getmPresenter();

        if (basePresenter instanceof IHomeSearchPresenter){
            String searchUrl = "http://blog.zhaoliang5156.cn/baweiapi/searchmusic?kword=欧美金曲榜";
            ((IHomeSearchPresenter) basePresenter).onSearchrPresenter(searchUrl);
        }*/
    }

    @Override
    public void onSearchViewSuccess(String json) {
        Log.i("xxx",json);


    }

    @Override
    public void onSearchViewError(String msg) {
        Log.i("xxx",msg);
    }
}
