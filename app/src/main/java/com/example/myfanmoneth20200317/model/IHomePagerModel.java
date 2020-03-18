package com.example.myfanmoneth20200317.model;

import com.example.myfanmoneth20200317.contract.IHomePagerContract;
import com.example.myfanmoneth20200317.utils.VolleyUtils;

public class IHomePagerModel implements IHomePagerContract.onPagerModelInterface {

    @Override
    public void onPagerModelMethod(String url, final onPageModelICallBack iCallBack) {
       //在M层中可直接调用工具类的方法
        VolleyUtils.getInstance().doGet(url, new VolleyUtils.ICallBack() {
            @Override
            public void onSuccess(String json) {
                //成功的方法
                iCallBack.modelSuccess(json);
            }

            @Override
            public void onError(String msg) {
                 //失败的方法
                iCallBack.modelError(msg);
            }
        });
    }
}
