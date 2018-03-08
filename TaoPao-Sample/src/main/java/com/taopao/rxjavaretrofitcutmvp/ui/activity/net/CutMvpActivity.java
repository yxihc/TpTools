package com.taopao.rxjavaretrofitcutmvp.ui.activity.net;

import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.ZC1Activity;
import com.taopao.rxjavaretrofitcutmvp.ui.adapter.StaggerdAdapter;
import com.taopao.rxjavaretrofitcutmvp.ui.presenter.CutMvpPresenter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.model.ContactsUrl;
import com.taopao.rxjavaretrofitcutmvp.model.base.BaseResult;
import com.taopao.rxjavaretrofitcutmvp.model.response.BannerInfo;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
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

public class CutMvpActivity extends BaseActivity<CutMvpPresenter, CutMvpView> implements CutMvpView {


    private TextView mTv_content;
    private RecyclerView mRv_context;
    private StaggeredGridLayoutManager mLayout;
    private StaggerdAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutmvp);
        initView();
        initData();
    }

    private void initView() {
        setToolBar();
        mTv_content = (TextView) findViewById(R.id.tv_content);
        mRv_context = (RecyclerView) findViewById(R.id.rv_context);
        mLayout = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRv_context.setLayoutManager(mLayout);

    }

    private void initData() {
        mPresenter.getImgList(ContactsUrl.IMGLIST);

    }

    @Override
    public void setToolBar() {

    }

    @Override
    public CutMvpPresenter createPresenter() {
        return new CutMvpPresenter(this);
    }

    @Override
    public CutMvpView createView() {
        return this;
    }

    @Override
    public void onNetChanged(int netState) {

    }

    @Override
    public void onGetBannerResult(BaseResult<ArrayList<BannerInfo>> banner) {
        mTv_content.setText(banner.getData().get(0).getImg());
    }

    @Override
    public void onGetImgListResult(ImgListInfo imgListInfo) {
        ImgListInfo.ResultsBean resultsBean = new ImgListInfo.ResultsBean();
        resultsBean.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518024199898&di=5c2976d82553c111e43340de0c7dc1d6&imgtype=0&src=http%3A%2F%2Fold.bz55.com%2Fuploads1%2Fallimg%2F120303%2F1_120303174421_2.jpg");
        imgListInfo.getResults().add(resultsBean);
        mAdapter = new StaggerdAdapter(this, imgListInfo);

        mAdapter.setOnItemOnClickListener(new StaggerdAdapter.onItemOnClickListener() {
            @Override
            public void onItemClick(ImgListInfo.ResultsBean resultsBean, int position, View view) {
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(CutMvpActivity.this, view, "iv");
                Intent intent = new Intent(CutMvpActivity.this, ZC1Activity.class);
                intent.putExtra("url",resultsBean.getUrl());
                startActivity(intent, optionsCompat.toBundle());
            }

        });
        mRv_context.setAdapter(mAdapter);
    }

    public void getbanner(View view) {
        mPresenter.getBanner("2");
        
    }


}
