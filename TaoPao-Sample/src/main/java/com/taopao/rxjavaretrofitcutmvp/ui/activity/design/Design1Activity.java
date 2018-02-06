package com.taopao.rxjavaretrofitcutmvp.ui.activity.design;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.jaeger.library.StatusBarUtil;
import com.taopao.rxjavaretrofitcutmvp.R;

public class Design1Activity extends AppCompatActivity {

    private Toolbar toolbar;
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design1);
        StatusBarUtil.setTranslucent(this, 0);
        initView();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        Toolbar viewById = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(viewById);
        lv = (ListView) findViewById(R.id.lv);

        ArrayAdapter<String> dataAdpater = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, citys);
        lv.setAdapter(dataAdpater);
        lv.setAdapter(dataAdpater);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StatusBarUtil.setColor(Design1Activity.this, getResources().getColor(R.color.colorPrimary),0);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
}
