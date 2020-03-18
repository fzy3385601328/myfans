package com.example.myfanmoneth20200317.presenter;

import com.example.myfanmoneth20200317.base.BasePresenter;
import com.example.myfanmoneth20200317.base.IBaseView;
import com.example.myfanmoneth20200317.contract.IHomeSearchContract;
import com.example.myfanmoneth20200317.model.IHomeSearchModel;
//搜索页面的P层代码
public class IHomeSearchPresenter extends BasePresenter implements IHomeSearchContract.onSearchPresenterInterface {
    IHomeSearchContract.onSearchModelInterface model;

    public IHomeSearchPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model =  new IHomeSearchModel();
    }

    @Override
    public void onSearchrPresenter(String url) {
        model.onSearchModelMethod(url, new IHomeSearchContract.onSearchModelInterface.onSearchModelICallBack() {
            @Override
            public void modelSuccess(String json) {
                IBaseView iBaseView = getView();
                if (iBaseView instanceof IHomeSearchContract.onSearchContractViewInterface){
                    IHomeSearchContract.onSearchContractViewInterface iBaseView1 = (IHomeSearchContract.onSearchContractViewInterface) iBaseView;
                    iBaseView1.onSearchViewSuccess(json);
                }
            }

            @Override
            public void modelError(String msg) {
                IBaseView iBaseView = getView();
                if (iBaseView instanceof IHomeSearchContract.onSearchContractViewInterface){
                    IHomeSearchContract.onSearchContractViewInterface iBaseView1 = (IHomeSearchContract.onSearchContractViewInterface) iBaseView;
                    iBaseView1.onSearchViewError(msg);
                }
            }
        });
    }
}
