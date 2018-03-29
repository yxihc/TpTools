package com.taopao.rxjavaretrofitcutmvp.ui.activity.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jaeger.library.StatusBarUtil;
import com.taopao.rxjavaretrofitcutmvp.R;

public class SwipeBackActivity extends AppCompatActivity {

    public static final String EXTRA_IS_TRANSPARENT="is_transparent";
    private Toolbar toolbar;
    private int mStatusBarColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置右滑动返回
        setContentView(R.layout.activity_swipe_back);
        mStatusBarColor = getResources().getColor(R.color.colorPrimary);
        initView();




    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        StatusBarUtil.setTranslucent(this, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
