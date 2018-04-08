package com.taopao.rxjavaretrofitcutmvp.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Author: 淘跑
 * @Data: 2018/1/30 09:10
 * @Use: Fragment基类
 */
public abstract class BaseMvpFragment<P extends BasePresenter<V>, V extends BaseView> extends BaseFragment {
    /**
     * 处理数据
     */
    private P mPresenter;

    /**
     * 用于创建Presenter(由子类实现)
     *
     * @return Presenter
     */
    protected abstract P createPresenter();

    /**
     * 用于创建view(由子类实现)
     *
     * @return view
     */
    protected abstract V createView();
    /**
     * 初始化数据
     *
     * @return view
     */
    protected abstract void initMvpData();

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initData() {
        attachView();
        initMvpData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        detachView();
    }

    /**
     * 初始化presenter和view
     */
    private void attachView() {
        mPresenter = createPresenter();
        //判断是否非空 以防子类并没有使用此架构
        if(mPresenter!=null) {
            mPresenter.attachView(createView());
        }
    }
    /**
     * 解绑view
     */
    private void detachView(){
        //判断是否非空 以防子类并没有使用此架构
        if(mPresenter!=null) {
            mPresenter.detachView();
        }
    }

}
