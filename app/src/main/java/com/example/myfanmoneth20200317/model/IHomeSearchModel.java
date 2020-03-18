package com.example.myfanmoneth20200317.model;

import com.example.myfanmoneth20200317.contract.IHomePagerContract;
import com.example.myfanmoneth20200317.contract.IHomeSearchContract;
import com.example.myfanmoneth20200317.utils.VolleyUtils;

public class IHomeSearchModel implements IHomeSearchContract.onSearchModelInterface {


    @Override
    public void onSearchModelMethod(String url, final onSearchModelICallBack iCallBack) {
        VolleyUtils.getInstance().doGet(url, new VolleyUtils.ICallBack() {
            @Override
            public void onSuccess(String json) {
                iCallBack.modelSuccess(json);
            }

            @Override
            public void onError(String msg) {
                iCallBack.modelError(msg);
            }
        });
    }
}
