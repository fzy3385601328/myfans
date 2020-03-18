package com.example.myfanmoneth20200317.utils;

import androidx.annotation.NonNull;

/***
 * 全局异常捕获处理
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler{

    private CrashHandler(){

    }

    private static class SingleInstance{
        private static CrashHandler INSTANCE = new CrashHandler();
    }

    public static CrashHandler getInstance(){
        return SingleInstance.INSTANCE;
    }

    public void init(){
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
           e.printStackTrace();
    }

}
