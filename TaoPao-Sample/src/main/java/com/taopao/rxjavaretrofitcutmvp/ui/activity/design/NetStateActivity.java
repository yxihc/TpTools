package com.taopao.rxjavaretrofitcutmvp.ui.activity.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.jaeger.library.StatusBarUtil;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;
import com.taopao.rxjavaretrofitcutmvp.utils.NetUtils;

/**
 * @Author: 淘跑
 * @Data: 2018/2/6 13:46
 * @Use: 接受网络变化执行相关逻辑
 */

public class NetStateActivity extends BaseActivity {

    private ImageView mIv_neterror;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netstate);
        mIv_neterror = (ImageView) findViewById(R.id.iv_neterror);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.bule), 0);
        mIv_neterror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetUtils.openSetting(NetStateActivity.this);
            }
        });
    }
    @Override
    public BasePresenter createPresenter() {
        return null;
    }
    @Override
    public BaseView createView() {
        return null;
    }
    @Override
    public void onNetChanged(int netWorkState) {
        switch (netWorkState) {
            case  NetUtils.NETWORK_NONE:
                mIv_neterror.setVisibility(View.VISIBLE);
                break;
            case  NetUtils.NETWORK_MOBILE:
                mIv_neterror.setVisibility(View.GONE);
                break;
            case  NetUtils.NETWORK_WIFI:
                mIv_neterror.setVisibility(View.GONE);
                break;
        }
    }
}
