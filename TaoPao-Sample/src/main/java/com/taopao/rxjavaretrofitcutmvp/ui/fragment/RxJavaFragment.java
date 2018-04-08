package com.taopao.rxjavaretrofitcutmvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.rxjava.RxMapActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseFragment;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * @Author： 淘跑
 * @Date: 2018/4/8 13:18
 * @Use：
 * @
 * @-------------------修改记录-------------------@
 * @
 * @Modifier: 修改者
 * @Data: 修改时间
 * @Version: 修改次数 v1
 * @EditContent: 修改内容
 */

public class RxJavaFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.rv_mian)
    RecyclerView mRvMian;
    @BindView(R.id.srl_mian)
    SmartRefreshLayout mSrlMian;
    private ArrayList<String> mTitles;
    private ArrayList<String> mContents;
    private RvAdapter mRvAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_main_rxjava;
    }

    @Override
    protected void initView(View view) {
        mRvMian.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {
        mTitles = new ArrayList<>();
        mContents = new ArrayList<>();

        mTitles.add("Map操作符");
        mContents.add("基本是RxJava 最简单的操作符了作用是对上游发送的每一个事件应用一个函数，使得每一个事件都按照指定的函数去变化");


        mRvAdapter = new RvAdapter(R.layout.recycle_item_rxjava, mTitles);
        mRvMian.setAdapter(mRvAdapter);
        mRvAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM );
        mRvAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(getActivity(), RxMapActivity.class));
                break;
        }

    }


    class RvAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public RvAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_title, item);
            helper.setText(R.id.tv_content, mContents.get(helper.getLayoutPosition()));
        }
    }


    /**
     * Fragment 传值
     *
     * @param title 顶部标题
     * @return RxJavaFragment
     */
    public static RxJavaFragment getInstance(String title) {
        RxJavaFragment fragment = new RxJavaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

}
