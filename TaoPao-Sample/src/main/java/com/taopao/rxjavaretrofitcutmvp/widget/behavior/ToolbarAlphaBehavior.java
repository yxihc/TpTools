package com.taopao.rxjavaretrofitcutmvp.widget.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.taopao.rxjavaretrofitcutmvp.R;

/**
 * @Author: 淘跑
 * @Data: 2018/2/1 20:00
 * @Use:
 */

public class ToolbarAlphaBehavior extends CoordinatorLayout.Behavior<View> {
    public ToolbarAlphaBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
    private int offset = 0;
    private int startOffset = 0;
    private int endOffset = 0;
    private Context context;

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       View child, View directTargetChild, View target,
                                       int nestedScrollAxes) {
        // TODO Auto-generated method stub
        return (nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL)||super.onStartNestedScroll(coordinatorLayout, child, directTargetChild,
                target, nestedScrollAxes);
    }
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout,
                                  View child, View target, int dx, int dy, int[] consumed) {
        startOffset = 0;
        endOffset = context.getResources().getDimensionPixelOffset(R.dimen.header_height) - child.getHeight();
        offset += dy;
        if (offset <= startOffset) {  //alpha为0
            child.getBackground().setAlpha(0);
        } else if (offset > startOffset && offset < endOffset) { //alpha为0到255
            float precent = (float) (offset - startOffset) / endOffset;
            int alpha = Math.round(precent * 255);
            child.getBackground().setAlpha(alpha);
        } else if (offset >= endOffset) {  //alpha为255
            child.getBackground().setAlpha(255);
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout,
                                 View child, View target, float velocityX, float velocityY,
                                 boolean consumed) {
        ((NestedScrollView)target).fling((int) velocityY);
        return super.onNestedFling(coordinatorLayout, target, child, velocityX,
                velocityY, consumed);
    }





}
