package com.taopao.rxjavaretrofitcutmvp.ui.presenter;

import android.content.Context;

import com.taopao.rxjavaretrofitcutmvp.http.Api;
import com.taopao.rxjavaretrofitcutmvp.http.ApiRetrofit;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;
import com.taopao.rxjavaretrofitcutmvp.rx.RxObserver;
import com.taopao.rxjavaretrofitcutmvp.rx.RxTransformer;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
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

public class SplashPresenter extends BasePresenter<SplashView> {
    public SplashPresenter(Context context) {
        super(context);
    }


    public void getImagePage(String page){
        ApiRetrofit.getInstance()
                .getImgListPage(page)
                .compose(RxTransformer.<ImgListInfo>switchSchedulers())
                .subscribe(new RxObserver<ImgListInfo>() {
                    @Override
                    public void onSuccess(ImgListInfo imgListInfo) {
                        if(getView()!=null) {
                            getView().onGetImgListPageResult(imgListInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //传回失败信息
                        if(getView()!=null) {
                            getView().onGetImgListPageError(e);
                        }
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}
