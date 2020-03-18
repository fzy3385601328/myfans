package com.example.myappmoneth1.presenter;

import com.example.myappmoneth1.base.BasePresenter;
import com.example.myappmoneth1.base.IBaseView;
import com.example.myappmoneth1.contract.IHomePagerContract;
import com.example.myappmoneth1.model.IHomePagerModel;

public class IHomePagerPresenter extends BasePresenter implements IHomePagerContract.IHomePagerPresenter {
    IHomePagerContract.IHomePagerModel model;
    public IHomePagerPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new IHomePagerModel();
    }

    @Override
    public void onMessionPresenter(String url) {
        model.onPagerModelMethod(url, new IHomePagerContract.IHomePagerModel.IHomePagerModelICallBack() {
            @Override
            public void onModelSuccess(String json) {
                IBaseView iBaseView = getView();
                if (iBaseView instanceof IHomePagerContract.IHomePagerView){
                    IHomePagerContract.IHomePagerView iBaseView1 = (IHomePagerContract.IHomePagerView) iBaseView;
                    iBaseView1.onViewSuccess(json);
                }

            }

            @Override
            public void onModelError(String msg) {
                IBaseView iBaseView = getView();
                if (iBaseView instanceof IHomePagerContract.IHomePagerView){
                    IHomePagerContract.IHomePagerView iBaseView1 = (IHomePagerContract.IHomePagerView) iBaseView;
                    iBaseView1.onViewError(msg);
                }
            }
        });
    }
}
