package com.example.myfanmoneth20200317.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.myfanmoneth20200317.R;

public class CustomViewGroup extends LinearLayout {

    private EditText editSearch;
    private ImageView imageSearch;

    public CustomViewGroup(Context context) {
        super(context);
        init(context);
    }

    public CustomViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        //设置布局样式
        View view = View.inflate(context, R.layout.custom_viewgroup, null);
        //设置布局控件
        editSearch = view.findViewById(R.id.edit_search);
        imageSearch = view.findViewById(R.id.image_search);

        //设置点击的事件
        imageSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchClickListener!=null){
                    //设置文本的标题
                    searchClickListener.onSearchClick(editSearch.getText().toString());
                }
            }
        });

        addView(view);
    }

    public void setData(String string){
        editSearch.setText(string);
    }

    private OnSearchClickListener searchClickListener;

    public void setSearchClickListener(OnSearchClickListener searchClickListener) {
        this.searchClickListener = searchClickListener;
    }

    //设置点击的接口
    public interface OnSearchClickListener{
        //创建点击的方法
        void onSearchClick(String str);
    }


}
