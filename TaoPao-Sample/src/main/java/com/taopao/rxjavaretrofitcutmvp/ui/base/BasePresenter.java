package com.taopao.rxjavaretrofitcutmvp.ui.base;

import android.content.Context;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

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
 * @Data: 2018/1/29 21:55
 * @Use:  Presenter基类
 */
public abstract class BasePresenter<V extends BaseView> {
    private Reference<V> mView;
    private CompositeDisposable mCompositeDisposable;

    public Context mContext;
    public BasePresenter(Context context) {
        mContext = context;
    }
    public void attachView(V view) {
        //使用软引用优化内存
        mView = new WeakReference<V>(view);
    }
    public void detachView() {
        if(mCompositeDisposable!=null) {
            mCompositeDisposable.clear();
            mCompositeDisposable=null;
        }
        if (mView != null) {
            mView.clear();
            mView=null;
        }
    }
    /**
     * 得到view层
     * @return V
     */
    public V getView() {
        return mView != null ? mView.get() : null;
    }

    /**
     * 添加订阅者 对订阅者统一管理
     * @param disposable
     */
    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }
}
