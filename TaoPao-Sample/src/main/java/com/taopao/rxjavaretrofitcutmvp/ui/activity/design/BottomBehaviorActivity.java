package com.taopao.rxjavaretrofitcutmvp.ui.activity.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.http.ApiRetrofit;
import com.taopao.rxjavaretrofitcutmvp.model.ContactsUrl;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;
import com.taopao.rxjavaretrofitcutmvp.rx.RxObserver;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.net.CutMvpActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.adapter.StaggerdAdapter;
import com.taopao.rxjavaretrofitcutmvp.utils.SnackbarUtils;
import com.taopao.rxjavaretrofitcutmvp.utils.UIUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BottomBehaviorActivity extends AppCompatActivity {

    private RecyclerView rv_context;
    private RelativeLayout design_bottom_sheet_bar;
    private TextView bottom_sheet_tv;
    private ImageView bottom_sheet_iv;
    private RelativeLayout design_bottom_sheet;
    private CoordinatorLayout bottom_sheet_demo_coordinatorLayout;
    private StaggeredGridLayoutManager mLayout;
    private StaggerdAdapter Adapter;
    private boolean isSetBottomSheetHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_behavior);
        initView();
        initData();
    }

    private void initData() {
        UIUtils.showWaitingDialog(this, "请骚等...");
        ApiRetrofit.getInstance()
                .getImgList(ContactsUrl.IMGLIST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ImgListInfo>() {
                    @Override
                    public void onSuccess(ImgListInfo imgListInfo) {
                        Adapter = new StaggerdAdapter(BottomBehaviorActivity.this, imgListInfo);

                        Adapter.setOnItemOnClickListener(new StaggerdAdapter.onItemOnClickListener() {
                            @Override
                            public void onItemClick(ImgListInfo.ResultsBean resultsBean, int position, View view) {
//                                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(BottomBehaviorActivity.this, view, "iv");
//                                Intent intent = new Intent(BottomBehaviorActivity.this, ZC1Activity.class);
//                                intent.putExtra("url",resultsBean.getUrl());
//                                startActivity(intent, optionsCompat.toBundle());

                                Glide.with(BottomBehaviorActivity.this)
                                        .load(resultsBean.getUrl())
                                        .into(bottom_sheet_iv);
                            }


                        });
                        rv_context.setAdapter(Adapter);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                });
    }



    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);

        //修改SetBottomSheet的高度 留出顶部工具栏的位置
        if(!isSetBottomSheetHeight){
            CoordinatorLayout.LayoutParams linearParams =(CoordinatorLayout.LayoutParams) design_bottom_sheet.getLayoutParams();
            linearParams.height=bottom_sheet_demo_coordinatorLayout.getHeight()-design_bottom_sheet_bar.getHeight();
            design_bottom_sheet.setLayoutParams(linearParams);
            isSetBottomSheetHeight=true;
        }

    }
    private void initView() {
        rv_context = (RecyclerView) findViewById(R.id.rv_context);
        design_bottom_sheet_bar = (RelativeLayout) findViewById(R.id.design_bottom_sheet_bar);
        bottom_sheet_tv = (TextView) findViewById(R.id.bottom_sheet_tv);
        bottom_sheet_iv = (ImageView) findViewById(R.id.bottom_sheet_iv);
        design_bottom_sheet = (RelativeLayout) findViewById(R.id.design_bottom_sheet);
        bottom_sheet_demo_coordinatorLayout = (CoordinatorLayout) findViewById(R.id.bottom_sheet_demo_coordinatorLayout);


        rv_context = (RecyclerView) findViewById(R.id.rv_context);
        mLayout = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv_context.setLayoutManager(mLayout);
    }
}
