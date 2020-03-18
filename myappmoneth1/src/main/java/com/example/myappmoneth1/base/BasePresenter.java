package com.example.myappmoneth1.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseView> {

    private WeakReference<V> weakReference;

    public BasePresenter(V v){

        weakReference = new WeakReference<>(v);
        initModel();
    }

    protected abstract void initModel();

    public V getView(){
        if (weakReference!=null){
            return weakReference.get();
        }
        return null;
    }

    protected void onDeatchView(){
        if (weakReference!=null){
            weakReference.clear();
            weakReference=null;
        }
    }
}
