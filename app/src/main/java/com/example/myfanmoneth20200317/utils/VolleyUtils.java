package com.example.myfanmoneth20200317.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myfanmoneth20200317.base.App;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class VolleyUtils {
    //创建请求队列
    RequestQueue eQueue;
    //创建私有的构造方法
    private VolleyUtils(){
        eQueue = Volley.newRequestQueue(App.getmContext());
    }
    //创建私有的静态的单例模式
    private static class SingleInstance{
        private static final VolleyUtils INSTANCE = new VolleyUtils();
    }


    public static VolleyUtils getInstance(){
        return SingleInstance.INSTANCE;
    }

    //判断是否有网
    public boolean isNetActivice(Context context){
        ConnectivityManager cons = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cons.getActiveNetworkInfo();
        //如果有网络就返回true
        if (info!=null){
            return true;
        }
        //如果没网就返回false
        return false;
    }

    //创建get请求
    public void doGet(String url, final ICallBack iCallBack){
        //创建字符串类型的数据请求
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 //get成功
                iCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //get失败
                iCallBack.onError(error.getMessage());
            }
        }){
            //加上阻止程序乱码的代码

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String a;
                try {
                    a=new String(response.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    a=new String(response.data);
                }
                return Response.success(a, HttpHeaderParser.parseCacheHeaders(response));
            }

        };
        eQueue.add(stringRequest);
    }
    //创建post请求
    public void doPost(String url, final Map<String,String> map, final ICallBack iCallBack){
        //创建字符串类型的数据请求
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 //post请求成功
                iCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //post请求失败
                iCallBack.onError(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        eQueue.add(stringRequest);
    }
    //创建公共的接口
    public interface ICallBack{
        //成功的方法
        void onSuccess(String json);
        //失败的方法
        void onError(String msg);
    }


}
