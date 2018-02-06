package com.taopao.rxjavaretrofitcutmvp.ui.activity.design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design4);
        lv = (ListView) findViewById(R.id.lv);

        ArrayAdapter<String> dataAdpater = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, citys);
        lv.setAdapter(dataAdpater);
        lv.setAdapter(dataAdpater);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_behavior);
//        toolbar.getBackground().setAlpha(0);//toolbar透明度初始化为0
    }
}
