package com.taopao.rxjavaretrofitcutmvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.model.base.BaseResult;
import com.taopao.rxjavaretrofitcutmvp.model.response.BannerInfo;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.presenter.CutMvpPresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.view.CutMvpView;

import java.util.ArrayList;

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
 * @Data: 2018/1/29 22:17
 * @Use:
 */

public class CutMvpActivity extends BaseActivity<CutMvpPresenter,CutMvpView> implements CutMvpView {


    private TextView mTv_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutmvp);
        mTv_content = (TextView) findViewById(R.id.tv_content);
    }

    @Override
    public CutMvpPresenter createPresenter() {
        return new CutMvpPresenter();
    }
    @Override
    public CutMvpView createView() {
        return this;
    }
    @Override
    public void onGetBannerResult(BaseResult<ArrayList<BannerInfo>> banner) {
        mTv_content.setText(banner.getData().get(0).getRedirectContent());
    }

    public void getbanner(View view){
        mPresenter.getBanner("2");
    }
}
