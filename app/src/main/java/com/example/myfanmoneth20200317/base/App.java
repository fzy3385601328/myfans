package com.example.myfanmoneth20200317.base;

import android.app.Application;
import android.content.Context;

import com.example.myfanmoneth20200317.utils.CrashHandler;

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        CrashHandler.getInstance().init();
    }

    public static Context getmContext() {
        return mContext;
    }
}
