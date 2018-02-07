package com.taopao.rxjavaretrofitcutmvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.taopao.rxjavaretrofitcutmvp.R;

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
 * @Data: 2018/2/7 14:25
 * @Use:
 */

public class TabLayoutFragment extends Fragment {
    private ListView lv;
    private String citys[] = new String[]{
            "北京",
            "上海",
            "广州",
            "成都",
            "安徽",
            "北京",
            "上海",
            "广州",
            "成都",
            "安徽",
            "北京",
            "上海",
            "广州",
            "成都",
            "安徽",
            "北京",
            "上海",
            "广州",
            "成都",
            "安徽"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        ScrollView scrollView = new ScrollView(getActivity());
//        ScrollView.LayoutParams layoutParams=new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,ScrollView.LayoutParams.MATCH_PARENT);
//        scrollView.setLayoutParams(layoutParams);

        String name = getArguments().getString("name");

        if(name.equals("1")) {
            View view = inflater.inflate(R.layout.fragment_tablayout, container, false);
            lv = (ListView) view.findViewById(R.id.lv);
            ArrayAdapter<String> dataAdpater = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1, citys);
            lv.setAdapter(dataAdpater);
            lv.setAdapter(dataAdpater);
            return view;
        }else {

            TextView textView = new TextView(getActivity());
            textView.setTextSize(50);
            textView.setGravity(Gravity.CENTER);
            textView.setText(name);
//        scrollView.addView(textView);
            return textView;
        }
    }
    public static TabLayoutFragment getInstance(String name){
        TabLayoutFragment tabLayoutFragment = new TabLayoutFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        tabLayoutFragment.setArguments(bundle);
        return tabLayoutFragment;
    }
}
