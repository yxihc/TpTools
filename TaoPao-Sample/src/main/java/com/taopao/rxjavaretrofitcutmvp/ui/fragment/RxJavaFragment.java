package com.taopao.rxjavaretrofitcutmvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseFragment;
import butterknife.BindView;
import butterknife.Unbinder;

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

public class RxJavaFragment extends BaseFragment {
    @BindView(R.id.rv_mian)
    RecyclerView mRvMian;
    @BindView(R.id.srl_mian)
    SmartRefreshLayout mSrlMian;
    Unbinder unbinder;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_main_rxjava;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

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
