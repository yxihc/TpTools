package com.taopao.rxjavaretrofitcutmvp.ui.activity.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.meituan.android.walle.WalleChannelReader;
import com.taopao.rxjavaretrofitcutmvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StringUtilsActivity extends AppCompatActivity {
    @BindView(R.id.tv_channel)
    TextView mTvChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_utils);
        ButterKnife.bind(this);

//        FileUtils.createDirs(AppConfig.fileRoot+ File.separator+"666");

        String channel = WalleChannelReader.getChannel(this.getApplicationContext());
        mTvChannel.setText(channel);
    }
}
