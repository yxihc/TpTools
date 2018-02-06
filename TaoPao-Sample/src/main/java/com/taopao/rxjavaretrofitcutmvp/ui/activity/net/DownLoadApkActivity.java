package com.taopao.rxjavaretrofitcutmvp.ui.activity.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.http.download.Download;
import com.taopao.rxjavaretrofitcutmvp.service.DownLoadService;
import com.taopao.rxjavaretrofitcutmvp.utils.StringUtils;

public class DownLoadApkActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton btn_download;
    private SeekBar cb;
    private TextView tv_pross;
    public static final String MESSAGE_PROGRESS = "message_progress";
    private LocalBroadcastManager bManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_apk);
        initView();
        registerReceiver();

    }

    private void initView() {
        btn_download = (AppCompatButton) findViewById(R.id.btn_download);
        cb = (SeekBar) findViewById(R.id.cb);
        cb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //拦截seekbar的触摸事件  禁止触摸
                return true;
            }
        });
        tv_pross = (TextView) findViewById(R.id.tv_pross);

        btn_download.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_download:
                Intent intent = new Intent(DownLoadApkActivity.this, DownLoadService.class);
                startService(intent);
                break;
        }
    }

    private void registerReceiver() {
        bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE_PROGRESS);
        bManager.registerReceiver(broadcastReceiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除注册时，使用注册时的manager解绑
        bManager.unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MESSAGE_PROGRESS)) {
                Download download = intent.getParcelableExtra("download");
                cb.setProgress(download.getProgress());
                if (download.getProgress() == 100) {
                    tv_pross.setText("File Download Complete");
                } else {
                    tv_pross.setText(
                            StringUtils.getDataSize(download.getCurrentFileSize())
                                    + "/" +
                                    StringUtils.getDataSize(download.getTotalFileSize()));

                }
            }
        }
    };
}
