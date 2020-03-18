package com.example.myfanmoneth20200317.presenter;

import com.example.myfanmoneth20200317.base.BasePresenter;
import com.example.myfanmoneth20200317.base.IBaseView;
import com.example.myfanmoneth20200317.contract.IHomePagerContract;
import com.example.myfanmoneth20200317.model.IHomePagerModel;
//主页面的P层代码
public class IHomePagerPresenter extends BasePresenter implements IHomePagerContract.onPagerPresenterInterface {
    IHomePagerContract.onPagerModelInterface model;

    public IHomePagerPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new IHomePagerModel();
    }

    @Override
    public void onPagerPresenter(String url) {

        model.onPagerModelMethod(url, new IHomePagerContract.onPagerModelInterface.onPageModelICallBack() {
            @Override
            public void modelSuccess(String json) {
                IBaseView iBaseView = getView();

                if (iBaseView instanceof IHomePagerContract.onPagerContractViewInterface){
                    IHomePagerContract.onPagerContractViewInterface iBaseView1 = (IHomePagerContract.onPagerContractViewInterface) iBaseView;
                    iBaseView1.onPagerViewSuccess(json);
                }
            }

            @Override
            public void modelError(String msg) {
                IBaseView iBaseView = getView();

                if (iBaseView instanceof IHomePagerContract.onPagerContractViewInterface){
                    IHomePagerContract.onPagerContractViewInterface iBaseView1 = (IHomePagerContract.onPagerContractViewInterface) iBaseView;
                    iBaseView1.onPagerViewError(msg);
                }
            }
        });
    }
}
