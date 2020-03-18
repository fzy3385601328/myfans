package com.example.myfanmoneth20200317.contract;

import com.example.myfanmoneth20200317.base.IBaseView;

public interface IHomeSearchContract{

    //定义V层接口
    interface onSearchContractViewInterface extends IBaseView {
        //定义V层的成功或失败的方法
        void onSearchViewSuccess(String json);
        void onSearchViewError(String msg);
    }

    //定义P层接口
    interface onSearchPresenterInterface{
        //定义普通的方法
        void onSearchrPresenter(String url);

    }

    //定义M层接口
    interface onSearchModelInterface{
        //定义方法进行接口的回调
        void onSearchModelMethod(String url, onSearchModelICallBack iCallBack);

        //定义M层的接口
        interface onSearchModelICallBack{
            //定义成功的方法
            void modelSuccess(String json);
            //定义失败的方法
            void modelError(String msg);
        }
    }
}
