package com.taopao.rxjavaretrofitcutmvp.ui.activity.design;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.http.ApiRetrofit;
import com.taopao.rxjavaretrofitcutmvp.model.ContactsUrl;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;
import com.taopao.rxjavaretrofitcutmvp.rx.RxObserver;
import com.taopao.rxjavaretrofitcutmvp.ui.adapter.StaggerdAdapter;
import com.taopao.rxjavaretrofitcutmvp.utils.UIUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZhiHuActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private RecyclerView rv_context;
    private FloatingActionButton fab;
    private CoordinatorLayout behavior_demo_coordinatorLayout;
    public StaggerdAdapter Adapter;
    private StaggeredGridLayoutManager mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_hu);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initView();

        initData();
    }

    private void initData() {
        UIUtils.showWaitingDialog(this, "请骚等...");
        ApiRetrofit.getInstance()
                .getImgList("http://gank.io/api/data/福利/10/3")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ImgListInfo>() {
                    @Override
                    public void onSuccess(ImgListInfo imgListInfo) {
                        Adapter = new StaggerdAdapter(ZhiHuActivity.this, imgListInfo);
                        rv_context.setAdapter(Adapter);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv_context = (RecyclerView) findViewById(R.id.rv_context);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        behavior_demo_coordinatorLayout = (CoordinatorLayout) findViewById(R.id.behavior_demo_coordinatorLayout);
        rv_context = (RecyclerView) findViewById(R.id.rv_context);
        mLayout = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv_context.setLayoutManager(mLayout);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:

                break;
        }
    }
}
