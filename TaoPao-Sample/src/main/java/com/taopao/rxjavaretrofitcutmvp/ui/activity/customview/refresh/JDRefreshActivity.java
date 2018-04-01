package com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.refresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.widget.refresh.MyRefreshHeader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JDRefreshActivity extends AppCompatActivity {

    private Handler mHandler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mSmartMian.finishRefresh();
        }
    };

    @BindView(R.id.smart_mian)
    SmartRefreshLayout mSmartMian;
    private MyRefreshHeader mRf_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jdrefresh);
        ButterKnife.bind(this);
        mSmartMian.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mHandler.sendEmptyMessageDelayed(10,1300);
            }
        });
    }
}
