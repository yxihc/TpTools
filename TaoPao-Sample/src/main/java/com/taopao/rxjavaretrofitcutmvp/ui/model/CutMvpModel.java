package com.taopao.rxjavaretrofitcutmvp.ui.model;

import com.taopao.rxjavaretrofitcutmvp.http.ApiRetrofit;
import com.taopao.rxjavaretrofitcutmvp.model.base.BaseResult;
import com.taopao.rxjavaretrofitcutmvp.model.response.BannerInfo;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;
import com.taopao.rxjavaretrofitcutmvp.rx.RxObserver;
import com.taopao.rxjavaretrofitcutmvp.rx.RxTransformer;
import com.taopao.rxjavaretrofitcutmvp.ui.contract.ICutMvpContract;
import com.taopao.rxjavaretrofitcutmvp.ui.contract.IPresenterCallBack;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author：淘跑
 * @Date: 2018/4/2 16:14
 * @Use：
 * @
 * @-------------------修改记录-------------------@
 * @
 * @Modifier: 修改者
 * @Data: 修改时间
 * @Version: 修改次数 v1
 * @EditContent: 修改内容
 */

public class CutMvpModel implements ICutMvpContract.Model {

    @Override
    public void getBanner(String loaction, final IPresenterCallBack<BaseResult<ArrayList<BannerInfo>>> callBack) {
        ApiRetrofit.getInstance()
                .getBanner(loaction)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<BaseResult<ArrayList<BannerInfo>>>() {
                    @Override
                    public void onSuccess(BaseResult<ArrayList<BannerInfo>> arrayListBaseResult) {
                        callBack.onSuccess(arrayListBaseResult);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        //统一管理订阅者(此步不可省略)
//                        addDisposable(d);
                    }
                });
    }

    @Override
    public void getImgList(String url, final IPresenterCallBack<ImgListInfo> callBack) {
        ApiRetrofit.getInstance()
                .getImgList(url)
                .compose(RxTransformer.<ImgListInfo>switchSchedulers())
                .subscribe(new RxObserver<ImgListInfo>() {
                    @Override
                    public void onSuccess(ImgListInfo imgListInfo) {
                        callBack.onSuccess(imgListInfo);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
//                        addDisposable(d);
                    }
                });
    }
}
