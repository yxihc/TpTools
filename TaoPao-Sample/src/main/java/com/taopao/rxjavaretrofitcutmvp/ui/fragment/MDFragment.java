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
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.SwipeBackActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.BottomBehaviorActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.Design1Activity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.Design2Activity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.Design3Activity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.Design4Activity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.Design5Activity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.ImageTouActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.ImmersiveActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.SearchViewActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.TabLayoutActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.ZhiHuActivity;
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
 * @Data: 2018/3/9 15:55
 * @Use:
 */

public class MDFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener {


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
        View view = inflater.inflate(R.layout.fragment_main_md, container, false);
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
    public static MDFragment getInstance(String title) {
        MDFragment fragment = new MDFragment();
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
    final int[] picResId = new int[]{R.mipmap.p22,R.mipmap.p22,R.mipmap.p22,
            R.mipmap.p22, R.mipmap.p22,R.mipmap.p22,
            R.mipmap.p22, R.mipmap.p22,R.mipmap.p22,R.mipmap.p22,R.mipmap.p22,R.mipmap.p22
    };
    final String[] mTitles = new String[]{"沉浸式", "下拉显示图片",
            "文字跟随下拉","Toolbar上滑消失","Toolbar文字居中",
            "跟随移动","SearchView","TabLayout"
            ,"向上滑动新页面","仿知乎上拉消失底部栏"
            ,"改变状态栏假toolbar背景"
            ,"侧滑关闭"
    };

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent;
        switch (position) {
            case  0:
                intent = new Intent(getActivity(), ImmersiveActivity.class);
                startActivity(intent);
                break;
            case  1:
                intent = new Intent(getActivity(), Design1Activity.class);
                startActivity(intent);
                break;
            case  2:
                intent = new Intent(getActivity(), Design2Activity.class);
                startActivity(intent);
                break;
            case  3:
                intent = new Intent(getActivity(), Design3Activity.class);
                startActivity(intent);
                break;
            case  4:
                intent = new Intent(getActivity(), Design4Activity.class);
                startActivity(intent);
                break;
            case  5:
                intent = new Intent(getActivity(), Design5Activity.class);
                startActivity(intent);
                break;
            case  6:
                intent = new Intent(getActivity(), SearchViewActivity.class);
                startActivity(intent);
                break;
            case  7:
                intent = new Intent(getActivity(), TabLayoutActivity.class);
                startActivity(intent);
                break;
            case  8:
                intent = new Intent(getActivity(), BottomBehaviorActivity.class);
                startActivity(intent);
                break;
            case  9:
                intent = new Intent(getActivity(), ZhiHuActivity.class);
                startActivity(intent);
                break;
            case  10:
                intent = new Intent(getActivity(), ImageTouActivity.class);
                startActivity(intent);
                break;

            case  11:
                intent = new Intent(getActivity(), SwipeBackActivity.class);
                intent.putExtra(SwipeBackActivity.EXTRA_IS_TRANSPARENT, false);
                startActivity(intent);
                break;
        }
    }

}
