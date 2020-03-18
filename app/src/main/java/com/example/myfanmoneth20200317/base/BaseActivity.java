package com.example.myfanmoneth20200317.base;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<V extends BasePresenter>extends AppCompatActivity implements IBaseView {

    BasePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化BasePresenter对象
        mPresenter = initPresenter();
        //布局文件
        setContentView(getLayoutResId());
        //控件
        initView();
        //布局里的数据
        getData();
    }

    public BasePresenter getmPresenter() {
        return mPresenter;
    }

    protected abstract BasePresenter initPresenter();

    protected abstract int getLayoutResId();

    protected abstract void initView();

    protected abstract void getData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.onDeatchView();
            mPresenter=null;
        }
    }
}
