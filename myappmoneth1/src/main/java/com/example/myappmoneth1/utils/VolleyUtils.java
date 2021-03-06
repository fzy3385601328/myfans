package com.example.myappmoneth1.utils;

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
import com.example.myappmoneth1.base.App;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class VolleyUtils {

    RequestQueue eQueue;

    private VolleyUtils(){
        eQueue = Volley.newRequestQueue(App.getmContext());
    }

    private static class SingleInstance{
        private static VolleyUtils INSTANCE = new VolleyUtils();
    }

    public static VolleyUtils getInstance(){
        return SingleInstance.INSTANCE;
    }

    public boolean isNetActivice(Context context){
        ConnectivityManager coms = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = coms.getActiveNetworkInfo();

        if (info!=null){
            return true;
        }
        return false;
    }

    public interface ICallBack{
        void onSuccess(String json);
        void onError(String msg);
    }

    public void doGet(String url, final ICallBack iCallBack){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iCallBack.onError(error.getMessage());
            }
        }){
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
        };;
        eQueue.add(stringRequest);
    }


    public void doPost(String url, final Map<String,String> map, final ICallBack iCallBack){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                  iCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
}
