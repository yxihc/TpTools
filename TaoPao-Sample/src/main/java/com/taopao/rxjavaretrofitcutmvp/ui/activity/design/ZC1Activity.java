package com.taopao.rxjavaretrofitcutmvp.ui.activity.design;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.taopao.rxjavaretrofitcutmvp.R;

public class ZC1Activity extends AppCompatActivity {

    private ImageView iv;
    private String mUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc1);
        if(getIntent().hasExtra("url")) {
            mUrl = getIntent().getExtras().getString("url", "");
        }
        initView();


    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        if(mUrl.isEmpty()) {
            iv.setImageResource(R.mipmap.bg);
        }else{
            Glide.with(this)
                    .load(mUrl)
                    .into(iv);
        }

    }
}
