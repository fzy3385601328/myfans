package com.example.myappmoneth1.model;

import com.example.myappmoneth1.contract.IHomePagerContract;
import com.example.myappmoneth1.utils.VolleyUtils;

public class IHomePagerModel implements IHomePagerContract.IHomePagerModel {
    @Override
    public void onPagerModelMethod(String url, final IHomePagerModelICallBack iHomePagerModelICallBack) {
        VolleyUtils.getInstance().doGet(url, new VolleyUtils.ICallBack() {
            @Override
            public void onSuccess(String json) {
                iHomePagerModelICallBack.onModelSuccess(json);
            }

            @Override
            public void onError(String msg) {
                iHomePagerModelICallBack.onModelError(msg);
            }
        });
    }
}
