package com.taopao.rxjavaretrofitcutmvp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Author：淘跑
 * @Date: 2018/4/2 14:21
 * @Use： 使用了Mvp架构的Activity的基类
 * @
 * @-------------------修改记录-------------------@
 * @
 * @Modifier: 修改者  v1
 * @Data: 修改时间
 * @Version: 修改次数
 * @EditContent: 修改内容
 */

public abstract class BaseMvpActivity<P extends BasePresenter<V>, V extends BaseView> extends BaseActivity {
    public P mPresenter;
    private CompositeDisposable mCompositeDisposable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    /**
     * 初始化presenter和view
     */
    private void init() {
        mPresenter = createPresenter();
        //判断是否非空 以防子类并没有使用此架构
        if(mPresenter!=null) {
            mPresenter.attachView(createView());
        }
        synchronized (mActivities) {
            mActivities.add(this);
        }
    }


    /**
     * 用于创建Presenter(由子类实现)
     * @return Presenter
     */
    public abstract P createPresenter();

    /**用于创建view(由子类实现)
     * @return view
     */
    public abstract V createView();



    /**
     * 得到Presenter
     * @return
     */
    public P getPresenter() {
        return mPresenter;
    }

    /**
     * 添加activity里的订阅者 对订阅者统一管理
     * @param disposable
     */
    protected void addActivityDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //判断是否非空 以防子类并没有使用此架构
        if(mPresenter!=null) {
            mPresenter.detachView();
        }
        //取消订阅者
        if (mCompositeDisposable != null) {
            //取消订阅
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }

    @Override
    public void onNetChanged(int netWorkState) {

    }
}
