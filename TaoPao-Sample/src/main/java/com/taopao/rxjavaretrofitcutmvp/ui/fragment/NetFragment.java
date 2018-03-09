package com.taopao.rxjavaretrofitcutmvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.NetStateActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.net.CutMvpActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.net.DownLoadApkActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.net.DownLoadFileActivity;
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
 * @Data: 2018/3/9 15:54
 * @Use:
 */

public class NetFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener {
    private final int[] picResId = new int[]{R.mipmap.p10, R.mipmap.p11, R.mipmap.p12, R.mipmap.p12
    };
    private final String[] mTitles = new String[]{"CutMvp", "DownloadeApk并安装", "监听网络状态","下载文件"};
    @BindView(R.id.rv_net)
    RecyclerView mRvNet;
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
        View view = inflater.inflate(R.layout.fragment_main_net, container, false);
        unbinder = ButterKnife.bind(this, view);

        initData();
        initRecycleyView();

        return view;
    }

    private void initRecycleyView() {
        mRvNet.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mAdapter = new BaseQuickAdapter<ImageAndTitleInfo,BaseViewHolder>(R.layout.item_rv,mImageAndTitleInfos) {
            @Override
            protected void convert(BaseViewHolder helper, ImageAndTitleInfo item) {
                helper.setImageResource(R.id.ivPic,item.getImageid());
                helper.setText(R.id.tvTitle,item.getTitle());
            }
        };
        mRvNet.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    /**
     * Fragment 传值
     *
     * @param title
     * @return
     */
    public static NetFragment getInstance(String title) {
        NetFragment fragment = new NetFragment();
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
            case  0:
                startActivity(new Intent(getActivity(),CutMvpActivity.class));
                break;
            case  1:
                startActivity(new Intent(getActivity(),DownLoadApkActivity.class));
                break;
            case  2:
                startActivity(new Intent(getActivity(),NetStateActivity.class));
                break;
            case  3:
                startActivity(new Intent(getActivity(),DownLoadFileActivity.class));
                break;
        }
    }

}
