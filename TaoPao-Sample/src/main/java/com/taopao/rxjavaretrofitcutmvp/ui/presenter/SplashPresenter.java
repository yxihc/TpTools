package com.taopao.rxjavaretrofitcutmvp.ui.presenter;

import android.content.Context;

import com.taopao.rxjavaretrofitcutmvp.http.Api;
import com.taopao.rxjavaretrofitcutmvp.http.ApiRetrofit;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;
import com.taopao.rxjavaretrofitcutmvp.rx.RxObserver;
import com.taopao.rxjavaretrofitcutmvp.rx.RxTransformer;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.contract.IPresenterCallBack;
import com.taopao.rxjavaretrofitcutmvp.ui.contract.ISplashContract;
import com.taopao.rxjavaretrofitcutmvp.ui.model.SplashModel;
import com.taopao.rxjavaretrofitcutmvp.ui.view.SplashView;

import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;

/**
 * _________
 * /\        \
 * \_| Topic |
 * |       |
 * |   ____|_
 * \_/______/
 *
 * @Author: 淘跑
 * @Data: 2018/3/9 09:28
 * @Use:
 */

public class SplashPresenter extends BasePresenter<ISplashContract.View> implements ISplashContract.Presenter {
    private final SplashModel mSplashModel;
    public SplashPresenter(Context context) {
        super(context);
        mSplashModel = new SplashModel();
    }
    @Override
    public void getImagePage(String page) {
        mSplashModel.getImgPage(page, new IPresenterCallBack<ImgListInfo>() {
            @Override
            public void onSuccess(ImgListInfo data) {
                getView().onImgListPageResult(data);
            }
            @Override
            public void onFailure(String msg) {
                getView().onImgListPageError(msg);
            }
        });
    }
}
