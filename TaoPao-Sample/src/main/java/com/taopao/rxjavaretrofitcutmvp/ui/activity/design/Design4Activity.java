package com.taopao.rxjavaretrofitcutmvp.ui.activity.design;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.taopao.rxjavaretrofitcutmvp.R;

public class Design4Activity extends AppCompatActivity {
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
            "安徽"
    };
    private CollapsingToolbarLayout collapsing_layout;
    private AppBarLayout app_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design4);
        initView();
        lv = (ListView) findViewById(R.id.lv);

        ArrayAdapter<String> dataAdpater = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, citys);
        lv.setAdapter(dataAdpater);
        lv.setAdapter(dataAdpater);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_behavior);
//        toolbar.getBackground().setAlpha(0);//toolbar透明度初始化为0
    }

    private void initView() {
        collapsing_layout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_layout);
        app_bar = (AppBarLayout) findViewById(R.id.app_bar);
        app_bar.setExpanded(false,true);
    }
}
