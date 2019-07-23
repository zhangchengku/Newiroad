package com.zggk.newiroad.utils;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 可以将图片放大缩小的viewpager控件（在相册里的查看大图时用到）
 *
 * @author dongxiaoqing
 *
 */
public class MutipleTouchViewPager extends ViewPager {

    /**
     * 构造方法
     *
     * @param context
     *            上下文对象
     */
    public MutipleTouchViewPager(Context context) {
        super(context);
    }

    /**
     * 构造方法
     *
     * @param context
     *            上下文对象
     * @param attrs
     *            控件属性
     */
    public MutipleTouchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

