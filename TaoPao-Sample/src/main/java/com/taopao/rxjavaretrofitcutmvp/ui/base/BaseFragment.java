package com.taopao.rxjavaretrofitcutmvp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃  神兽保佑
 * 　　　　┃　　　┃  代码无bug
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　     ┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @Author: 淘跑
 * @Data: 2018/1/30 09:10
 * @Use: Fragment基类
 */
public abstract class BaseFragment<P extends BasePresenter<V>, V extends BaseView> extends Fragment {

    /**
     * 管理订阅者
     */
    private CompositeDisposable mCompositeDisposable;

    /**
     * 处理数据
     */
    public P mPresenter;

    /**
     * 用于创建Presenter(由子类实现)
     *
     * @return Presenter
     */
    public abstract P createPresenter();

    /**
     * 用于创建view(由子类实现)
     *
     * @return view
     */
    public abstract V createView();

    /**
     * 添加activity里的订阅者 对订阅者统一管理
     * @param disposable
     */
    protected void addFragmentDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }
    /**
     * 初始化presenter和view
     */
    private void init() {
        mPresenter = createPresenter();
        //判断是否非空 以防子类并没有使用此架构
        if (mPresenter != null) {
            mPresenter.attachView(createView());
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //判断是否非空 以防子类并没有使用此架构
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
