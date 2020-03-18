package com.example.myfanmoneth20200317.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseView> {

    //使用弱应用包裹对象
    private WeakReference<V> weakReference;

    public BasePresenter(V v){
        //成员化初始变量
        weakReference = new WeakReference<>(v);
        //定义一个抽象方法
        initModel();
    }

    protected abstract void initModel();
    //创建绑定的方法
    public V getView(){
        //判断如果弱引用的对象不为空
        if (weakReference!=null){
            return weakReference.get();
        }
        return null;
    }

    //创建解绑的方法
    protected void onDeatchView(){
        //判断如果弱引用的对象不为空
        if (weakReference!=null){
            weakReference.clear();
            weakReference=null;
        }
    }
}
