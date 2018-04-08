package com.taopao.rxjavaretrofitcutmvp.ui.contract;

/**
 * @Author： 淘跑
 * @Date: 2018/4/4 14:26
 * @Use：
 * @
 * @-------------------修改记录-------------------@
 * @
 * @Modifier: 修改者
 * @Data: 修改时间
 * @Version: 修改次数 v1
 * @EditContent: 修改内容
 */

public interface IPresenterCallBack<T> {
    void onSuccess(T data);
    void onFailure(String msg);
}
