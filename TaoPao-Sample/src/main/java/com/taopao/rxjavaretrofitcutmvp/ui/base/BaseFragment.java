package com.taopao.rxjavaretrofitcutmvp.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author：淘跑
 * @Date: 2018/4/2 15:11
 * @Use： Fragment基类
 * @
 * @-------------------修改记录-------------------@
 * @
 * @Modifier: 修改者  v1
 * @Data: 修改时间
 * @Version: 修改次数
 * @EditContent: 修改内容
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mBind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentViewLayoutID() != 0) {
            return inflater.inflate(getContentViewLayoutID(), null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化黄油刀
        mBind = ButterKnife.bind(this, view);
        initView(view);
        initData();
    }
    /**
     * 得到布局文件Layout的ID
     *
     * @return
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 初始化布局
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mBind!=null) {
            mBind.unbind();
        }
    }


    /**
     * 防止快速点击
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }
}
