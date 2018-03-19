package com.taopao.rxjavaretrofitcutmvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.taopao.rxjavaretrofitcutmvp.R;

/**
 *  @Author: 淘跑
 *  @Data: 2018/3/19 16:27
 *  @Use:  工具集合
 *                 <====修改记录====>
 *  @Version 修改次数 v1
 *  @Modifier 修改者
 *  @Data 修改时间
 *  @EditContent 修改内容
 *
 *
 */
public class UtilsFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_utils, container, false);
        return view;
    }

    /**
     * Fragment 传值
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
}
