package com.taopao.rxjavaretrofitcutmvp.ui.presenter;

import android.content.Context;

import com.taopao.rxjavaretrofitcutmvp.model.base.BaseResult;
import com.taopao.rxjavaretrofitcutmvp.model.response.BannerInfo;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.contacts.CutMvpContract;
import com.taopao.rxjavaretrofitcutmvp.ui.contacts.IPresenterCallBack;
import com.taopao.rxjavaretrofitcutmvp.ui.model.CutMvpModel;

import java.util.ArrayList;

/**
 * @Author: 淘跑
 * @Data: 2018/1/29 22:18
 * @Use:
 */

public class CutMvpPresenter extends BasePresenter<CutMvpContract.View> implements CutMvpContract.Presenter{
    private final CutMvpModel mCutMvpModel;

    public CutMvpPresenter(Context context) {
        super(context);
        mCutMvpModel = new CutMvpModel();
    }
    @Override
    public void getBanner(String loaction) {
        getView().onLoading();
        mCutMvpModel.getBanner(loaction, new IPresenterCallBack<BaseResult<ArrayList<BannerInfo>>>() {
            @Override
            public void onSuccess(BaseResult<ArrayList<BannerInfo>> data) {
                if (getView() != null) {
                    getView().onBannerResult(data);
                }
            }
            @Override
            public void onFailure(String msg) {

            }
        });

    }

    @Override
    public void getImgList(String url) {
        getView().onLoading();
        mCutMvpModel.getImgList(url, new IPresenterCallBack<ImgListInfo>() {
            @Override
            public void onSuccess(ImgListInfo data) {
                if(getView()!=null) {
                    getView().onImgListResult(data);
                }
            }
            @Override
            public void onFailure(String msg) {

            }
        });
    }

}
