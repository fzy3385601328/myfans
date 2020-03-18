package com.example.myfanmoneth20200317.contract;


import com.example.myfanmoneth20200317.base.IBaseView;

public interface IHomePagerContract {

    //定义V层接口
    interface onPagerContractViewInterface extends IBaseView {
        //定义V层的成功或失败的方法
        void onPagerViewSuccess(String json);
        void onPagerViewError(String msg);
    }

    //定义P层接口
    interface onPagerPresenterInterface{
        //定义普通的方法
        void onPagerPresenter(String url);

    }

    //定义M层接口
    interface onPagerModelInterface{
        //定义方法进行接口的回调
        void onPagerModelMethod(String url,onPageModelICallBack iCallBack);

        //定义M层的接口
        interface onPageModelICallBack{
            //定义成功的方法
            void modelSuccess(String json);
            //定义失败的方法
            void modelError(String msg);
        }
    }
}
