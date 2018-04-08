package com.taopao.rxjavaretrofitcutmvp.ui.model;

import com.taopao.rxjavaretrofitcutmvp.http.ApiRetrofit;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;
import com.taopao.rxjavaretrofitcutmvp.rx.RxObserver;
import com.taopao.rxjavaretrofitcutmvp.rx.RxTransformer;
import com.taopao.rxjavaretrofitcutmvp.ui.contract.IPresenterCallBack;
import com.taopao.rxjavaretrofitcutmvp.ui.contract.ISplashContract;

import io.reactivex.disposables.Disposable;

/**
 * @Author：淘跑
 * @Date: 2018/4/8 21:21
 * @Use：
 * @
 * @-------------------修改记录-------------------@
 * @
 * @Modifier: 修改者  v1
 * @Data: 修改时间
 * @Version: 修改次数
 * @EditContent: 修改内容
 */

public class SplashModel implements ISplashContract.Model {
    @Override
    public void getImgPage(String page, final IPresenterCallBack<ImgListInfo> callBack) {
        ApiRetrofit.getInstance()
                .getImgListPage(page)
                .compose(RxTransformer.<ImgListInfo>switchSchedulers())
                .subscribe(new RxObserver<ImgListInfo>() {
                    @Override
                    public void onSuccess(ImgListInfo imgListInfo) {
                        callBack.onSuccess(imgListInfo);
                    }
                    @Override
                    public void onError(Throwable e) {
                        //传回失败信息
                        callBack.onFailure(e.getMessage());
                    }
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                });
    }
}
