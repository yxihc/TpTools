package com.taopao.rxjavaretrofitcutmvp.rx;

import com.taopao.rxjavaretrofitcutmvp.app.App;
import com.taopao.rxjavaretrofitcutmvp.model.base.BaseResult;
import com.taopao.rxjavaretrofitcutmvp.utils.NetUtils;
import com.taopao.rxjavaretrofitcutmvp.utils.UIUtils;
import io.reactivex.Observer;

/**
 * @Author: 淘跑
 * @Data: 2018/1/28 21:32
 * @Use:  自定义Observer
 */
public abstract class RxObserver<T> implements Observer<T> {
//    private Context mContext;
//    public RxObserver(Context context) {
//        mContext=context;
//    }
    @Override
    public void onComplete() {
        //一般不用 需要的时候重写
        UIUtils.hideWaitingDialog();
    }

    @Override
    public void onError(Throwable e) {
        UIUtils.hideWaitingDialog();
//        if(e instanceof HttpException){
//            String errNetwork = "网络错误";
//            LogUtils.i("onError: " + errNetwork);
//            UIUtils.showToast(errNetwork);
//        }
//        if (e instanceof ServerException) {
//            // 服务器异常
//            msg = e.getMessage();
//        } else if(e instanceof ReloginException){
//            // 踢出登录
//        }else if (throwable instanceof UnknownHostException) {
//            msg = "没有网络...";
//        } else if (throwable instanceof SocketTimeoutException) {
//            // 超时
//            msg = "超时...";
//        }else{
//            msg = "请求失败，请稍后重试...");
//        }


        if (!NetUtils.isNetworkAvailable(App.getContext())) {
            UIUtils.showToast("你连接的网络有问题，请检查路由器");
            return;
        }
        UIUtils.showToast("程序员哥哥偷懒去了，快去举报他");

        // TODO: 处理其它通用错误
        // 覆写此方法自行处理异常，通用异常使用super.onError(e)保留
        e.printStackTrace();
    }

    @Override
    public void onNext(T t) {
        if(t instanceof BaseResult){
            BaseResult response = (BaseResult) t;
            // 判断是否请求错误，出错直接转到onError()
            if(!response.getErrorCode().equals(BaseResult.onResultOk)){
                Throwable e = new Throwable(response.getErrorMsg());
                this.onError(e);
                UIUtils.showToast(response.getErrorMsg());
                return;
            }
        }
        onSuccess(t);
    }

    /**
     * 请求成功回调
     * @param t 最终响应结果
     */
    public abstract void onSuccess(T t);
}