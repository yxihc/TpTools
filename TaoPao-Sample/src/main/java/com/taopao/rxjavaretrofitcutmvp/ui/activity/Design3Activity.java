package com.taopao.rxjavaretrofitcutmvp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jaeger.library.StatusBarUtil;
import com.taopao.rxjavaretrofitcutmvp.R;

public class Design3Activity extends AppCompatActivity {
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
        setContentView(R.layout.activity_design3);
//        StatusBarUtil.setTranslucent(this, 0);
        lv = (ListView) findViewById(R.id.lv);

        ArrayAdapter<String> dataAdpater = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, citys);
        lv.setAdapter(dataAdpater);
        lv.setAdapter(dataAdpater);
    }
}
