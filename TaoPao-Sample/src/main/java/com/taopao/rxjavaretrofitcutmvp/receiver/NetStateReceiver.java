package com.taopao.rxjavaretrofitcutmvp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import com.taopao.rxjavaretrofitcutmvp.interfaces.INetEvent;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.utils.NetUtils;

/**
 * @Author: 淘跑
 * @Data: 2018/1/5 16:05
 * @Use: 监听网络状态的广播
 */
public class NetStateReceiver extends BroadcastReceiver {

    private INetEvent mINetEvent= BaseActivity.mINetEvent;
    @Override
    public void onReceive(Context context, Intent intent) {
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            //容错机制
            if(mINetEvent!=null) {
                mINetEvent.onNetChange(NetUtils.getNetWorkState(context));
            }
        }
    }

}
