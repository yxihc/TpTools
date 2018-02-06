package com.taopao.rxjavaretrofitcutmvp.ui.activity.design;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.taopao.rxjavaretrofitcutmvp.R;

public class Design2Activity extends AppCompatActivity {

    private AppBarLayout mAppBarLayout;
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
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design2);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),0);


        mToolbar = (Toolbar) findViewById(R.id.toolbara);
        setSupportActionBar(mToolbar);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        //滑动监听
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean misAppbarExpand = true;
            View fab = findViewById(R.id.fab);
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int scrollRange = appBarLayout.getTotalScrollRange();
                float fraction = 1f * (scrollRange + verticalOffset) / scrollRange;
                if (fraction < 0.1 && misAppbarExpand) {
                    misAppbarExpand = false;
                    fab.animate().scaleX(0).scaleY(0);
                    Toast.makeText(Design2Activity.this, "关闭", Toast.LENGTH_SHORT).show();
                    if (fraction > 0.8 && !misAppbarExpand) {
                        misAppbarExpand = true;
                        fab.animate().scaleX(1).scaleY(1);
                        Toast.makeText(Design2Activity.this, "展开", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        lv = (ListView) findViewById(R.id.lv);

        ArrayAdapter<String> dataAdpater = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, citys);
        lv.setAdapter(dataAdpater);
        lv.setAdapter(dataAdpater);

    }
}
