package com.example.myappmoneth1.contract;

public interface IHomePagerContract {

    //定义V层接口
    interface IHomePagerView{
        void onViewSuccess(String json);
        void onViewError(String msg);
    }
    //定义P层接口
    interface IHomePagerPresenter{
        void onMessionPresenter(String url);
    }

    //定义M层接口
    interface IHomePagerModel{
        void onPagerModelMethod(String url,IHomePagerModelICallBack iHomePagerModelICallBack);

        interface IHomePagerModelICallBack{
            void onModelSuccess(String json);
            void onModelError(String msg);
        }
    }

}
