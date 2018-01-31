package com.taopao.rxjavaretrofitcutmvp.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jaeger.library.StatusBarUtil;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;

public class Design1Activity extends BaseActivity {
    private ListView lv;
    private Toolbar toolbar;
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
    private AppBarLayout home_appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design1);
        initView();
    }

    @Override
    protected void initToolBar() {
    }

    @Override
    protected void initStatusBar() {
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        home_appbar = (AppBarLayout) findViewById(R.id.home_appbar);

        ArrayAdapter<String> dataAdpater = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, citys);
        lv.setAdapter(dataAdpater);
        lv.setAdapter(dataAdpater);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StatusBarUtil.setColor(Design1Activity.this, getResources().getColor(R.color.colorPrimary),0);
            }
        });

    }


}
