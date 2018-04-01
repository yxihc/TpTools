package com.taopao.rxjavaretrofitcutmvp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImageAndTitleInfo;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.DialogActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.DragItemActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.FrActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.GlideActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.RandomTextActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.WebViewActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.recyclerview.RvBaseAdapterActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.refresh.JDRefreshActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.refresh.MeiTuanRefreshActivity;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * _________
 * /\        \
 * \_| Topic |
 * |       |
 * |   ____|_
 * \_/______/
 *
 * @Author: 淘跑
 * @Data: 2018/3/9 15:28
 * @Use:
 */

public class UiFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener {
    private final int[] picResId = new int[]{R.mipmap.p5,R.mipmap.p5, R.mipmap.p33, R.mipmap.p33,R.mipmap.p6, R.mipmap.p7,R.mipmap.p8,R.mipmap.p12,R.mipmap.p12};
    private final String[] mTitles = new String[]{"随机显示TextView", "Dialog",
            "仿微信朋友圈下拉刷新","仿微信带进度条网页","RecyclerView拖动","Glide使用","BaseRecyclerViewAdapterHelper", "仿美团下拉刷新", "仿京东到家下拉刷新"};

    @BindView(R.id.rv_ui)
    RecyclerView mRvUi;
    Unbinder unbinder;
    private String mTitle;
    private ArrayList<ImageAndTitleInfo> mImageAndTitleInfos;
    private BaseQuickAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //得到title
        mTitle = getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_ui, container, false);
        unbinder = ButterKnife.bind(this, view);

        initData();
        initRecycleyView();


        return view;
    }

    private void initRecycleyView() {
        mRvUi.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mAdapter = new BaseQuickAdapter<ImageAndTitleInfo,BaseViewHolder>(R.layout.item_rv,mImageAndTitleInfos) {
            @Override
            protected void convert(BaseViewHolder helper, ImageAndTitleInfo item) {
                helper.setImageResource(R.id.ivPic,item.getImageid());
                helper.setText(R.id.tvTitle,item.getTitle());
            }
        };
        mRvUi.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    /**
     * Fragment 传值
     *
     * @param title
     * @return
     */
    public static UiFragment getInstance(String title) {
        UiFragment fragment = new UiFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void initData(){
        mImageAndTitleInfos = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            ImageAndTitleInfo imageAndTitleInfo = new ImageAndTitleInfo();
            imageAndTitleInfo.setTitle(mTitles[i]);
            imageAndTitleInfo.setImageid(picResId[i]);
            mImageAndTitleInfos.add(imageAndTitleInfo);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        switch (position) {
            case 0:
                startActivity(new Intent(getActivity(), RandomTextActivity.class));
                break;
            case 1:
                startActivity(new Intent(getActivity(), DialogActivity.class));
                break;
            case 2:
                startActivity(new Intent(getActivity(), FrActivity.class));
                break;
            case 3:
                WebViewActivity.loadUrl(getActivity(), "https://github.com/404NotFuond/RxJava-Retrofit-CutMvp");
                break;
            case 4:
                startActivity(new Intent(getActivity(), DragItemActivity.class));
                break;
            case 5:
                startActivity(new Intent(getActivity(), GlideActivity.class));
                break;
            case 6:
                startActivity(new Intent(getActivity(), RvBaseAdapterActivity.class));
                break;
            case 7:
                startActivity(new Intent(getActivity(), MeiTuanRefreshActivity.class));
                break;
            case 8:
                startActivity(new Intent(getActivity(), JDRefreshActivity.class));
                break;
        }
    }

}

