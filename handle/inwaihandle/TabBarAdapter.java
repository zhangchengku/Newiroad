package com.zggk.newiroad.handle.inwaihandle;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zggk.newiroad.R;


/**
 * 轮播图底部标签适配器
 *
 * @author dongxiaoqing
 */
public class TabBarAdapter extends BaseAdapter{
    private int count;// 个数
    private Context context;// context对象
    private int optFor = 0;// 当前所在的页卡

    /**
     * 构造方法
     *
     * @param count   个数
     * @param context context对象
     */
    public TabBarAdapter(int count, Context context) {
        this.count = count;
        this.context = context;
    }

    /**
     * 适配器更新方法
     *
     * @param optFor 当前所在的页卡下标
     */
    public void setOptFor(int optFor) {
        this.optFor = optFor;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ImageView imageView = new ImageView(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        if (arg0 == optFor) {
            imageView.setImageResource(R.drawable.circle_dot_pink_small);
        } else {
            imageView.setImageResource(R.drawable.circle_dot_gray_small);
        }
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

}
