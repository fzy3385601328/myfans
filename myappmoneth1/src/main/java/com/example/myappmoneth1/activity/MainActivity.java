package com.example.myappmoneth1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myappmoneth1.R;
import com.example.myappmoneth1.base.BaseActivity;
import com.example.myappmoneth1.base.BasePresenter;
import com.example.myappmoneth1.contract.IHomePagerContract;
import com.example.myappmoneth1.presenter.IHomePagerPresenter;

public class MainActivity extends BaseActivity implements IHomePagerContract.IHomePagerView {

    private RecyclerView recyclerView;

    @Override
    protected BasePresenter initPresenter() {
        return new IHomePagerPresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recycler_View);
    }



    @Override
    protected void getData() {
        String url = "http://blog.zhaoliang5156.cn/baweiapi/searchmusic?kword=欧美金曲榜";
        BasePresenter basePresenter = getmPresenter();
        if (basePresenter instanceof IHomePagerPresenter){
            IHomePagerPresenter presenter = (IHomePagerPresenter) basePresenter;
            presenter.onMessionPresenter(url);

        }
    }

    @Override
    public void onViewSuccess(String json) {
        Log.i("xxx",json);
    }

    @Override
    public void onViewError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
