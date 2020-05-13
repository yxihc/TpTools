package com.taopao.tptools.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.taopao.tptools.databinding.ActivityJdRefreshBinding;

public class JDRefreshActivity extends AppCompatActivity {
    private com.taopao.tptools.databinding.ActivityJdRefreshBinding mInflate;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mInflate.smartMian.finishRefresh();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflate = ActivityJdRefreshBinding.inflate(LayoutInflater.from(this));
        setTitle("仿京东到家下拉刷新");
        setContentView(mInflate.getRoot());
        mInflate.smartMian.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mHandler.sendEmptyMessageDelayed(10, 1300);
            }
        });
    }
}
