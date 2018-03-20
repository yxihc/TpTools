package com.taopao.rxjavaretrofitcutmvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImageAndTitleInfo;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.utils.PermissionActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.utils.StringUtilsActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author: 淘跑
 * @Data: 2018/3/19 16:27
 * @Use: 工具集合
 * <====修改记录====>
 * @Version 修改次数 v1
 * @Modifier 修改者
 * @Data 修改时间
 * @EditContent 修改内容
 */
public class UtilsFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener {
    private final int[] picResId = new int[]{R.mipmap.p10, R.mipmap.p11, R.mipmap.p12, R.mipmap.p12
    };
    private final String[] mTitles = new String[]{"StringUtils", "Permission管理", "监听网络状态","下载文件"};

    @BindView(R.id.rv_utils)
    RecyclerView mRvUtils;
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_utils, container, false);
        unbinder = ButterKnife.bind(this, view);

        initData();
        initRecycleyView();
        return view;
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

    private void initRecycleyView() {
        mRvUtils.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mAdapter = new BaseQuickAdapter<ImageAndTitleInfo,BaseViewHolder>(R.layout.item_rv,mImageAndTitleInfos) {
            @Override
            protected void convert(BaseViewHolder helper, ImageAndTitleInfo item) {
                helper.setImageResource(R.id.ivPic,item.getImageid());
                helper.setText(R.id.tvTitle,item.getTitle());
            }
        };
        mRvUtils.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    /**
     * Fragment 传值
     *
     * @param title 顶部标题
     * @return UtilsFragment
     */
    public static UtilsFragment getInstance(String title) {
        UtilsFragment fragment = new UtilsFragment();
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case  0:
                startActivity(new Intent(getActivity(), StringUtilsActivity.class));
                break;
            case  1:
                startActivity(new Intent(getActivity(), PermissionActivity.class));
                break;
        }
    }
}
