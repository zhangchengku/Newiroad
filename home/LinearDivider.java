package com.zggk.newiroad.home;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 张成昆 on 2019-3-4.
 */
public class LinearDivider extends RecyclerView.ItemDecoration {
    private Drawable mDividerDarwable;
    private int mOrientation;
    private int mDividerHight = 1;
    private Paint mColorPaint;
    public final int[] ATRRS = new int[]{android.R.attr.listDivider};

    public LinearDivider(Context context, int orientation) {
        final TypedArray ta = context.obtainStyledAttributes(ATRRS);
        this.mDividerDarwable = ta.getDrawable(0);
        ta.recycle();
        setOrientation(orientation);
    }

    public LinearDivider(Context context, int orientation, int dividerHight, Drawable dividerDrawable) {
        this(context, orientation);
        mDividerHight = dividerHight;
        mDividerDarwable = dividerDrawable;
    }

    public LinearDivider(Context context, int orientation, int dividerHight, int dividerColor) {
        this(context, orientation);
        mDividerHight = dividerHight;
        mColorPaint = new Paint();
        mColorPaint.setColor(dividerColor);
    }

    public void setOrientation(int orientation) {
        if (orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("方向参数错误！");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            drawHorizontalDivider(c, parent);
        } else {
            drawVirticalDivider(c, parent);
        }
    }

    public void drawVirticalDivider(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDividerHight;
            if (mDividerDarwable != null) {
                mDividerDarwable.setBounds(left, top, right, bottom);
                mDividerDarwable.draw(c);
            }
            if (mColorPaint != null) {
                c.drawRect(left, top, right, bottom, mColorPaint);
            }
        }
    }

    public void drawHorizontalDivider(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDividerHight;
            if (mDividerDarwable != null) {
                mDividerDarwable.setBounds(left, top, right, bottom);
                mDividerDarwable.draw(c);
            }
            if (mColorPaint != null) {
                c.drawRect(left, top, right, bottom, mColorPaint);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            outRect.set(0, 0, 0, mDividerHight);
        } else {
            outRect.set(0, 0, mDividerHight, 0);
        }
    }
}


