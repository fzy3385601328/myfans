package com.example.myfanmoneth20200317.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myfanmoneth20200317.R;

public class FlowLayout extends ViewGroup {

    private int color;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);

        color = typedArray.getColor(R.styleable.FlowLayout_color, Color.BLUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量所有孩子的宽高
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //分别在测量一下孩子的宽高
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //得到孩子的宽高
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //自定义方法来得到孩子的具体的宽高度
        compare(width-getPaddingRight());
        //最后测量自身
        setMeasuredDimension(width,height);
    }

    public void compare(int width){
        int userWidth = getPaddingLeft();
        int userHeight = getPaddingTop();
        //遍历所有的孩子
        for (int i=0;i<getChildCount();i++){
            //得到孩子的角标
            View child = getChildAt(i);
            //获取孩子的布局管理器
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) child.getLayoutParams();
            //计算孩子的实际宽度 = 孩子本身的宽度+左边距+右边距
            int childWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            //计算孩子的实际高度 = 孩子本身的高度+上边距+下边距
            int childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

            //判断如果孩子的实际宽度累加已经超出了屏幕则需要进行换行操作
            if ((userWidth+childWidth)>width){
                userWidth = getPaddingLeft();
                userHeight+=childHeight;
            }
            userWidth+=childWidth;

            //创建Rect对象
            Rect rect = new Rect();
            rect.left = userWidth - childWidth;
            rect.right = userWidth;
            rect.top = userHeight;
            rect.bottom = userHeight + childHeight;
            //添加标记
            child.setTag(rect);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //遍历所有的孩子
        for (int i=0;i<getChildCount();i++){
            View child = getChildAt(i);
            //获取孩子的标记
            Rect rect = (Rect) child.getTag();
            //设置孩子的摆放位置
            child.layout(rect.left,rect.top,rect.right,rect.bottom);
            //为孩子设置颜色
            child.setTag(color);

        }
    }
}
