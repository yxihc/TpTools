package com.taopao.rxjavaretrofitcutmvp.interfaces;

/**
 * @Author: 淘跑
 * @Data: 2018/2/5 20:09
 * @Use: 网络变化的回调
 */

public interface INetEvent {
    void onNetChange(int netWorkState);
}
