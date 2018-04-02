package com.taopao.rxjavaretrofitcutmvp.widget.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.taopao.rxjavaretrofitcutmvp.R;


/**
 * @Author：淘跑
 * @Date: 2018/4/1 09:35
 * @Use： SmartRefreshLayout 的自定义下拉刷新 Header
 * @
 * @-------------------修改记录-------------------@
 * @
 * @Modifier: 修改者  v1
 * @Data: 修改时间
 * @Version: 修改次数
 * @EditContent: 修改内容
 */

public class MyRefreshHeader extends LinearLayout implements RefreshHeader {
    private ImageView mImage;
    private AnimationDrawable refreshingAnim;
    private float mCurTranslationX;
    public MyRefreshHeader(Context context) {
        this(context, null, 0);
    }

    public MyRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRefreshHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.widget_my_refresh_header, this);
        mImage = (ImageView) view.findViewById(R.id.iv_refresh_header);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {

    }

    /**
     * 状态改变时调用。
     * @param refreshLayout
     * @param oldState
     * @param newState
     */
    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case PullDownToRefresh: //下拉刷新开始。正在下拉还没松手时调用
                //每次重新下拉时，将图片资源重置为小车
                mImage.setImageResource(R.drawable.pull_to_refresh_people_0);
                break;
            case Refreshing: //正在刷新。只调用一次
                //状态切换为正在刷新状态时，设置图片资源为小车的动画并开始执行
                mImage.setImageResource(R.drawable.anim_mypull_refreshing);
                refreshingAnim = (AnimationDrawable) mImage.getDrawable();
                refreshingAnim.start();
                break;
            case RefreshFinish://刷新结束
                //刷新结束时调用该动画
                mCurTranslationX = mImage.getTranslationX();
                Animation translateAnimation = new TranslateAnimation(mCurTranslationX,1000,0,0);
                translateAnimation.setDuration(500);
                mImage.startAnimation(translateAnimation);
                break;
        }
    }

    /**
     * 动画结束后调用
     */
    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        // 结束动画
        if (refreshingAnim != null && refreshingAnim.isRunning()) {
            refreshingAnim.stop();
        }
        return 400;//延时400毫秒关闭下拉刷新
    }


    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {

    }

    /**
     * 下拉过程中不断调用此方法。从小变大的小车
     */

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
        // 下拉的百分比小于100%时，不断调用 setScale 方法改变图片大小
        if (percent < 1) {
            mImage.setScaleX(percent);
            mImage.setScaleY(percent);
        }
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }


}
